package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Mechanisms.Arm.ArmConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.MecanumConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.IntakeConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.SpinnerConfig;

public class RobotConfig {

    static public MecanumConfig mecanumConfig = new MecanumConfig("frontLeftMotor", "backLeftMotor", "frontRightMotor", "backRightMotor", Motor.GoBILDA.RPM_223);

    static public ArmConfig armConfig = new ArmConfig("armMotor", 1451.5, 13.5, Motor.GoBILDA.RPM_117);

    static public IntakeConfig intakeConfig = new IntakeConfig("intakeMotor", Motor.GoBILDA.RPM_435);

    static public SpinnerConfig spinnerConfig = new SpinnerConfig("spinnerMotor", Motor.GoBILDA.RPM_117);

}
