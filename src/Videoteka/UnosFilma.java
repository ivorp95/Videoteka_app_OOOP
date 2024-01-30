package Videoteka;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import Videoteka.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UnosFilma {

	private JFrame frame;
	private JTextField naslov;
	private JTextField redatelj;
	private JTextField godIzd;
	private JTextField trajanje;
	private JLabel lblTrajanje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosFilma window = new UnosFilma();
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
	public UnosFilma() {
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
		
		JLabel lblNewLabel = new JLabel("Naslov");
		lblNewLabel.setBounds(29, 27, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAutor = new JLabel("Redatelj");
		lblAutor.setBounds(29, 74, 61, 16);
		frame.getContentPane().add(lblAutor);
		
		JLabel lblGodinaIzadnja = new JLabel("Godina izadnja");
		lblGodinaIzadnja.setBounds(29, 126, 147, 16);
		frame.getContentPane().add(lblGodinaIzadnja);
		
		
		JButton btnNewButton = new JButton("Unesi Film");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String naslovs, redateljs, godIzds,trajanjes;
				
				naslovs=naslov.getText();
				redateljs=redatelj.getText();
				godIzds=godIzd.getText();
				trajanjes=trajanje.getText();
				
				if (naslovs.isEmpty()==false  && redateljs.isEmpty()==false && godIzds.isEmpty()==false && trajanjes.isEmpty()==false ) {
					
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String insert="INSERT INTO filmVideoteka (naslov,redatelj,god_izdanja,trajanje) VALUES (?,?,?,?)"; 
					PreparedStatement ps=con.prepareStatement(insert);
					ps.setString(1, naslovs);
					ps.setString(2,redateljs);
					ps.setString(3, godIzds);
					ps.setString(4, trajanjes);	
					
					int ubacenoRedaka=ps.executeUpdate();
					
					if(ubacenoRedaka==1) {
						JOptionPane.showMessageDialog(null, "Novi Film uspijesno unesen");
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Greska u unosu filma kod komunikacije sa Bazom");
					}
				}
					
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					e1.printStackTrace();
				}
				
			}
				else {
					JOptionPane.showMessageDialog(null, "Polja moraju bit popunjena za unos u bazu");
				}
				
			}
		});
		btnNewButton.setBounds(219, 224, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		naslov = new JTextField();
		naslov.setBounds(219, 22, 130, 26);
		frame.getContentPane().add(naslov);
		naslov.setColumns(10);
		
		redatelj = new JTextField();
		redatelj.setColumns(10);
		redatelj.setBounds(219, 69, 130, 26);
		frame.getContentPane().add(redatelj);
		
		godIzd = new JTextField();
		godIzd.setColumns(10);
		godIzd.setBounds(219, 121, 130, 26);
		frame.getContentPane().add(godIzd);
		
		
		trajanje = new JTextField();
		trajanje.setBounds(219, 174, 130, 29);
		frame.getContentPane().add(trajanje);
		trajanje.setColumns(10);
		
		lblTrajanje = new JLabel("Trajanje u min.");
		lblTrajanje.setBounds(29, 181, 147, 16);
		frame.getContentPane().add(lblTrajanje);
	}
	public void showWindow(){
		frame.setVisible(true);
	}
}
