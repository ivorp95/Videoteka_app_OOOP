package Videoteka;
import java.awt.EventQueue;
import Videoteka.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class RegLogin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegLogin window = new RegLogin();
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
	public RegLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Registracija");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registracija reg = new Registracija();
				reg.showWindow();
				
			}
		});
		btnNewButton.setBounds(37, 138, 123, 50);
		frame.getContentPane().add(btnNewButton);
		
		
		// gumb Log-in otvara novi prozor iz projekta Videoteka klasa Login.java
		
		JButton btnLogiin = new JButton("Log-in");
		btnLogiin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login li=new Login();
				li.showWindow();
				
			}
		});
		btnLogiin.setBounds(270, 138, 123, 50);
		frame.getContentPane().add(btnLogiin);
		
		JLabel lblNewLabel = new JLabel("Dobrodošli u Videoteku ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(74, 26, 307, 50);
		frame.getContentPane().add(lblNewLabel);
	}
		
		public void showWindow(){
			frame.setVisible(true);
	}
}
