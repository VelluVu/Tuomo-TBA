package pack;

import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.hardware.Sound;

/**
 * Musiikki class of the Lego mindstorm program.
 * Uses thread to play music.
 * 
 * @author Veli-Matti Vuoti
 * @version 2.0
 * @since 3.5.2018
 */

public class Musiikki extends Thread {
	private TuomoBox musiikki;
	boolean onkohan;
	File soundFile;

	public Musiikki (TuomoBox musiikki) {
		this.musiikki = musiikki;
		this.soundFile = musiikki.biisiValinta();
		this.onkohan = false;
	}
	
	/**
	 * This method is used to start music by calling startSong method.
	 */
	
	public void run() {
		
		startSong();
		
	}
	
	/**
	 * This method changes onkohan boolean status false.
	 * Planned to use with pausing music.
	 */
	
	public void pause() {
		this.onkohan = false;
	}
	
	/**
	 * This method changes onkohan boolean to true.
	 * Planned to use with playing music.
	 */
	
	public void play() {
		this.onkohan = true;
	}
	
	/**
	 * This method blinks leds 5 times.
	 * Visual testing.
	 */
	
	public void menoLedit() {
		Button.LEDPattern(5);
	}
	
	/**
	 * This method initializes new random song in to soundFile variable. 
	 */
	
	public void changeSong() {
		
		this.soundFile = musiikki.biisiValinta();
	}
	
	/**
	 * This method calls changeSong to change song and then plays that file 100% vol.
	 */
	
	public void startSong() {
		this.changeSong();
		Sound.playSample(this.soundFile, 100);

	}
}
