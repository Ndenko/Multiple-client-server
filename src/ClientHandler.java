import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
	private Socket clientConnection; 
	private PrintWriter toClient;
	private BufferedReader fromClient;

	public ClientHandler(Socket clientSocket) throws IOException {
		this.clientConnection = clientSocket;
		toClient = new PrintWriter(clientConnection.getOutputStream(), true);
		fromClient = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
	}
	@Override
	public void run() {
	
		try {
			while (true) {
			
				//read a line from that input
				String request = fromClient.readLine();
				if (ServerTest.secrets.contains(request)) {
					toClient.println("[SERVER] Secret already exists...");
				} else {
					ServerTest.secrets.add(request);
					toClient.println("[SERVER] Secret added!");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
	}

}
	public static void main(String[] args) {
		
	}

}