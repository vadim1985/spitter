package service;

import dao.SpitterDao;
import entity.Spitter;
import entity.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@EnableTransactionManagement
@Transactional(propagation=Propagation.REQUIRED)
public class SpitterServiceImpl implements SpitterService {

    @Autowired
    private SpitterDao spitterDao;

    @Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
    public List<Spittle> getRecentSpittles(int count) {
        List<Spittle> recentSpittles = spitterDao.getRecentSpittle();
        Collections.reverse(recentSpittles);
        return recentSpittles.subList(0, Math.min(count, recentSpittles.size()));
    }

    public void saveSpittle(String message, Spitter spitter) {
        spitterDao.saveSpittle(message, spitter);
    }

    public void saveSpitter(Spitter spitter) {
        spitterDao.saveSpitter(spitter);
    }

    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public Spitter getSpitter(long id) {
        return spitterDao.getSpitterById(id);
    }

    public void startFollowing(Spitter follower, Spitter followee) {

    }

    public List<Spittle> getSpittlesForSpitter(Spitter spitter) {
        return spitterDao.getSpittlesForSpitter(spitter);
    }

    public List<Spittle> getSpittlesForSpitter(String username) {
        Spitter spitter = spitterDao.getSpitterByUsername(username);
        return spitterDao.getSpittlesForSpitter(spitter);
    }

    public Spitter getSpitter(String username) {
        try {
            Spitter spitter = spitterDao.getSpitterByUsername(username);
            return spitter;
        }catch (Exception e){
            return null;
        }
    }

    public Spittle getSpittleById(long id) {
        return spitterDao.getSpittleById(id);
    }

    public void deleteSpittle(long id) {
        spitterDao.deleteSpittle(id);
    }

    public List<Spitter> getAllSpitters() {
        return spitterDao.findAllSpitters();
    }
}
