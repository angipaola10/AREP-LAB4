package edu.escuelaing.arep.httpServer;

/**
 * TClass thats represents a HttpResponse.
 */
public class HttpResponse {

    private String contentType;
    private String status;
    private String body;
    private String version = "HTTP/1.0";

    /**
     * The constant NOT_FOUND response.
     */
    public static final HttpResponse NOT_FOUND = new HttpResponse("HTTP/1.0", "404 Not Found", "text/html", "404 not found");

    /**
     * The constant OK response.
     */
    public static final HttpResponse OK = new HttpResponse("HTTP/1.0", "200 OK", "text/html", "");

    /**
     * Instantiates a new Http response.
     */
    public HttpResponse(){
        this.contentType = "text/plain";
        this.status = "";
        this.body = "";
    }

    /**
     * Instantiates a new Http response.
     *
     * @param version     the version
     * @param status      the status of response
     * @param contentType the content type of body
     * @param body        the body of response
     */
    public HttpResponse(String version, String status, String contentType, String body){
        this.version = version;
        this.status = status;
        this.contentType = contentType;
        this.body = body;
    }

    /**
     * Get content type of body.
     *
     * @return the string
     */
    public String getContentType(){
        return this.contentType;
    }

    /**
     * Set status.
     *
     * @param status the status of response
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * Get status of response.
     *
     * @return the string status of response
     */
    public String getStatus(){
        return this.status;
    }

    /**
     * Set content type of body.
     *
     * @param filename the filename searched
     */
    public void setContentType(String filename){
        if (filename.endsWith(".html") || filename.endsWith(".htm")){
            this.contentType =  "text/html";
        }
        else if (filename.endsWith(".PNG") ||  filename.endsWith(".png")){
            this.contentType = "image/png";
        }
    }

    /**
     * Gets body of response.
     *
     * @return the body of response
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets body of response.
     *
     * @param body the body of response
     */
    public void setBody(String body) {
        this.body = body;
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
}
