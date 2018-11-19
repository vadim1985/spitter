package controller;
import entity.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.SpitterService;

import java.util.List;

@RestController
public class RestfullController {
    @Autowired
    private SpitterService spitterService;

//    It works, amazing
    @RequestMapping(value = "/spitters/", method = RequestMethod.GET)
    public ResponseEntity<List<Spitter>> getSpitterList(){
        List<Spitter> allSpitters = spitterService.getAllSpitters();
        return new ResponseEntity<List<Spitter>>(allSpitters, HttpStatus.OK);
    }

}
