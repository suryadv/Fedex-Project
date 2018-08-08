
import java.sql.PreparedStatement;
import java.util.List;

public class Output extends Thread{

	public Packet packet;
	
	public Output(Packet packet) {
		this.packet = packet;
	}
	
	public void run() {
		Dijkstra d = new Dijkstra();
		Vertex[] vertices=d.calldij();
		Database db = Utils.db;
		PreparedStatement pst = null;
		try {
			pst = db.connect.prepareStatement("insert into packet_track (id, src, dest, dimen, weight, created) values"
				+ "(?, ?, ?, ?, ?, ?)");
			pst.setString(1, packet.getId());
			pst.setString(2, packet.getSrc());
			pst.setString(3, packet.getDst());
			pst.setString(4, packet.getDimen());
			pst.setString(5, packet.getWeight());
			pst.setString(6, packet.getCreated());
			pst.executeUpdate();
			
			Vertex source = null;
			Vertex target = null;
			for(Vertex vertex : vertices) {
				if(vertex.name.equals(packet.getSrc())) {
					source = vertex;
				} else if(vertex.name.equals(packet.getDst())) {
					target = vertex;
				}
				if(target != null && source != null)
					break;
			}
			
			d.computePaths(source, packet.getId(), packet.getDst());
			
			d.getShortestPathTo(target);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
