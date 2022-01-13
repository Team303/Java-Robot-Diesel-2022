package frc.robot.actions;

import edu.wpi.first.wpilibj.Timer;

/**
 * Action that executes a second Action after a certain delay
 */
public class ActionDelayedAction extends Action {

    Timer timer = new Timer();
    double waitTime;
    Action action;

    public ActionDelayedAction(double seconds, Action action) {
        this.waitTime = seconds;
        this.action = action;
    }

    @Override
    protected void beforeFirstRun() {
        timer.start();
    }

    @Override
    protected void onRun() {
        if (timer.get() >= waitTime)
            action.execute();
    }

    @Override
    protected boolean isDone() {
        return action.hasFinished;
    }

    @Override
    public String getName() {
        return "Delayed Action";
    }
}