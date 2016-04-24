package client.ui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import client.ctl.JsonBuilderClient;
import client.ctl.MsgHandle;
import client.intf.IMsgHandle;
import client.intf.IMsgWindow;

/**
 * message window UI
 * @author Dai
 *
 */
public class MsgWindow extends JFrame implements IMsgWindow{

	private JPanel contentPane;
	private JTextArea textField;
	private JButton btnSend;
	private JButton btnCompress;
	private JLabel lblUser;
	private JLabel lblUserToShow;
	private JTextArea textArea;
	private JScrollPane sp;
	private JLabel lblTip;
	private IMsgHandle imh;
	private String usr;
	
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
		
		textField = new JTextArea();
		JScrollPane tmpSp = new JScrollPane(textField);
		tmpSp.setBounds(66, 274, 240, 41);
		contentPane.add(tmpSp);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(310, 274, 65, 41);
		contentPane.add(btnSend);
		
		btnCompress = new JButton("ZIP");
		btnCompress.setBounds(378, 274, 60, 41);
        contentPane.add(btnCompress);
		
		lblUser = new JLabel("User:");
		lblUser.setBounds(183, 26, 33, 16);
		contentPane.add(lblUser);
		
		lblUserToShow = new JLabel("New label");
		lblUserToShow.setBounds(228, 26, 120, 16);
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
		
		usr = "";
	}
	
	@Override
	public void setUsr(String usr){
		this.usr = usr;
		lblUserToShow.setText(usr);
	}

	@Override
	public void appendMsgRecord(String singleLineMsg) {
		// TODO Auto-generated method stub
		textArea.append(singleLineMsg + "\n");
		sp.getViewport().setViewPosition(new Point(0, sp.getVerticalScrollBar().getMaximum()));
	}
	
	@Override
	public void sendMsg(){
		lblTip.setText("");
		String msgStr = textField.getText();
		if(msgStr.equals("")){
			lblTip.setText("Empty!");
			return;
		}
		String jsonStr = JsonBuilderClient.getMessageJson(usr, msgStr);
//		imh = new MsgHandle(this);
		imh.sendMessage(jsonStr);
		textField.setText("");
	}


	@Override
	public void toShowWindow() {
		// TODO Auto-generated method stub
		setVisible(true);
	}

	@Override
	public void toCloseWindow() {
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public IMsgHandle getMsgHandle() {
		// TODO Auto-generated method stub
		return imh;
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
