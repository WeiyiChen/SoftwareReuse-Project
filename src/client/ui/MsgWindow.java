package client.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.intf.IMsgWindow;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MsgWindow extends JFrame implements IMsgWindow{

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnSend;
	private JLabel lblUser;
	private JLabel lblUserToShow;
	JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public MsgWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(66, 69, 314, 175);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(66, 274, 199, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(280, 271, 100, 29);
		contentPane.add(btnSend);
		
		lblUser = new JLabel("User");
		lblUser.setBounds(128, 26, 33, 16);
		contentPane.add(lblUser);
		
		lblUserToShow = new JLabel("New label");
		lblUserToShow.setBounds(173, 26, 120, 16);
		contentPane.add(lblUserToShow);
	}
	
	@Override
	public void setUsr(String usr){
		lblUserToShow.setText(usr);
	}

	@Override
	public void appendMsgRecord(String singleLineMsg) {
		// TODO Auto-generated method stub
		textArea.append(singleLineMsg + "\n");
	}
	
	@Override
	public void sendMsg(){
		
	}


	@Override
	public void showMsgWindow() {
		// TODO Auto-generated method stub
		setVisible(true);
	}

	@Override
	public void closeMsgWindow() {
		// TODO Auto-generated method stub
		dispose();
	}
	
	

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MsgWindow frame = new MsgWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
