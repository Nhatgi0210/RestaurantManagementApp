package test;

import java.io.IOException;
import java.net.UnknownHostException;

import socket.Client;
import socket.Server;


public class testSignup {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		
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
		
		new Client();
	}
}
