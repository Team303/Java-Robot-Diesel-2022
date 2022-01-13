package frc.robot.actions;

import frc.robot.Robot;

/**
 * Template for how Robot Actions work
 */
public abstract class Action {
    protected boolean isFirstRun;
    protected boolean hasFinished;

    public Action() {
        this.isFirstRun = false;
    }

    /**
     * Display name to show in SmartDashboard
     *
     * @return The display name of the Action
     */
    public abstract String getName();

    /**
     * Called after every run to find out if the action has completed,
     * and should no longer be run. Once a true value is returned, 
     * it will never be called again
     * 
     * <br/>
     * <br/>
     * 
     * 🟡🔴 Should ⛔<b>NEVER</b>⛔ be called outside the <i>Action</i> class 🔴🟡
     * <br/><br/>
     * If any of you call this outside of the <code>execute()</code> method I'll have your head
     *
     * @return Whether or not the action has finished its job
     */
    protected abstract boolean isDone();

    /**
     * Called every time the action is run until it is finished
     * 
     * <br/>
     * <br/>
     * 
     * 🟡🔴 Should ⛔<b>NEVER</b>⛔ be called outside the <i>Action</i> class 🔴🟡
     * <br/><br/>
     * If any of you call this outside of the <code>execute()</code> method I'll have your head
     */
    protected void onRun() {

    }

    /**
     * Executed right before the Action is run for the first time
     * 
     * <br/>
     * <br/>
     * 
     * 🟡🔴 Should ⛔<b>NEVER</b>⛔ be called outside the <i>Action</i> class 🔴🟡
     * <br/><br/>
     * If any of you call this outside of the <code>execute()</code> method I'll have your head
     */
    protected void beforeFirstRun() {

    }

    /**
     * Executed right after the Action is run for the first time
     * 
     * <br/>
     * <br/>
     * 
     * 🟡🔴 Should ⛔<b>NEVER</b>⛔ be called outside the <i>Action</i> class 🔴🟡
     * <br/><br/>
     * If any of you call this outside of the <code>execute()</code> method I'll have your head
     */
    protected void afterFirstRun() {

    }

    /**
     * Executed right after the Action has finished running
     * 
     * <br/>
     * <br/>
     * 
     * 🟡🔴 Should ⛔<b>NEVER</b>⛔ be called outside the <i>Action</i> class 🔴🟡
     * <br/><br/>
     * If any of you call this outside of the <code>execute()</code> method I'll have your head
     */
    protected void afterFinished() {

    }

    /**
     * Called to run the action from an action handler
     * <p>
     * <br/>
     * <br/>
     * <p>
     * Makes sure life cycle events are called
     *
     * @return Whether or not the Action is finished
     */
    public boolean execute() {
        boolean wasFirstRun = isFirstRun;

        if (this.isFirstRun) {
            this.beforeFirstRun();
            this.isFirstRun = false;
        }

        if (wasFirstRun)
            this.afterFirstRun();

        // After Finishing, this part will lock up
        if (this.hasFinished)
            return true;

        this.onRun();

        this.hasFinished = this.isDone();
        if (this.hasFinished)
            this.afterFinished();

        return this.hasFinished;
    }

    /**
     * TODO - Make this more clear
     *
     * @param powSetpoint
     * @param angleDifference
     * @param tuningConstant
     */
    public static void driveStraight(double powSetpoint, double angleDifference, double tuningConstant) {
        double powLeft = powSetpoint + (angleDifference * tuningConstant);
        double powRight = powSetpoint - (angleDifference * tuningConstant);
        Robot.drivebase.drive(powLeft, powRight);
    }
}