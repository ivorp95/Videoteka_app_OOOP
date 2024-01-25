package Videoteka;

import java.awt.EventQueue;
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
import Videoteka.*;

public class Registracija {

	private JFrame frame;
	private JTextField ime;
	private JTextField prezime;
	private JTextField brojMob;
	private JPasswordField lozinka;
	private JPasswordField ponLozinka;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registracija window = new Registracija();
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
	public Registracija() {
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
		
		JLabel lblNewLabel = new JLabel("Ime");
		lblNewLabel.setBounds(54, 21, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(54, 54, 61, 16);
		frame.getContentPane().add(lblPrezime);
		
		JLabel lblBrojMobitela = new JLabel("Broj Mobitela");
		lblBrojMobitela.setBounds(54, 92, 104, 16);
		frame.getContentPane().add(lblBrojMobitela);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setBounds(54, 130, 61, 16);
		frame.getContentPane().add(lblLozinka);
		
		JLabel lblPonovljenaLozinka = new JLabel("Ponovljena Lozinka");
		lblPonovljenaLozinka.setBounds(54, 168, 150, 16);
		frame.getContentPane().add(lblPonovljenaLozinka);
		
		ime = new JTextField();
		ime.setBounds(222, 16, 130, 26);
		frame.getContentPane().add(ime);
		ime.setColumns(10);
		
		prezime = new JTextField();
		prezime.setBounds(222, 49, 130, 26);
		prezime.setColumns(10);
		frame.getContentPane().add(prezime);
		
		brojMob = new JTextField();
		brojMob.setBounds(222, 87, 130, 26);
		brojMob.setColumns(10);
		frame.getContentPane().add(brojMob);
		
		lozinka = new JPasswordField();
		lozinka.setBounds(222, 125, 130, 26);
		frame.getContentPane().add(lozinka);
		
		ponLozinka = new JPasswordField();
		ponLozinka.setBounds(222, 163, 130, 26);
		frame.getContentPane().add(ponLozinka);
		
		JButton btnNewButton = new JButton("Registriraj se");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String imes, prezimes, brojMobs, lozinkas,  ponLozinkas;
				
				imes=ime.getText();
				prezimes=prezime.getText();
				brojMobs=brojMob.getText();

				
				lozinkas=new String (lozinka.getPassword());
				ponLozinkas=new String(ponLozinka.getPassword());			
				
				
			if(imes.isEmpty()==false && prezimes.isEmpty()==false && brojMobs.isEmpty()==false && lozinkas.isEmpty()==false) {	// provjera ako je uneseno sve u polja
			
			
				if(lozinkas.equals(ponLozinkas)) {					//provjera ako su lozinke iste sa metodom String.eqals
					
					
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
						String upit="SELECT * FROM clanVideoteka WHERE broj_mob=?";
						PreparedStatement ps=con.prepareStatement(upit);
						ps.setString(1, brojMobs);							//provjera ako u bazi ima vec taj broj mobitela
						ResultSet rs=ps.executeQuery();
						
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Registracija nije moguca, korisnik sa tim brojem mobitela vec postoji");
						}
						else {
						String insert="INSERT INTO clanVideoteka (ime,prezime,broj_mob,lozinka) VALUES (?,?,?,?)";  //provjera ako u bazi ima vec taj broj mobitela
						PreparedStatement psInsert=con.prepareStatement(insert);
						psInsert.setString(1, imes);
						psInsert.setString(2, prezimes);
						psInsert.setString(3, brojMobs);
						psInsert.setString(4, lozinkas);
						
						
						int ubacenoRedaka=psInsert.executeUpdate();
							if(ubacenoRedaka==1) {
								JOptionPane.showMessageDialog(null, "Registracija uspjesna");
								
								// kod uspjesne registracije zatvara se prozor registracije
								frame.dispose();
								
							}
							else {
								JOptionPane.showMessageDialog(null, "Registracija neuspjesna");
							}
						}
						
						
						
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Doslo je do greske u povezivanju sa bazom bloku");
						e1.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Lozinke nisu jednake");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Polja moraju bit popunjena za unos u bazu");
			}
				
			}
		});
		btnNewButton.setBounds(222, 208, 130, 29);
		frame.getContentPane().add(btnNewButton);
	}
	
	public void showWindow(){			//metoda showWindow() definicija nove metode za otvaranje novog prozora -----definicija metoda ide na pocetak ili kraj 
		frame.setVisible(true);
	}

}
