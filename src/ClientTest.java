import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ClientTest {
	
	private static final int SERVER_PORT = 9090;
	
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket serverConnection = new Socket(SERVER_IP, SERVER_PORT);
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader (System.in));
		BufferedReader fromSever = new BufferedReader(new InputStreamReader ( serverConnection.getInputStream()));

		PrintWriter toServer = new PrintWriter(serverConnection.getOutputStream(), true);
		System.out.println(fromSever.readLine());
		
		while (true) {
			System.out.println("> ");
			
			String command = keyboard.readLine();
			
			if (command.equals("quit")) break;
			toServer.println(command);
			
			String serverResponse = fromSever.readLine();
			
	//		JOptionPane.showMessageDialog(null, serverResponse);
			
			System.out.println("[SERVER]: " + serverResponse);
			
		}
		System.out.println("[Client] Finished tasks... Closing...");
		serverConnection.close();
		System.exit(0);
		
		
	}

}
