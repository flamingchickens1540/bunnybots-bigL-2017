package org.team1540.liam2019;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Solenoid;
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
    public static AHRS gyro;

    public static void init() {
        initDrive();
        initGyro();
        initIntake();
    }

    static void initDrive() {
        rightA = new CANSparkMax(1, MotorType.kBrushless);
        rightB = new CANSparkMax(2, MotorType.kBrushless);
        leftA = new CANSparkMax(3, MotorType.kBrushless);
        leftB = new CANSparkMax(4, MotorType.kBrushless);

        rightB.follow(rightA);
        leftB.follow(leftA);
    }

    static void initGyro() {
        gyro = new AHRS(Port.kMXP);
    }

    static void initIntake() {
        wrist = new ChickenTalon(5);
        leftIntake = new ChickenTalon(6);
        rightIntake = new ChickenTalon(7);
        intakeSensor = new AnalogInput(0);

        rightIntake.follow(leftIntake);
        rightIntake.setInverted(true);
        wrist.config_kP(0, 1);
        wrist.config_kI(0, 0);
        wrist.config_kD(0, 1);
        wrist.setSelectedSensorPosition(0);

        arms = new Solenoid(0);
    }
}
