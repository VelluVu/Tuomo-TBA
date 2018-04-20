package pack;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class AutoDrive extends Thread {
	private RegulatedMotor lMotor;
	private RegulatedMotor rMotor;
	private EV3IRSensor irSensor;
	private IRDistance irDistance;
	private Motor motor;
	private static int drive = 1;

	public static void main(String[] args) {
		new AutoDrive();
	}

	public AutoDrive() {
		lMotor = new EV3LargeRegulatedMotor(MotorPort.D);
		rMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		irSensor = new EV3IRSensor(SensorPort.S1);
		irDistance = new IRDistance(irSensor);
		motor = new Motor(lMotor, rMotor);

	}

	public void grab() {
		irSensor.getDistanceMode();
		float distance = irDistance.getDistance();

		drive = 0;
		int spinR = 0;
		int spinL = 0;
		do {
			motor.spinRight();
			spinR++;
			Delay.msDelay(10);
		} while (distance <= 20);
		if ((distance >= 20 && spinR > 0) || (distance <= 20 && spinR > 0 && spinL > 0)) {
			do {
				motor.spinLeft();
				spinL++;
				Delay.msDelay(10);
			} while (distance <= 20);
		}

	}

	public void homing() {
		drive = 1;
		switch (drive) {

		case 1:
			motor.driveForward();
			drive++;
			Delay.msDelay(2000);
			break;
		case 2:
			motor.spinLeft();
			drive++;
			Delay.msDelay(500);
			break;
		case 3:
			motor.spinRight();
			drive++;
			Delay.msDelay(1000);
			break;
		case 4:
			motor.spinLeft();
			drive++;
			Delay.msDelay(500);
			break;
		case 5:
			drive = 1;
			break;
		}
	}

	public void run() {
		while (!Button.ESCAPE.isDown()) {
			LCD.clear(0);
			irSensor.getDistanceMode();
			float distance = irDistance.getDistance();
			Delay.msDelay(1);
			LCD.drawInt(new Float(distance).intValue(), 8, 1);
			if (distance > 20 && drive >= 1) {
				homing();
			}
			if (distance <= 20) {
				grab();
			}
		}
	}
}
