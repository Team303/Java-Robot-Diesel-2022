package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.util.Vector2D;
import frc.robot.util.Vector3D;

public class RobotIO {
	static final Joystick LEFT_JOYSTICK;
	static final Joystick RIGHT_JOYSTICK;

	public static final Vector3D LEFT_JOYSTICK_POS;
	public static final Vector3D RIGHT_JOYSTICK_POS;

	static {
		LEFT_JOYSTICK = new Joystick(0);
		RIGHT_JOYSTICK = new Joystick(1);

		LEFT_JOYSTICK_POS = new Vector3D();
		RIGHT_JOYSTICK_POS = new Vector3D();
	}

	public static void update() {
		LEFT_JOYSTICK_POS.setX(LEFT_JOYSTICK.getX());
		LEFT_JOYSTICK_POS.setY(LEFT_JOYSTICK.getY());
		LEFT_JOYSTICK_POS.setZ(LEFT_JOYSTICK.getZ());

		RIGHT_JOYSTICK_POS.setX(RIGHT_JOYSTICK.getX());
		RIGHT_JOYSTICK_POS.setY(RIGHT_JOYSTICK.getY());
		RIGHT_JOYSTICK_POS.setZ(RIGHT_JOYSTICK.getZ());
	}

	public static boolean shouldDrive() {
		return nonZero(LEFT_JOYSTICK_POS.getY(), RIGHT_JOYSTICK_POS.getY());
	}

	private static boolean nonZero(double u, double v) {
		return Math.abs(u) > 0.1 || Math.abs(v) > 0.1;
	}
}
