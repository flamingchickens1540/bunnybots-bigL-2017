package org.team1540.liam2019.commands.wrist;

import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.liam2019.Robot;

public class MoveWrist extends TimedCommand {

    public static final double TOLERANCE = 10;
    private static double DEFAULT_TIMEOUT = 2;

    public enum WristPosition {
        DOWN(-2600),
        CARRY(-2000),
        YEET(-1200),
        UP(0),
        SHAKE(100),
        BACK(500);

        int value;

        WristPosition(int value) {
            this.value = value;
        }
    }

    private double position;

    public MoveWrist(double position, double timeout) {
        super(timeout);
        requires(Robot.wrist);
        this.position = position;
    }

    public MoveWrist(double position) {
        this(position, DEFAULT_TIMEOUT);
    }

    public MoveWrist(WristPosition position, double timeout) {
        this(position.value, timeout);
    }

    public MoveWrist(WristPosition position) {
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
