package pack;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class RemoteControlMain {

	private RegulatedMotor leftMotor;
	private RegulatedMotor rightMotor;
	private EV3IRSensor irSensor;
	// private IRDistance irDistance;
	private Motor m;
	
	
	public static void main(String[] args) {
		
		new RemoteControlMain();
		
	}
	
	public RemoteControlMain() {
		
		// Sensors and motors
		// REMEMBER TO CHECK PORTS!!!
		// Infrared: 	S1
		// Left motor: 	D
		// Right motor: A
		
		leftMotor  = new EV3LargeRegulatedMotor(MotorPort.D);
		rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		irSensor   = new EV3IRSensor(SensorPort.S1);
		// irDistance = new IRDistance(irSensor);
		m          = new Motor(leftMotor, rightMotor);
		
		// Start listening remote inputs
		remoteAndDistanceLoop();
		
		// Close ports and exit program
		irSensor.close();
		m.closeMotors();
		System.exit(0);
	}

	public void remoteAndDistanceLoop() {
		
		Sound.twoBeeps();
		
		while(!Button.ESCAPE.isDown()) {
			/*
			* 1 TOP-LEFT
			* 2 BOTTOM-LEFT
			* 3 TOP-RIGHT
			* 4 BOTTOM-RIGHT
			* 5 TOP-LEFT + TOP-RIGHT
			* 6 TOP-LEFT + BOTTOM-RIGHT
			* 7 BOTTOM-LEFT + TOP-RIGHT
			* 8 BOTTOM-LEFT + BOTTOM-RIGHT
			* 9 CENTRE/BEACON
			* 10 BOTTOM-LEFT + TOP-LEFT
			* 11 TOP-RIGHT + BOTTOM-RIGHT
			*/
			
			
			LCD.clear(3);
			
			// IR sensor distance checking disabled
			// due to conflicting readings with
			// remote inputs.
			
			//irSensor.getDistanceMode();
			//float distance = irDistance.getDistance();
			//Delay.msDelay(100);
			
			// Remote channel set to 4 on remote
			int remoteInput = irSensor.getRemoteCommand(3);
			
			switch(remoteInput) {
			
				case 1:
					// drive forward
					LCD.drawString("forward", 1, 3);
					//if(distance < 20) break;
					m.resetMotorSpeed();
					m.driveForward();
					break;
				case 2:
					// drive backward
					LCD.drawString("backward", 1, 3);
					m.resetMotorSpeed();
					m.driveBackward();
					break;
				case 3:
					// spin right
					LCD.drawString("spin right", 1, 3);
					m.resetMotorSpeed();
					m.spinRight();
					break;
				case 4:
					// spin left
					LCD.drawString("spin left", 1, 3);
					m.resetMotorSpeed();
					m.spinLeft();
					break;
				case 5:
					// turn right
					LCD.drawString("turn right", 1, 3);
					//if(distance < 20) break;
					m.turnRightForward();
					break;
				case 6:
					// turn left
					LCD.drawString("turn left", 1, 3);
					//if(distance < 20) break;
					m.turnLeftForward();
					break;
				case 7:
					// turn back right
					LCD.drawString("turn back right", 1, 3);
					m.turnRightBackward();
					break;
				case 8:
					// turn back left
					LCD.drawString("turn back left", 1, 3);
					m.turnLeftBackward();
					break;
				case 9:
					// BEEP
					LCD.drawString("BEEP", 4, 3);
					Sound.beep();
					// smallMotor.forward();
					break;
				default:
					// stop motors
					LCD.drawString("waiting input...", 1, 3);
					// smallMotor.stop();
					m.stopMotors();
					m.resetMotorSpeed();
					break;
			}
			LCD.refresh();
			Delay.msDelay(10);	
		}
	}
}
