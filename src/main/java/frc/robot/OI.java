package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class OI {

    static Joystick left = new Joystick(0);
	static Joystick right = new Joystick(1);
	static XboxController xbox = new XboxController(2);

    //for joystick
    static double lX = 0, lY = 0, lZ = 0;
	static double rX = 0, rY = 0, rZ = 0;
    //xbox
	static double xlX = 0, xlY = 0, xrX = 0, xrY = 0;

    

    public static void update(){

        lX = left.getX();
		lY = left.getY();
		lZ = left.getZ();
		
		rX = right.getX();
		rY = right.getY();
		rZ = right.getZ();
		
		xlX = xbox.getLeftX();
		xlY = xbox.getLeftY();
		xrX = xbox.getRightX();
        xrY = xbox.getRightY();
		
    }
}


