package socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import view.LoginFrame;
import view.SignupFrame;

public class ClientLogin {
	public ClientLogin() {
		Socket socket;
		try {
			socket = new Socket("localhost", 1234);
			new LoginFrame(socket);
			System.out.println("Kết Nối Server Thành Công!");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		 new ClientLogin();
	}
	
}
