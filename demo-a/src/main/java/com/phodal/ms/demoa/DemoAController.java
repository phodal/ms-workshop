package com.phodal.ms.demoa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoAController {

    @RequestMapping("/name")
    public String name(){
        return "A";
    }
}
