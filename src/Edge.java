
public class Edge {
	@Override
	public String toString() {
		return "Edge [target=" + target.name + ", weight=" + weight + "]";
	}
	public final Vertex target;
	    public final double weight;
	     public Edge(Vertex argTarget, double argWeight)
	     { target = argTarget; weight = argWeight; }

	     
	     
}



