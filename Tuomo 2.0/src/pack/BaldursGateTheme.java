package pack;

import java.io.File;

import lejos.hardware.Audio;
import lejos.utility.Delay;

public class BaldursGateTheme extends Thread {
	private File soundFile;
	public BaldursGateTheme (File soundFile){
		this.soundFile = soundFile;
	}
	public void playBGtheme() {
		Thread t = new Thread() {
			@Override
			public void run() {
				
				Sound.playSample(soundFile, 100);
				
			}
		};
		t.setDaemon(true);
		t.start();
	}
}