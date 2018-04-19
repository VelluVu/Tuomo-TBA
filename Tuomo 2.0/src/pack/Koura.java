import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Koura {
	//muuttujia
	private RegulatedMotor koura;
	private boolean ote;

	public Koura (RegulatedMotor koura) {
		this.koura = koura;
		this.ote = false;
		this.koura.setSpeed(500);
		
	}
	//ottaa esineen haltuun ja tallentaa sen ote booleaniin
	public void autoKaappaa() {
		
		if (ote == false) {
			
			koura.backward();
			Delay.msDelay(4000);
			ote = true;
			Delay.msDelay(200);
		}
		koura.stop();
		
	}
	//p‰‰st‰‰ esineen irti ja tallentaa sen ote booleaniin
	public void autoIrtiTuomo() {
		
		if (ote == true) {

			koura.forward();
			Delay.msDelay(4000);
			ote = false;
			Delay.msDelay(200);
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
