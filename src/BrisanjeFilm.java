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

public class BrisanjeFilm {

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
					BrisanjeFilm window = new BrisanjeFilm();
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
	public BrisanjeFilm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 619, 469);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Prikaži filmove");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String upit="SELECT * FROM filmVideoteka";
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(upit);

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					
					while (rs.next()){
						int film_id=rs.getInt(1);
						String naslov=rs.getString(2);
						String redatelj=rs.getString(3);
						String godIzd=rs.getString(5);
						String trajanje=rs.getString(4);
						model.addRow(new Object[] {film_id, naslov, redatelj, godIzd, trajanje});
						}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Greška servera!");
					
			}
			}
		});
		btnNewButton.setBounds(104, 382, 132, 37);
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
				"id", "Naslov", "Redatelj", "Godina izdavanja", "Trajanje"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		btnNewButton_1 = new JButton("Izbriši film");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				
				
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					int odabranRedak = table.getSelectedRow();
					int idFilma= (int) table.getValueAt(odabranRedak, 0);
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					
					String upit="DELETE FROM filmVideoteka WHERE film_id='"+idFilma+"'";
					PreparedStatement ps = con.prepareStatement(upit);
					
					int obrisaniRedak = ps.executeUpdate();
					
					if (obrisaniRedak == 1) {
						JOptionPane.showMessageDialog(null, "Film je uspješno obrisan.");
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
		btnNewButton_1.setBounds(353, 382, 132, 37);
		frame.getContentPane().add(btnNewButton_1);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(21);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(308);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(96);
		table.getColumnModel().getColumn(4).setResizable(false);
	}
	
	public void showWindow(){
		frame.setVisible(true);
	}

}
