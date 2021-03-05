package edu.escuelaing.arep.httpServer;

import edu.escuelaing.arep.picospringboot.PicoSpringServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class thats represents a HttpServer.
 */
public class HttpServer {

    private int port;
    private boolean isRunning = true;
    private String staticPath = "src/main/resources/static";

    /**
     * Start.
     *
     * @param port the port of server
     * @throws IOException the io exception
     */
    public void start( int port ) throws IOException {
        this.port = port;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + this.port);
            System.exit(1);
        }
        while (this.isRunning){
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintStream  out = new PrintStream (clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()) );
            HttpRequest request = createHttpRequest(in);
            if (request.getPath() != null){
                processRequest(request, out);
            }
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();

    }

    private HttpRequest createHttpRequest(BufferedReader in ) throws IOException {
        String inputLine;
        Boolean isFirstLine = true;
        HttpRequest httpRequest = new HttpRequest();
        while ((inputLine = in.readLine()) != null) {
            if (!in.ready()) break;
            if (isFirstLine){
                httpRequest.setType( Type.valueOf( inputLine.split(" ")[0] ) );
                httpRequest.setPath( inputLine.split(" ")[1] );
                httpRequest.setVersion( inputLine.split(" ")[2] );
                isFirstLine = false;
            }else{
                httpRequest.addHeader(inputLine.split(": ")[0], inputLine.split(": ")[1]);
            }
        }
        return httpRequest;
    }

    private void processRequest( HttpRequest request, PrintStream  out ) throws IOException {
        HttpResponse response = new HttpResponse();
        if (request.getPath().startsWith("/controller/")){
            String body = PicoSpringServer.getInstance().invoke(request.getPath().substring(11));
            if (body.equals("Not found")) {
                response = HttpResponse.NOT_FOUND;
            }else{
                response = HttpResponse.OK;
                response.setBody(body);
            }
        }else{
            if (request.getPath().equals("/")){
                request.setPath("/index.html");
            }
            try {
                String fileString = getStaticResource(request.getPath());
                response =  response = HttpResponse.OK;
                response.setBody(fileString);

            } catch (FileNotFoundException e) {
                response = HttpResponse.NOT_FOUND;
            }
        }
        out.println(response.getVersion() + " " + response.getStatus() + " Content-type: " + response.getContentType() + "\r\n\r\n");
        out.print(response.getBody());
    }

    private String getStaticResource (String path) throws IOException {
        File file = new File(staticPath + path);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        StringBuffer sb = new StringBuffer();
        String line;
        while((line = br.readLine())!=null){
            sb.append(line);
            sb.append("\n");
        }
        reader.close();
        return sb.toString();
    }


}
