package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.MecanumConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank.TankConfig;

import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.SpinnerConfig;

import org.firstinspires.ftc.teamcode.Mechanisms.Intake.IntakeConfig;

public class RobotConfig {

    //Chassis Config
//    static public TankConfig tankConfig = new TankConfig("frontLeftMotor", "backLeftMotor", "frontRightMotor", "backRightMotor", Motor.GoBILDA.RPM_223);
    static public MecanumConfig mecanumConfig = new MecanumConfig("frontLeftMotor", "backLeftMotor", "frontRightMotor", "backRightMotor", Motor.GoBILDA.RPM_223);

    //Mechanisms Config    
    static public IntakeConfig intakeConfig = new IntakeConfig("intakeMotor", Motor.GoBILDA.RPM_223);
    
    static public SpinnerConfig spinnerConfig = new SpinnerConfig("spinnerMotor", Motor.GoBILDA.RPM_223);

}