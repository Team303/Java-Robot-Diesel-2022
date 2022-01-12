package frc.robot;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivebase {
 
    public WPI_TalonSRX leftMotor;
    public WPI_TalonSRX rightMotor;
 
    DifferentialDrive drive;

    public Drivebase(){

        leftMotor = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_ID);
        rightMotor = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_ID);

        leftMotor.setNeutralMode(NeutralMode.Brake);
        rightMotor.setNeutralMode(NeutralMode.Brake);

        leftMotor.setInverted(SmartDashboard.getBoolean("Left Invert", RobotMap.LEFT_MOTOR_INVERTED));
        rightMotor.setInverted(SmartDashboard.getBoolean("Right Invert", RobotMap.LEFT_MOTOR_INVERTED));
    
        drive = new DifferentialDrive(leftMotor, rightMotor);
    }    

    public void drive(double left, double right){
        drive.tankDrive(left, right);
    }

}
