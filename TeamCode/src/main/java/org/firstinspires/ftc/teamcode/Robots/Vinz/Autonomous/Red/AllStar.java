//
// Created by Neil Rodriguez 12/28/2022
//

package org.firstinspires.ftc.teamcode.Robots.Vinz.Autonomous.Red;

import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.Mechanisms.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.Mechanisms.intakeConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.Mechanisms.manipulatorConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.Mechanisms.spinnerConfig;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Mechanisms.Arm.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.Manipulator;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;

@Autonomous(name = "gfjcjchvjlb")
public class AllStar extends EctoOpMode {

    // Chassis
    SampleMecanumDrive drive;

    // Mechanisms
    Manipulator manipulator;
    Arm arm;
    Intake intake;
    Spinner spinner;

    // Trajectories
    TrajectorySequence trajectory;

    // Robot Positions
    Pose2d startingPosition;
    Pose2d allianceShippingHub;
    Pose2d spinnerPos;
    Pose2d wareHouseP1;
    Pose2d wareHouseP2;

    @Override
    public void initRobotClasses() {
        // Autonomous Init Process
        drive = new SampleMecanumDrive(hardwareMap);

        startingPosition = new Pose2d(12, -62, 0);
        allianceShippingHub = new Pose2d(-12, -42, Math.toRadians(90));
        spinnerPos = new Pose2d(-52, -65.5, Math.toRadians(90));
        wareHouseP1 = new Pose2d(-40, -65.5, Math.toRadians(0));
        wareHouseP2 = new Pose2d(38, -65.5, Math.toRadians(0));

        drive.setPoseEstimate(startingPosition);

        // Mechanisms
        arm = new Arm("arm", "Mechanism", armConfig);
        manipulator = new Manipulator("Manipulator", "Mechanism", manipulatorConfig);
        intake = new Intake("intake", "Mechanism", intakeConfig);
        spinner = new Spinner("spinner", "Mechanism", spinnerConfig);
    }

    @Override
    public void initRobot() {

        mechanismManager.addMechanism(manipulator);
        mechanismManager.addMechanism(arm);
        mechanismManager.addMechanism(intake);
        mechanismManager.addMechanism(spinner);

        // Inits Our Trajectory
        trajectory =
                drive
                        .trajectorySequenceBuilder(startingPosition)
                        .forward(10)
                        .build();

    }

    @Override
    public void startRobot() {

        drive.followTrajectorySequenceAsync(trajectory);

    }

    @Override
    public void updateRobot(Double timeStep) {
        drive.update();
    }
}
