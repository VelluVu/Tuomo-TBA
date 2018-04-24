package pack;

import java.io.File;

import lejos.hardware.Audio;
import lejos.utility.Delay;

public class TuomoBox extends Thread {
	final File soundFileBG;
	final File soundFileNyan;
	final File soundFileMario;
	final File soundFileDooM;
	final File soundFileMonkey;
	final File soundFileDS;
	final File soundFileMK;
	final File soundFileHF;
	final File soundFileDA;
	File soundFile;
	Random random;
	int biisiNro;

	public TuomoBox () {
		this.soundFileBG = new File("BGthememusic.wav");
		this.soundFileNyan = new File("NyanCat.wav");
		this.soundFileMario = new File("SuperMario.wav");
		this.soundFileDooM = new File("DooMtheme.wav");
		this.soundFileMonkey = new File("MItheme.wav");
		this.soundFileDS = new File("DarkSouls.wav");
		this.soundFileMK = new File("MKtheme.wav");
		this.soundFileHF = new File("HalfLife.wav");
		this.soundFileDA = new File("DAtheme.wav");
		this.random = new Random();
		this.biisiNro = random.nextInt(9);

	}
	public void tuomoBox() {
		this.biisiValinta();
		Thread t = new Thread(){
			@Override
			public void run() {

				Sound.playSample(soundFile, 100);

			}
		};
		t.setDaemon(true);
		t.start();
	}
	public void biisiValinta() {
		
		switch (this.biisiNro) {
		case 0:
			this.soundFile = this.soundFileBG;
		case 1:
			this.soundFile = this.soundFileDA;
		case 2:
			this.soundFile = this.soundFileDooM;
		case 3:
			this.soundFile = this.soundFileDS;
		case 4:
			this.soundFile = this.soundFileHF;
		case 5:
			this.soundFile = this.soundFileMario;
		case 6:
			this.soundFile = this.soundFileMK;
		case 7:
			this.soundFile = this.soundFileMonkey;
		case 8:
			this.soundFile = this.soundFileNyan;
		}
	}
}