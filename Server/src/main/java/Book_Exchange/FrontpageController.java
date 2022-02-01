package Book_Exchange;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class FrontpageController {


    public FrontpageController(){

    }

    @GetMapping("/index")
    public String index(){
        return "Welcome!";
    }



}
