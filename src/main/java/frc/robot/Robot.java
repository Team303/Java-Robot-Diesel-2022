// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autonomous.AutoFactory;
import frc.robot.autonomous.Autonomous;
import static frc.robot.autonomous.AutoFactory.Position;;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

	public static Drivebase drivebase;
	public static Autonomous auto;

	static public SendableChooser<AutoFactory> autoChooser = new SendableChooser<>();
	static public SendableChooser<Position> positionChooser = new SendableChooser<>();
	static private Position lastPos;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any
	 * initialization code.
	 */
	@Override
	public void robotInit() {
		// Init Drivebase
		drivebase = new Drivebase();
		drivebase.zeroEncoders();

		// Add starting position to SmartDashboard
		positionChooser.addOption("Left", Position.LEFT);
		positionChooser.addOption("Center", Position.CENTER);
		positionChooser.addOption("Right", Position.RIGHT);
		positionChooser.setDefaultOption("Center", Position.CENTER);

		SmartDashboard.putData("Starting Position", positionChooser);

		Position selectedPosition = positionChooser.getSelected();
		lastPos = selectedPosition;

		// Add Autonomous options to SmartDashboard based on selected position
		for (AutoFactory auto : AutoFactory.getAutosFromPos(selectedPosition))
			autoChooser.addOption(auto.getName(), auto);

		autoChooser.setDefaultOption(AutoFactory.DO_NOTHING.getName(), AutoFactory.DO_NOTHING);

		SmartDashboard.putData("Autos", autoChooser);

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

		AutoFactory selected = autoChooser.getSelected();

		auto = selected.construct();
	}

	@Override
	public void autonomousPeriodic() {
		drivebase.periodic();
		auto.run();
	}

	@Override
	public void teleopInit() {
		
	}

	@Override
	public void teleopPeriodic() {

		double adjustment = SmartDashboard.getNumber("Speed Multiplier", 1);

		if (RobotIO.shouldDrive())
			drivebase.drive(adjustment * RobotIO.LEFT_JOYSTICK_POS.getY(), adjustment * RobotIO.RIGHT_JOYSTICK_POS.getY());
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
		SmartDashboard.setDefaultNumber("Speed Multiplier", 1);

		// If the selected postion changed from the last update, 
		// update the auto list with the most recent values.
		Position selectedPosition = positionChooser.getSelected();
		if (selectedPosition != lastPos) {
			lastPos = selectedPosition;

			autoChooser.close();
			autoChooser = new SendableChooser<>();

			for (AutoFactory auto : AutoFactory.getAutosFromPos(selectedPosition))
				autoChooser.addOption(auto.getName(), auto);

			autoChooser.setDefaultOption(AutoFactory.DO_NOTHING.getName(), AutoFactory.DO_NOTHING);

			SmartDashboard.putData("Autos", autoChooser);
		}
	}
}
