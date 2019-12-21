package org.team1540.liam2019.commands.wrist;

import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.liam2019.Hardware;
import org.team1540.liam2019.Robot;

public class MoveWristToBottom extends TimedCommand {

    private static final int WRIST_DOWN_POS = -2300;
    private static final double TIMEOUT = 0.6;
    private static final double DOWN_PERCENT = -0.7;


    public MoveWristToBottom() {
        super(TIMEOUT);
        requires(Robot.wrist);
    }

    @Override
    protected void initialize() {
        Hardware.wrist.setBrake(false);
    }

    @Override
    protected void execute() {
        Robot.wrist.setPercent(DOWN_PERCENT);
    }

    @Override
    protected boolean isFinished() {
        return Robot.wrist.getPosition() < WRIST_DOWN_POS || isTimedOut();
    }

    @Override
    protected void end() {
        Robot.wrist.setPercent(0);
    }
}
