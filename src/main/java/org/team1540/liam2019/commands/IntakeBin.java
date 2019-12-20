package org.team1540.liam2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.liam2019.Hardware;
import org.team1540.liam2019.Robot;
import org.team1540.liam2019.Tuning;

public class IntakeBin extends Command {

    double intakeVel;

    public IntakeBin(double intakeVel) {
        requires(Robot.intake);
        this.intakeVel = intakeVel;
    }

    @Override
    protected void initialize() {
        Robot.intake.set(intakeVel);
    }

    @Override
    protected void end() {
        Robot.intake.set(0);
    }

    @Override
    protected boolean isFinished() {
        return Hardware.intakeSensor.getVoltage() < Tuning.intakeSensorThreshold;
    }
}