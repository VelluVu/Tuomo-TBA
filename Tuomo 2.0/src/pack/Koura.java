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

	public Koura (RegulatedMotor koura, RegulatedMotor nostaja) {
		this.koura = koura;
		this.nostaja = nostaja;
		this.ote = false;
		this.nosto = false;
		this.koura.setSpeed(500);
		
	}
	public boolean onkoOte () {
		return ote;
	}
	public void nosta() {
		if (ote == true && nosto == false) {
			nostaja.forward();
			nosto = true;
			Delay.msDelay(1000);
		} else {
			nostaja.stop();
		}
		nostaja.stop();
	}
	public void laske() {
		if (ote == false && nosto == true) {
			nostaja.backward();
			nosto = false;
			Delay.msDelay(1000);
		} else {
			nostaja.stop();
		}
		nostaja.stop();
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
	//p��st�� esineen irti ja tallentaa sen ote booleaniin
	public void autoIrtiTuomo() {
		
		if (ote == true) {

			koura.forward();
			Delay.msDelay(4000);
			ote = false;
			Delay.msDelay(200);
		}
		koura.stop();
	}
	//niin kauan kun valittua n�pp�int� painetaan koura kiristyy
	public void manuaaliKaappaa() {
		
		while (Button.RIGHT.isDown()) {
			
			koura.backward();
			
		}
		koura.stop();
		
	}
	//niin kauan kun valittua n�pp�int� painetaan koura l�ys��
	public void manuaaliIrtiTuomo() {
		
		if (Button.LEFT.isDown()) {

			koura.forward();
			
		}
		koura.stop();
	}
}	

