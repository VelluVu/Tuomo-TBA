package pack;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Koura {
	//muuttujia
	private RegulatedMotor koura;
	private RegulatedMotor nostaja;
	private boolean ote;
	private boolean nosto;
	private int kulma;
	private int kulma2;

	public Koura (RegulatedMotor koura, RegulatedMotor nostaja) {
		this.koura = koura;
		this.nostaja = nostaja;
		this.ote = false;
		this.nosto = false;
		this.koura.setSpeed(500);
		this.kulma = 0;
		this.kulma2 = 0;

	}
	public boolean onkoOte () {
		return ote;
	}

	//AUTOMAATTI METODIT:

	public void autoNosta() {
		//LCD.drawString("" + kulma, 0 ,0);
		if (ote == true && nosto == false) {
			//nostaja.forward();
			nostaja.rotate(85);

			//LCD.clear(0);
			//LCD.drawString(""+kulma,0,0);

			//Delay.msDelay(1150);
			nosto = true;
			Delay.msDelay(200);
		}
		nostaja.stop();
	}
	public void autoLaske() {
		//LCD.drawString("" + kulma, 0 ,1);
		if (ote == true && nosto == true) {
			//nostaja.backward();
			nostaja.rotate(-85);

			//LCD.clear(1);
			//LCD.drawString(""+kulma,0,1);

			//Delay.msDelay(1150);
			nosto = false;
			Delay.msDelay(50);
		}
		nostaja.stop();
	}
	//ottaa esineen haltuun ja tallentaa sen ote booleaniin
	public void autoKaappaa() {
		//LCD.drawString("" + kulma2, 0 ,2);
		if (ote == false) {

			//koura.backward();
			koura.rotate(-1880);

			//LCD.clear(2);
			//LCD.drawString(""+kulma2,0,2);
			//Delay.msDelay(3600);
			ote = true;
			Delay.msDelay(50);
		}
		koura.stop();

	}
	//p‰‰st‰‰ esineen irti ja tallentaa sen ote booleaniin
	public void autoIrtiTuomo() {
		//LCD.drawString("" + kulma2, 0 ,3);
		if (ote == true) {

			//koura.forward();
			koura.rotate(1880);

			//LCD.clear(3);
			//LCD.drawString(""+kulma2,0,3);
			//Delay.msDelay(3600);
			ote = false;
			Delay.msDelay(50);
		}
		koura.stop();
	}

	// MANUAALI METODIT:

	//tekee niinku metodin nimi sanoo ja ote pit‰‰ olla
	public void manuaaliNosta() {
		while (Button.UP.isDown() && ote == true) {

			koura.forward();

		}
		koura.stop();
	}
	public void manuaaliLaske() {
		while (Button.DOWN.isDown() && ote == true) {

			koura.backward();

		}
		koura.stop();
	}
	//niin kauan kun valittua n‰pp‰int‰ painetaan koura kiristyy
	public void manuaaliKaappaa() {

		while (Button.RIGHT.isDown()) {

			koura.backward();

		}
		koura.stop();

	}
	//niin kauan kun valittua n‰pp‰int‰ painetaan koura lˆys‰‰
	public void manuaaliIrtiTuomo() {

		if (Button.LEFT.isDown()) {

			koura.forward();

		}
		koura.stop();
	}
}	

