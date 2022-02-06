package org.firstinspires.ftc.teamcode.Robots.Vinz.TeleOperated;


import com.arcrobotics.ftclib.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;

@TeleOp(name = "ColorTest")
public class ColorTest extends EctoOpMode {

    ColorSensor colorSensor;

    @Override
    public void initRobotClasses() {

       colorSensor = new ColorSensor(hardwareMap, "ColorSensor");
    }

    @Override
    public void initRobot() {
    }

    @Override
    public void startRobot() {

    }

    @Override
    public void updateRobot(Double timeStep) {
        telemetry.addData("Red Value,",   colorSensor.red());

    }
}