package org.team1540.robot2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.robot2019.Robot;

public class SetGrabber extends Command {

    boolean state;

    public SetGrabber(boolean state) {
        this.state = state;
        requires(Robot.grabber);
    }

    @Override
    protected void initialize() {
        Robot.grabber.set(state);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
