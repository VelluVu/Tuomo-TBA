

import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class IRDistance {
	
	private SampleProvider sp;
	private float[] sample;
	
	//private boolean stop;
	
	public IRDistance(EV3IRSensor sensor) {
		this.sp = sensor;
		this.sample = new float[this.sp.sampleSize()];
	}

	/*public void checkDistance() {
			
		float distance = getDistance();
		LCD.clear(1);
		LCD.drawInt((new Float(distance).intValue()), 8, 1);
			
		if(distance < 20) {
			if(!stop) {
				setStop(true);
				Button.LEDPattern(5);
			}
		} else {
			if(stop) {
				setStop(false);
				Button.LEDPattern(0);
			}
		}
			
		LCD.refresh();
	}*/
		
	
	public float getDistance() {
		sp.fetchSample(sample, 0);
		LCD.clear(1);
		LCD.drawInt((new Float(sample[0]).intValue()), 8, 1);
		LCD.refresh();
		return sample[0];
	}
	
	/*public void setStop(boolean newState) {
		this.stop = newState;
	}
	
	public boolean getStop() {
		return stop;
	}*/
	
}
