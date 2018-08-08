import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class ViewCreator
{
	private JFrame frame;
	private Container container1, container2, container3;
	private JTextField trackID, weight, dimensions;
	private Font textBoxFont, labelFont;
	private JComboBox<String> source, destination;
	
	private void mainView() {
		frame.setTitle("FedEx Tracking");
		container1 = new Container();
		frame.setContentPane(container1);
		
		JButton button = new JButton("Create Shipment");
		button.setBackground(Color.BLUE);
		button.setForeground(Color.MAGENTA);
		button.setBounds(500, 300, 150, 35);
		container1.add(button);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createForm();
			}
		});
		
		button = new JButton("Track Shipment");
		button.setBackground(Color.BLUE);
		button.setForeground(Color.MAGENTA);
		button.setBounds(500, 400, 150, 35);
		container1.add(button);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				trackPack();
			}
		});
		
		render();
	}
	
	public ViewCreator() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	}
	
	public void start() {
		mainView();
	}
	
	private void render() {
		frame.setSize( 1280, 1024 );     
	    frame.setVisible( true );
	}
	
	private void createForm() {
		frame.setTitle("Shipment Form");
		container2 = new Container();
		frame.setContentPane(container2);
		
		JLabel label = null;
		textBoxFont = new Font("TextBox Label", Font.PLAIN, 14);
		labelFont = new Font("Label Font", Font.BOLD, 15);
	
		label = new JLabel("Tracking ID");
		label.setFont(labelFont);
		label.setBounds(116, 31, 310, 16);
		trackID = getTextField(220, 28, 250, 25, 10);
		trackID.setEnabled(false);
		Random ran = new Random();
		trackID.setText((10000000 + ran.nextInt(90000000)) + "");
		trackID.setFont(new Font("TEMP", Font.BOLD, 15));
		trackID.setForeground(Color.WHITE);
		trackID.setBackground(Color.GRAY);
		container2.add(label);
		container2.add(trackID);
		
		
		label = new JLabel("Weight (in Kgs)");
		label.setFont(labelFont);
		label.setBounds(91, 68, 310, 16);
		weight = getTextField(220, 65, 250, 25, 10);
		container2.add(label);
		container2.add(weight);
		
		label = new JLabel("Dimensions (in cm)");
		label.setFont(labelFont);
		label.setBounds(65, 115, 310, 16);
		dimensions = getTextField(220, 112, 250, 25, 10);
		container2.add(label);
		container2.add(dimensions);
		
		label = new JLabel("Select Source");
		label.setFont(labelFont);
		label.setBounds(95, 162, 310, 16);
		source = getPlaceOptions("Select a Source", 220, 157, 250, 25);
		container2.add(label);
		container2.add(source);
		
		
		label = new JLabel("Select Destination");
		label.setFont(labelFont);
		label.setBounds(69, 210, 310, 16);
		destination = getPlaceOptions("Select a Destination", 220, 205, 250, 25);
		container2.add(label);
		container2.add(destination);
	
		JButton submit = new JButton("Create Shipment");
		
		submit.setBackground(Color.BLUE);
		submit.setForeground(Color.MAGENTA);
		submit.setBounds(105, 275, 150, 35);
		container2.add(submit);
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(trackID.getText().isEmpty()||dimensions.getText().isEmpty()||weight.getText().isEmpty()||source.getSelectedItem().equals("Select a Source")
						||destination.getSelectedItem().equals("Select a Destination"))
					JOptionPane.showMessageDialog(null, "Data is Missing");
				else {
					Packet packet = new Packet();
					packet.setId(trackID.getText());
					packet.setSrc(source.getSelectedItem().toString());
					packet.setDst(destination.getSelectedItem().toString());
					packet.setDimen(dimensions.getText());
					packet.setWeight(weight.getText());
					packet.setCreated(new Timestamp(new Date().getTime()).toString());
					trackID.setText((10000000 + ran.nextInt(90000000)) + "");
					weight.setText(null);
					dimensions.setText(null);
					source.setSelectedItem("Select a Source");
					destination.setSelectedItem("Select a Destination");
					new Output(packet).start();
					JOptionPane.showMessageDialog(null, "Successfully Created Shipment");
				}
			}
		});
		
		JButton back = new JButton("Back");
		back.setBackground(Color.BLUE);
		back.setForeground(Color.MAGENTA);
		back.setBounds(265, 275, 150, 35);
		container2.add(back);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainView();
			}
		});
		
		render();
		
	}
  
  	private void trackPack() {
  		frame.setTitle("Shipment Tracking");
		container3 = new Container();
  		frame.setContentPane(container3);
  		
  		JLabel label = null;
		textBoxFont = new Font("TextBox Label", Font.PLAIN, 14);
		labelFont = new Font("Label Font", Font.BOLD, 15);
	
		label = new JLabel("Tracking ID");
		label.setFont(labelFont);
		label.setBounds(86, 31, 310, 16);
		trackID = getTextField(191, 28, 250, 25, 10);
		container3.add(label);
		container3.add(trackID);
		
		JButton track = new JButton("Track");
		
		track.setBackground(Color.BLUE);
		track.setForeground(Color.MAGENTA);
		track.setBounds(455, 23, 150, 35);
		container3.add(track);
		
		track.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(trackID.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Data is Missing");
				else {
					try {
						PreparedStatement pst = Utils.db.connect.prepareStatement("select * from packet_track where id = ?");
						pst.setString(1, trackID.getText());
						ResultSet result = pst.executeQuery();
						result.next();
						Packet packet = new Packet();
						packet.setId(result.getString("id"));
						packet.setSrc(result.getString("src"));
						packet.setDst(result.getString("dest"));
						packet.setDimen(result.getString("dimen"));
						packet.setWeight(result.getString("weight"));
						packet.setCreated(result.getString("created"));
						
						pst = Utils.db.connect.prepareStatement("select * from track_info where id like ? order by recieved desc");
						pst.setString(1, packet.getId());
						result = pst.executeQuery();
						
						int height = 30;
						int i = 0;
						JScrollPane container = new JScrollPane();
						while (result.next()) {
							getComponentView(container, packet, result, 10 + i * height);
							i++;
						}
						
						JLabel label = new JLabel("Travel History");
						label.setFont(new Font("Heading", Font.BOLD, 20));
						label.setBounds(86, 100, 310, 25);
						container3.add(label);
					
						label = new JLabel("Shipment Details");
						label.setFont(new Font("Heading", Font.BOLD, 20));
						label.setBounds(880, 100, 310, 25);
						container3.add(label);
						
						container.setBounds(86, 150, 750, 800);
						container.setVerticalScrollBar(container.createVerticalScrollBar());
						container.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
						while(container3.getComponentCount() > 6) {
							container3.remove(container3.getComponentCount()-1);
						}
						container3.add(container);
					
						label = new JLabel("Dimensions: " + packet.getDimen() + " inches");
						label.setFont(labelFont);
						label.setBounds(880, 150, 310, 16);
						container3.add(label);
						
						label = new JLabel("Weight: " + packet.getWeight() + " kgs");
						label.setFont(labelFont);
						label.setBounds(880, 180, 310, 16);
						container3.add(label);
						
						frame.repaint();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton back = new JButton("Back");
		back.setBackground(Color.BLUE);
		back.setForeground(Color.MAGENTA);
		back.setBounds(620, 23, 150, 35);
		container3.add(back);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(container3.getComponentCount() > 4) {
					container3.remove(container3.getComponentCount()-1);
				}
				trackID.setText(null);
				mainView();
			}
		});
		
		render();
  		
  	}
  	
  	private void getComponentView(JScrollPane container, Packet packet, ResultSet row, int height) throws SQLException {
  		JLabel label = new JLabel(row.getString("recieved"));
		label.setFont(labelFont);
		label.setBounds(10, height, 310, 16);
		container.add(label);
		if(packet.getDst().equals(row.getString("reached"))) {
			label = new JLabel("Deliverd");
		} else {
			label = new JLabel("In transit");
		}
		label.setFont(labelFont);
		label.setBounds(310, height, 310, 16);
		container.add(label);
		
		label = new JLabel(row.getString("reached"));
		label.setFont(labelFont);
		label.setBounds(410, height, 310, 16);
		container.add(label);
  	}
  
  	private JTextField getTextField(int x, int y, int width, int height, int cols) {
  		JTextField textBox = new JTextField();
  		textBox.setBounds(x, y, width, height);
  		textBox.setColumns(cols);
  		textBox.setFont(textBoxFont);
  		return textBox;
  	}
  	
  	private JComboBox<String> getPlaceOptions(String msg, int x, int y, int width, int height) {
  		JComboBox<String> comboBox = new JComboBox<String>();
		List<String>  places = Utils.getPlaces();
		comboBox.addItem(msg);
		for(String place : places) {
			comboBox.addItem(place);
		}
		comboBox.setBounds(x, y, width, height);
		comboBox.setFont(textBoxFont);
		return comboBox;
  	}
  
}