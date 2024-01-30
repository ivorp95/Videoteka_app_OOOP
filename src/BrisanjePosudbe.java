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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BrisanjePosudbe {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjePosudbe window = new BrisanjePosudbe();
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
	public BrisanjePosudbe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 619, 469);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Prikaži aktivne posudbe");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String upit="SELECT datum_vracanja, clanVideoteka.ime, clanVideoteka.broj_mob, filmVideoteka.film_id, filmVideoteka.god_izdanja  FROM posudbaVideoteka LEFT OUTER JOIN clanVideoteka ON posudbaVideoteka.clan_id = clanVideoteka.clan_id LEFT OUTER JOIN filmVideoteka ON filmVideoteka.film_id = posudbaVideoteka.film_id";
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(upit);

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					
					while (rs.next()){
						String DV=rs.getString(1);
						String ime=rs.getString(2);
						String brojMob=rs.getString(3);
						int film_id=rs.getInt(4);
						String godIzd=rs.getString(5);
						
						model.addRow(new Object[] {DV, ime, brojMob, film_id, godIzd});
						}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Greška servera!");
					
			}
			}
		});
		btnNewButton.setBounds(76, 382, 177, 37);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 583, 360);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"datum vracanja", "Ime", "Broj Mobitela člana", "film_id", "godina izdanja filma"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		btnNewButton_1 = new JButton("Obriši posudbu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					int odabranRedak = table.getSelectedRow();
					int idFilma= (int) table.getValueAt(odabranRedak, 3);
					int idPosudbe=0;
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					
					String select="SELECT id_posudba FROM posudbaVideoteka WHERE film_id='"+idFilma+"'";
					PreparedStatement psSelect = con.prepareStatement(select);
					ResultSet rsSelect = psSelect.executeQuery();
					
					if (rsSelect.next()) {
						idPosudbe = rsSelect.getInt(1);
						
					}
					
					
					String upit="DELETE FROM posudbaVideoteka WHERE id_posudba='"+idPosudbe+"'";
					
					PreparedStatement ps = con.prepareStatement(upit);
					
					int obrisaniRedak = ps.executeUpdate();
					
					if (obrisaniRedak == 1) {
						JOptionPane.showMessageDialog(null, "Posudba je uspješno obrisana.");
					}
						
					else {
						JOptionPane.showMessageDialog(null, "Došlo je do pogreške prilikom brisanja.");					
					}
					

				
						
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Greška servera!");
					
			}
				
				
			}
		});
		btnNewButton_1.setBounds(351, 382, 177, 37);
		frame.getContentPane().add(btnNewButton_1);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
	}
		
		public void showWindow(){
			frame.setVisible(true);
	}

}
