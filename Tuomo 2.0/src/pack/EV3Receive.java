package pack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class EV3Receive extends Thread {

	private ServerSocket serv;
	private Socket s;
	private DataInputStream in;
	private DataOutputStream out;
	private int controllerButtonInput;
	
	public EV3Receive() throws IOException {		
		this.serv = new ServerSocket(1111);
		this.s = serv.accept(); // Wait for laptop connection
		this.in = new DataInputStream(s.getInputStream());
		this.out = new DataOutputStream(s.getOutputStream());
	}
	
	public void run() {
		
		connectionState("Connected!");
		
		while(true) {
			try {
				controllerButtonInput = in.read();
			} catch (IOException e1) {
				
			}
			
			Delay.msDelay(10);
		}
	}
	
	public int getContorllerButton() {
		return controllerButtonInput;
	}
	
	public void closeSockets() throws IOException {
		s.close();
		serv.close();
	}
	
	public void connectionState(String msg) {
		LCD.drawString(msg, 0, 4);
	}
}
