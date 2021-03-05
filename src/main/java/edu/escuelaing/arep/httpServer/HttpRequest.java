package edu.escuelaing.arep.httpServer;

import java.util.HashMap;
import java.util.Map;

/**
 * Class thats represents a HttpRequest.
 */
public class HttpRequest {

    private String path;
    private String body;
    private Map<String, String> headers = new HashMap<>();
    private Type type;
    private String version;

    /**
     * Instantiates a new Http request.
     */
    public HttpRequest(){}

    /**
     * Instantiates a new Http request.
     *
     * @param path    the path of resource
     * @param type    the type of resource
     * @param version the version
     */
    public HttpRequest(String path, Type type, String version){
        this.path = path;
        this.type = type;
        this.version = version;
    }

    /**
     * Gets path of request.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path of request.
     *
     * @param path the path of resorce
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets body of request.
     *
     * @return the body of request
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets body of request.
     *
     * @param body the body of request
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets headers of request
     *
     * @return the headers  of request
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets headers  of request.
     *
     * @param headers the headers  of request
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets type of resource.
     *
     * @param type the type or resource
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Add header to request..
     *
     * @param name  the name of header
     * @param value the value of header
     */
    public void addHeader ( String name, String value){
        headers.put(name, value);
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    public String toString (){
        String headers = "";
        for (String name: getHeaders().keySet()){
            headers += name + ": " + getHeaders().get( name ) + "\n";
        }
        return type + " " + path + " " + version + "\n" +
                headers;
    }
}
