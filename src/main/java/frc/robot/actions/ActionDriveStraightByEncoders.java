package frc.robot.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

/**
 * Action to drive straight based on encoder feedback
 */
public class ActionDriveStraightByEncoders extends Action {

    private static final double TURNING_CONSTANT = 0.009;

    private double distance;
    private double power;
    private double timeout;

    Timer timer;
    public double initalYaw;
    public int initialEncoderVal;

    public ActionDriveStraightByEncoders(double distance, double power, double timeout) {
        this.distance = distance;
        this.power = power;
        this.timeout = timeout;

        this.timer = new Timer();
        this.initalYaw = 0;
        this.initialEncoderVal = 0;
    }

    @Override
    protected void beforeFirstRun() {
        timer.start();
        initialEncoderVal = Robot.drivebase.getLeftEncoder();
    }

    @Override
    protected void onRun() {
        Action.driveStraight(power, 0 /* Robot.navX.getYaw() */, TURNING_CONSTANT);
    }

    /**
     * Tests to see if the robot is finished with this task by doing math with the
     * encoders.
     * 
     * If done, the robot will reset to not moving.
     */
    @Override
    protected boolean isDone() {
        // If timeout has exceeded, stop timer
        if (timer.get() >= timeout) {
            timer.stop();
            return true;
        }

        // Check if driven distance exceeds the provided distance
        return Math.abs(Robot.drivebase.getLeftEncoder() - initialEncoderVal) >= Math.abs(distance);
    }

    @Override
    public String getName() {
        return "Drive Straight By Encoders";
    }
}
