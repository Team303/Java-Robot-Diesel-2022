package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class RobotIO {
	static final XboxController XBOX_CONTROLLER;

	public static final Vector XBOX_LEFT_POS;
	public static final Vector XBOX_RIGHT_POS

	static {
		XBOX_CONTROLLER = new XboxController(0)

		XBOX_LEFT_POS = new Vector();
		XBOX_RIGHT_POS = new Vector();
	}

    public static void update() {
		XBOX_LEFT_POS.setX(xbox.getLeftX());
		XBOX_LEFT_POS.setY(xbox.getLeftY());

		XBOX_RIGHT_POS.setX(xbox.getRightX());
		XBOX_RIGHT_POS.setY(xbox.getRightY());
    }

	public static boolean shouldDrive() {
		return Math.abs(OI.XBOX_LEFT_POS.getY()) > 0.1 || Math.abs(OI.XBOX_RIGHT_POS.getY()) > 0.1;
	}
}


