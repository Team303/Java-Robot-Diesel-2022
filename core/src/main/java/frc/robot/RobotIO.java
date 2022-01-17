package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class RobotIO {
	static final Joystick LEFT_JOYSTICK = new Joystick(RobotMap.LEFT_JOYSTICK_ID);
	static final Joystick RIGHT_JOYSTICK = new Joystick(RobotMap.RIGHT_JOYSTICK_ID);

	/**
	 * Handle joystick life cycle events
	 */
	public static void update() {

	}

	/**
	 * Check if input from joysticks is above threshold (dont send drive commands if the joysticks are resting)
	 * @return Whether or not either of the joysticks have an above 0 value
	 */
	public static boolean joysticksNonZero() {
		return Math.abs(LEFT_JOYSTICK.getY()) > RobotMap.JOYSTICK_THRESHOLD
		   || Math.abs(RIGHT_JOYSTICK.getY()) > RobotMap.JOYSTICK_THRESHOLD;
	}
}
