package pack;

import java.io.File;

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
		
		
		
		while (!Button.ESCAPE.isDown()) {
			
			Button.waitForAnyPress();
			
			if (Button.LEFT.isDown()) {
				
				//tuomonKoura.autoKaappaa();
				//tuomonKoura.autoNosta();
				//manuaali.ajo();
				
				
			} else if (Button.RIGHT.isDown()) {
				//tuomonKoura.autoLaske();
				//tuomonKoura.autoIrtiTuomo();
				//automaatti.run();	
				
			} else if (Button.UP.isDown()) {
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
		}
		
		music.stop();
		/*koura.close();
		nostaja.close();
		lmotor.close();
		rmotor.close();
		irSensor.close();*/
		LCD.clear();
	}
}
