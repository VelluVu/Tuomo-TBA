package pack;

import lejos.robotics.RegulatedMotor;

/**
 * This class includes various large motor methods.
 * 
 * @author TBA
 * @version 2.0
 * @since 4.5.2018
 */

public class Motor {
	
	private RegulatedMotor leftMotor;
	private RegulatedMotor rightMotor;
	
	/**
	 * This constructor synchronizes leftMotor with rightMotor.
	 * 
	 * @param leftMotor This is left large motor used for driving.
	 * @param rightMotor This is right large motor used for driving.
	 */
	
	public Motor(RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		this.leftMotor =  leftMotor;
		this.rightMotor =  rightMotor;
		this.leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });
		resetMotorSpeed();
	}
	
	/**
	 * This method sets speed for motors.
	 * @param multiplier This is multiplier for speed.
	 * @return This returns the final speed.
	 */

	public int setSpeedValue(float multiplier) {
		int finalSpeed;
		float motorMax = leftMotor.getMaxSpeed();
		motorMax *= multiplier;
		finalSpeed = Math.round(motorMax);
		return finalSpeed;
	}
	
	/**
	 * This method resets motor speed to default.
	 */

	public void resetMotorSpeed() {
		leftMotor.setSpeed(setSpeedValue(1.0f));
		rightMotor.setSpeed(setSpeedValue(1.0f));
	}
	
	/**
	 * This method makes robot go forward.
	 */
	
	public void driveForward() {
		leftMotor.startSynchronization();
		leftMotor.forward();
		rightMotor.forward();
		leftMotor.endSynchronization();
	}
	
	/**
	 * This method makes robot go backward.
	 */

	public void driveBackward() {
		leftMotor.startSynchronization();
		leftMotor.backward();
		rightMotor.backward();
		leftMotor.endSynchronization();
	}
	
	/**
	 * This method makes robot spin right "turn".
	 */

	public void spinRight() {
		leftMotor.startSynchronization();
		leftMotor.forward();
		rightMotor.backward();
		leftMotor.endSynchronization();
	}
	
	/**
	 * This method makes robot spin left "turn".
	 */
	
	public void spinLeft() {
		leftMotor.startSynchronization();
		leftMotor.backward();
		rightMotor.forward();
		leftMotor.endSynchronization();
	}
	
	/**
	 * This method makes robot turn right while moving forward.
	 */

	public void turnRightForward() {
		// set right motor speed to be 20%
		rightMotor.setSpeed(setSpeedValue(0.2f));

		leftMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.forward();
		leftMotor.endSynchronization();
	}
	
	/**
	 * This method makes robot turn left while moving forward.
	 */

	public void turnLeftForward() {
		// set left motor speed to be 20%
		leftMotor.setSpeed(setSpeedValue(0.2f));

		leftMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.forward();
		leftMotor.endSynchronization();
	}
	
	/**
	 * This method makes robot turn left while moving backward.
	 */

	public void turnLeftBackward() {
		// set left motor speed to be 20%
		leftMotor.setSpeed(setSpeedValue(0.2f));

		leftMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.backward();
		leftMotor.endSynchronization();
	}
	
	/**
	 * This method makes robot turn right while moving backward.
	 */

	public void turnRightBackward() {
		// set left motor speed to be 20%
		rightMotor.setSpeed(setSpeedValue(0.2f));

		leftMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.backward();
		leftMotor.endSynchronization();
	}
	
	/**
	 * This method stops motors.
	 */

	public void stopMotors() {
		leftMotor.startSynchronization();
		leftMotor.stop();
		rightMotor.stop();
		leftMotor.endSynchronization();
	}
	
	/**
	 * This method closes motors.
	 */
	
	public void closeMotors() {
		leftMotor.close();
		rightMotor.close();
	}

}
