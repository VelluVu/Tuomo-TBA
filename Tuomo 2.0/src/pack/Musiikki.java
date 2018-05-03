import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.hardware.Sound;

public class Musiikki extends Thread {
	//k‰ytt‰‰ tuomoBoxia
	private TuomoBox musiikki;
	boolean onkohan;
	File soundFile;

	public Musiikki (TuomoBox musiikki) {
		this.musiikki = musiikki;
		this.soundFile = new File("");
		this.onkohan = false;
	}
	public void run() {
		//soittaa kappaleita
		
		startSong();
		startSong();
		startSong();
		startSong();
		startSong();
		startSong();
		
	}
	//pausettaa musiikin "kehitys kesken"
	public void pause() {
		this.onkohan = false;
	}
	//jatkaa musiikkia "kehitys kesken"
	public void play() {
		this.onkohan = true;
	}
	//v‰lkytt‰‰ ledej‰
	public void menoLedit() {
		Button.LEDPattern(5);
	}
	//vaihtaa biisin "kehitys kesken"
	public void changeSong() {
		this.soundFile = musiikki.biisiValinta();
	}
	//aloittaa kappaleen soiton
	public void startSong() {
		changeSong();
		Sound.playSample(this.soundFile, 100);

	}
}
