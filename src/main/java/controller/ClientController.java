package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.google.gson.Gson;

import models.Employee;
import others.MaHoaAES;
import view.SignupFrame;

public class ClientController {
	private Socket socket;
	private MaHoaAES maHoaAES;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientController(Socket socket) throws Exception {
		this.socket = socket;
		maHoaAES = new MaHoaAES();
			
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		SecretKey temp = (SecretKey) in.readObject();
		int ivLength = in.readInt();
		byte[] ivBytes = new byte[ivLength];
		in.readFully(ivBytes);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
		maHoaAES.setSecretKey(temp);
		
		maHoaAES.setIvParameterSpec(ivParameterSpec);
	}
	public String sendRequest(String[] request) {
			try {
				
				InputStream in = socket.getInputStream();
				Gson gson = new Gson();
				String requestJson = gson.toJson(request);
				byte[] dataReceived = requestJson.getBytes();
				byte[] mahoa = maHoaAES.maHoa(requestJson.getBytes());
				out.write(mahoa);
				out.flush();
				
				byte[] answer = new byte[3000];
				int length = in.read(answer);
				return giaima(Arrays.copyOf(answer, length));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	public String giaima(byte[] dataReceived) {
		try {
			String temp = new String(maHoaAES.giaiMa(dataReceived));
			return temp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
