//
// Created by Neil Rodriguez 12/28/2022
//

package org.firstinspires.ftc.teamcode.Robots.Vinz.Autonomous.Red;

import static org.firstinspires.ftc.teamcode.Robots.Vinz.Autonomous.Red.AllStar.State.TRAJ1;
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

@Autonomous(name = "Red-AllStar")
public class AllStar extends EctoOpMode {
  enum State {
    TRAJ1,
    TRAJ2,
    TRAJ3,
  }

  // Chassis
  SampleMecanumDrive drive;

  // Mechanisms
  Manipulator manipulator;
  Arm arm;
  Intake intake;
  Spinner spinner;

  // Trajectories
  TrajectorySequence trajectoryOne;
  TrajectorySequence trajectoryTwo;
  TrajectorySequence trajectoryThree;

  // Robot Positions
  Pose2d startPos;
  Pose2d allianceShippingHubPos;
  Pose2d allianceShippingHubP3Pos;
  Pose2d spinnerPos;
  Pose2d wareHouseP1Pos;
  Pose2d wareHouseP2Pos;

  // Arm Positions
  int low = 20;
  int medium = 50;
  int high = 120;

  State currentState = TRAJ1;

  @Override
  public void initRobotClasses() {
    // Autonomous Init Process
    drive = new SampleMecanumDrive(hardwareMap);

    startPos = new Pose2d(12, -64, 0);
    allianceShippingHubPos = new Pose2d(-12, -42, Math.toRadians(90));
    allianceShippingHubP3Pos = new Pose2d(-8, -42, Math.toRadians(90));
    wareHouseP1Pos = new Pose2d(-20, -66);
    wareHouseP2Pos = new Pose2d(48, -66);
    spinnerPos = new Pose2d(-54, -64, Math.toRadians(90));

    drive.setPoseEstimate(startPos);

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

    trajectoryOne =
        drive
            .trajectorySequenceBuilder(startPos)
            //            .addDisplacementMarker(() -> arm.setPosition(medium))

            // 1st Cycle
            .lineToSplineHeading(allianceShippingHubPos)
            .addDisplacementMarker(
                () -> {
                  //                  arm.setPosition(high);
                  manipulator.turnOn(1);
                })
            .lineToSplineHeading(startPos)
            .addDisplacementMarker(
                () -> {
                  //                  arm.setPosition(medium);
                  manipulator.turnOff();
                })
            .lineToSplineHeading(wareHouseP2Pos)
            .addDisplacementMarker(
                () -> {
                  //                  arm.setPosition(low);
                  manipulator.turnOn(-1);
                  intake.turnOn(-1);
                })
            .lineToSplineHeading(startPos)
            .addDisplacementMarker(
                () -> {
                  //                  arm.setPosition(medium);
                  manipulator.turnOff();
                  intake.turnOff();
                })
            .lineToSplineHeading(allianceShippingHubPos)
            .strafeRight(5)
            // FIN
            .build();

    trajectoryTwo =
        drive
            .trajectorySequenceBuilder(allianceShippingHubPos)
            // 2nd Cycle

            .addDisplacementMarker(
                () -> {
                  //                  arm.setPosition(high);
                  manipulator.turnOn(1);
                })
            .lineToSplineHeading(startPos)
            .addDisplacementMarker(
                () -> {
                  //                  arm.setPosition(medium);
                  manipulator.turnOff();
                })
            .lineToSplineHeading(wareHouseP2Pos)
            .addDisplacementMarker(
                () -> {
                  //                  arm.setPosition(low);
                  manipulator.turnOn(-1);
                  intake.turnOn(-1);
                })
            .lineToSplineHeading(startPos)
            .addDisplacementMarker(
                () -> {
                  //                  arm.setPosition(medium);
                  manipulator.turnOff();
                  intake.turnOff();
                })
            .lineToSplineHeading(allianceShippingHubPos)
            .strafeRight(5)
            .build();

    trajectoryThree =
        drive
            .trajectorySequenceBuilder(trajectoryTwo.end())
            // 3rd Cycle

            .addDisplacementMarker(
                () -> {
                  spinner.turnOn(-1);
                  manipulator.turnOn(1);
                  //                  arm.setPosition(high);
                })

            // Spinner
            .lineToSplineHeading(spinnerPos)
            .waitSeconds(1.5)
            .addDisplacementMarker(
                () -> {
                  //                  arm.stopMechanism();
                  manipulator.turnOff();
                  spinner.turnOff();
                })

            // Warehouse Parking
            .lineToSplineHeading(wareHouseP1Pos)
            .lineToSplineHeading(wareHouseP2Pos)
            .build();

    drive.followTrajectorySequenceAsync(trajectoryOne);
  }

  @Override
  public void startRobot() {}

  @Override
  public void updateRobot(Double timeStep) {

    switch (currentState) {
      case TRAJ1:
        if (!drive.isBusy()) {
          currentState = State.TRAJ2;
          drive.followTrajectorySequenceAsync(trajectoryTwo);
        }
      case TRAJ2:
        if (!drive.isBusy()) {
          currentState = State.TRAJ2;
          drive.followTrajectorySequenceAsync(trajectoryThree);
        }
      case TRAJ3:
        if (!drive.isBusy()) {
          currentState = State.TRAJ3;
        }
    }
    drive.update();
  }
}
