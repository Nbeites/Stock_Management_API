package pt.upacademy.JAXRS;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;



@Path("/hello")
public class HelloWorld {


@Context
private UriInfo context;


@GET
@Path("healthCheck")
@Produces(MediaType.TEXT_PLAIN) // text/plain"
public String healthCheck() {
	
	return "URI" + context.getRequestUri().toString() + " is OK!"; 
	
}

}
