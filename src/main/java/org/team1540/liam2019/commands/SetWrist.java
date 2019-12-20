package org.team1540.liam2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.liam2019.Hardware;
import org.team1540.liam2019.Robot;

public class SetWrist extends TimedCommand {

    public static final double TOLERANCE = 10;
    private static double DEFAULT_TIMEOUT = 2;

    public enum WristPosition {
        DOWN(-2500),
        CARRY(-2000),
        UP(0),
        SHAKE(100),
        BACK(500);

        int value;
        WristPosition(int value) {
            this.value = value;
        }
    }

    private double position;

    public SetWrist(double position, double timeout) {
        super(timeout);
        requires(Robot.wrist);
        this.position = position;
    }

    public SetWrist(double position) {
        this(position, DEFAULT_TIMEOUT);
    }

    public SetWrist(WristPosition position, double timeout) {
        this(position.value, timeout);
    }

    public SetWrist(WristPosition position) {
        this(position.value, DEFAULT_TIMEOUT);
    }

    @Override
    protected void execute() {
        Robot.wrist.setPosition(position);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.wrist.getPosition() - position) < TOLERANCE;
    }
}
