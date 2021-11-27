package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.MecanumConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank.TankConfig;

import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.SpinnerConfig;

import org.firstinspires.ftc.teamcode.Mechanisms.Intake.IntakeConfig;

public class RobotConfig {
    static public MecanumConfig mecanumConfig = new MecanumConfig("frontLeftMotor", "backLeftMotor", "frontRightMotor", "backRightMotor", Motor.GoBILDA.RPM_223);
}