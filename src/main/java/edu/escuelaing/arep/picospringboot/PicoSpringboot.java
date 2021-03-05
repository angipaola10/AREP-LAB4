package edu.escuelaing.arep.picospringboot;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Class thats represents the entry of a small implementation of Spring Boot framework.
 */
public class PicoSpringboot {

    private Map<String, Method> beans = new HashMap<>();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ClassNotFoundException the class not found exception
     */
    public  static void  main(String[] args) throws ClassNotFoundException {
        args = new String[]{"edu.escuelaing.arep.controller.AppController"};
        try{
            PicoSpringServer iocServer = PicoSpringServer.getInstance();
            iocServer.start(args);
        }catch (Exception ex){
            ex.printStackTrace();;
        }
    }


}
