package pack;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class AutoDrive {
	private EV3IRSensor irSensor;
	private IRDistance irDistance;
	private Motor motor;
	private static int drive = 1;

	public AutoDrive(EV3IRSensor irSensor, RegulatedMotor lMotor, RegulatedMotor rMotor) {
		this.irSensor = irSensor;
		irDistance = new IRDistance(irSensor);
		motor = new Motor(lMotor, rMotor);

	}

	public float checkDistance() {
		irSensor.getDistanceMode();
		float distance = irDistance.getDistance();
		return distance;

	}

	public int grab() {

		drive = 0;
		int spinR = 0;
		int spinL = 0;
		do {
			motor.spinRight();
			spinR++;
			Delay.msDelay(10);
		} while (checkDistance() <= 20);
		if ((checkDistance() >= 20 && spinR > 0) || (checkDistance() <= 20 && spinR > 0 && spinL > 0)) {
			do {
				motor.spinLeft();
				spinL++;
				Delay.msDelay(10);
			} while (checkDistance() <= 20);
		}
		return spinL++;
	}

	public void homing() {
		// drive = 1;
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
			LCD.drawInt(new Float(checkDistance()).intValue(), 8, 1);
			while (checkDistance() > 20 && drive >= 1) {
				homing();
			}
			while (checkDistance() <= 20 || drive == 0) {
				motor.stopMotors();
			}
		}
		irSensor.close();
		motor.closeMotors();
		Delay.msDelay(10);
		System.exit(0);
	}
}
