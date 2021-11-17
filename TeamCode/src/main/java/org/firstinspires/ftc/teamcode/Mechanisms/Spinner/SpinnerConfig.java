
package org.firstinspires.ftc.teamcode.Mechanisms.Spinner;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.intake.SpinnerConfig;

public class Spinner extends EctoMechanism {

    public Spinner(String moduleName, String moduleType, org.firstinspires.ftc.teamcode.Mechanisms.intake.SpinnerConfig config) {
        super(moduleName, moduleType);
        spinnerConfig = config;
    }
