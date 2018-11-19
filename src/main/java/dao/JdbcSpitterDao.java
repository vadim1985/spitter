package dao;

import entity.Spitter;
import entity.Spittle;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JdbcSpitterDao implements SpitterDao {

    private static final Logger LOGGER = Logger.getLogger(JdbcSpitterDao.class);

    private static final String SQL_INSERT_SPITTER =
            "INSERT INTO spitter (username, password, fullname) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_SPITTER =
            "UPDATE spitter SET username = ?, password = ?, fullname = ? WHERE id = ?";
    private static final String SQL_SELECT_SPITTER =
            "SELECT id, username, password, fullname FROM spitter";
    private static final String SQL_SELECT_SPITTER_BY_ID =
            SQL_SELECT_SPITTER + " WHERE id = ?";
    private static final String SQL_SELECT_SPITTER_BY_NAME =
            SQL_SELECT_SPITTER + " WHERE username = ?";
    private static final String SQL_SELECT_SPITTLE =
            "SELECT id, spitter_id, spittleText, postedTime FROM spittle";
    private static final String SQL_SELECT_SPITTLE_BY_ID =
            SQL_SELECT_SPITTLE + " WHERE id = ?";
    private static final String SQL_SELECT_RECENT_SPITTLE =
            SQL_SELECT_SPITTLE + " WHERE postedTime > ? ORDER BY postedTime DESC";
    private static final String SQL_SELECT_SPITTLES_FOR_SPITTER =
            SQL_SELECT_SPITTLE + " WHERE spitter_id = ? ORDER BY postedTime desc";
    private static final String SQL_INSERT_SPITTLE =
            "INSERT INTO spittle (spitter_id, spittleText, postedTime) VALUES (?, ?, ?)";
    private static final String SQL_DELETE_FROM_SPITTLES =
            "DELETE FROM spittle WHERE id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcSpitterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveSpitter(Spitter spitter) {
        LOGGER.info("Try save the spitter: " + spitter);
        jdbcTemplate.update(SQL_INSERT_SPITTER, spitter.getUsername(), spitter.getPassword(), spitter.getFullName());
    }

    public void updateSpitter(Spitter spitter) {
        jdbcTemplate.update(SQL_UPDATE_SPITTER, spitter.getUsername(), spitter.getPassword(), spitter.getFullName(), spitter.getId());
    }

    public Spitter getSpitterById(long id) {
        LOGGER.info("Get spitter by id = " + id);
        return jdbcTemplate.queryForObject(SQL_SELECT_SPITTER_BY_ID, new RowMapper<Spitter>() {
            public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
                return getNewSpitter(rs);
            }
        }, id);
    }

    public List<Spittle> getRecentSpittle() {
        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateTime dt = new DateTime().minusDays(1);
        LOGGER.info("Get recent spittle (ALL) where date > " + formatDate.format(dt.toDate()));
        return jdbcTemplate.query(SQL_SELECT_RECENT_SPITTLE, new RowMapper<Spittle>() {
            public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
                return getNewSpittle(rs);
            }
        }, formatDate.format(dt.toDate()));
    }

    public void saveSpittle(String message, Spitter spitter) {
        Date date = new Date();
        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(spitter.getId() + "/" + message + " / " + formatDate.format(date));
        jdbcTemplate.update(SQL_INSERT_SPITTLE, spitter.getId(), message, formatDate.format(date));
    }

    public List<Spittle> getSpittlesForSpitter(Spitter spitter) {
        return jdbcTemplate.query(SQL_SELECT_SPITTLES_FOR_SPITTER, new RowMapper<Spittle>() {
            public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
                return getNewSpittle(rs);
            }
        }, spitter.getId());
    }

    public Spitter getSpitterByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_SELECT_SPITTER_BY_NAME, new RowMapper<Spitter>() {
            public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
                return getNewSpitter(rs);
            }
        }, username);
    }

    public void deleteSpittle(long id) {
        jdbcTemplate.update(SQL_DELETE_FROM_SPITTLES, id);
    }

    public Spittle getSpittleById(long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_SPITTLE_BY_ID, new RowMapper<Spittle>() {
            public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
                return getNewSpittle(rs);
            }
        }, id);
    }

    public List<Spitter> findAllSpitters() {
        return jdbcTemplate.query(SQL_SELECT_SPITTER, new RowMapper<Spitter>() {
            public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
                return getNewSpitter(rs);
            }
        });
    }

    private Spitter getNewSpitter(ResultSet rs){
        Spitter spitter = new Spitter();
        try {
            spitter.setId(rs.getInt("id"));
            spitter.setUsername(rs.getString("username"));
            spitter.setPassword(rs.getString("password"));
            spitter.setFullName(rs.getString("fullname"));
            return spitter;
        } catch (SQLException e) {
            System.out.println("---------ERROR-----------");
            System.out.println(e.getMessage());
            System.out.println("-------------------------");
            return null;
        }
    }

    private Spittle getNewSpittle(ResultSet rs){
        Spittle spittle = new Spittle();
        try {
            spittle.setId(rs.getInt("id"));
            spittle.setSpitter(getSpitterById(rs.getLong("spitter_id")));
            spittle.setText(rs.getString("spittleText"));
            spittle.setWhen(rs.getDate("postedTime"));
            return spittle;
        }catch (SQLException e) {
            System.out.println("---------ERROR-----------");
            System.out.println(e.getMessage());
            System.out.println("-------------------------");
            return null;
        }
    }
}
