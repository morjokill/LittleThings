package mqtt.example.controller;

import mqtt.example.container.DataContainer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
@Produces("application/json; charset=utf-8")
public class SimpleController {
    @GET
    @Path("/hi")
    public String hi() {
        return "Русские вперед!";
    }

    @GET
    @Path("/data.json")
    public Response query() {
        System.out.println(DataContainer.getData());
        return Response.status(200).entity(DataContainer.getData().getPixels()).build();
    }
}
