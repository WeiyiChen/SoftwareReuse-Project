package edu.tongji.reuse.teameleven.client.ui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import edu.tongji.reuse.teameleven.client.ctrl.JsonBuilderClient;
import edu.tongji.reuse.teameleven.client.ctrl.MsgHandle;
import edu.tongji.reuse.teameleven.client.intf.IMsgHandle;
import edu.tongji.reuse.teameleven.client.intf.IMsgWindow;

/**
 * message window UI
 * @author Dai
 *
 */
public class MsgWindow extends JFrame implements IMsgWindow {

	private JPanel contentPane;
	private JTextArea textField;
	private JButton btnSend;
	private JLabel lblUser;
	private JLabel lblUserToShow;
	private JTextArea textArea;
	private JScrollPane sp;
	private JLabel lblTip;
	private IMsgHandle imh;
	private String usr;
	private JScrollPane scrollPane;
	private JList cantactlist;
	private DefaultListModel<String> listModel;
	
	/**
	 * Create the frame.
	 */
	public MsgWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		sp = new JScrollPane(textArea);
		sp.setBounds(230, 62, 374, 175);
		contentPane.add(sp);
		JScrollPane tmpSp = new JScrollPane();
		tmpSp.setBounds(230, 274, 240, 41);
		contentPane.add(tmpSp);
		
		textField = new JTextArea();
		tmpSp.setViewportView(textField);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(499, 274, 65, 41);
		contentPane.add(btnSend);
		
		lblUser = new JLabel("User:");
		lblUser.setBounds(273, 26, 33, 16);
		contentPane.add(lblUser);
		
		lblUserToShow = new JLabel("New label");
		lblUserToShow.setBounds(370, 26, 120, 16);
		contentPane.add(lblUserToShow);
		
		lblTip = new JLabel("");
		lblTip.setBounds(267, 279, 61, 16);
		contentPane.add(lblTip);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 62, 120, 241);
		contentPane.add(scrollPane);
		listModel = new DefaultListModel<String>();
		cantactlist = new JList(listModel);
		scrollPane.setViewportView(cantactlist);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setBounds(52, 26, 61, 16);
		contentPane.add(lblContact);
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


    @Override
    public void addContact(String user){
        listModel.addElement(user);
    }

    @Override
    public boolean removeContact(String user){
        return listModel.removeElement(user);
    }
}
