import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//Server, single client implementation, no threading used so
//interactions between server and client must take turns due 
//to blocking calls

//this class represents our Server
public class ServerTest {
	
	static final int PORT = 9090;
	static final int NUM_CLIENTS = 3;
	public static ArrayList<String> secrets = new ArrayList();
	private static ExecutorService pool = Executors.newFixedThreadPool(NUM_CLIENTS);
	
	public ArrayList<String> getSecrets() {
		return secrets;
	}
	public static void main(String[] args) throws IOException {
				
		//stores all our multithreaded client connections
		ArrayList<ClientHandler> clientHandlers = new ArrayList();
		//stores the secrets that the client inserts
		
//		Random random = new Random();
		
		//listen on port 9090, waiting for client to connect to
		ServerSocket listener = new ServerSocket(PORT);

		while(true) {
			
			System.out.println("[SERVER] Waiting for client connection...");
			//the clients we are listening to will be accepted here
			Socket clientConnection = listener.accept();
			
			System.out.println("[SERVER] Connected to client!");
			
			ClientHandler clientHandler = new ClientHandler(clientConnection);
			clientHandlers.add(clientHandler);
			
			pool.execute(clientHandler);
			
			
			//the output to the connected client
			PrintWriter toClient = new PrintWriter(clientConnection.getOutputStream(), true);
			
			toClient.println("[Server] Tell me a secret...");
			//input from connected client
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
			
			
			}

//		clientConnection.close();
//		listener.close();
		}
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

