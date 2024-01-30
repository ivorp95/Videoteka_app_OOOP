package Videoteka;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BrisanjeIzmjena {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjeIzmjena window = new BrisanjeIzmjena();
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
	public BrisanjeIzmjena() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 533, 301);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Brisanje Filmova");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BrisanjeFilm bf = new BrisanjeFilm();
				bf.showWindow();
			}
		});
		btnNewButton.setBounds(199, 62, 132, 45);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBrisanjelanova = new JButton("Brisanje Članova");
		btnBrisanjelanova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BrisanjeClan bc = new BrisanjeClan();
				bc.showWindow();
				
			}
		});
		btnBrisanjelanova.setBounds(10, 62, 143, 45);
		frame.getContentPane().add(btnBrisanjelanova);
		
		JButton btnBrisanjePosudbi = new JButton("Brisanje Posudbi");
		btnBrisanjePosudbi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BrisanjePosudbe bp = new BrisanjePosudbe();
				bp.showWindow();
			}
		});
		btnBrisanjePosudbi.setBounds(375, 62, 132, 45);
		frame.getContentPane().add(btnBrisanjePosudbi);
		
		JButton btnIzmjenaFilmova = new JButton("Izmjena Filmova");
		btnIzmjenaFilmova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				IzmjenaFilma izf = new IzmjenaFilma();
				izf.showWindow();
				
			}
		});
		btnIzmjenaFilmova.setBounds(199, 161, 132, 45);
		frame.getContentPane().add(btnIzmjenaFilmova);
	}
		public void showWindow(){
			frame.setVisible(true);
	}

}
