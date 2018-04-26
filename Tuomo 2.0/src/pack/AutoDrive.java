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
	private Koura koura;
	private static int drive = 1;

	public AutoDrive(EV3IRSensor irSensor, RegulatedMotor lMotor, RegulatedMotor rMotor, Koura tuomonKoura) {
		this.irSensor = irSensor;

		irDistance = new IRDistance(irSensor);
		motor = new Motor(lMotor, rMotor);
		this.koura = tuomonKoura;
	}

	public float checkDistance() {
		irSensor.getDistanceMode();
		float distance = irDistance.getDistance();
		return distance;
	}

	public void grab() {
		drive = 0;
		this.koura.autoKaappaa();
		this.koura.autoNosta();
		motor.driveBackward();
		Delay.msDelay(500);
		motor.stopMotors();
		this.koura.autoLaske();
		this.koura.autoIrtiTuomo();
	}

	public void run() {
		// int adjust = 0;
		while (!Button.ESCAPE.isDown()) {
			LCD.clear(0);
			LCD.drawInt(new Float(checkDistance()).intValue(), 8, 1);
			if (checkDistance() > 40 && drive >= 1) {
				homing();
			} else if (checkDistance() > 27 && checkDistance() <= 40 && drive >= 1) {
				motor.driveForward();
			} else if (checkDistance() <= 27) {
				motor.stopMotors();
				grab();
				// adjust = 1;
			} /*
				 * else if (adjust == 1) { grab(); }
				 */
		}
		irSensor.close();
		motor.closeMotors();
		Delay.msDelay(10);
		System.exit(0);
	}

	public void homing() {
		drive++;
		Delay.msDelay(10);
		if (drive < 100) {
			motor.driveForward();
		} else if (drive >= 100 && drive < 140) {
			motor.spinLeft();
		} else if (drive >= 140 && drive < 220) {
			motor.spinRight();
		} else if (drive >= 220 && drive < 260) {
			motor.spinLeft();
		} else {
			drive = 1;
		}

	}
	// esineen mittauksen metodit
	// public static int getSpin() {
	// return spin;
	// }
	//
	// public static void setSpin(int spin) {
	// AutoDrive.spin = spin;
	// }
	// public void calculate() {

	// switch (adjust) {
	// case 1:
	// adjustRight();
	// Delay.msDelay(1000);
	// adjust++;
	// break;
	// case 2:
	// adjustLeft();
	// Delay.msDelay(1000);
	// adjust++;
	// break;
	// case 3:
	// adjustMiddle();
	// Delay.msDelay(1000);
	// adjust++;
	// break;
	// case 4:
	// motor.stopMotors();
	// break;
	// }
	// }

	// public void adjustRight() {
	// do {
	// motor.spinRight();
	// } while (checkDistance() <= 27);
	//
	// }

	// public void adjustLeft() {
	// do {
	// motor.spinLeft();
	// setSpin(getSpin() + 1);
	// Delay.msDelay(1);
	// } while (checkDistance() <= 27);
	//
	// }
	//
	// public void adjustMiddle() {
	// int x = 0;
	// do {
	// motor.spinRight();
	// Delay.msDelay(getSpin() / 2);
	// x++;
	// } while (x < 1);
	// }

}
