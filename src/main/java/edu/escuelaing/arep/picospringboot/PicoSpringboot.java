package edu.escuelaing.arep.picospringboot;

/**
 * Class thats represents the entry of a small implementation of Spring Boot framework.
 */
public class PicoSpringboot {

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
            iocServer.setPort(getPort());
            iocServer.start(args);
        }catch (Exception ex){
            ex.printStackTrace();;
        }
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }


}
