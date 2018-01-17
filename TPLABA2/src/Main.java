import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Main {

	private JFrame frame;
	Parking parking;
	private Frame btnColor;
	private Frame btnSelectdopColor;
	private JTextField numPlace; 
	JPanel panel;
	private String[] elements = new String[6];
	JList listLevels;
	SelectSamolet select;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		parking = new Parking(5);
		initialize();
		for (int i = 0; i < 5; i++) {
			elements[i] = "������� " + (i+1);
		}
		listLevels.setSelectedIndex(parking.getCurrentLevel());
	}
	public void getSamolet() {
				select = new SelectSamolet(frame);
				if (select.res()) {
					Itechnica samolet = select.getSamolet();
					int place = parking.putSamolet(samolet);
					panel.repaint();
				System.out.println("���� �����: " + place);
			}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new Panel1(parking);
		panel.setBounds(10, 11, 854, 499);
		frame.getContentPane().add(panel);
		
		JPanel panelTake = new JPanel();
		panelTake.setBounds(901, 11, 153, 170);
		frame.getContentPane().add(panelTake);
		JButton btnTake = new JButton("�����");
				btnTake.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(checkPlace(numPlace.getText())) {
							Itechnica samolet = parking.getSamolet(Integer.parseInt(numPlace.getText()));
							Graphics gr = panelTake.getGraphics();
							gr.clearRect(0, 0, panelTake.getWidth(), panelTake.getHeight());
							samolet.setPosition(5, 5);
							samolet.drawSamolet(gr);
							panel.repaint();
		 				}
		}
		});
        
		btnTake.setBounds(973, 233, 81, 23);
		frame.getContentPane().add(btnTake);
		
		JLabel lblNewLabel = new JLabel("�����:");
		lblNewLabel.setBounds(912, 205, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		numPlace = new JTextField();
		numPlace.setBounds(968, 202, 86, 20);
		frame.getContentPane().add(numPlace);
		numPlace.setColumns(10);
        
		listLevels = new JList(elements);
		listLevels.setBounds(891, 373, 153, 111);
		frame.getContentPane().add(listLevels);
		
		JButton btnLevelDown = new JButton("Down");
		btnLevelDown.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				parking.levelDown();
				listLevels.setSelectedIndex(parking.getCurrentLevel());
				panel.repaint();
			}
		});
		btnLevelDown.setBounds(869, 495, 89, 23);
		frame.getContentPane().add(btnLevelDown);
		
		JButton btnLevelUp = new JButton("Up");
		btnLevelUp.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			parking.levelUp();
				listLevels.setSelectedIndex(parking.getCurrentLevel());
				panel.repaint();
			}
		});
		btnLevelUp.setBounds(973, 495, 89, 23);
		frame.getContentPane().add(btnLevelUp);
		
		JButton btnGetSamolet = new JButton("��������");
		btnGetSamolet.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						getSamolet();
					}
				});
		        btnGetSamolet.setBounds(927, 300, 96, 23);
				frame.getContentPane().add(btnGetSamolet);
	}

	private boolean checkPlace(String str){
		try {
	        Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
	}
		if(Integer.parseInt(str)>20) return false;
		return true;
	}

}
