package client.ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.intf.ILogInWindow;

public class LoginWindow implements ILogInWindow{

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginWindow window = new LoginWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public void setVisible(boolean flag){
		frame.setVisible(flag);
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 290, 286);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(99, 56, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(99, 106, 130, 26);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setBounds(82, 174, 106, 29);
		frame.getContentPane().add(btnLogIn);
		
		JLabel lblAccount = new JLabel("Account");
		lblAccount.setBounds(36, 61, 61, 16);
		frame.getContentPane().add(lblAccount);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(36, 111, 61, 16);
		frame.getContentPane().add(lblPassword);
	}

	@Override
	public List<String> getUsrPwd() {
		String usr = textField.getText();
		String pwd = passwordField.getText();
		List<String> result = new ArrayList<String>();
		
		return null;
	}
}
