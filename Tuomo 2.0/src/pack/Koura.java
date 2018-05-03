package pack;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Koura {
	// muuttujia
	private RegulatedMotor koura;
	private RegulatedMotor nostaja;
	private boolean ote;
	private boolean nosto;
	private int kulma;
	private int kulma2;

	public Koura() {
		
		this.koura = new EV3MediumRegulatedMotor(MotorPort.C);
		this.nostaja = new EV3MediumRegulatedMotor(MotorPort.B);
		
		this.ote = false;
		this.nosto = false;
		this.koura.setSpeed(500);
		this.kulma = 0;
		this.kulma2 = 0;
		// boolean jota voi hyˆdynt‰‰

	}

	public boolean onkoOte() {
		return this.ote;
	}

	// AUTOMAATTI METODIT:
	// nostaa esineen
	public void autoNosta() {
		LCD.clear(0);
		// LCD.drawString("" + kulma, 0 ,0);
		if (this.ote == true && this.nosto == false) {
			// nostaja.forward();
			this.nostaja.rotate(85);
			this.kulma = this.nostaja.getTachoCount();
			// LCD.clear(0);
			LCD.drawString("" + kulma, 0, 0);

			// Delay.msDelay(1150);
			this.nosto = true;
			Delay.msDelay(200);
		}
		this.nostaja.stop();
	}

	// laskee esineen
	public void autoLaske() {
		LCD.clear(1);
		// LCD.drawString("" + kulma, 0 ,1);
		if (this.ote == true && this.nosto == true) {
			// nostaja.backward();
			this.nostaja.rotate(-85);
			this.kulma = this.nostaja.getTachoCount();

			// LCD.clear(1);
			LCD.drawString("" + kulma, 0, 1);

			// Delay.msDelay(1150);
			this.nosto = false;
			Delay.msDelay(50);
		}
		this.nostaja.stop();
	}

	// ottaa esineen haltuun ja tallentaa sen ote booleaniin
	public void autoKaappaa() {
		// LCD.drawString("" + kulma2, 0 ,2);
		LCD.clear(2);
		if (this.ote == false) {

			// koura.backward();
			this.koura.rotate(-1880);
			this.kulma2 = this.koura.getTachoCount();

			// LCD.clear(2);
			LCD.drawString("" + kulma2, 0, 2);
			// Delay.msDelay(3600);
			this.ote = true;
			Delay.msDelay(50);
		}
		this.koura.stop();

	}

	// p‰‰st‰‰ esineen irti ja tallentaa sen ote booleaniin
	public void autoIrtiTuomo() {
		// LCD.drawString("" + kulma2, 0 ,3);
		LCD.clear(3);
		if (this.ote == true) {

			// koura.forward();
			this.koura.rotate(1880);
			this.kulma2 = this.koura.getTachoCount();
			// LCD.clear(3);
			LCD.drawString("" + kulma2, 0, 3);
			// Delay.msDelay(3600);
			this.ote = false;
			Delay.msDelay(50);
		}
		this.koura.stop();
	}

	// MANUAALI METODIT:

	// tekee niinku metodin nimi sanoo ja ote pit‰‰ olla
	public void manuaaliNosta() {
		while (Button.UP.isDown() && ote == true) {

			this.koura.forward();

		}
		this.koura.stop();
	}

	public void manuaaliLaske() {
		while (Button.DOWN.isDown() && ote == true) {

			this.koura.backward();

		}
		this.koura.stop();
	}

	// niin kauan kun valittua n‰pp‰int‰ painetaan koura kiristyy
	public void manuaaliKaappaa() {

		while (Button.RIGHT.isDown()) {

			this.koura.backward();

		}
		this.koura.stop();

	}

	// niin kauan kun valittua n‰pp‰int‰ painetaan koura lˆys‰‰
	public void manuaaliIrtiTuomo() {

		if (Button.LEFT.isDown()) {

			this.koura.forward();

		}
		this.koura.stop();
	}
	public void close() {
		this.koura.close();
		this.nostaja.close();
	}
}
