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

public class BrisanjeClan {

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
					BrisanjeClan window = new BrisanjeClan();
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
	public BrisanjeClan() {
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
		
		JButton btnNewButton = new JButton("Prikaži registrirane članove");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String upit="SELECT * FROM clanVideoteka";
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(upit);

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					
					while (rs.next()){
						int clan_id=rs.getInt(1);
						String ime=rs.getString(2);
						String prezime=rs.getString(3);
						String brojMob=rs.getString(4);
						model.addRow(new Object[] {clan_id, ime, prezime, brojMob});
						}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Greška servera!");
					
			}
			}
		});
		btnNewButton.setBounds(71, 382, 185, 37);
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
				"id", "Ime", "Prezime", "Broj Mobitela"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		btnNewButton_1 = new JButton("Izbriši člana ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	

				
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					int odabranRedak = table.getSelectedRow();
					int idClana= (int) table.getValueAt(odabranRedak, 0);
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					
					String upit="DELETE FROM clanVideoteka WHERE clan_id='"+idClana+"'";
					PreparedStatement ps = con.prepareStatement(upit);
					
					int obrisaniRedak = ps.executeUpdate();
					
					if (obrisaniRedak == 1) {
						JOptionPane.showMessageDialog(null, "Član je uspješno obrisan.");
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
		btnNewButton_1.setBounds(360, 382, 185, 37);
		frame.getContentPane().add(btnNewButton_1);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
	}
		
		public void showWindow(){
			frame.setVisible(true);

		
	}

}
