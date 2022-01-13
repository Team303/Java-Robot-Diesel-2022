package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.util.Vector2D;
import frc.robot.util.Vector3D;

public class RobotIO {
	public enum InputMethod {
		XBOX, JOYSTICK
	}

	static final XboxController XBOX_CONTROLLER;

	static final Joystick LEFT_JOYSTICK;
	static final Joystick RIGHT_JOYSTICK;

	public static final Vector2D XBOX_LEFT_POS;
	public static final Vector2D XBOX_RIGHT_POS;

	public static final Vector3D LEFT_JOYSTICK_POS;
	public static final Vector3D RIGHT_JOYSTICK_POS;

	static {
		XBOX_CONTROLLER = new XboxController(0);
		LEFT_JOYSTICK = new Joystick(1);
		RIGHT_JOYSTICK = new Joystick(2);

		XBOX_LEFT_POS = new Vector2D();
		XBOX_RIGHT_POS = new Vector2D();

		LEFT_JOYSTICK_POS = new Vector3D();
		RIGHT_JOYSTICK_POS = new Vector3D();
	}

    public static void update() {
		XBOX_LEFT_POS.setX(XBOX_CONTROLLER.getLeftX());
		XBOX_LEFT_POS.setY(XBOX_CONTROLLER.getLeftY());

		XBOX_RIGHT_POS.setX(XBOX_CONTROLLER.getRightX());
		XBOX_RIGHT_POS.setY(XBOX_CONTROLLER.getRightY());

		LEFT_JOYSTICK_POS.setX(LEFT_JOYSTICK.getX());
		LEFT_JOYSTICK_POS.setY(LEFT_JOYSTICK.getY());
		LEFT_JOYSTICK_POS.setZ(LEFT_JOYSTICK.getZ());

		RIGHT_JOYSTICK_POS.setX(RIGHT_JOYSTICK.getX());
		RIGHT_JOYSTICK_POS.setY(RIGHT_JOYSTICK.getY());
		RIGHT_JOYSTICK_POS.setZ(RIGHT_JOYSTICK.getZ());
    }

	public static boolean shouldDrive() {
		InputMethod selectedInput = Robot.inputChooser.getSelected();

		switch (selectedInput) {
			case XBOX:
				return nonZero(XBOX_LEFT_POS.getY(), XBOX_RIGHT_POS.getY());
			case JOYSTICK:
				return nonZero(LEFT_JOYSTICK_POS.getY(), RIGHT_JOYSTICK_POS.getY());
			default:
				return false;
		}
	}

	private static boolean nonZero(double u, double v) {
		return Math.abs(u) > 0.1 || Math.abs(v) > 0.1;
	}
}


