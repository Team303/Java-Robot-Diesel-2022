package com.team303.diesel.autonomous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.team303.diesel.autonomous.actions.Action;
import com.team303.diesel.autonomous.actions.ActionWait;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Represents an instance of instructions to preform during the autonomous period
 */
public class AutonomousProgram {
	public static final int ANGLE_THRESHOLD = 1;
    public static final double TURNING_CONSTANT = 0.005;

    static public SendableChooser<AutonomousProgram> autoFactoryChooser = new SendableChooser<>();
	static public SendableChooser<Position> positionChooser = new SendableChooser<>();
	static private Position lastPos;

    /**
     * Registry of all auto programs
     * String - Name
     * AutonomousProgram - The instance to use
     */
    private static Map<String, AutonomousProgram> registry = new HashMap<>();

    public static AutonomousProgram DO_NOTHING = new AutonomousProgram("Do Nothing", () -> Arrays.asList(new ActionWait(Double.MAX_VALUE)));

    List<Action> actions;
    AutonomousProvider provider;
	int taskIndex;
    String name;
    Position startingPos;

	public AutonomousProgram(String name, AutonomousProvider provider) {
        this(name, null, provider);
	}

    public AutonomousProgram(String name, Position startingPos, AutonomousProvider provider) {
        this.name = name;
		this.taskIndex = 0;
        this.startingPos = startingPos;
        this.provider = provider;

        if (registry.get(name) != null)
            throw new IllegalArgumentException(String.format("Cannot register multiple autos with name\"%s\"!", name));

        registry.put(name, this);
	}

    /**
     *  Called in autonomousPeriodic() 
     */    
	public void run() {
        System.out.println(taskIndex);

        if (taskIndex >= actions.size())
            return;

        Action currentTask = actions.get(taskIndex);

        if (currentTask.execute())
            taskIndex++;
    }

    public void init() {
        this.taskIndex = 0;
        this.actions = provider.construct();
    }

    public String getName() {
        return name;
    }

    public Action getCurrentTask() {
        if (actions.size() >= taskIndex) return null;
        return actions.get(taskIndex);
    }

    public String getCurrentTaskName() {
        var task = getCurrentTask();

        if (task == null) return "None";
        return task.getName();
    }

    public int getTaskIndex() {
        return this.taskIndex;
    }

    public int getNumTasks() {
        return this.actions.size();
    }

    /**
     * Starting Field Postion of the Robot (selcted in smart dashboard)
     */
    public enum Position {
        LEFT("Left"), RIGHT("Right"), CENTER("Center");

        String name;

        private Position(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /** 
	 * RAW DOGGING MANUAL AUTO_FACTORY AND POSITION LOGIC (⛔ Don't Touch Please ⛔)
	 */
	public static void processAutoChoosers() {
		// First Run Initialize Options
		if (lastPos == null) {
			// Add starting position to SmartDashboard
			positionChooser.addOption(Position.LEFT.getName(), Position.LEFT);
			positionChooser.addOption(Position.CENTER.getName(), Position.CENTER);
			positionChooser.addOption(Position.RIGHT.getName(), Position.RIGHT);
			positionChooser.setDefaultOption(Position.CENTER.getName(), Position.CENTER);

			SmartDashboard.putData("Starting Position", positionChooser);

			Position selectedPosition = positionChooser.getSelected();
			lastPos = selectedPosition;

			// Add Autonomous options to SmartDashboard based on selected position
			for (AutonomousProgram auto : AutonomousProgram.getAutosFromPos(selectedPosition))
				autoFactoryChooser.addOption(auto.getName(), auto);

			autoFactoryChooser.setDefaultOption(AutonomousProgram.DO_NOTHING.getName(), AutonomousProgram.DO_NOTHING);

			SmartDashboard.putData("Autos", autoFactoryChooser);
		}

		// If the selected postion changed from the last update,
		// update the auto list with the most recent values.
		Position selectedPosition = positionChooser.getSelected();
		if (selectedPosition != lastPos) {
			lastPos = selectedPosition;

			autoFactoryChooser.close();
			autoFactoryChooser = new SendableChooser<>();

			for (AutonomousProgram auto : AutonomousProgram.getAutosFromPos(selectedPosition))
				autoFactoryChooser.addOption(auto.getName(), auto);

			autoFactoryChooser.setDefaultOption(AutonomousProgram.DO_NOTHING.getName(), AutonomousProgram.DO_NOTHING);

			SmartDashboard.putData("Autos", autoFactoryChooser);
		}
	}

    /* Smart Dashboard Dropdown Methods (Don't touch these) */

    public static ArrayList<AutonomousProgram> getAllAutos() {
        return getAllAutos((auto) -> true);
    }

    private static ArrayList<AutonomousProgram> getAllAutos(Predicate<? super AutonomousProgram> filter) {
        ArrayList<AutonomousProgram> autos = new ArrayList<>();
        for (AutonomousProgram val : registry.values())
            if (filter.test(val))
                autos.add(val);

        return autos;
    }

    public static ArrayList<AutonomousProgram> getAutosFromPos(Position pos) {
        return getAllAutos((auto) -> auto.startingPos == pos || auto.startingPos == null);
    }


    public static AutonomousProgram getSelected() {
        return autoFactoryChooser.getSelected();
    }
}
