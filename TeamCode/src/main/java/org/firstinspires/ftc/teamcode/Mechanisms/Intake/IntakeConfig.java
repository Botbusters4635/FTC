package org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class IntakeConfig {

    public IntakeConfig(String intakeMotorId, String leftServoId, String rightServoId, int minServoAngle, int maxServoAngle, Motor.GoBILDA gobildaMotorType) {
        getIntakeMotorId = intakeMotorId;
        getGobildaType = gobildaMotorType;
        getLeftServoId = leftServoId;
        getRightServoId = rightServoId;
        getMaxServoAngle = maxServoAngle;
        getMinServoAngle = minServoAngle;
    }

    String getLeftServoId;
    String getRightServoId;
    String getIntakeMotorId;
    Motor.GoBILDA getGobildaType;

    int getMinServoAngle;
    int getMaxServoAngle;

}
