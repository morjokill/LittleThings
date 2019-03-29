package mqtt.example.controller;

import freemarker.template.Template;
import mqtt.example.app.TemplateConfigurationContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

@Path("/train")
@Produces("text/html; charset=utf-8")
public class TrainController {
    private static final String TEMPLATE_NAME = "train.html";

    @GET
    @Path("/")
    public Response draw() {
        try {
            Template temp = TemplateConfigurationContext.getConfiguration().getTemplate(TEMPLATE_NAME);
            Writer writer = new StringWriter();
            temp.process(new HashMap(), writer);
            return Response.status(Response.Status.ACCEPTED).entity((writer.toString())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(("Русские назад")).build();
        }
    }
}
