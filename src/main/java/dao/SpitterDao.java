package dao;

import entity.Spitter;
import entity.Spittle;

import java.util.List;

public interface SpitterDao {
    void saveSpitter(Spitter spitter);

    void updateSpitter(Spitter spitter);

    Spitter getSpitterById(long id);

    List<Spittle> getRecentSpittle();

    void saveSpittle(String message, Spitter spitter);

    List<Spittle> getSpittlesForSpitter(Spitter spitter);

    Spitter getSpitterByUsername(String username);

    void deleteSpittle(long id);

    Spittle getSpittleById(long id);

    List<Spitter> findAllSpitters();
}
