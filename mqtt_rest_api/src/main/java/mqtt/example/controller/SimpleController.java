package mqtt.example.controller;

import mqtt.example.container.DataContainer;
import mqtt.example.container.RawDataContainer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Path("/")
@Produces("application/json; charset=utf-8")
public class SimpleController {
    @GET
    @Path("/")
    public String hi() {
        return "Русские вперед!";
    }

    @GET
    @Path("/data.json")
    public Response getData() {
        System.out.println(DataContainer.getData());
        return Response.status(200).entity(DataContainer.getData().getPixels()).build();
    }

    @GET
    @Path("/raw")
    public Response getRawData() {
        byte[] rawData = RawDataContainer.getData();
        String rawLine = Arrays.toString(rawData);
        System.out.println(rawLine);
        return Response.status(200).entity(rawLine).build();
    }
}
