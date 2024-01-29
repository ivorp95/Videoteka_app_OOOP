package Videoteka;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Videoteka.*;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class UnosPosudbe {

	private JFrame frame;
	private JTextField DP;
	private JTextField DV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosPosudbe window = new UnosPosudbe();
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
	public UnosPosudbe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 497, 373);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox<String> comboClan = new JComboBox<>();
		comboClan.setBounds(26, 80, 189, 27);
		frame.getContentPane().add(comboClan);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
			String upit="SELECT prezime FROM clanVideoteka";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(upit);
			
			while (rs.next()) {
				String podatak=rs.getString(1);
				comboClan.addItem(podatak);
			}
			
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, "Greška servera!");
		};
		
		JComboBox comboFilm = new JComboBox();
		comboFilm.setBounds(26, 195, 189, 27);
		frame.getContentPane().add(comboFilm);
		
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
		
		
		JButton btnNewButton = new JButton("Posudba");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String odabraniClan=(String)comboClan.getSelectedItem();
					String odabraniFilm=(String)comboFilm.getSelectedItem();
					
					String DPs = DP.getText();
					String DVs = DV.getText();
					
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String upitClan="SELECT clan_id FROM clanOOOP WHERE broj_mob=(select broj_mob from clanOOOP where prezime='"+odabraniClan+"');";
					String upitFilm="SELECT film_id FROM filmVideoteka WHERE naslov='"+odabraniFilm+"'";
					
					Statement stmtClan=con.createStatement();
					Statement stmtFilm=con.createStatement();
					
					ResultSet rsClan=stmtClan.executeQuery(upitClan);
					ResultSet rsFilm=stmtFilm.executeQuery(upitFilm);
					
					//treba nam dva broja jer posudba ima dva vanjska kljuca id knjige i id clana
					
					int idClan=0, idFilm=0
							;
					if(rsClan.next()) {
						idClan=rsClan.getInt(1);
					}
					if(rsFilm.next()) {
						idFilm=rsFilm.getInt(1);
					}
					
					String upit="INSERT INTO posudbaVideoteka (clan_id,film_id,datum_posudbe,datum_vracanja) VALUES (?,?,?,?)";
					PreparedStatement ps=con.prepareStatement(upit);
					ps.setInt(1, idClan);
					ps.setInt(2, idFilm);
					ps.setString(3, DPs);
					ps.setString(4, DVs);
					
					int ubacenoRedaka=ps.executeUpdate();
					
					if(ubacenoRedaka==1) {
						JOptionPane.showMessageDialog(null, "Uspjesno posuđen film");
					}
					else {
						JOptionPane.showMessageDialog(null, "Posudba neuspješna");
					}
					
				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnNewButton.setBounds(169, 260, 130, 36);
		frame.getContentPane().add(btnNewButton);
		
		DP = new JTextField();
		DP.setBounds(294, 80, 130, 26);
		frame.getContentPane().add(DP);
		DP.setColumns(10);
		
		DV = new JTextField();
		DV.setColumns(10);
		DV.setBounds(294, 195, 130, 26);
		frame.getContentPane().add(DV);
		
		JLabel lblNewLabel = new JLabel("Odaberite člana koji vrši posudbu");
		lblNewLabel.setBounds(26, 55, 189, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblOdaberiteFilmKoji = new JLabel("Odaberite film koji član posuđuje");
		lblOdaberiteFilmKoji.setBounds(26, 170, 189, 14);
		frame.getContentPane().add(lblOdaberiteFilmKoji);
		
		JLabel lblDatumPosudbe = new JLabel("Datum posudbe YYYY-MM-DD");
		lblDatumPosudbe.setBounds(292, 55, 189, 14);
		frame.getContentPane().add(lblDatumPosudbe);
		
		JLabel lblDatumVraanjaYyyymmdd = new JLabel("Datum vraćanja YYYY-MM-DD");
		lblDatumVraanjaYyyymmdd.setBounds(294, 170, 189, 14);
		frame.getContentPane().add(lblDatumVraanjaYyyymmdd);
	}
	
		public void showWindow(){
			frame.setVisible(true);
	}
}
