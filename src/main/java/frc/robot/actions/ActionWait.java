package frc.robot.actions;

import edu.wpi.first.wpilibj.Timer;

/**
 * Forces the Robot to stop everything, and wait a predetermined amount of time
 */
public class ActionWait extends Action {
    double waitTime;
    Timer timer;

    public ActionWait(double seconds) {
        this.waitTime = seconds;
        timer = new Timer();
    }

    @Override
    protected void beforeFirstRun() {
        timer.start();
    }

    @Override
    public boolean isDone() {
        if (timer.get() >= waitTime) {
            timer.stop();
            timer.reset();
            return true;
        }

        return false;
    }

    @Override
    public String getName() {
        return "Wait";
    }

}
