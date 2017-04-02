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
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.io.Serializable;
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
@Path("FetchProject")
public class FetchProject extends BasicDBObject implements Serializable {
 private static final long serialVersionUID = 1234L;
    @Context
    private UriInfo context;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Creates a new instance of LoginServiceResource
     */
    public FetchProject() {
    }

    public UriInfo getContext() {
        return context;
    }

    public void setContext(UriInfo context) {
        this.context = context;
    }

    /**
     * Retrieves representation of an instance of DashboardServices.LoginServiceResource
     * @return an instance of java.lang.String
     */
    
    
     @POST
     @Produces(MediaType.TEXT_PLAIN)
	public Response getUsers(@Context UriInfo info) {
     String userId = info.getQueryParameters().getFirst("userId");
         String finalOutput="";
    
  try {
MongoClient mongoClient;
        
              try {
             mongoClient = new MongoClient();
         // Now connect to your databases
         DB db = mongoClient.getDB( "SSKDatabase" );
         //System.out.println("Connect to database successfully");
         DBCollection coll = db.getCollection("UserCollection");
        
          //DBCollection coll2 = db.getCollection("ProjectCollection");

          DBObject match = new BasicDBObject("$match", new BasicDBObject("_id", userId));
 
          DBObject unwind=new BasicDBObject("$unwind", new BasicDBObject("path","$projects"));
     
          DBObject lookup=new BasicDBObject("$lookup",new BasicDBObject("from","ProjectCollection")
                  .append("localField", "projects.projectIndexId")
                  .append("foreignField", "_id").append("as", "productObjects"));
         
          DBObject unwind2=new BasicDBObject("$unwind","$productObjects");

        DBObject group=new BasicDBObject("$group",new BasicDBObject("_id","$_id")
                .append("projects", new BasicDBObject("$push","$projects"))
                .append("productObjects", new BasicDBObject("$push","$productObjects")));
      DBObject group2=new BasicDBObject("$group",new BasicDBObject("_id","$productObjects.projectName"));
               
    
        


         //System.out.println("Collection mycol selected successfully");
         
        AggregationOutput output = coll.aggregate(match,unwind,lookup,unwind2,group,group2);

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
     * PUT method for updating or creating an instance of LoginServiceResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    
}