package frc.robot.autonomous;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.actions.Action;

/**
 * Represents an instance of instructions to preform during the autonomous period
 */
public class Autonomous {
	public static final int ANGLE_THRESHOLD = 1;
    public static final double TURNING_CONSTANT = 0.005;
    
    final ArrayList<Action> actions;
	int taskIndex;
    String name;

	public Autonomous(String name) {
        this.name = name;
		this.taskIndex = 0;
        this.actions = new ArrayList<Action>();
	}

	public void run() {
        if (actions.size() < taskIndex) return;

        Action currentTask = actions.get(taskIndex);

        if (currentTask.execute())
            taskIndex++;

        SmartDashboard.putString("Current Task", currentTask.getName());
		SmartDashboard.putNumber("Tasks Completed", taskIndex);
    }

    public void reset() {
        this.taskIndex = 0;
    }

    public String getName() {
        return name;
    }
}
