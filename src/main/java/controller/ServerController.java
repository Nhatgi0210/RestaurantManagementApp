package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.google.gson.Gson;

import DAO.RestaurenDAO;
import controller.LoginController;
import controller.SignupController;
import models.Employee;
import models.Restaurant;
import others.MaHoaAES;

public class ServerController extends Thread {
	private Socket clientSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private MaHoaAES maHoaAES;
	private String[] controller;

	public ServerController(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
		this.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

//			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			maHoaAES = new MaHoaAES();
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			out.writeObject(maHoaAES.getSecretKey());
	
			byte[] ivBytes = maHoaAES.getIvParameterSpec().getIV();
			out.writeInt(ivBytes.length);
			out.flush();
			out.write(ivBytes);
			out.flush();
			
			byte[] temp = new byte[3000];
			int length;
			while (true) {
				length = in.read(temp);
				if (length != -1) {
					controller = changeData(Arrays.copyOf(temp, length));
					control();
				}
			}
		} catch (/* IOException | ClassNotFound */Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String[] changeData(byte[] dataReceived) throws Exception {
		byte[] dataBytes = maHoaAES.giaiMa(dataReceived);
		String dataJson = new String(dataBytes);
		Gson gson = new Gson();
		return gson.fromJson(dataJson, String[].class);
	}

	public void control() throws NumberFormatException, SQLException, IOException {
		OutputStream out = clientSocket.getOutputStream();
		switch (controller[0]) {
		case "signup":
			int result = SignupController.signup(controller[1]);
			out.write(mahoa(Integer.toString(result)));
			out.flush();
			break;
		case "login":
			String employee = LoginController.Login(controller[1]);
			out.write(mahoa(employee));
			out.flush();
			break;
		case "getRestaurantById":
			String restaurant = GetDataController.getRestaurentById(Integer.parseInt(controller[1]));
			out.write(mahoa(restaurant));
			out.flush();
			break;
		case "addRestaurantAndGetId":
			AddDataController.addRestaurant(controller[1], controller[2]);
			int id = GetDataController.getId(controller[1], controller[2]);
			out.write(mahoa(Integer.toString(id)));
			out.flush();
			break;
		case "setIdRestaurantForEmployee":
			UpdateDataController.setIdRestaurant(Integer.parseInt(controller[1]), Integer.parseInt(controller[2]));
			out.write(mahoa("1"));
			out.flush();
			break;
		case "getAllEmployee":
			String employees = GetDataController.getAllEmployee(Integer.parseInt(controller[1]));
			out.write(mahoa(employees));
			out.flush();
			break;
		case "deleteByUsername":
			UpdateDataController.deleteByUsername(controller[1]);
			break;
		case "getEmployeeByPos":
			String result1 = GetDataController.getEmployeeByPos(Integer.parseInt(controller[1]), controller[2]);
			out.write(mahoa(result1));
			out.flush();
			break;
		case "getEmployeeByWord":
			String result2 = GetDataController.getEmployeeByWord(Integer.parseInt(controller[1]), controller[2]);
			out.write(mahoa(result2));
			out.flush();
			break;
		case "updateNamePhonePos":
			UpdateDataController.updateNamePhonePos(controller[1]);
			break;
		case "getAllType":
			String result3 = GetDataController.getAllType(Integer.parseInt(controller[1]));
			out.write(mahoa(result3));
			out.flush();
			break;
		case "getAllProduct":
			String result4 = GetDataController.getAllProduct(Integer.parseInt(controller[1]));
			out.write(mahoa(result4));
			out.flush();
			break;
		case "getProductByType":
			String result5 = GetDataController.getProductByType(Integer.parseInt(controller[1]));
			out.write(mahoa(result5));
			out.flush();
			break;
		case "getProductByWord":
			String result6 = GetDataController.getProductByWord(Integer.parseInt(controller[1]), controller[2]);
			out.write(mahoa(result6));
			out.flush();
			break;
		default:
			break;
		}
	}

	public byte[] mahoa(String data) {
		try {
		
			return maHoaAES.maHoa(data.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
