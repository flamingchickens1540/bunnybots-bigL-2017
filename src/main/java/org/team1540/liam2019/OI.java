package org.team1540.liam2019;


import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1540.liam2019.commands.auto.Auto;
import org.team1540.liam2019.commands.grabber.SetGrabber;
import org.team1540.liam2019.commands.intake.SensorIntakeBin;
import org.team1540.liam2019.commands.intake.SetIntake;
import org.team1540.liam2019.commands.intake.SetIntakeForTime;
import org.team1540.liam2019.commands.wrist.ManualWrist;
import org.team1540.liam2019.commands.wrist.MoveWrist;
import org.team1540.liam2019.commands.wrist.MoveWristToBottom;
import org.team1540.liam2019.utils.ChickenXboxController;
import org.team1540.liam2019.utils.DualAxisButton;
import org.team1540.liam2019.utils.SimpleCommand;
import org.team1540.rooster.triggers.DPadAxis;

import static org.team1540.liam2019.utils.ChickenXboxController.XboxAxis;
import static org.team1540.liam2019.utils.ChickenXboxController.XboxButton;

public class OI {

    public static ChickenXboxController driver = new ChickenXboxController(0);
    public static Button zeroPointDrive = driver.getButton(XboxButton.Y);
    public static Button autoTest = driver.getButton(XboxButton.A);


    public static ChickenXboxController copilot = new ChickenXboxController(1);
    public static Button openGrabber = copilot.getButton(XboxButton.LB);
    public static Button closeGrabber = copilot.getButton(XboxButton.RB);

    public static Button floorIntake = copilot.getButton(XboxButton.A);
    public static Button dumpAndEjectBin = copilot.getButton(XboxButton.B);

    public static Button intakeUp = copilot.getButton(DPadAxis.UP);
    public static Button intakeYeet = copilot.getButton(DPadAxis.LEFT);
    public static Button intakeDown = copilot.getButton(DPadAxis.DOWN);

    public static Button manualIntakeBin = copilot.getButton(XboxButton.X);
    public static Button manualEjectBin = copilot.getButton(XboxButton.Y);

    public static Button enableManualWrist = new DualAxisButton(copilot, 0.15, XboxAxis.LEFT_Y.value);
    public static Button zeroWrist = copilot.getButton(XboxButton.BACK);

    public static void init() {
        initDriver();
        initCopilot();
    }

    private static void initDriver() {
        Command autoCommand = new Auto();
        autoTest.whenPressed(autoCommand);
        autoTest.whenReleased(new SimpleCommand(autoCommand::cancel));

    }

    private static void initCopilot() {
        openGrabber.whenPressed(new SimpleCommand(() -> Robot.grabber.setGrabbed(false)));
        closeGrabber.whenPressed(new SimpleCommand(() -> Robot.grabber.setGrabbed(true)));

        FloorIntake intakeCommand = new FloorIntake();
        floorIntake.whenPressed(intakeCommand);
        floorIntake.whenReleased(new SimpleCommand(intakeCommand::cancel));

        dumpAndEjectBin.whenPressed(new DumpAndEjectBin());

        intakeUp.whenPressed(new MoveWrist(MoveWrist.WristPosition.UP, 1));
        intakeYeet.whenPressed(new MoveWrist(MoveWrist.WristPosition.YEET, 1));
        intakeDown.whenPressed(new MoveWristToBottom());

        manualIntakeBin.whileHeld(new SetIntake(-0.5));
        manualEjectBin.whileHeld(new SetIntake(1));

        enableManualWrist.whileHeld(new ManualWrist());
        zeroWrist.whenPressed(new SimpleCommand(Robot.wrist::zero));
    }

    public static class FloorIntake extends CommandGroup {
        {
            addSequential(new SetGrabber(false));
            addParallel(new MoveWristToBottom());
            addSequential(new SensorIntakeBin(-0.5, 2));
            addSequential(new SetGrabber(true));
            addParallel(new SetIntakeForTime(-1, 0.15));
            addSequential(new MoveWrist(MoveWrist.WristPosition.CARRY, 1));
        }
    }

    public static class DumpAndEjectBin extends CommandGroup {
        {
            addSequential(new DumpBin());
            addSequential(new YeetBin());
        }
    }

    public static class DumpBin extends CommandGroup {
        {
            addSequential(new SetGrabber(true));
            addSequential(new MoveWrist(MoveWrist.WristPosition.BACK, 1));
//            addSequential(new MoveWrist(MoveWrist.WristPosition.SHAKE, 1));
//            addSequential(new MoveWrist(MoveWrist.WristPosition.BACK, 1));
        }
    }

    public static class YeetBin extends CommandGroup {
        {
            addSequential(new MoveWrist(MoveWrist.WristPosition.YEET, 1));
            addSequential(new SetIntakeForTime(1, 0.5));
            addSequential(new SetGrabber(false));
        }
    }
}
