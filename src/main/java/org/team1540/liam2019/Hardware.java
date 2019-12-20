package org.team1540.liam2019;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Solenoid;
import org.team1540.liam2019.wrappers.NavX;
import org.team1540.rooster.wrappers.ChickenTalon;

public class Hardware {

    public static CANSparkMax leftA;
    public static CANSparkMax leftB;
    public static CANSparkMax rightA;
    public static CANSparkMax rightB;

    public static ChickenTalon wrist;
    public static ChickenTalon leftIntake;
    public static ChickenTalon rightIntake;
    public static Solenoid arms;

    public static AnalogInput intakeSensor;
    public static NavX navx;

    public static void init() {
        initDrive();
        initGyro();
        initIntake();
        initWrist();
    }

    static void initDrive() {
        rightA = new CANSparkMax(1, MotorType.kBrushless);
        rightB = new CANSparkMax(2, MotorType.kBrushless);
        leftA = new CANSparkMax(3, MotorType.kBrushless);
        leftB = new CANSparkMax(4, MotorType.kBrushless);

        rightA.setInverted(true);
        leftA.setInverted(false);

        rightB.follow(rightA);
        leftB.follow(leftA);
    }

    static void initGyro() {
        navx = new NavX(Port.kMXP);
    }

    static void initIntake() {
        // Intake
        leftIntake = new ChickenTalon(6);
        rightIntake = new ChickenTalon(7);
        rightIntake.follow(leftIntake);


        leftIntake.setInverted(true);
        rightIntake.setInverted(false);

        leftIntake.setBrake(true);
        rightIntake.setBrake(true);

        // Arms
        arms = new Solenoid(0);

        // Sensor
        intakeSensor = new AnalogInput(0);
    }

    static void initWrist() {
        wrist = new ChickenTalon(5);
        wrist.setBrake(true);

        wrist.config_kP(0, 3);
        wrist.config_kI(0, 0.01);
        wrist.config_IntegralZone(0, 100);
        wrist.config_kD(0, 0);
        wrist.config_kF(0, 0);
        wrist.configClosedLoopPeakOutput(0, 0.9);
        wrist.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        wrist.setSelectedSensorPosition(0);

    }
}
