package pack;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * Koura class of the Lego mindstorm program.
 * Usefull claw methods for AutoDrive class and ManualDrive class.
 * 
 * @author Vellu
 * @version 2.0
 * @since 3.5.2018
 */

public class Koura {
	
	/**
	 *  Variables for claw manipulation.
	 */
	
	private RegulatedMotor koura;
	private RegulatedMotor nostaja;
	private boolean ote;
	private boolean nosto;
	private int kulma;
	private int kulma2;

	public Koura() {
		
		/**
		 * Initializes variables
		 */
		
		this.koura = new EV3MediumRegulatedMotor(MotorPort.C);
		this.nostaja = new EV3MediumRegulatedMotor(MotorPort.B);	
		this.ote = false;
		this.nosto = false;
		this.koura.setSpeed(500);
		this.kulma = 0;
		this.kulma2 = 0;

	}

	public boolean onkoOte() {
		return this.ote;
	}

	/**
	 * <h1>AUTOMATIC METHODS:</h1>
	 */
	
	/** 
	 * <h2>autoNosta method:</h2> 
	 * First clears screen row 0. 
	 * If have previously closed the claw and not raised the claw.
	 * Will lift the claw up for 85 degree "nostaja" motor turn.
	 * Saves the rotate amount in the kulma variable for later use.
	 * Prints the kulma amount on row 0.
	 * Sets the nosto boolean true.
	 */
	
	public void autoNosta() {
		LCD.clear(0);
		if (this.ote == true && this.nosto == false) {
			this.nostaja.rotate(85);
			this.kulma = this.nostaja.getTachoCount();
			LCD.drawString("" + kulma, 0, 0);
			this.nosto = true;
			Delay.msDelay(200);
		}
		this.nostaja.stop();
	}

	/**
	 *  <h2>autoLaske method:</h2>
	 *  First clears screen row 1.
	 *  If claw is closed and lifted up.
	 *  Will lower the claw for negative 85 degree "nostaja" motor turn.
	 *  Saves the rotate amount in the kulma variable.
	 *  Prints the kulma amount on row 1.
	 *  Sets nosto booolean to false.
	 */
	
	public void autoLaske() {
		LCD.clear(1);
		if (this.ote == true && this.nosto == true) {
			this.nostaja.rotate(-85);
			this.kulma = this.nostaja.getTachoCount();;
			LCD.drawString("" + kulma, 0, 1);
			this.nosto = false;
			Delay.msDelay(50);
		}
		this.nostaja.stop();
	}

	/**
	 *  <h2>autoKaappaa method:</h2>
	 *  Clears row 2 on screen.
	 *  If claw is not closed.
	 *  Rotates the koura motor negative 1880 degree.
	 *  >>	Motor closes the claw.
	 *  Saves the rotate amount in the kulma2 variable.
	 *  Prints the kulma2 amount on row 2.
	 *  Set the ote boolean true.
	 */
	
	public void autoKaappaa() {
		LCD.clear(2);
		if (this.ote == false) {
			this.koura.rotate(-1880);
			this.kulma2 = this.koura.getTachoCount();
			LCD.drawString("" + kulma2, 0, 2);
			this.ote = true;
			Delay.msDelay(50);
		}
		this.koura.stop();

	}

	/**
	 * <h2>autoIrtiTuomo method:</h2>
	 * Clears row 3 on screen.
	 * If claw is closed.
	 * Rotate motor koura for 1880 degree turn.
	 * >> Motor opens the claw.
	 * Saves rotate amount for kulma2 variable.
	 * prints turn amount on row 3.
	 * Sets the ote boolean false.
	 */

	public void autoIrtiTuomo() {
		LCD.clear(3);
		if (this.ote == true) {
			this.koura.rotate(1880);
			this.kulma2 = this.koura.getTachoCount();
			LCD.drawString("" + kulma2, 0, 3);
			this.ote = false;
			Delay.msDelay(50);
		}
		this.koura.stop();
	}

	/**
	 *  <h1>MANUAL METHODS:</h1>
	 */

	/**
	 * <h2>manuaaliNosta method:</h2>
	 * While button UP or whatever key chosen from controller and claw is closed.
	 * Raises the claw until tachocount is 85.
	 */
	
	public void manuaaliNosta() {
		while (Button.UP.isDown() && ote == true && this.nostaja.getTachoCount()<=85) {

			this.nostaja.forward();

		}
		this.nostaja.stop();
	}
	
	/**
	 * <h2>manuaaliLaske method:</h2>
	 * While button DOWN or whatever key chosen from controller and claw is closed.
	 * Lowers the claw until tachocount is negative 85.
	 */
	
	public void manuaaliLaske() {
		while (Button.DOWN.isDown() && ote == true && this.nostaja.getTachoCount()>=-85) {

			this.nostaja.backward();

		}
		this.nostaja.stop();
	}

	/**
	 * <h2>manuaaliKaappaa method:</h2>
	 * While button RIGHT or whatever key chosen from controller.
	 * Closes the claw until tachocount is negative 1880.
	 */
	
	public void manuaaliKaappaa() {

		while (Button.RIGHT.isDown() && this.koura.getTachoCount()>=-1880) {

			this.koura.backward();

		}
		this.koura.stop();
		this.ote = true;

	}

	/**
	 * <h2>manuaaliIrtiTuomo method:</h2>
	 * While button LEFT or whatever key chosen from controller.
	 * Opens the claw until tachocount is 1880.
	 */
	
	public void manuaaliIrtiTuomo() {

		if (Button.LEFT.isDown() && this.koura.getTachoCount()<=1880) {

			this.koura.forward();

		}
		this.koura.stop();
		this.ote = false;
	}
	
	/**
	 * <h2>close</h2>
	 * Closes both claw motors, koura and nostaja.
	 */
	
	public void close() {
		this.koura.close();
		this.nostaja.close();
	}
}
