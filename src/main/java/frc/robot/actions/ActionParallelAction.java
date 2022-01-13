package frc.robot.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Executes multiple Actions at the same time (in parallel)
 * 
 * Actions can be defined as either conditional or non-conditional
 * 
 * Conditional Actions determine whether or not the entire Task is completed,
 * while Non-Conditional Actions will run until completion or until all of 
 * the Conditional Actions have completed. 
 */
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
        for (Action e : conditionActions) {
            e.execute();
        }
        for (Action e : nonConditionActions) {
            e.execute();
        }
    }

    // checks if all conditional actions are finished
    @Override
    public boolean isDone() {
        for (Action e : conditionActions) {
            if (!e.isDone())
                return false;
        }
        return true;
    }

    @Override
    public String getName() {
        String condNames = "";
        String nonCondNames = "";

        for (int i = 0; i < conditionActions.size(); i++) {
            condNames += conditionActions.get(0).getName();
            if (i != conditionActions.size() - 1)
                condNames += ", ";
        }

        for (int i = 0; i < nonConditionActions.size(); i++) {
            nonCondNames += nonConditionActions.get(0).getName();
            if (i != nonConditionActions.size() - 1)
                nonCondNames += ", ";
        }

        return String.format("Parallel Action (Coniditionals - [%s]) and (Non-Coniditionals - [%s])",
                condNames,
                nonCondNames);
    }
}
