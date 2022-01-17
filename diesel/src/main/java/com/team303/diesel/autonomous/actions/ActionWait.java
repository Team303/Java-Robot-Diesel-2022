package com.team303.diesel.autonomous.actions;

import com.team303.diesel.autonomous.actions.annotations.ActionConstructor;
import com.team303.diesel.autonomous.actions.annotations.ActionInput;
import com.team303.diesel.autonomous.actions.annotations.AutoAction;

import edu.wpi.first.wpilibj.Timer;

/**
 * A default Action that comes with diesel
 * 
 * <br/><br/>
 * 
 * Forces the Robot to stop everything, and wait a predetermined amount of time
 */
@AutoAction("Wait")
public class ActionWait extends Action {
    double waitTime;
    Timer timer;

    @ActionConstructor
    public ActionWait(@ActionInput("Seconds") double seconds) {
        this.waitTime = seconds;
        timer = new Timer();
    }

    @Override
    protected void beforeFirstRun() {
        timer.start();
    }

    @Override
    protected boolean isDone() {
        if (timer.get() >= waitTime) {
            timer.stop();
            timer.reset();
            return true;
        }

        return false;
    } 
}
