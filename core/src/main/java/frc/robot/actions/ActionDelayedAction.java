package frc.robot.actions;

import com.team303.diesel.autonomous.actions.Action;
import com.team303.diesel.autonomous.actions.annotations.ActionConstructor;
import com.team303.diesel.autonomous.actions.annotations.ActionInput;
import com.team303.diesel.autonomous.actions.annotations.AutoAction;

import edu.wpi.first.wpilibj.Timer;

/**
 * Action that executes a second Action after a certain delay
 */
@AutoAction("Delayed Action")
public class ActionDelayedAction extends Action {

    Timer timer = new Timer();
    double waitTime;
    Action action;

    @ActionConstructor
    public ActionDelayedAction(@ActionInput("Seconds") Double seconds, @ActionInput("Action") Action action) {
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
}