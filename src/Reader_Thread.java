import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

public class Reader_Thread extends Thread{
	JTextArea chatArea;
	ServerSocket ownNodeSocket;
	String recieverName;
	int ownPort; 

	public Reader_Thread(JTextArea chatArea, int ownPort, String recieverName) {
		this.chatArea = chatArea;
		this.ownPort = ownPort;
		this.recieverName = recieverName;
		
		try { this.ownNodeSocket = new ServerSocket(this.ownPort); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	public void run() {
		while (true) {
			try {
				Socket socket_cli = this.ownNodeSocket.accept();
				DataInputStream in = new DataInputStream(socket_cli.getInputStream());
				String message = in.readUTF();
				this.chatArea.append(this.recieverName+" > "+ message + "\n");
			} catch (IOException e) { e.printStackTrace(); }
		}
	}
}
