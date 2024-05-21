package controller;

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
import java.sql.SQLException;

import DAO.RestaurenDAO;
import controller.LoginController;
import controller.SignupController;
import models.Restaurant;

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
	public void controller(String[] controller) throws NumberFormatException, SQLException {
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
		case "getRestaurantById":
			String restaurant = GetDataController.getRestaurentById(Integer.parseInt(controller[1]));
			out.println(restaurant);
			break;
		case "addRestaurantAndGetId":
			AddDataController.addRestaurant(controller[1],controller[2]);
			int id = GetDataController.getId(controller[1], controller[2]);
			try {
				out1 = new DataOutputStream(clientSocket.getOutputStream());
				out1.writeInt(id);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "setIdRestaurantForEmployee":
			UpdateDataController.setIdRestaurant(Integer.parseInt(controller[1]),Integer.parseInt(controller[2]));
			break;
		case "getAllEmployee":
			String result = GetDataController.getAllEmployee(Integer.parseInt(controller[1]));
			out.println(result);
			break;
		case "deleteByUsername":
			UpdateDataController.deleteByUsername(controller[1]);
			break;
		case "getEmployeeByPos":
			String result1 = GetDataController.getEmployeeByPos(Integer.parseInt(controller[1]),controller[2]);
			out.println(result1);
			break;
		case "getEmployeeByWord":
			String result2 = GetDataController.getEmployeeByWord(Integer.parseInt(controller[1]),controller[2]);
			out.println(result2);
			break;
		default:
			break;
		}
	}
}
