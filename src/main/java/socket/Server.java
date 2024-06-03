package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;

import com.google.gson.Gson;

import controller.ServerController;
import others.MaHoaAES;

public class Server{
		public Server() throws Exception {
			ServerSocket server = new ServerSocket(1234);
			
			while(true) {
				Socket socket = server.accept();
				new ServerController(socket);
//				MaHoaRSA rsa = new MaHoaRSA();
//				
//				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//				PublicKey clientPublicKey = (PublicKey) in.readObject();
//				out.writeObject(rsa.getPublicKey());
//				byte[] inb = new byte[1028];
//				in.read(inb);
//				byte[] mahoa = rsa.giaiMa(inb,rsa.getPrivateKey());
//				String json = new String(mahoa);
//				Gson gson = new Gson();
//				String[] result = gson.fromJson(json, String[].class);
//				System.out.println(result[0] + " "+ result[1]);
				
			}
		}
		public static void main(String[] args) throws Exception {
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
