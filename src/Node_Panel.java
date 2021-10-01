import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
// import java.awt.GridBagLayout;
//import java.awt.GridBagConstraints;
import javax.swing.JButton;
// import java.awt.Insets;
// import java.awt.TextArea;
// import java.awt.BorderLayout;
// import java.awt.FlowLayout;
import javax.swing.JTextField;

import java.awt.BorderLayout;
// import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
// import java.net.*;
import java.io.*;
import java.awt.Font;

public class Node_Panel extends JPanel{
	private static final long serialVersionUID = -3201287144495410462L;
	String ownName;
	String recieverName;
	String desiredIp;
	int ownPort;
	int desiredPort;
	private JTextField recieverNameField;
	private JTextField myPortField;
	private JTextField myNameField;

	public Node_Panel() {
		JTextField ipField;
		JTextField messageField;
		JTextField portField;
		JLabel ipLabel;
		JLabel portLabel;
		JButton sentBtn;
		JButton connectBtn;
		JButton clearBtn;
		JTextArea chatArea;
				 
		setBackground(Color.LIGHT_GRAY);
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		this.setPreferredSize(new Dimension(800, 600));

		ipField = new JTextField();
		ipField.setBounds(343, 436, 150, 34);
		add(ipField);
		ipField.setColumns(10);
		
		connectBtn = new JButton("CONNECT");
		connectBtn.setBounds(505, 391, 150, 34);
		add(connectBtn);
		
		messageField = new JTextField();
		messageField.setColumns(10);
		messageField.setBounds(31, 531, 575, 34);
		add(messageField);
		
		sentBtn = new JButton("SENT");
		sentBtn.setBounds(617, 530, 96, 34);
		add(sentBtn);
		
		portField = new JTextField();
		portField.setColumns(10);
		portField.setBounds(343, 480, 150, 34);
		add(portField);
		
		ipLabel = new JLabel("IP ADRESS");
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ipLabel.setBounds(246, 436, 96, 34);
		add(ipLabel);
		
		portLabel = new JLabel("PORT");
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		portLabel.setBounds(282, 480, 60, 34);
		add(portLabel);
		
		chatArea = new JTextArea();
		chatArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		chatArea.setEditable(false);
		
		clearBtn = new JButton("CLEAR");
		clearBtn.setBounds(660, 359, 130, 28);
		add(clearBtn);
		
		recieverNameField = new JTextField();
		recieverNameField.setColumns(10);
		recieverNameField.setBounds(343, 392, 150, 34);
		add(recieverNameField);
		
		JLabel recieverNameLabel = new JLabel("NAME");
		recieverNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		recieverNameLabel.setBounds(282, 389, 60, 34);
		add(recieverNameLabel);
		
		JLabel friendLabel = new JLabel("FRIEND");
		friendLabel.setHorizontalAlignment(SwingConstants.CENTER);
		friendLabel.setBounds(360, 356, 103, 34);
		add(friendLabel);
		
		myPortField = new JTextField();
		myPortField.setColumns(10);
		myPortField.setBounds(92, 436, 150, 34);
		add(myPortField);
		
		JLabel myPortLabel = new JLabel("PORT");
		myPortLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myPortLabel.setBounds(31, 435, 60, 34);
		add(myPortLabel);
		
		myNameField = new JTextField();
		myNameField.setColumns(10);
		myNameField.setBounds(92, 392, 150, 34);
		add(myNameField);
		
		JLabel myNameLabel = new JLabel("NAME");
		myNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myNameLabel.setBounds(31, 389, 60, 34);
		add(myNameLabel);
		
		JLabel meLabel = new JLabel("ME");
		meLabel.setHorizontalAlignment(SwingConstants.CENTER);
		meLabel.setBounds(117, 359, 103, 34);
		add(meLabel);
		
		JLabel myPortMessageLabel = new JLabel("");
		myPortMessageLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		myPortMessageLabel.setBounds(711, 574, 79, 16);
		add(myPortMessageLabel);
		
		JScrollPane scrollPane = new JScrollPane(chatArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 10, 780, 339);
		add(scrollPane);
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );

		connectBtn.addActionListener(new ActionListener() {
            @Override            
            public void actionPerformed(ActionEvent e) {
            	ownName = myNameField.getText();
            	ownPort = Integer.parseInt(myPortField.getText());
            	recieverName = recieverNameField.getText();
            	desiredIp = ipField.getText();
            	desiredPort =  Integer.parseInt(portField.getText());
            	chatArea.append("You can now send messages to " + recieverName + " on port #" + desiredPort + "...\n\n");
            	Reader_Thread readerThread = new Reader_Thread(chatArea, ownPort, recieverName);
            	readerThread.start();
            	
            	myPortMessageLabel.setText("My port: "+ownPort);
            	connectBtn.setText("CONNECTED!");
            	
            }
        });
		
		sentBtn.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				Socket socket;
            	String desiredMessage = messageField.getText();
            	
            	messageField.setText(null);
            	chatArea.append(ownName + " > " + desiredMessage + "\n");
            	
	       		try {
					socket = new Socket(desiredIp, desiredPort);
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeUTF(desiredMessage);
					socket.close();
				} catch (NumberFormatException | IOException exception) { exception.printStackTrace(); }
            }
        });
		
		clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	chatArea.setText(null);
            }
        });
	}
}
