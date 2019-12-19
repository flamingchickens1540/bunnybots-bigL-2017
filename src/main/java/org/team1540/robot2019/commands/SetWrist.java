package org.team1540.robot2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.robot2019.Hardware;
import org.team1540.robot2019.Robot;

public class SetWrist extends Command {

    double position;

    //false is up, true is down
    public SetWrist(boolean state) {
        requires(Robot.intakeWrist);

        if (state) position=1000;
        else position=0;
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        Hardware.wrist.set(position);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Hardware.wrist.getSelectedSensorPosition() - position) < 0.1;
    }
}
