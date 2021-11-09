package org.firstinspires.ftc.teamcode.OperatingModes.TeleOp;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank.TankConfig;

public class RobotConfig {

        static public TankConfig tankConfig = new TankConfig("frontLeftMotor", "backLeftMotor", "frontRightMotor", "backRightMotor", Motor.GoBILDA.RPM_223);

}
