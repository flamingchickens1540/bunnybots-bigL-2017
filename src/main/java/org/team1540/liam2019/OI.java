package org.team1540.liam2019;


import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1540.liam2019.commands.grabber.SetGrabber;
import org.team1540.liam2019.commands.intake.EjectBin;
import org.team1540.liam2019.commands.intake.SensorIntakeBin;
import org.team1540.liam2019.commands.wrist.ManualWrist;
import org.team1540.liam2019.commands.wrist.MoveWrist;
import org.team1540.liam2019.utils.ChickenXboxController;
import org.team1540.liam2019.utils.SimpleCommand;
import org.team1540.rooster.triggers.DPadAxis;

public class OI {

    static double joyAngleR;

    public static ChickenXboxController driver = new ChickenXboxController(0);
    public static Button zeroPointDrive = driver.getButton(ChickenXboxController.XboxButton.Y);

    public static ChickenXboxController copilot = new ChickenXboxController(1);
    public static Button toggleGrabber = copilot.getButton(ChickenXboxController.XboxButton.A);

    public static Button intakeBin = copilot.getButton(DPadAxis.DOWN);
    public static Button carryBin = copilot.getButton(DPadAxis.LEFT);
    public static Button dumpBin = copilot.getButton(DPadAxis.UP);
    public static Button ejectBin = copilot.getButton(ChickenXboxController.XboxButton.B);

    public static Button enableManualWrist = copilot.getButton(ChickenXboxController.XboxButton.LEFT_PRESS);

    public static void init() {
        initDriver();
        initCopilot();
    }

    private static void initDriver() {

    }

    private static void initCopilot() {
        toggleGrabber.whenPressed(new SimpleCommand(() -> Robot.grabber.toggleGrabbed(), Robot.grabber));

        intakeBin.whenPressed(new FloorIntake());

        dumpBin.whenPressed(new DumpBin());

        carryBin.whenPressed(new CommandGroup() {{
            addSequential(new MoveWrist(MoveWrist.WristPosition.CARRY, 1));
        }});

        ejectBin.whileHeld(new EjectBin(1));

        enableManualWrist.whileHeld(new ManualWrist());
    }

    public static class FloorIntake extends CommandGroup {
        {
            addSequential(new SetGrabber(false));
            addParallel(new MoveWrist(MoveWrist.WristPosition.DOWN, 1));
            addSequential(new SensorIntakeBin(-0.3, 2));
            addSequential(new SetGrabber(true));
        }
    }

    public static class DumpBin extends CommandGroup {
        {
            addSequential(new MoveWrist(MoveWrist.WristPosition.BACK, 1));
            addSequential(new MoveWrist(MoveWrist.WristPosition.SHAKE, 0.5));
            addSequential(new MoveWrist(MoveWrist.WristPosition.BACK, 0.5));
        }
    }
}
