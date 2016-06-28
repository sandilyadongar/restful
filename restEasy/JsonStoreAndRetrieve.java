package restEasy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import restEasy.Response;
import restEasy.UserDetails;
import restEasy.client.*;

@Path("/usingJson")
public class JsonStoreAndRetrieve {
	@POST
	@Path("/setJsonInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getResponseJSON(UserDetails user) {
		
		Response resp = new Response();
		resp.setResponseFname(user.getFirstname());
		resp.setResponseLname(user.getLastname());
		resp.setResponsePnum(user.getPhnumber());

		return javax.ws.rs.core.Response.status(201).entity(resp).build();

	}
	
	@POST
	@Path("/emp")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response readAndReturnJson(UserDetails user) {

		System.out.println(user.getFirstname());
		System.out.println(user.getLastname());
		System.out.println(user.getPhnumber());
		Response resp = new Response();
		resp.setResponseFname(user.getFirstname());
		resp.setResponseLname(user.getLastname());
		resp.setResponsePnum(user.getPhnumber());
		
		return javax.ws.rs.core.Response.status(201).entity(resp).build();
	}
} 