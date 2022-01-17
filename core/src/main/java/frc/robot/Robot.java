package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.actions.ActionDriveStraightByEncoders;

import com.team303.diesel.autonomous.AutonomousProgram;
import com.team303.diesel.autonomous.actions.ActionWait;
import com.team303.diesel.util.MathUtil;

import static java.util.Arrays.asList;;

/**
 * Main Robot class where all the important robot life cycle events are handled
 */
public class Robot extends TimedRobot {

	public static Drivebase drivebase;
	public static AutonomousProgram autoProgram;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Init Drivebase
		drivebase = new Drivebase();
		drivebase.zeroEncoders();

		updateSmartDashboard();
	}

	@Override
	public void robotPeriodic() {
		RobotIO.update();
		updateSmartDashboard();
	}

	@Override
	public void autonomousInit() {
		drivebase.zeroEncoders();

		autoProgram = AutonomousProgram.getSelected();
		autoProgram.init();
	}

	@Override
	public void autonomousPeriodic() {
		autoProgram.run();
	}

	@Override
	public void teleopInit() {

	}

	@Override
	public void teleopPeriodic() {
		double adjustment = SmartDashboard.getNumber("Speed Multiplier", RobotMap.SPEED_MULTIPLIER);

		adjustment = MathUtil.map(adjustment, 0, 1, 0.8, 1.1);
		adjustment = MathUtil.clamp(adjustment, 0.8, 1.0);

		if (RobotIO.joysticksNonZero())
			drivebase.drive(adjustment * -RobotIO.LEFT_JOYSTICK.getY(), adjustment * -RobotIO.RIGHT_JOYSTICK.getY());
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {

	}

	@Override
	public void testInit() {

	}

	@Override
	public void testPeriodic() {

	}

	public void updateSmartDashboard() {
		/*
		 * Set Defaults for all values to make sure they are autopopulated in
		 * smartdashboard
		 */

		SmartDashboard.setDefaultNumber("Speed Multiplier", RobotMap.SPEED_MULTIPLIER);

		SmartDashboard.setDefaultNumber("Left Motor Encoder", 0);
		SmartDashboard.setDefaultNumber("Right Motor Encoder", 0);

		SmartDashboard.setDefaultNumber("Left Joystick Pos", 0);
		SmartDashboard.setDefaultNumber("Right Joystick Pos", 0);

		SmartDashboard.setDefaultString("Current Task", "None");
		SmartDashboard.setDefaultNumber("Tasks Completed", 0);

		/* Update values here that dont get updated elsewhere */

		SmartDashboard.putNumber("Left Motor Encoder", drivebase.getLeftEncoder());
		SmartDashboard.putNumber("Right Joystick Pos", drivebase.getRightEncoder());

		SmartDashboard.putNumber("Left Joystick Pos", RobotIO.LEFT_JOYSTICK.getY());
		SmartDashboard.putNumber("Right Joystick Pos", RobotIO.RIGHT_JOYSTICK.getY());

		if (autoProgram != null) {
			SmartDashboard.putString("Current Task", autoProgram.getCurrentTaskName());
			SmartDashboard.putNumber("Tasks Completed", autoProgram.getTaskIndex());
		}

		// Handle auto and position dropdown updates
		AutonomousProgram.processAutoChoosers();
	}

	/* Assemble All Robot Autonomous Strategies */
	static {
		new AutonomousProgram("Drive Straight", () -> asList(
				new ActionDriveStraightByEncoders(750, 0.5, 15)));

		new AutonomousProgram("Drive Forwards", () -> asList(
				new ActionDriveStraightByEncoders(750, 0.5, 15)));

		new AutonomousProgram("Drive Forwards Then Back", () -> asList(
				new ActionDriveStraightByEncoders(750, 0.5, 15),
				new ActionDriveStraightByEncoders(250, -0.5, 15)));

		new AutonomousProgram("Drive Backwards", () -> asList(
				new ActionDriveStraightByEncoders(50, -0.7, 15)));

		new AutonomousProgram("Drive Wait Drive (Center)", () -> asList(
				new ActionDriveStraightByEncoders(100, 0.5, 10),
				new ActionWait(2),
				new ActionDriveStraightByEncoders(100, 0.5, 10)));
	}
}
