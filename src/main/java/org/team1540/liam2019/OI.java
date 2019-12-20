package org.team1540.liam2019;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
import org.team1540.liam2019.commands.SetIntakeUntilCaught;
import org.team1540.liam2019.commands.SetWrist;
import org.team1540.liam2019.utils.ChickenXboxController;
import org.team1540.liam2019.utils.SimpleCommand;

public class OI {

    static double joyAngleR;

    public static ChickenXboxController driver = new ChickenXboxController(0);
    public static Button buttonA = driver.getButton(ChickenXboxController.XboxButton.A);
    public static Button buttonB = driver.getButton(ChickenXboxController.XboxButton.B);
    public static Button buttonX = driver.getButton(ChickenXboxController.XboxButton.X);
    public static Button buttonY = driver.getButton(ChickenXboxController.XboxButton.Y);
    public static Button LB = driver.getButton(ChickenXboxController.XboxButton.LB);
    public static Button RB = driver.getButton(ChickenXboxController.XboxButton.RB);

    public static void init() {
        buttonA.whenPressed(new SimpleCommand(() -> Robot.grabber.toggleGrabbed(), Robot.grabber));

        LB.whenPressed(new SetIntakeUntilCaught(-0.5));
        RB.whenPressed(new SimpleCommand(() -> Robot.intake.set(0.5), Robot.intake));
        RB.whenReleased(new SimpleCommand(() -> Robot.intake.set(0), Robot.intake));

        buttonB.whenPressed(new SetWrist(true));
        buttonB.whenReleased(new SetWrist(false));
    }

}
