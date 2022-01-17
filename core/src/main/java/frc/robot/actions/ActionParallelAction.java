package frc.robot.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.team303.diesel.autonomous.actions.Action;
import com.team303.diesel.autonomous.actions.annotations.AutoAction;

/**
 * Executes multiple Actions at the same time (in parallel)
 * 
 * Actions can be defined as either conditional or non-conditional
 * 
 * Conditional Actions determine whether or not the entire Task is completed,
 * while Non-Conditional Actions will run until completion or until all of
 * the Conditional Actions have completed.
 */
@AutoAction("Parallel Action")
public class ActionParallelAction extends Action {

    // Actions that control when it is finished
    List<Action> conditionActions;

    // Actions that run simoultaneously until they finish,
    // or the conditional actions finishe
    List<Action> nonConditionActions;

    public ActionParallelAction(Action conditionalAction, Action... nonConditionalActions) {
        this(Arrays.asList(conditionalAction), Arrays.asList(nonConditionalActions));
    }

    public ActionParallelAction(List<Action> conditionActions, List<Action> nonConditionActions) {
        this.conditionActions = conditionActions;
        this.nonConditionActions = nonConditionActions;
    }

    public ActionParallelAction(ArrayList<Action> conditionActions) {
        this(conditionActions, new ArrayList<>());
    }

    // runs all actions
    protected void onRun() {
        for (Action e : conditionActions)
            e.execute();

        for (Action e : nonConditionActions)
            e.execute();
    }

    @Override
    protected boolean isDone() {
        // If any are not done return false
        for (Action e : conditionActions) {
            if (!e.hasFinished)
                return false;
        }
        return true;
    }
}
