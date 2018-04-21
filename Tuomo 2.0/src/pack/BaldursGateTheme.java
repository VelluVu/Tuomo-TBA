package pack;

import java.io.File;

import lejos.hardware.Audio;
import lejos.utility.Delay;

public class BaldursGateTheme {

	public BaldursGateTheme() {
		
	}
	private void playBGtheme(final Audio audio) {
		Thread t = new Thread() {
			@Override
			public void run() {
				audio.playSample(new File("BGtheme.wav"), 100);
			}
		};
		t.setDaemon(true);
		t.start();
		Delay.msDelay(6000);
		t.interrupt();
	}
}