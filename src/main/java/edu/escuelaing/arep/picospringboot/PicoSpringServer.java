package edu.escuelaing.arep.picospringboot;

import edu.escuelaing.arep.httpServer.HttpServer;
import edu.escuelaing.arep.picospringboot.annotations.RequestMapping;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Class thats represents a IoCServer for a small implementation of Spring Boot framework.
 */
public class PicoSpringServer {

    private HttpServer httpServer;
    private int port = 8080;
    private Map<String, Method> beans = new HashMap<>();
    private static PicoSpringServer instance = new PicoSpringServer();

    private PicoSpringServer(){
        httpServer = new HttpServer();
    }

    /**
     * Start.
     *
     * @param classRoutes the class routes
     * @throws ClassNotFoundException the class not found exception
     * @throws IOException            the io exception
     */
    public void start(String[] classRoutes) throws ClassNotFoundException, IOException {

        for (String classRoute: classRoutes){
            for (Method method : Class.forName(classRoute).getMethods()) {
                if (method.isAnnotationPresent(RequestMapping.class))
                    beans.put(method.getAnnotation(RequestMapping.class).value(), method);
            }
        }

        httpServer.start(this.port);
    }

    /**
     * Invoke method asociated to a specific path.
     *
     * @param path the path
     * @return the string
     */
    public String invoke(String path)  {
        if (beans.containsKey( path )){
            try {
                return beans.get(path).invoke(null).toString();
            } catch (IllegalAccessException | InvocationTargetException | NullPointerException e) {
            }
        } return "Not found";
    }

    /**
     * Gets port of server.
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets port to server.
     *
     * @param port the port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Get instance pico spring server.
     *
     * @return the pico spring server
     */
    public static PicoSpringServer getInstance(){
        return instance;
    }

}
