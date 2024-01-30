package Videoteka;
import java.awt.EventQueue;
import org.mindrot.jbcrypt.BCrypt;
import Videoteka.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	private JFrame frame;
	private JTextField brojMob;
	private JPasswordField lozinka;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Broj Mobitela");
		lblNewLabel.setBounds(56, 42, 103, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setBounds(56, 166, 61, 16);
		frame.getContentPane().add(lblLozinka);
		
		brojMob = new JTextField();
		brojMob.setBounds(240, 37, 130, 26);
		frame.getContentPane().add(brojMob);
		brojMob.setColumns(10);
		
		lozinka = new JPasswordField();
		lozinka.setBounds(240, 161, 130, 26);
		frame.getContentPane().add(lozinka);
		
		JButton btnNewButton = new JButton("Logiraj se");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String brojMobs, hashLozinka, lozinkas;
				brojMobs=brojMob.getText();
				lozinkas=new String (lozinka.getPassword());
				

				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String upit="SELECT lozinka FROM clanVideoteka WHERE broj_mob='"+brojMobs+"'";
					PreparedStatement ps=con.prepareStatement(upit);
					
					ResultSet rs=ps.executeQuery();
					
					if (rs.next()) {
						hashLozinka = rs.getString(1);
						
						if(BCrypt.checkpw(lozinkas, hashLozinka)) {
							
						GlavniIzbornik gi = new GlavniIzbornik();
						gi.showWindow();
						
						//kod uspjesnog logina zatvara se login prozor i otvara Glavni Izbornik
						frame.dispose();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Login nije moguć; lozinka ne odgovara");
					}
					
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Greska servera");
					e1.printStackTrace();
					
				}
			}
		});
		btnNewButton.setBounds(240, 224, 117, 29);
		frame.getContentPane().add(btnNewButton);
	}
		public void showWindow(){
			frame.setVisible(true);
	}
	}

