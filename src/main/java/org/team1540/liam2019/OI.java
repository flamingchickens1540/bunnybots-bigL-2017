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

    public static void init() {
        buttonA.whenPressed(new SimpleCommand(() -> Robot.grabber.toggleGrabbed()));

        buttonB.whenPressed(new SetIntakeUntilCaught(-1));

        buttonX.whenPressed(new SetWrist(true));
        buttonX.whenReleased(new SetWrist(false));
    }

    //returns an updated value if the joystick is not in the center
    public static double getAngleR() {
        if (!(driver.getY(GenericHID.Hand.kRight) < 0.1 && driver.getX(GenericHID.Hand.kRight) < 0.1)) {
            joyAngleR = Math.atan2(driver.getY(GenericHID.Hand.kRight), driver.getX(GenericHID.Hand.kRight));
        }
        return joyAngleR;
    }

    public static double getThrottleL() {
        return driver.getY(GenericHID.Hand.kLeft);
    }

}
