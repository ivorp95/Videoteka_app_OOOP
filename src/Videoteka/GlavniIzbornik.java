package Videoteka;
import java.awt.EventQueue;
import Videoteka.*;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GlavniIzbornik {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniIzbornik window = new GlavniIzbornik();
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
	public GlavniIzbornik() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 612, 488);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Pregled filmova");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PregledFilma pf = new PregledFilma();
				pf.showWindow();
				
			}
		});
		btnNewButton.setBounds(89, 53, 125, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnPregledlanova = new JButton("Pregled članova");
		btnPregledlanova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PregledClana pc = new PregledClana();
				pc.showWindow();
			}
		});
		btnPregledlanova.setBounds(89, 135, 125, 43);
		frame.getContentPane().add(btnPregledlanova);
		
		JButton btnPregledPosudbi = new JButton("Pregled posudbi");
		btnPregledPosudbi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PregledPosudbe pp = new PregledPosudbe();
				pp.showWindow();
			}
		});
		btnPregledPosudbi.setBounds(89, 218, 125, 43);
		frame.getContentPane().add(btnPregledPosudbi);
		
		JButton btnPronaiFilm = new JButton("Pronađi film");
		btnPronaiFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPronaiFilm.setBounds(89, 304, 125, 43);
		frame.getContentPane().add(btnPronaiFilm);
		
		JButton btnLogin = new JButton("Unos filma");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosFilma uf = new UnosFilma();
				uf.showWindow();
			}
		});
		btnLogin.setBounds(361, 53, 125, 43);
		frame.getContentPane().add(btnLogin);
		
		//unos clana gumb otvara ponovo registracijski obrazac 
		JButton btnUnoslana = new JButton("Unos člana");
		btnUnoslana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Registracija reg = new Registracija();
				reg.showWindow();
				
			}
		});
		btnUnoslana.setBounds(361, 135, 125, 43);
		frame.getContentPane().add(btnUnoslana);
		
		JButton btnUnosPosudbe = new JButton("Unos posudbe");
		btnUnosPosudbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosPosudbe up = new UnosPosudbe();
				up.showWindow();
			}
		});
		btnUnosPosudbe.setBounds(361, 218, 125, 43);
		frame.getContentPane().add(btnUnosPosudbe);
		
		JButton btnProna = new JButton("Brisanje ");
		btnProna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProna.setBounds(361, 304, 125, 43);
		frame.getContentPane().add(btnProna);
		
		JButton btnZavriSRadom = new JButton("Završi s radom");
		btnZavriSRadom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnZavriSRadom.setBounds(226, 392, 125, 32);
		frame.getContentPane().add(btnZavriSRadom);
	}
		
		
		public void showWindow(){
			frame.setVisible(true);
	}
		

}
