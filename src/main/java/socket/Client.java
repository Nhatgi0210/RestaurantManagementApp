package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.PublicKey;

import com.google.gson.Gson;

import controller.ClientController;
import others.MaHoaAES;
import view.LoginFrame;
import view.SignupFrame;

public class Client {
	public Client() {
		Socket socket;
		
			try {
				socket = new Socket("localhost", 1234);
				ClientController clientController = new ClientController(socket);
				new LoginFrame(clientController);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			System.out.println("Kết Nối Server Thành Công!");
//			String[] a = {"hello","Đinh Phúc Tuấn Nhật"};
//			Gson gson = new Gson();
//			String ajson = gson.toJson(a);
//			
//			MaHoaRSA rsa = new MaHoaRSA();
//			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//			out.writeObject(rsa.getPublicKey());
//			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//			PublicKey serverPublicKey = (PublicKey) in.readObject();
//			byte[] mahoa = rsa.maHoa(ajson.getBytes(), serverPublicKey);
//			out.write(mahoa);
//			out.flush();
//			
			
		
		
	}
	public static void main(String[] args) throws Exception {
		 new Client();
	}
	
	
}
