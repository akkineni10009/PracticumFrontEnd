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
 * @author Arul
 */
@Path("fetchrole")
public class Fetchrole {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Fetchrole
     */
    public Fetchrole() {
    }

    /**
     * Retrieves representation of an instance of DashboardServices.Fetchrole
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    
     @POST
     @Produces(MediaType.TEXT_PLAIN)
	public Response getRole(@Context UriInfo info) {
         String userId = info.getQueryParameters().getFirst("userId");
         String projectname = info.getQueryParameters().getFirst("projectname");
         String finalOutput="";
         try {
         MongoClient mongoClient;
         try {
          mongoClient = new MongoClient();
           // Now connect to your databases
          DB db = mongoClient.getDB( "SSKDatabase" );
          //System.out.println("Connect to database successfully");
          DBCollection coll = db.getCollection("ProjectCollection");
          DBObject match = new BasicDBObject("$match", new BasicDBObject("projectName", projectname));
          DBObject lookup=new BasicDBObject("$lookup",new BasicDBObject("from","UserCollection")
                  .append("localField", "_id")
                  .append("foreignField", "projects.projectIndexId").append("as", "productObjects"));
          DBObject unwind=new BasicDBObject("$unwind","$productObjects");
          DBObject match2 = new BasicDBObject("$match", new BasicDBObject("productObjects._id", userId));
          DBObject unwind2=new BasicDBObject("$unwind","$productObjects.projects");
          DBObject match3 = new BasicDBObject("$match", new BasicDBObject("productObjects.projects.projectName",projectname));   
          DBObject project=new BasicDBObject("$project",new BasicDBObject("productObjects.projects.projectRole",1)
                .append("_id",0));
          DBObject group=new BasicDBObject("$group",new BasicDBObject("_id","$productObjects.projects.projectRole"));
          AggregationOutput output = coll.aggregate(match,lookup,unwind,match2,unwind2,match3,project,group);
          for (DBObject result : output.results()) {
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
