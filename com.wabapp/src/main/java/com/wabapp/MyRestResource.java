package com.wabapp;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
 
@Path( "/get-data" )
public class MyRestResource {
 
    @GET
    public Response getDataToDisplay() {
        return Response.ok().entity( "Yes, it works." ).build();
    }
}