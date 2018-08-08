
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

class Dijkstra
  {    
	
	private Vertex source;
	private Vertex target;
	private String id;
  
  
      public void computePaths(Vertex source, String id, String target)
      {
          source.minDistance = 0.;
          this.id = id;
          PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
          vertexQueue.add(source);
          try {
  			while (!vertexQueue.isEmpty()) {
  				
  				Vertex u = vertexQueue.poll();
  				if(target.equals(u.name)) {
  					break;
  				}
  
  				for (Edge e : u.adjacencies)
  				{
  					Vertex v = e.target;
  					double weight = e.weight;
  					double distanceThroughU = u.minDistance + weight;
                  	if (distanceThroughU < v.minDistance) {
                  		vertexQueue.remove(v);
                  		v.minDistance = distanceThroughU;
                  		v.previous = u;
                  		vertexQueue.add(v);
                  	}
  				}
  			}
          } catch(Exception e) {
        	  e.printStackTrace();
          }
      }
  
      public void getShortestPathTo(Vertex target) throws Exception
      {
          List<Vertex> path = new ArrayList<Vertex>();
          for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
              path.add(vertex);
     
          Collections.reverse(path);

          Database db = Utils.db;
          PreparedStatement pst = null;
          for(Vertex vertex: path) {
				vertex.reachTime = new Timestamp(new Date().getTime()).toString();
        	  	pst = db.connect.prepareStatement("insert into track_info (id, recieved, reached) values (?, ?, ?)");
      			pst.setString(1, id);
      			pst.setString(2, vertex.reachTime);
      			pst.setString(3, vertex.name);
      			pst.executeUpdate();
  				Thread.sleep(5000);
          }
      }
      
      public Vertex[] calldij()
      
      {  
          Vertex v0 = new Vertex("Northborough, MA");
          Vertex v1 = new Vertex("Edison, NJ");
          Vertex v2 = new Vertex("Pittsburgh, PA");
          Vertex v3 = new Vertex("Allentown, PABD Strap Endliksth");
          Vertex v4 = new Vertex("Martinsburg, WV");
          Vertex v5 = new Vertex("Charlotte, NC");
          Vertex v6 = new Vertex("Atlanta, GA");
          Vertex v7 = new Vertex("Orlando, FL");
          Vertex v8 = new Vertex("Memphis, TN");
          Vertex v9 = new Vertex("Grove City, OH");
          Vertex v10 = new Vertex("Indianapolis, IN");
          Vertex v11 = new Vertex("Detroit, MI");
          Vertex v12 = new Vertex("New Berlin, WI");
  		  Vertex v13 = new Vertex("Minneapolis, MN");
  		  Vertex v14 = new Vertex("St.Louis, MO");
  		  Vertex v15 = new Vertex("Kansas, KS");
  		  Vertex v16 = new Vertex("Dallas, TX");
  		  Vertex v17 = new Vertex("Houston, TX");
  		  Vertex v18 = new Vertex("Denver, CO");
  		  Vertex v19 = new Vertex("Salt Lake City, UT");
  		  Vertex v20 = new Vertex("Phoenix, AZ");
  		  Vertex v21 = new Vertex("Los Angeles, CA");
  		  Vertex v22 = new Vertex("Chino, CA");
  		  Vertex v23 = new Vertex("Sacramento, CA");
  		  Vertex v24 = new Vertex("Seattle, WA");
  	
  		  v0.adjacencies = new Edge[]{ new Edge(v11,  1),
  	                             new Edge(v2,  1)};
  		  v1.adjacencies = new Edge[]{ new Edge(v0,  1),
  	                             new Edge(v2,  1),
  	                              new Edge(v7,  1)};
  		  v2.adjacencies = new Edge[]{ new Edge(v3,  1), 
  			                     new Edge(v4,  1),
  			                     new Edge(v1,  1)};
  		  v3.adjacencies = new Edge[]{ new Edge(v2,  1),
	                             new Edge(v9,  1),
	                             new Edge(v11,  1)};
  		  v4.adjacencies = new Edge[]{ new Edge(v8, 1),
 			                     new Edge(v2,  1),
 			                    new Edge(v5,  1)};
  		  v5.adjacencies = new Edge[]{ new Edge(v4,  1),
 	                             new Edge(v6,  1),
 	                            new Edge(v10,  1)};
  		  v6.adjacencies = new Edge[]{ new Edge(v7,  1),
 	                             new Edge(v5,  1),
 	                            new Edge(v8, 1)};
  		  v7.adjacencies = new Edge[]{ new Edge(v1,  1),
                                 new Edge(v6,  1),
                                 new Edge(v17,  1)};
  		  v8.adjacencies = new Edge[]{ new Edge(v16,  1),
  				  				 new Edge(v6, 1),
  				  				 new Edge(v4,  1)};
  		  v9.adjacencies = new Edge[]{ new Edge(v3,  1),
  				  		 		   new Edge(v10,  1)};
  		  v10.adjacencies = new Edge[]{new Edge(v5,  1),
            new Edge(v14,  1),
           new Edge(v9, 1)};
 	v11.adjacencies = new Edge[]{ new Edge(v0, 1),
             new Edge(v3,  1),
             new Edge(v12,  1)};
 	v12.adjacencies = new Edge[]{  new Edge(v13, 1 ),
             new Edge(v11,  1) };
 	v13.adjacencies = new Edge[]{ new Edge(v12,  1),
             new Edge(v19,  1),
             new Edge(v14, 1)};
 	v14.adjacencies = new Edge[]{new Edge(v0, 1),
             new Edge(v3,  1),
            new Edge(v12,  1)};
 	v15.adjacencies = new Edge[]{ new Edge(v14,  1),
                    new Edge(v18,  1)};
 	v16.adjacencies = new Edge[]{ 
             new Edge(v18,  1),
             new Edge(v17, 1),
            new Edge(v8,  1) };
 	v17.adjacencies = new Edge[]{ new Edge(v16, 1),
 			new Edge(v7,  1)};
 	v18.adjacencies = new Edge[]{ new Edge(v19,  1),
             new Edge(v20,  1),
             new Edge(v16, 1),
             new Edge(v15,  1)};
 	v19.adjacencies = new Edge[]{ new Edge(v18,  1),
             new Edge(v24,  1),
             new Edge(v23, 1),
             new Edge(v13,  1)};
 	v20.adjacencies = new Edge[]{ new Edge(v18,  1),
                   new Edge(v22,  1)};
 	v21.adjacencies = new Edge[]{ new Edge(v22,  1),
             new Edge(v23,  1) };
 	v22.adjacencies = new Edge[]{ new Edge(v21,  1),
             new Edge(v20,  1),
             new Edge(v19, 1),
             new Edge(v23,  1) };
 	v23.adjacencies = new Edge[]{  new Edge(v19,  1),
             new Edge(v21, 1),
             new Edge(v24,  1)};
 	v24.adjacencies = new Edge[]{ new Edge(v23,  1),
             new Edge(v19,  1)};
 	
 		Vertex[] vertices = { v0, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23, v24 };
    
         return vertices;
 
 
     }
      
      public Vertex getSource(int i)
      {
    	  return source;
      }
      public Vertex getTarget(int i)
      {
    	  return target;
      }
      
      
      
   
      
 }  