package org.team1540.liam2019.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.liam2019.OI;
import org.team1540.liam2019.commands.intake.SetIntakeForTime;
import org.team1540.liam2019.commands.wrist.MoveWrist;

public class NoVisionAuto extends CommandGroup {
    public NoVisionAuto() {
        addParallel(new OI.FloorIntake());
        addSequential(new DriveUntilSensorOrDistance(0.3, 5000, 3));
        addSequential(new DriveForTime(0.1, 2));
        addSequential(new OI.DumpBin());
        addParallel(new MoveWrist(MoveWrist.WristPosition.YEET));
        addSequential(new TimedCommand(0.1));
        addSequential(new SetIntakeForTime(1, 1));
    }
}
