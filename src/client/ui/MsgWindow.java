package client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import client.ctl.MsgHandle;
import client.intf.IMsgHandle;
import client.intf.IMsgWindow;

public class MsgWindow extends JFrame implements IMsgWindow{

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnSend;
	private JLabel lblUser;
	private JLabel lblUserToShow;
	private JTextArea textArea;
	private JScrollPane sp;
	private JLabel lblTip;
	private IMsgHandle imh;
	
	/**
	 * Create the frame.
	 */
	public MsgWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		sp = new JScrollPane(textArea);
		sp.setBounds(66, 69, 374, 175);
		contentPane.add(sp);
		
		textField = new JTextField();
		textField.setBounds(66, 274, 199, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(340, 274, 100, 29);
		contentPane.add(btnSend);
		
		lblUser = new JLabel("User");
		lblUser.setBounds(171, 26, 33, 16);
		contentPane.add(lblUser);
		
		lblUserToShow = new JLabel("New label");
		lblUserToShow.setBounds(242, 26, 120, 16);
		contentPane.add(lblUserToShow);
		
		lblTip = new JLabel("");
		lblTip.setBounds(267, 279, 61, 16);
		contentPane.add(lblTip);
		imh = new MsgHandle(this);
		
		btnSend.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sendMsg();
			}
			
		});
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
		lblTip.setText("");
		String msgStr = textField.getText();
		if(msgStr.equals("")){
			lblTip.setText("Empty!");
			return;
		}
//		imh = new MsgHandle(this);
		imh.sendMessage(msgStr);
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
