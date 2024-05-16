package socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import controller.LoginController;
import controller.SignupController;

public class ServerController extends Thread{
	Socket clientSocket;
	PrintWriter out;
	ObjectInputStream in;
	public ServerController(Socket clientSocket) throws IOException, ClassNotFoundException {
		super();
		this.clientSocket = clientSocket;
		this.start();
		
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 		try {
			
//			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			 
		
		while(true) {
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
			in = new ObjectInputStream(clientSocket.getInputStream());
			
			String[] control = (String[]) in.readObject();
			controller(control);

		}
		} catch (/*IOException | ClassNotFound*/Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void controller(String[] controller) {
		switch (controller[0]) {
		case "signup":
			DataOutputStream out1;
			try {
				out1 = new DataOutputStream(clientSocket.getOutputStream());
				int result = SignupController.signup(controller[1]);
				out1.writeInt(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case "login":
			String employee = LoginController.Login(controller[1]);
			//				System.out.println(employee);
			out.println(employee);
			break;
		default:
			break;
		}
	}
}
