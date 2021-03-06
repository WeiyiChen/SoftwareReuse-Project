package edu.tongji.reuse.teameleven.client.ui;

import edu.tongji.reuse.teameleven.client.ctrl.ClientMonitorController;
import edu.tongji.reuse.teameleven.client.ctrl.WindowJumpFromLoginToMsg;
import edu.tongji.reuse.teameleven.client.intf.ILogInCheck;
import edu.tongji.reuse.teameleven.client.intf.ILoginWindow;
import edu.tongji.reuse.teameleven.client.intf.IMsgWindow;
import edu.tongji.reuse.teameleven.client.intf.IWindowJump;
import edu.tongji.reuse.teameleven.client.ctrl.LogInCheck;
import edu.tongji.reuse.teameleven.client.transport.StrMsgSender;
import edu.tongji.reuse.teameleven.codependent.base.JsonBuilderBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * log in UI
 * @author Dai
 *
 */
public class LoginWindow implements ILoginWindow {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblTip;

	public void setVisible(boolean flag) {
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

		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usr = textField.getText();
				
				String pwd = passwordField.getText();

				// // test the result of getText()
				// if(pwd.equals("")){
				// System.out.println("\"\"");
				// }
				// System.out.println(pwd);
				// lblTip.setText(usr+pwd);
				if (pwd.equals("") || usr.equals("")) {
					lblTip.setText("Illegal Input!");
					return;
				}
				lblTip.setText("");
				ILogInCheck logInCheck = new LogInCheck();
				boolean logInResult = false;
				try {
					logInResult = logInCheck.check(usr, pwd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
//					ClientRecordController crc = ClientRecordController.getInstance();
//					crc.setUserId(usr);
					if (logInResult) {
//						IJump2MsgWindow windowJump = new JumpToMsgWindowOld();
//						windowJump.startMsgWindow(usr);
						
						IWindowJump windowJump = new WindowJumpFromLoginToMsg();
						IMsgWindow ims = new MsgWindow();
						windowJump.jump(null, ims);
						ims.setUsr(usr);
						frame.dispose();

						ClientMonitorController.increaseLoginTimes();

						final String userId = usr;
						new Thread(()->{
							new StrMsgSender().send(JsonBuilderBase.getFirstMsgJson(userId));
						}).start();

//						ClientLogger.updateUsr(usr);
//						ClientLogger.writeLoginSuccessful(usr);
//						ClientLogger.increaseLoginSucceedCount();
//						ClientLogger.resetSendNum();
//						ClientLogger.resetReceiveNum();
						
//						crc.loginSucceedCountAdd();
//						crc.reset();
//						ClientLogger.setIsLogin(true);
//						

					} else {
						ClientMonitorController.increaseReceiveNum();
//						crc.loginFailedCountAdd();
//						ClientLogger.writeLoginFailed(usr);
//						ClientLogger.increaseLoginFailedCount();
						lblTip.setText("Error input!");
					}
				
			}
		});
	}

	@Override
	public void setTip(String tip) {
		lblTip.setText(tip);

	}

	@Override
	public void toShowWindow() {
		frame.setVisible(true);
	}

	@Override
	public void toCloseWindow() {
		frame.dispose();
	}

	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// LoginWindow window = new LoginWindow();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

}
