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
     * TODO
     */
    public void periodic() {

    }

    public void zeroEncoders() {
        leftMotor.setSelectedSensorPosition(0, 0, 1000);
        rightMotor.setSelectedSensorPosition(0, 0, 1000);
    }

    public int getLeftEncoder() {
        return (int) leftMotor.getSelectedSensorPosition(0);
    }

    public int getRightEncoder() {
        return (int) -rightMotor.getSelectedSensorPosition(0);
    }

}
