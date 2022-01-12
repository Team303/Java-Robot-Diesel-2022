package frc.robot;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;



public class Drivebase {
 
    WPI_TalonSRX leftMotor;
    WPI_TalonSRX rightMotor;
 
    DifferentialDrive drive;

    public Drivebase(){

        leftMotor = new WPI_TalonSRX(RobotMap.leftMotorControler);
        rightMotor = new WPI_TalonSRX(RobotMap.rightMotorControler);

        leftMotor.setNeutralMode(NeutralMode.Brake);
        rightMotor.setNeutralMode(NeutralMode.Brake);
        
        leftMotor.setInverted(false);
        rightMotor.setInverted(false);
    
        drive = new DifferentialDrive(leftMotor, rightMotor);

    }    

    public void drive(double left, double right){

        drive.tankDrive(left, right);
    }

}
