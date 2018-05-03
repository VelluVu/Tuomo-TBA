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
		
		TuomoBox musiikki = new TuomoBox();
		Musiikki music = new Musiikki(musiikki);

		
		
		
		while (!Button.ESCAPE.isDown()) {
			
			
			LCD.clear();
			LCD.drawString("Valitse", 5, 2);
			LCD.drawString("<Manual | Auto>", 1, 3);
			
			Button.waitForAnyPress();
			if (Button.LEFT.isDown()) {
				LCD.clear();
				EV3Receive manual = new EV3Receive();
				manual.startManualControl();
				
				
			} else if (Button.RIGHT.isDown()) {
				/*EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S1);
				RegulatedMotor koura = new EV3MediumRegulatedMotor(MotorPort.C);
				RegulatedMotor nostaja = new EV3MediumRegulatedMotor(MotorPort.B);
				RegulatedMotor lmotor = new EV3LargeRegulatedMotor(MotorPort.D);
				RegulatedMotor rmotor = new EV3LargeRegulatedMotor(MotorPort.A);
				Motor motor = new Motor(lmotor, rmotor);
				Koura tuomonKoura = new Koura(koura, nostaja);*/
				
				AutoDrive automaatti = new AutoDrive();
				automaatti.startAuto();
				
			} else if (Button.UP.isDown()) {
				try {
					LCD.clear(5);
				music.changeSong();
				music.start();
				}
				catch (Exception e)
				{
					
					LCD.drawString("Vituiks meni1", 0, 5);

				}
			} else if (Button.DOWN.isDown()) {
				try {
					LCD.clear(5);
				music.interrupt();
				}
				catch (Exception e) 
				{
				
					LCD.drawString("Vituiks meni2", 0, 5);
				}
			}
			LCD.refresh();
			Delay.msDelay(100);
			
		}
		
		music.interrupt();
		music.stop();
		/*koura.close();
		nostaja.close();
		lmotor.close();
		rmotor.close();
		irSensor.close();*/
		LCD.clear();
	}
}
