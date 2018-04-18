package pack;

import lejos.robotics.RegulatedMotor;

public class Motor {
	
	private RegulatedMotor leftMotor;
	private RegulatedMotor rightMotor;
		
	public Motor(RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.leftMotor.synchronizeWith(new RegulatedMotor[] {rightMotor});
		resetMotorSpeed();
	}
	
	public int setSpeedValue(float multiplier) {
		int finalSpeed;
		float motorMax = leftMotor.getMaxSpeed();
		motorMax      *= multiplier;
		finalSpeed     = Math.round(motorMax);
		return finalSpeed;
	}
	
	public void resetMotorSpeed() {
		leftMotor.setSpeed(setSpeedValue(1.0f));
		rightMotor.setSpeed(setSpeedValue(1.0f));
	}
	
	public void driveForward() {
		leftMotor.startSynchronization();
		leftMotor.forward();
		rightMotor.forward();
		leftMotor.endSynchronization();
	}
	
	public void driveBackward() {
		leftMotor.startSynchronization();
		leftMotor.backward();
		rightMotor.backward();
		leftMotor.endSynchronization();
	}
	
	public void spinRight() {
		leftMotor.startSynchronization();
		leftMotor.forward();
		rightMotor.backward();
		leftMotor.endSynchronization();
	}
	
	public void spinLeft() {
		leftMotor.startSynchronization();
		leftMotor.backward();
		rightMotor.forward();
		leftMotor.endSynchronization();
	}
	
	public void turnRightForward() {
		//set right motor speed to be 20%
		rightMotor.setSpeed(setSpeedValue(0.2f));
		
		leftMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.forward();
		leftMotor.endSynchronization();
	}
	
	public void turnLeftForward() {
		//set left motor speed to be 20%
		leftMotor.setSpeed(setSpeedValue(0.2f));
		
		leftMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.forward();
		leftMotor.endSynchronization();
	}
	
	public void turnLeftBackward() {
		//set left motor speed to be 20%
		leftMotor.setSpeed(setSpeedValue(0.2f));
		
		leftMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.backward();
		leftMotor.endSynchronization();
	}
	
	public void turnRightBackward() {
		//set left motor speed to be 20%
		rightMotor.setSpeed(setSpeedValue(0.2f));
		
		leftMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.backward();
		leftMotor.endSynchronization();
	}
	
	public void stopMotors() {
		leftMotor.startSynchronization();
		leftMotor.stop();
		rightMotor.stop();
		leftMotor.endSynchronization();
	}
	
	public void closeMotors() {
		leftMotor.close();
		rightMotor.close();
	}
	
}
