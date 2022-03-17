package org.firstinspires.ftc.teamcode.Robots.Happy.Autonomous.Red;

import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.intakeConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.manipulatorConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.spinnerConfig;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Mechanisms.Arm.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.Manipulator;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;
import org.firstinspires.ftc.teamcode.Robots.Happy.Configuration;

@Autonomous(name = "Red52Pointer", group = "Red")
public class Red52Pointer extends EctoOpMode {

  // + DISTANCES
  Double forwardness = 11.0;
  Double backness = 11.0;

  Double rightness = 5.0;
  Double leftness = 5.0;

  // + TIMERS
  Double spinnerTimer = 4.0;


  // + TRAJECTORIES
  TrajectorySequence trajectoryInitializer;
  TrajectorySequence levelOne;
  TrajectorySequence levelTwo;
  TrajectorySequence levelThree;

  Pose2d startingPosition = new Pose2d(-36, -64, 0);

  Pose2d allianceShippingPosition = new Pose2d(-36, -23, Math.toRadians(0));

  Pose2d spinnerPosition = new Pose2d(-59, -55, Math.toRadians(90));

  Pose2d spinnerDuckPositioning = new Pose2d(-55, -40, Math.toRadians(270));
  Pose2d spinnerDuckPosition = new Pose2d(-55, -58, Math.toRadians(270));

  Pose2d wareHouseP1 = new Pose2d(-39, 2, Math.toRadians(0));
  Pose2d wareHouseP2 = new Pose2d(10, 2, Math.toRadians(0));
  Pose2d wareHouseP3 = new Pose2d(10, -62, Math.toRadians(0));
  Pose2d wareHouseP4 = new Pose2d(40, -77, Math.toRadians(0));
  Pose2d wareHouseP5 = new Pose2d(44, -54, Math.toRadians(90));

  // + Mechanisms
  SampleMecanumDrive drive;

  Arm arm;
  Manipulator manipulator;
  Spinner spinner;
  Intake intake;

  @Override
  public void initRobotClasses() {

    drive = new SampleMecanumDrive(hardwareMap);
    drive.setPoseEstimate(startingPosition);

    trajectoryInitializer =
        drive
            .trajectorySequenceBuilder(startingPosition)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
                })
            .addTemporalMarker(
                0.5,
                () -> {
                  arm.setPosition(Configuration.Mechanisms.Positions.arm.highPosition);
                })
            .addTemporalMarker(
                1,
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.up);
                })
            .lineToLinearHeading(allianceShippingPosition)
            .forward(forwardness)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(0.5);
                  spinner.turnOn(0.6);
                })
            .lineToLinearHeading(spinnerPosition)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
                  arm.setHomePosition();
                  manipulator.turnOff();
                })
            .strafeLeft(0.1)
            .waitSeconds(spinnerTimer)
            .lineToLinearHeading(spinnerDuckPositioning)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(-1);
                  intake.turnOn(-1);
                  spinner.turnOff();
                })
            .lineToLinearHeading(spinnerDuckPosition)
            .waitSeconds(1)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOff();
                  intake.turnOff();
                  arm.setPosition(Configuration.Mechanisms.Positions.arm.highPosition);
                })
            .lineToLinearHeading(allianceShippingPosition)
            .strafeRight(rightness)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.up);
                })
            .forward(forwardness)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(0.7);
                })
            .back(backness)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOff();
                  arm.setHomePosition();
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
                })
            .lineToLinearHeading(wareHouseP1)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.up);
                })
            .lineToLinearHeading(wareHouseP2)
            .lineToLinearHeading(wareHouseP3)
            .strafeRight(rightness)
            .lineToLinearHeading(wareHouseP4)
            .lineToLinearHeading(wareHouseP5)
            .build();

    arm = new Arm("arm", "Mechanism", armConfig);
    manipulator = new Manipulator("Manipulator", "Mechanism", manipulatorConfig);
    spinner = new Spinner("spinner", "Mechanism", spinnerConfig);
    intake = new Intake("intake", "Mechanism", intakeConfig);

    mechanismManager.addMechanism(arm);
    mechanismManager.addMechanism(manipulator);
    mechanismManager.addMechanism(spinner);
    mechanismManager.addMechanism(intake);
  }

  @Override
  public void initRobot() {}

  @Override
  public void startRobot() {
    arm.resetEncoder();
    drive.followTrajectorySequenceAsync(trajectoryInitializer);
  }

  @Override
  public void updateRobot(Double timeStep) {
    drive.update();
  }
}
