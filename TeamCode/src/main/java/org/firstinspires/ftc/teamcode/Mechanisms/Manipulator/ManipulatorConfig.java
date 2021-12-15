package org.firstinspires.ftc.teamcode.Mechanisms.Manipulator;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class ManipulatorConfig {

    public ManipulatorConfig(String manipulatorMotorId, Motor.GoBILDA GobildaMotorType) {
        getManipulatorMotorId = manipulatorMotorId;
        getGobildaType = GobildaMotorType;
    }

    String getManipulatorMotorId;
    Motor.GoBILDA getGobildaType;

}
