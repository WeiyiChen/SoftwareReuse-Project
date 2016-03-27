package client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.ctl.LogInCheck;
import client.ctl.WindowJump;
import client.intf.ILogInCheck;
import client.intf.ILogInWindow;
import client.intf.IWindowJump;

public class LoginWindow implements ILogInWindow{

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblTip;


	
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
		btnLogIn.setBounds(83, 186, 106, 29);
		frame.getContentPane().add(btnLogIn);
		
		JLabel lblAccount = new JLabel("Account");
		lblAccount.setBounds(36, 61, 61, 16);
		frame.getContentPane().add(lblAccount);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(36, 111, 61, 16);
		frame.getContentPane().add(lblPassword);
		
		lblTip = new JLabel("");
		lblTip.setBounds(83, 146, 106, 16);
		frame.getContentPane().add(lblTip);
		
		btnLogIn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String usr = textField.getText();
				String pwd = passwordField.getText();
				
//				// test the result of getText()
//				if(pwd.equals("")){
//					System.out.println("\"\"");
//				}
//				System.out.println(pwd);
//				lblTip.setText(usr+pwd);
				if(pwd.equals("")||usr.equals("")){
					lblTip.setText("Illegal Input!");
					return;
				}
				lblTip.setText("");
				ILogInCheck logInCheck = new LogInCheck();
				if(logInCheck.check(usr, pwd)){
					IWindowJump windowJump = new WindowJump();
					windowJump.startMsgWindow();
					frame.dispose();
				}
				else{
					lblTip.setText("Error input!");
				}
			}
		});
	}

	@Override
	public List<String> getUsrPwd() {
		String usr = textField.getText();
		String pwd = passwordField.getText();
		List<String> result = new ArrayList<String>();
		result.add(usr);
		result.add(pwd);
		return null;
	}
	
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
	
}
