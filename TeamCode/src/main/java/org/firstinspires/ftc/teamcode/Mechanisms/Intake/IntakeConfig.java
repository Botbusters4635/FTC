package org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class IntakeConfig {

    public IntakeConfig(String intakeMotorId, String leftServoId, String rightServoId, Motor.GoBILDA gobildaMotorType) {
        getIntakeMotorId = intakeMotorId;
        getGobildaType = gobildaMotorType;
        getLeftServoId = leftServoId;
        getRightServoId = rightServoId;
    }

    String getLeftServoId;
    String getRightServoId;
    String getIntakeMotorId;
    Motor.GoBILDA getGobildaType;

}
