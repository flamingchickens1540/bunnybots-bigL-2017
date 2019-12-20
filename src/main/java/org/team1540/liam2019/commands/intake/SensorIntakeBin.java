package org.team1540.liam2019.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.liam2019.Hardware;
import org.team1540.liam2019.Robot;

public class SensorIntakeBin extends Command {

    double intakeVel;
    private double intakeSensorThreshold;

    public SensorIntakeBin(double throttle, double sensorThreshold) {
        requires(Robot.intake);
        this.intakeVel = throttle;
        this.intakeSensorThreshold = sensorThreshold;
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
        return Hardware.intakeSensor.getVoltage() < intakeSensorThreshold;
    }
}