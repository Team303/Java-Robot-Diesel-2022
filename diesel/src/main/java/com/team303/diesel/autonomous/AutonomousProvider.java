package com.team303.diesel.autonomous;

import java.util.List;

import com.team303.diesel.autonomous.actions.Action;

public interface AutonomousProvider {
    List<Action> construct();
}