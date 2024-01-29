package Videoteka;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IzmjenaFilma {

	private JFrame frame;
	private JLabel lbNaslov;
	private JTextField naslov;
	private JTextField redatelj;
	private JTextField godIzd;
	private JTextField trajanje;
	private JLabel lblTrajanje;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmjenaFilma window = new IzmjenaFilma();
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
	public IzmjenaFilma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 497, 373);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lbNaslov = new JLabel("Novi naslov");
		lbNaslov.setBounds(31, 95, 147, 16);
		frame.getContentPane().add(lbNaslov);
		
		lblTrajanje = new JLabel("Trajanje u min.");
		lblTrajanje.setBounds(31, 248, 147, 16);
		frame.getContentPane().add(lblTrajanje);
		
		JLabel lblAutor = new JLabel("Novi redatelj");
		lblAutor.setBounds(31, 142, 135, 16);
		frame.getContentPane().add(lblAutor);
		
		JLabel lblGodinaIzadnja = new JLabel("Godina izadnja");
		lblGodinaIzadnja.setBounds(31, 194, 147, 16);
		frame.getContentPane().add(lblGodinaIzadnja);
		
		JComboBox comboFilm = new JComboBox();
		comboFilm.setBounds(24, 35, 189, 27);
		frame.getContentPane().add(comboFilm);
		
		JLabel lblNewLabel = new JLabel("Odaberite naslov filma za izmjenu");
		lblNewLabel.setBounds(264, 41, 207, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
		naslov = new JTextField();
		naslov.setBounds(264, 90, 130, 26);
		frame.getContentPane().add(naslov);
		naslov.setColumns(10);
		
		redatelj = new JTextField();
		redatelj.setColumns(10);
		redatelj.setBounds(264, 137, 130, 26);
		frame.getContentPane().add(redatelj);
		
		godIzd = new JTextField();
		godIzd.setColumns(10);
		godIzd.setBounds(264, 189, 130, 26);
		frame.getContentPane().add(godIzd);
		
		
		trajanje = new JTextField();
		trajanje.setBounds(264, 242, 130, 29);
		frame.getContentPane().add(trajanje);
		trajanje.setColumns(10);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
			String upit="SELECT naslov FROM filmVideoteka";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(upit);
			
			while (rs.next()) {
				String podatak=rs.getString(1);
				comboFilm.addItem(podatak);
			}
			
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, "Greška servera!");
		};
		
		btnNewButton = new JButton("Izmjeni film");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String naslovs, redateljs, godIzds,trajanjes;
				String odabraniFilm=(String)comboFilm.getSelectedItem();
			
				int idFilma=0;
				naslovs=naslov.getText();
				redateljs=redatelj.getText();
				godIzds=godIzd.getText();
				trajanjes=trajanje.getText();
				
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
				
				String select="SELECT film_id FROM filmVideoteka WHERE naslov='"+odabraniFilm+"'";
				PreparedStatement psSelect = con.prepareStatement(select);
				ResultSet rsSelect = psSelect.executeQuery();
				
				if (rsSelect.next()) {
					idFilma = rsSelect.getInt(1);
					
				}
				//JOptionPane.showMessageDialog(null, " ID= '"+idFilma+"'");
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Greška kod komunikacije sa Bazom 1");
					e1.printStackTrace();
				}
				
				
				
				if (naslovs.isEmpty()==false  && redateljs.isEmpty()==false && godIzds.isEmpty()==false && trajanjes.isEmpty()==false ) {
					
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con1=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String izmjeni="UPDATE filmVideoteka SET naslov=?, redatelj=?, god_izdanja=?, trajanje=? WHERE film_id=?"; 
					PreparedStatement ps=con1.prepareStatement(izmjeni);
					ps.setString(1, naslovs);
					ps.setString(2,redateljs);
					ps.setString(3, godIzds);
					ps.setString(4, trajanjes);	
					ps.setInt(5, idFilma);
					
					//JOptionPane.showMessageDialog(null, " ID= '"+idFilma+"'i");
				
					
					int ubacenoRedaka=ps.executeUpdate();
					
					if(ubacenoRedaka==1) {
						JOptionPane.showMessageDialog(null, "Podaci filma sa ID= '"+idFilma+"'uspijesno izmjenjeni");
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Greška kod komunikacije sa Bazom");
						
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
		btnNewButton.setBounds(264, 296, 130, 27);
		frame.getContentPane().add(btnNewButton);
	}
		public void showWindow(){
			frame.setVisible(true);
	}

}
