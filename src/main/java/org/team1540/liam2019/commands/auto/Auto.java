package org.team1540.liam2019.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1540.liam2019.OI;

public class Auto extends CommandGroup {
    public Auto() {
        addSequential(new OI.AutoGetBin());
        addParallel(new OI.DumpBin());
        addSequential(new DriveForTime(-0.1, 3));
    }
}
