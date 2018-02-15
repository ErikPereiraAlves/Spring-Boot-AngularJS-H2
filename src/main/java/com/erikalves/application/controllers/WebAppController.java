package com.erikalves.application.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebAppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppController.class);

    @RequestMapping("/store/")
    String home(ModelMap modal) {
        modal.addAttribute("title","CRUD Example");
        LOGGER.debug("***************************  calling index.....");
        return "index";
    }

    @RequestMapping("/store/{page}")
    String partialHandler(@PathVariable("page") final String page) {

        LOGGER.debug("***************************  calling ....."+page);
        return page;
    }

}

