package org.team1540.liam2019.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1540.liam2019.OI;

public class Auto extends CommandGroup {
    public Auto() {
        addParallel(new OI.FloorIntake());
        addSequential(new VisionDriveUntilSensorOrDistance(0.3, 363 * 0.47, 15, 2));
    }
}
