package Videoteka;
import Videoteka.*;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class PretragaFilma {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PretragaFilma window = new PretragaFilma();
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
	public PretragaFilma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 572, 426);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 181, 536, 195);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id","Naslov", "Redatelj", "Godina izdavanja", "Trajanje u min."
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(147);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(96);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		JComboBox comboRedatelj = new JComboBox();
		comboRedatelj.setBounds(20, 79, 131, 22);
		frame.getContentPane().add(comboRedatelj);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
			String upit="SELECT DISTINCT redatelj FROM filmVideoteka ORDER BY redatelj ASC";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(upit);
			comboRedatelj.addItem(null);
			
			while (rs.next()) {
				String podatak=rs.getString(1);
				comboRedatelj.addItem(podatak);
			}
			
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, "Greška servera!");
		};
		
		JComboBox comboGodina = new JComboBox();
		comboGodina.setBounds(212, 79, 131, 22);
		frame.getContentPane().add(comboGodina);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
			String upit="SELECT DISTINCT god_izdanja FROM filmVideoteka ORDER BY god_izdanja ASC";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(upit);
			comboGodina.addItem(null);
			
			while (rs.next()) {
				String podatak=rs.getString(1);
				comboGodina.addItem(podatak);
			}
			
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, "Greška servera!");
		};
		
		
		JButton btnNewButton = new JButton("Pretraži");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String odabraniRedatelj=(String)comboRedatelj.getSelectedItem();
				String odabranaGodina=(String)comboGodina.getSelectedItem();
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String upit="SELECT * FROM filmVideoteka WHERE redatelj=? OR god_izdanja=? ORDER BY god_izdanja ASC";
					PreparedStatement ps=con.prepareStatement(upit);
					ps.setString(1, odabraniRedatelj);	
					ps.setString(2,odabranaGodina);
					
					ResultSet rs=ps.executeQuery();

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					
					while (rs.next()){
						int film_id=rs.getInt(1);
						String naslov=rs.getString(2);
						String redatelj=rs.getString(3);
						String trajanje=rs.getString(4);
						String godIzd=rs.getString(5);
						model.addRow(new Object[] {film_id, naslov, redatelj, godIzd, trajanje});
						}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Greška servera!");
					e1.printStackTrace();
					
			}
				
				
				
				
			}
		});
		btnNewButton.setBounds(403, 71, 125, 39);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Pronađi film po Redatelju");
		lblNewLabel.setBounds(20, 54, 146, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPronaiFilmPo = new JLabel("Pronađi film po godini izlaska");
		lblPronaiFilmPo.setBounds(212, 54, 191, 14);
		frame.getContentPane().add(lblPronaiFilmPo);
		
	}
		public void showWindow(){
			frame.setVisible(true);
	}
}
