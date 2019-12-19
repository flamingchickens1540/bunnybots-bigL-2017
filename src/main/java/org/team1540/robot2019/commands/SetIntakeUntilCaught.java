package org.team1540.robot2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.robot2019.Hardware;
import org.team1540.robot2019.Robot;
import org.team1540.robot2019.Tuning;

public class SetIntakeUntilCaught extends Command {

    int state;

    public SetIntakeUntilCaught(int state) {
        requires(Robot.intake);
        this.state = state;
    }

    @Override
    protected void initialize() {
        Robot.intake.set(state);
    }

    @Override
    protected void end() {
        Robot.intake.set(0);
    }

    @Override
    protected boolean isFinished() {
        return Hardware.intakeSensor.getVoltage() < Tuning.intakeSensorThreshold || state==0;
    }
}
