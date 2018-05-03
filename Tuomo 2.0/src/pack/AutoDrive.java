package pack;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
//import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class AutoDrive {
	// Määrittää moottorin, kouran, sensorin ja etäisyyden mittaamisen ja arvon
	// jolla mitataan ajon pituutta
	private EV3IRSensor irSensor;
	private IRDistance irDistance;
	private Motor motor;
	private Koura kKoura;
	private RegulatedMotor rMotor;
	private RegulatedMotor lMotor;
	//private RegulatedMotor koura;
	//private RegulatedMotor nostaja;
	private static int drive = 1;

	public AutoDrive() {
		
		this.lMotor = new EV3LargeRegulatedMotor(MotorPort.D);
		this.rMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		
		this.irSensor = new EV3IRSensor(SensorPort.S1);
		
		this.irDistance = new IRDistance(irSensor);
		this.motor = new Motor(lMotor,rMotor);
		this.kKoura = new Koura();
	}

	// Etäisyyden mittaamisen palautus arvo
	public float checkDistance() {
		irSensor.getDistanceMode();
		float distance = irDistance.getDistance();
		return distance;
	}

	// Ajaa esineeseen kiinni nostaa ilmaan vie sen johonkin ja laskee sen
	public void grab() {
		drive = 0;
		motor.driveForward();
		Delay.msDelay(50);
		motor.stopMotors();
		this.kKoura.autoKaappaa();
		this.kKoura.autoNosta();
		motor.spinLeft();
		Delay.msDelay(1000);
		motor.stopMotors();
		motor.driveForward();
		Delay.msDelay(2000);
		motor.stopMotors();
		this.kKoura.autoLaske();
		this.kKoura.autoIrtiTuomo();
		irSensor.close();
		motor.closeMotors();
		this.kKoura.close();
		/*
		 * motor.driveBackward(); Delay.msDelay(500); motor.stopMotors();
		 * this.koura.autoLaske(); this.koura.autoIrtiTuomo();
		 */
	}

	// ajaa ohjelman
	public void startAuto() {
		// int adjust = 0;
		while (!Button.ESCAPE.isDown()) {
			LCD.clear(0);
			LCD.drawInt(new Float(checkDistance()).intValue(), 8, 1);
			// katsoo etäisyyden ja ajon ajan määrittävän arvon
			if (checkDistance() > 40 && drive >= 1) {
				homing();
				// katsoo etäisyyden pysähtyy ja aloittaa esineen nostamisen funktion
			} else if (checkDistance() <= 40) {
				motor.stopMotors();
				grab();
				// adjust = 1;
			}
				/*
			}
				 * else if (adjust == 1) { grab(); }
				 */
		}

	}

	// ajaa ja etsii etsii esinettä
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
	// } while (checkDistance() == 27);
	//
	// }

	// public void adjustLeft() {
	// do {
	// motor.spinLeft();
	// setSpin(getSpin() + 1);
	// Delay.msDelay(1);
	// } while (checkDistance() == 27);
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
