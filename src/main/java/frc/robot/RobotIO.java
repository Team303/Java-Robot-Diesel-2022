package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.util.Vector2D;

public class RobotIO {
	static final XboxController XBOX_CONTROLLER;

	public static final Vector2D XBOX_LEFT_POS;
	public static final Vector2D XBOX_RIGHT_POS;

	static {
		XBOX_CONTROLLER = new XboxController(0);

		XBOX_LEFT_POS = new Vector2D();
		XBOX_RIGHT_POS = new Vector2D();
	}

    public static void update() {
		XBOX_LEFT_POS.setX(XBOX_CONTROLLER.getLeftX());
		XBOX_LEFT_POS.setY(XBOX_CONTROLLER.getLeftY());

		XBOX_RIGHT_POS.setX(XBOX_CONTROLLER.getRightX());
		XBOX_RIGHT_POS.setY(XBOX_CONTROLLER.getRightY());
    }

	public static boolean shouldDrive() {
		return Math.abs(XBOX_LEFT_POS.getY()) > 0.1 || Math.abs(XBOX_RIGHT_POS.getY()) > 0.1;
	}
}


