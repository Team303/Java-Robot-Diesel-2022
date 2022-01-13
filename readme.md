# Team 303 Java 2022 Robot Code

## New Features

This years Robot code features many improvements over the previously design action system,
IO classes, and provides a much nicer developer experience.

### New Action System

Actions are no longer an `interface`, and instead are now an `abstract class`. This allows for a robust system that eliminates the annoying need for "firstRun" variables and complicated task creation. This system has been majorly improved upon with a new event based system with full Action life cycle events. These include `beforeFirstRun()`, `afterFirstRun()`, `afterFinished()`, and of course the main event `onRun()`. For more detailed information on the new event system, see [Creating Actions](#creating_actions).

### New Autonomous System

With the new actions there is of course a revamped way to preprogram autonomous strategies. After analyzing the code from last year we realized that it was just clunky and not easyily maintainable. We attribute this to the large use of chained `if` and `else if` statements, and dozens of methods to `assemble` autnomous programs.

The new system merges everything into one convenient location for programming autonomous strategies. in the `frc.robot.autonomous` package there is a new `enum` called `Auto`. Here you can easily assemble programs by passing in a display name, optionally a starting position on the field, and a argument vector of `Action`s. Using the position selector on SmartDashboard, we can automatically filter through the programmed autonomous strategies and only show those that can run in the selected Position. This allows us to remove all the nasty if statements.

# Documentation and Guide

To assist new members with learning our codebase, I will be creating some basic guides/tutorials about how to use parts of the code so that everyone understands how the new systems work.

## Actions

During the robot's autonomous control phase (the first 15 seconds of the game), the drive team is not able to control the robot. Instead, we have to create a preprogrammed route (or usually a lot of preprogrammed routes) in order to instruct the robot to move without our input. These instructions can be simple like "wait 5 seconds" and "drive straight 1 meter", or complicated like "aim towards the goal and fire a projectile". Reguardless, we need a way to give the robot a list of reusable instructions to tell it what to do during autonomous. This is were Actions come in. 

An Action is just a task the robot is programmed to do, that can be measured as being complete or incomplete. For example, a task can be "wait 5 seconds", and we can measure its completeness by seeing how much time has passed since the task was launched.

### Creating Actions

to be continued...