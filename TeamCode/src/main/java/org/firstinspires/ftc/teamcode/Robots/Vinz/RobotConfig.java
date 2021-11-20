package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank.TankConfig;

import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.SpinnerConfig;

import org.firstinspires.ftc.teamcode.Mechanisms.intake.intakeConfig ;

public class RobotConfig {

    static public TankConfig tankConfig = new TankConfig("frontLeftMotor", "backLeftMotor", "frontRightMotor", "backRightMotor", Motor.GoBILDA.RPM_223);

    static public SpinnerConfig spinnerConfig = new SpinnerConfig("spinnerMotor", Motor.GoBILDA.RPM_223);

    static public IntakeConfig intakeConfig = new IntakeConfig("intakeMotor", Motor.GoBILDA.RPM_223);
}