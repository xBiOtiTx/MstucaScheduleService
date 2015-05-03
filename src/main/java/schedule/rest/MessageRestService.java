package schedule.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/message")
public class MessageRestService {
 
	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg) {
		String result = "321 Restful example : " + msg;
		return Response.status(200).entity(result).build();
	}
	
//	@GET
//	@Path("/{param}")
//	public String printMessage(@PathParam("param") String msg) {
//		String result = "123 Restful example : " + msg;
//		return result;
//	}
 
}
