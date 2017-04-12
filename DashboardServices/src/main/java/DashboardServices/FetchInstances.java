/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DashboardServices;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
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
 * @author Akkineni
 */
@Path("fetchinstances")
public class FetchInstances {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Fetchrole
     */
    public FetchInstances() {
    }

    /**
     * Retrieves representation of an instance of DashboardServices.Fetchrole
     * @return an instance of java.lang.String
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    
     @GET
     @Produces(MediaType.TEXT_PLAIN)
	public Response getRole(@Context UriInfo info) {
         String projectId = info.getQueryParameters().getFirst("projectId");
         String entityName = info.getQueryParameters().getFirst("entityName");
         System.out.println("projectId"+projectId);
         String finalOutput="";
         try {
         MongoClient mongoClient;
         try {
          mongoClient = new MongoClient();
           // Now connect to your databases
          DB db = mongoClient.getDB( "SSKDatabase" );
          //System.out.println("Connect to database successfully");
          DBCollection coll = db.getCollection("insa");
          
          DBObject match = new BasicDBObject("$match", new BasicDBObject("_id", projectId));
          DBObject unwind=new BasicDBObject("$unwind","$"+entityName);
          DBObject project=new BasicDBObject("$project",new BasicDBObject("instances","$"+entityName));
          
          AggregationOutput output = coll.aggregate(match,unwind,project);
          if(output!=null)
          {
              System.out.println("Ajay worked");
          }
          else
          {
              System.out.println("ajay didnt not woek");
          }
             System.out.println("before if condition");
          for (DBObject result : output.results()) {
              System.out.println("hai i reached here");
          System.out.println(result);
          finalOutput+=result;
          }
                }
                catch(Exception e)
                {
                    System.out.println("error");
                }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
                }		
        return Response.status(200).entity(finalOutput).build(); 
        }
    /**
     * PUT method for updating or creating an instance of Fetchrole
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}