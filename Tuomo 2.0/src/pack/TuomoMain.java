package pack;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class TuomoMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*RegulatedMotor koura = new EV3MediumRegulatedMotor(MotorPort.C);
		RegulatedMotor nostaja = new EV3MediumRegulatedMotor(MotorPort.B);
		RegulatedMotor lmotor = new EV3LargeRegulatedMotor(MotorPort.D);
		RegulatedMotor rmotor = new EV3LargeRegulatedMotor(MotorPort.A);
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S1);
		Koura tuomonKoura = new Koura(koura, nostaja);
		AutoDrive automaatti = new AutoDrive(irSensor, lmotor, rmotor, tuomonKoura);*/
		TuomoBox musiikki = new TuomoBox();
		Musiikki music = new Musiikki(musiikki);
		EV3Receive manual = new EV3Receive();

		
		
		
		while (!Button.ESCAPE.isDown()) {
			
			
			LCD.clear();
			LCD.drawString("Valitse", 5, 2);
			LCD.drawString("<Manual | Auto>", 1, 3);
			
			Button.waitForAnyPress();
			if (Button.LEFT.isDown()) {
				LCD.clear();
				try { manual.startManualControl(); } catch (IOException e) {}
				//tuomonKoura.autoKaappaa();
				//tuomonKoura.autoNosta();
				//manuaali.ajo();
				
				
			} else if (Button.RIGHT.isDown()) {
				AutoDrive.startAuto();
				
			} else if (Button.UP.isDown()) {
				music.start();
			} else if (Button.DOWN.isDown()) {
				music.interrupt();
			}
			LCD.refresh();
			Delay.msDelay(100);
			
		}
		
		
		/*koura.close();
		nostaja.close();
		lmotor.close();
		rmotor.close();
		irSensor.close();*/
		LCD.clear();
	}
}
