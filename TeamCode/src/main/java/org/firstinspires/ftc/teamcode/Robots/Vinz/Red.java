//
// Created by Neil Rodriguez 12/28/2021
//

package org.firstinspires.ftc.teamcode.Robots.Vinz;

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

@Autonomous(name = "Red Autonomous")
public class Red extends EctoOpMode {

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

    startingPosition = new Pose2d(12, -65.5, 0);
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

    // Adding Mechanisms 2 MM (Mechanism Manager)
    mechanismManager.addMechanism(manipulator);

    mechanismManager.addMechanism(arm);

    mechanismManager.addMechanism(intake);

    mechanismManager.addMechanism(spinner);

    // Inits Our Trajectory
    trajectory =
        drive

            // 1st Cycle
            .trajectorySequenceBuilder(startingPosition)
            .addDisplacementMarker(
                () -> {
                  // Levantar Brazo (300)
                })
            .lineToSplineHeading(allianceShippingHub)
            .addDisplacementMarker(
                () -> {
                  // Prender Manipulador
                })
            .lineToSplineHeading(startingPosition)
            .addDisplacementMarker(
                () -> {
                  // Apagar Manipulador
                  // Bajar Brazo (150)
                })
            .forward(15)
            .addDisplacementMarker(
                () -> {
                  // Bajar Brazo (0)
                })
            .forward(15)
            .addDisplacementMarker(
                () -> {
                  // Iniciar El Intake
                  // Iniciar El Manipulador
                })
            .back(4)
            .addDisplacementMarker(
                () -> {
                  // Apagar El Intake
                  // Apagar El Manipulador
                  // Subir Brazo (150)
                })
            .back(26)
            .addDisplacementMarker(
                () -> {
                  // Subir El Brazo (300)
                })

            // 2nd Cycle
            .lineToSplineHeading(allianceShippingHub)
            .addDisplacementMarker(
                () -> {
                  // Prender Manipulador
                })
            .lineToSplineHeading(startingPosition)
            .addDisplacementMarker(
                () -> {
                  // Apagar Manipulador
                  // Bajar Brazo (150)
                })
            .forward(15)
            .addDisplacementMarker(
                () -> {
                  // Bajar Brazo (0)
                })
            .forward(15)
            .addDisplacementMarker(
                () -> {
                  // Iniciar El Intake
                  // Iniciar El Manipulador
                })
            .back(4)
            .addDisplacementMarker(
                () -> {
                  // Apagar El Intake
                  // Apagar El Manipulador
                  // Subir Brazo (150)
                })
            .back(26)
            .addDisplacementMarker(
                () -> {
                  // Subir El Brazo (300)
                })

            // 3rd Cycle
            .lineToSplineHeading(allianceShippingHub)
            .addDisplacementMarker(
                () -> {
                  // Prender Manipulador
                })
            .waitSeconds(0.2)
            .addDisplacementMarker(
                () -> {
                  // Apagar Manipulador
                })
            .lineToSplineHeading(spinnerPos)
            .addDisplacementMarker(
                () -> {
                  // Apagar Manipulador
                })
            .waitSeconds(2)
            .addDisplacementMarker(
                () -> {
                  // Apagar Manipulador
                })
            .lineToSplineHeading(wareHouseP1)
            .lineToSplineHeading(wareHouseP2)
            // FIN
            .build();

    drive.followTrajectorySequenceAsync(trajectory);
  }

  @Override
  public void startRobot() {}

  @Override
  public void updateRobot(Double timeStep) {
    drive.update();
  }
}
