package controller;

import entity.Spitter;
import entity.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.SpitterService;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    public static final int DEFAULT_SPITTLES_PER_PAGE = 8;

    @Autowired
    private SpitterService spitterService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/")
    public String showHomePage(Model model){
        model.addAttribute("spittles", spitterService.getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE));
        return "home";
    }

    @GetMapping("/spitter/{id}")
    public String getSpitterInfo(@PathVariable("id") int id, Model model){
        model.addAttribute("spitterInfo", spitterService.getSpitter(id));
        Spitter spitter = spitterService.getSpitter(id);
        List<Spittle> spittleList = spitterService.getSpittlesForSpitter(spitter);
        model.addAttribute("allSpittleOfSpitter", spittleList);
        return "spitterInfo";
    }

    @PostMapping("/newMessage")
    public String getMessage(@RequestParam(value = "user") String loginName,
                             @RequestParam(value = "message") String message){
        System.out.println("-----------------------------");
        System.out.println(loginName + " / " + message);
        System.out.println("-----------------------------");
        Spitter spitter = spitterService.getSpitter(loginName);
        spitterService.saveSpittle(message, spitter);
        return "redirect:/";
    }

    @GetMapping("/newSpitter")
    public String showNewSpitterPage(){
        return "newSpitter";
    }

    //just test->
    @PostMapping("/newSpitter")
    public String getNewSpitter(@RequestParam(value = "id", defaultValue = "1") int id,
                                @RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String pass,
                                @RequestParam(value = "fullName") String fname,
                                Model model){
        Spitter spitter = null;
        spitter = spitterService.getSpitter(username);
        if (spitter == null){
            spitter = new Spitter(username, pass, fname);
            spitterService.saveSpitter(spitter);
            return "newSpitter";
        }else{
            String builder = new StringBuilder(username)
                            .append(fname)
                            .toString()
                            .replaceAll(" ", "");
            model.addAttribute("mistake" , username + " is already taken. Please use different one." +
                    "Ore pick our suggestion: " + builder);
            return "error";
        }

    }

}
