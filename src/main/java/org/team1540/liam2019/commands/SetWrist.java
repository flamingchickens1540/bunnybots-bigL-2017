package org.team1540.liam2019.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.team1540.liam2019.Hardware;
import org.team1540.liam2019.Robot;

public class SetWrist extends Command {

    double position;

    //false is up, true is down
    public SetWrist(boolean state) {
        requires(Robot.wrist);

        if (state) position=0;
        else position=-2500;
    }

    public SetWrist(double position) {
        requires(Robot.wrist);
        this.position = position;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Hardware.wrist.set(ControlMode.Position, position);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Hardware.wrist.getSelectedSensorPosition() - position) < 0.1;
    }
}
