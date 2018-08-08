
import java.util.*;
  
  class Vertex implements Comparable<Vertex>
  {
     public final String name;
      public Edge[] adjacencies;
      public double minDistance = Double.POSITIVE_INFINITY;
      public Vertex previous;
      public String reachTime;
      public Vertex(String argName) { name = argName; }
      
	
     @Override
	public String toString() {
		return "Vertex [name=" + name + ", adjacencies=" + Arrays.toString(adjacencies) + ", minDistance=" + minDistance
				+ ", previous=" + previous + "]";
	}


	public int compareTo(Vertex other)
      {
         return Double.compare(minDistance, other.minDistance);
     }
  
  }