package edu.escuelaing.arep.controller;

import edu.escuelaing.arep.picospringboot.annotations.RequestMapping;

/**
 * Example of controller class
 */
public class AppController {

    /**
     * Method of example - using of RequestMapping.
     *
     * @return the string defined
     */
    @RequestMapping("/hello")
    public static String hello(){
        return "Greetings from Pico Spring Boot";
    }

}
