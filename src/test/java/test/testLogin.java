package test;

import java.io.IOException;

import socket.Client;
import socket.ClientLogin;
import socket.Server;
import view.LoginFrame;

public class testLogin {
	public static void main(String[] args) {

		Thread tr= new Thread(new Runnable() {
	
			@Override
			public void run() {
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
		});
		tr.start();
		
		new ClientLogin();
	}
}
