package org.team1540.liam2019.commands.wrist;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import org.team1540.liam2019.OI;
import org.team1540.liam2019.Robot;
import org.team1540.rooster.Utilities;

public class ManualWrist extends Command {

    public ManualWrist() {
        requires(Robot.wrist);
    }

    @Override
    protected void execute() {
        double percent = Utilities.processDeadzone(OI.copilot.getRectifiedX(GenericHID.Hand.kLeft), 0.1);
        Robot.wrist.setPercent(percent);
    }

    @Override
    protected void end() {
        Robot.wrist.setPosition(Robot.wrist.getPosition());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}