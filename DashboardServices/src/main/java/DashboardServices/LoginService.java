/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DashboardServices;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Arul
 */
@Path("LoginService")
public class LoginService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoginServiceResource
     */
    public LoginService() {
    }

    /**
     * Retrieves representation of an instance of DashboardServices.LoginService
     * @return an instance of java.lang.String
     */
    
    
     @POST
     @Produces(MediaType.TEXT_PLAIN)
	public Response getUsers(@Context UriInfo info) {
		String username = info.getQueryParameters().getFirst("username");
		String password = info.getQueryParameters().getFirst("password");
		 MongoClient mongoClient;
		String finalOutput="HELLO";
		try {
			 mongoClient = new MongoClient();
         // Now connect to your databases
         DB db = mongoClient.getDB( "SSKDatabase" );
         DBCollection coll = db.getCollection("UserCollection");
         BasicDBObject andQuery = new BasicDBObject();
         List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
         obj.add(new BasicDBObject("_id", username));
         obj.add(new BasicDBObject("password", password));
         andQuery.put("$and", obj);
         //System.out.println(andQuery.toString());
         DBCursor cursor = coll.find(andQuery);
                   
        while (cursor.hasNext()) {
             finalOutput+=cursor.next();
        }
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
    if(finalOutput.equals("HELLO"))
{
   
    return Response.status(200).entity("failure").build(); 
}
else
{
     return Response.status(200).entity("sucess").build();
}
		   
	}
}