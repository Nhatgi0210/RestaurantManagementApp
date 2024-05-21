package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.ServerController;

public class Server {
		public Server() throws IOException, ClassNotFoundException {
			ServerSocket server = new ServerSocket(1234);
			while(true) {
				Socket socket = server.accept();
				new ServerController(socket);
			}
		}
		public static void main(String[] args) {
			 try {
				new Server();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
