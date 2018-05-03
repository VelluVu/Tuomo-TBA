package pack;

import java.io.File;
import java.io.IOException;

import lejos.hardware.Audio;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class TuomoMaini {

	public static void main(String[] args) {
		
		//RegulatedMotor koura = new EV3MediumRegulatedMotor(MotorPort.C);
		//RegulatedMotor nostaja = new EV3MediumRegulatedMotor(MotorPort.B);
		
		//RegulatedMotor lmotor = new EV3LargeRegulatedMotor(MotorPort.D);
		//RegulatedMotor rmotor = new EV3LargeRegulatedMotor(MotorPort.A);
		
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S1);
		
		// Koura tuomonKoura = new Koura(koura, nostaja);
		// AutoDrive automaatti = new AutoDrive(irSensor, lmotor, rmotor, tuomonKoura);
		TuomoBox musiikki = new TuomoBox();
		EV3Receive manuaali = new EV3Receive();
		//musiikki.tuomoBox();

		while (!Button.ESCAPE.isDown()) {
			int valinta = Button.waitForAnyPress();
			LCD.clear();
			LCD.drawString("Valitse", 5, 2);
			LCD.drawString("<Manual | Auto>", 1, 3);
			
			if(valinta == Button.ID_LEFT) {
				LCD.clear();
				try { manuaali.startManualControl(); } catch (IOException e) {}
			}
			
			LCD.refresh();
			Delay.msDelay(100);
		}

		// koura.close();
		// nostaja.close();
		// lmotor.close();
		// rmotor.close();
		irSensor.close();
		LCD.clear();
	}
}
