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

	public void grab() {

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
	}

	public void homing() {
		drive = 1;
		int div = 0;

		switch (drive) {
		case 1:
			if (div < 100 && checkDistance() > 20) {
				motor.driveForward();
				div++;
				Delay.msDelay(10);
			} else {
				drive++;

			}
			break;
		case 2:

			if (div >= 100 && div < 200 && checkDistance() > 20) {
				motor.spinLeft();
				div++;
				Delay.msDelay(5);
			} else {
				drive++;

			}
			break;
		case 3:

			if (div >= 200 && div < 300 && checkDistance() > 20) {
				motor.spinRight();
				div++;
				Delay.msDelay(10);
			} else {
				drive++;

			}
			break;
		case 4:

			if (div <= 300 && div < 400 && checkDistance() > 20) {
				motor.spinLeft();
				div++;
				Delay.msDelay(5);
			} else {
				drive++;

			}
			break;
		case 5:
			drive = 1;
			div = 0;
			break;
		}
	}

	public void run() {
		while (!Button.ESCAPE.isDown()) {
			LCD.clear(0);
			LCD.drawInt(new Float(checkDistance()).intValue(), 8, 1);
			if (checkDistance() > 20 && drive >= 1) {
				homing();
			} else {

				motor.stopMotors();
			}
		}
		irSensor.close();
		motor.closeMotors();
		Delay.msDelay(10);
		System.exit(0);
	}
}
