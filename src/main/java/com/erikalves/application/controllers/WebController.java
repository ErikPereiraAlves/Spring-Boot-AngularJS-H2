package com.erikalves.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class WebController {

    @Controller
    public class MainController {

        @RequestMapping(value="/",method = RequestMethod.GET)
        public String homepage(){
            return "index";
        }
    }


}
