package pack;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.EncoderMotor;
import lejos.utility.Delay;

public class EV3Receive {
	
	private Socket s;
	private ServerSocket serv;
	private DataInputStream in;
	
	private EncoderMotor emLeft, emRight;
	private EV3MediumRegulatedMotor clawMotor, liftMotor;
	
	public EV3Receive() {
		
		this.emLeft = new UnregulatedMotor(MotorPort.D);
		this.emRight = new UnregulatedMotor(MotorPort.A);
		
		this.clawMotor = new EV3MediumRegulatedMotor(MotorPort.C);
		this.liftMotor = new EV3MediumRegulatedMotor(MotorPort.B);
		
	}

	
	public void startManualControl() throws IOException {
		
		try { this.serv = new ServerSocket(1111); } catch (IOException e) {}
		LCD.drawString("Waiting PC...", 0, 2);
		try { this.s = serv.accept(); } catch (IOException e) {}
		
		try { this.in = new DataInputStream(s.getInputStream()); } catch (IOException e) {}
		
		LCD.clear();
		LCD.drawString("Waiting input", 2, 2);
		
		clawMotor.setSpeed(720);
		liftMotor.setSpeed(80);
		boolean isUp = false;
		
		while(!Button.ESCAPE.isDown()) {
			
			String controllerInput = in.readUTF();
			String[] values = controllerInput.split(" ");
			int leftMotor = Integer.parseInt(values[0]);
			int rightMotor = Integer.parseInt(values[1]);
			
			drive(leftMotor, rightMotor);
			
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
		
		s.close();
		serv.close();
		liftMotor.close();
		clawMotor.close();
	}
	
	public void drive(int leftM, int rightM) {
		int leftPower = leftM;
		int rightPower = rightM;
		
		emLeft.setPower(leftPower);
		emRight.setPower(rightPower);
	}

}









