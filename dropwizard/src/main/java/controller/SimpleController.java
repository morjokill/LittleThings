package controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/")
public class SimpleController {
    @GET
    @Path("/hi")
    public String hello() {
        return "Hi!";
    }

    @GET
    @Path("/msg")
    public String query(@QueryParam("message") String message) {
        return "You passed " + message;
    }

    @POST
    @Path("/post")
    public String postParam(String message) {
        return "You posted " + message;
    }
}
