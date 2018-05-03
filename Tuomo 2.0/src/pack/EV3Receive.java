package pack;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class EV3Receive {
	
	private Socket s;
	private ServerSocket serv;
	private DataInputStream in;
	
	private EV3LargeRegulatedMotor left, right;
	private EV3MediumRegulatedMotor clawMotor, liftMotor;
	
	public EV3Receive() {
		
		this.left = new EV3LargeRegulatedMotor(MotorPort.D);
		this.right = new EV3LargeRegulatedMotor(MotorPort.A);
		
		this.clawMotor = new EV3MediumRegulatedMotor(MotorPort.C);
		this.liftMotor = new EV3MediumRegulatedMotor(MotorPort.B);
		
	}

	
	public void startManualControl() {
		
		// Tekee uuden socketin ja jää odottamaan että
		// PC yhdistää IP osoitteeseen 10.0.1.1 ja porttiin 1111.
		try { this.serv = new ServerSocket(1111); } catch (IOException e) {}
		LCD.drawString("Waiting PC...", 0, 2);
		try { this.s = serv.accept(); } catch (IOException e) {}
		
		// Avaa data input streamin
		try { this.in = new DataInputStream(s.getInputStream()); } catch (IOException e) {}
		
		LCD.clear();
		LCD.drawString("Waiting input", 2, 2);
		
		// Asettaa kouran moottorin tehon 100%:iin ja 
		// nosto moottorin tehon sopivaksi todettuun tehoon.
		clawMotor.setSpeed(720);
		liftMotor.setSpeed(80);
		boolean isUp = false;
		
		while(!Button.ESCAPE.isDown()) {
			
			// Lukee datan jonka PC lähettää ja liittää sen muuttujaan.
			String controllerInput = "";
			try { controllerInput = in.readUTF(); } catch (IOException e) {}
			
			// Data on muotoa "'teho' 'teho' 'ohjaimen nappi'", esim. "100 80 1".
			// Split metodi leikkaa stringin kolmeen osaan tekee niistä Arrayn.
			String[] values = controllerInput.split(" ");
			
			// Liitetään teho arvot omiin muuttujiin ja muutetaan ne stringistä integeriksi.
			float leftMotor = Float.parseFloat(values[0]);
			float rightMotor = Float.parseFloat(values[1]);
			
			// Moottorien ajo metodi.
			drive(leftMotor, rightMotor);
			
			// Liitetään ohjaimen näppäinkomento omaan muuttujaansa ja muutetaan integeriksi.
			int controllerButtons = Integer.parseInt(values[2]);
			int clawTacho = clawMotor.getTachoCount();
			int liftTacho = liftMotor.getTachoCount();
			
			switch(controllerButtons) {
			case 1:
				if(clawTacho > -1780) {
					clawMotor.backward();					
				} else {
					clawMotor.stop(true);
				}
				break;
			case 2:
				if(clawTacho < -85) {
					clawMotor.forward();					
				} else {
					clawMotor.stop(true);
				}
				break;
			case 3:
				if(!isUp) {
					if(liftTacho <= 73) {
						liftMotor.forward();					
					} else {
						liftMotor.stop(true);
					}					
				}
				
				if(isUp) {
					if(liftTacho >= 1) {
						liftMotor.backward();
					} else {
						liftMotor.stop(true);
					}
				}
				break;
			default:
				if(liftTacho >= 73 && !isUp) isUp = true;
				if(liftTacho <= 1 && isUp) isUp = false;
				
				
				clawMotor.stop(true);
				liftMotor.stop(true);
			}
			
			
			LCD.clear(4);
			LCD.clear(5);
			LCD.clear(6);
			LCD.clear(7);
			LCD.drawString("Left: " + leftMotor + "%", 0, 4);
			LCD.drawString("Right: " + rightMotor + "%", 0, 5);
			LCD.drawString("Button: " + controllerButtons, 0, 6);
			LCD.drawString("CT:" + clawTacho + " LT:" + liftTacho, 0, 7);
			LCD.refresh();
			
			Delay.msDelay(80);
		}
		
		// Suljetaan bluetooth yhteys, socketti ja moottori portit.
		try {
			s.close();
			serv.close();
		} catch (IOException e) {}
		left.close();
		right.close();
		liftMotor.close();
		clawMotor.close();
	}
	
	// Asettaa moottorien tehot ohjaimen arvojen mukaan.
	public void drive(float leftM, float rightM) {
		float leftPower = (leftM / 100) * 720;
		float rightPower = (rightM / 100) * 720;
		
		left.setSpeed(leftPower);
		right.setSpeed(rightPower);
	}

}









