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

/**
 * This is Main class of the Lego Mindstorm program Tuomo 2.0, 
 * which uses the manualDrive with ps3 controller or the autoDrive
 * to get close to the object and grab it with the claw
 * and hopefully move it towards the user.
 * 
 * Also can play theme songs of 9 different games:
 * 
 * Monkey Island, Dragon Age, DooM, Super Mario, Dark Souls,
 * Baldur's Gate, Half Life, Mortal Kombat, Nyan Cat.
 * 
 * @author TBA
 * @version 2.0
 * @since 3.5.2018
 */

public class TuomoMain {
	
	/**
	 * This is main method of program, sets up menu where you can choose mode
	 * left is for manual drive and right is for auto drive.
	 * Up button starts music and Down button stops it.
	 * @param args Unused
	 * @return Nothing
	 */

	public static void main(String[] args) {

		TuomoBox musiikki = new TuomoBox();
		Musiikki music = new Musiikki(musiikki);
		
		/*
		 * Main menu, exit with escape button, 
		 * clears screen and prints out mode choises.
		 */
	
		
		while (!Button.ESCAPE.isDown()) {

			LCD.clear();
			LCD.drawString("Valitse", 5, 2);
			LCD.drawString("<Manual | Auto>", 1, 3);
			LCD.drawString("UP for music", 1, 4);
			LCD.drawString("DOWN stop music", 1, 5);

			Button.waitForAnyPress();

			if (Button.LEFT.isDown()) {
				LCD.clear();
				RemoteContorl manual = new RemoteContorl();
				manual.startManualControl();


			} else if (Button.RIGHT.isDown()) {
				LCD.clear();
				AutoDrive automaatti = new AutoDrive();
				automaatti.startAuto();

			} else if (Button.UP.isDown()) {
				try {
					LCD.clear(6);
					music.start();
					LCD.drawString("Playing", 0, 6);
				}
				catch (Exception e)
				{

					LCD.drawString("error 1", 0, 6);

				}
			} else if (Button.DOWN.isDown()) {
				try {
					LCD.clear(6);
					music.interrupt();
					LCD.drawString("Stopped", 0, 6);
				}
				catch (Exception e) 
				{

					LCD.drawString("error 2", 0, 6);
				}
			}
			LCD.refresh();
			Delay.msDelay(100);
		}

		music.interrupt();
		music.stop();
		LCD.clear();
	}
}
