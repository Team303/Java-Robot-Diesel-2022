package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivebase {

    public WPI_TalonSRX leftMotor;
    public WPI_TalonSRX rightMotor;

    DifferentialDrive drive;

    public Drivebase() {
        leftMotor = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_ID);
        rightMotor = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_ID);

        leftMotor.setNeutralMode(NeutralMode.Brake);
        rightMotor.setNeutralMode(NeutralMode.Brake);

        leftMotor.setInverted(RobotMap.LEFT_MOTOR_INVERTED);
        rightMotor.setInverted(RobotMap.RIGHT_MOTOR_INVERTED);

        this.drive = new DifferentialDrive(leftMotor, rightMotor);
    }

    public void drive(double left, double right) {
        this.drive.tankDrive(left, right);
    }

    /**
     * TODO - Make this more clear
     *
     * @param powSetpoint     Initial Power ?
     * @param angleDifference ? no idea
     * @param tuningConstant  Some amount to turn in order to achieve angle?
     */
    public void driveStraight(double powSetpoint, double angleDifference, double tuningConstant) {
        double powLeft = powSetpoint + (angleDifference * tuningConstant);
        double powRight = powSetpoint - (angleDifference * tuningConstant);
        this.drive(powLeft, powRight);
    }

    public void zeroEncoders() {
        leftMotor.setSelectedSensorPosition(0, 0, 1000);
        rightMotor.setSelectedSensorPosition(0, 0, 1000);
    }

    public int getLeftEncoder() {
        return (int) leftMotor.getSelectedSensorPosition(RobotMap.LEFT_MOTOR_ID);
    }

    public int getRightEncoder() {
        return (int) -rightMotor.getSelectedSensorPosition(RobotMap.RIGHT_MOTOR_ID);
    }

}
