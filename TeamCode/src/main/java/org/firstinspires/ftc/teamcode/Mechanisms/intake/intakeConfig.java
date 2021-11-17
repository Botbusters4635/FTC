
package org.firstinspires.ftc.teamcode.Mechanisms.intake;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.intake.IntakeConfig;

public class Intake extends EctoMechanism {

    public Intake (String moduleName, String moduleType, org.firstinspires.ftc.teamcode.Mechanisms.intake.IntakeConfig config) {
        super(moduleName, moduleType);

        intakeConfig = config;
    }
