package org.team1540.liam2019.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1540.liam2019.OI;

public class NoVisionAuto extends CommandGroup {
    public NoVisionAuto() {
        addParallel(new OI.FloorIntake());
        addSequential(new VisionDriveUntilSensorOrDistance(0.3, 5000, 2));
    }
}
