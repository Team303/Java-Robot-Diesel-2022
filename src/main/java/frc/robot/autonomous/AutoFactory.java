package frc.robot.autonomous;

import frc.robot.actions.Action;
import frc.robot.actions.ActionDriveStraightByEncoders;
import frc.robot.actions.ActionWait;
import java.util.ArrayList;
import java.util.function.Predicate;

public enum AutoFactory {
    DO_NOTHING("Do Nothing", new ActionWait(Double.MAX_VALUE)),
    DRIVE_FORWARDS("Drive Forwards", new ActionDriveStraightByEncoders(29863, 0.4, 15)),
    CENTER_DRIVE_TURN_DRIVE("Drive Turn Drive (Center)", Position.CENTER,
            new ActionDriveStraightByEncoders(100, 0.5, 10), new ActionWait(2),
            new ActionDriveStraightByEncoders(100, 0.5, 10));

    private Action[] actions;
    private Position startingPosition;
    private String name;

    /**
     * Defines an Auto that is dependent on its position on the field
     * 
     * @param name             Name to display in the Auto selection menu
     * @param startingPosition The position on the field which this auto can run on
     * @param actions          An array of actions which define the Auto's behavior
     */
    private AutoFactory(String name, Position startingPosition, Action... actions) {
        this.startingPosition = startingPosition;
        this.actions = actions;
        this.name = name;
    }

    /**
     * Defines an Auto that doesnt care about its position on the field
     * 
     * @param name    Name to display in the Auto selection menu
     * @param actions An array of actions which define the Auto's behavior
     */
    private AutoFactory(String name, Action... actions) {
        this(name, null, actions);
    }

    public Autonomous construct() {
        Autonomous auto = new Autonomous(this.name);

        for (Action action : actions)
            auto.actions.add(action);

        return auto;
    }

    public String getName() {
        return this.name;
    }

    public static ArrayList<AutoFactory> getAllAutos() {
        return getAllAutos((auto) -> false);
    }

    private static ArrayList<AutoFactory> getAllAutos(Predicate<? super AutoFactory> filter) {
        ArrayList<AutoFactory> autos = new ArrayList<>();
        for (AutoFactory val : values())
            autos.add(val);

        autos.removeIf(filter);
        return autos;
    }

    public static ArrayList<AutoFactory> getAutosFromPos(Position pos) {
        return getAllAutos((auto) -> auto.startingPosition == pos);
    }

    /**
     * Starting Field Postion of the Robot (selcted in smart dashboard)
     */
    public enum Position {
        LEFT, RIGHT, CENTER
    }

}
