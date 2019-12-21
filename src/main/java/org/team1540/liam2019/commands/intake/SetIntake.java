package org.team1540.liam2019.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.liam2019.Robot;

public class SetIntake extends Command {

    double percent;

    public SetIntake(double percent) {
        requires(Robot.intake);
        this.percent = percent;
    }

    @Override
    protected void initialize() {
        Robot.intake.set(percent);
    }

    @Override
    protected void end() {
        Robot.intake.set(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}