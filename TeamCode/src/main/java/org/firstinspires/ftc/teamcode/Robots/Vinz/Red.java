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

  SampleMecanumDrive drive;

  Manipulator manipulator;

  Arm arm;

  Intake intake;

  Spinner spinner;

  TrajectorySequence trajectory;

  // Robot Positions
  Pose2d startingPosition;
  Pose2d allianceShippingHub;

  @Override
  public void initRobotClasses() {
    // Autonomous Init Process
    drive = new SampleMecanumDrive(hardwareMap);

    startingPosition = new Pose2d(12, -65.5, 0);
    allianceShippingHub = new Pose2d(-12, -42, Math.toRadians(90));

    drive.setPoseEstimate(startingPosition);

    //Inits Our Trajectory
    trajectory =
            drive
                    .trajectorySequenceBuilder(startingPosition)
                    // 1st Cycle
                    .lineToSplineHeading(allianceShippingHub)
                    .lineToSplineHeading(startingPosition)
                    .forward(30)
                    .back(30)
                    // 2nd Cycle
                    .lineToSplineHeading(allianceShippingHub)
                    .lineToSplineHeading(startingPosition)
                    .forward(30)
                    .back(30)
                    // 3nd Cycle
                    .lineToSplineHeading(allianceShippingHub)
                    .lineToSplineHeading(startingPosition)
                    .forward(30)
                    // FIN
                    .build();

    // Mechanisms
    arm = new Arm("arm", "Mechanism", armConfig);
    manipulator = new Manipulator("Manipulator", "Mechanism", manipulatorConfig);
    intake = new Intake("intake", "Mechanism", intakeConfig);
    spinner = new Spinner("spinner", "Mechanism", spinnerConfig);
  }

  @Override
  public void initRobot() {
    //Adding Mechanisms 2 MM (Mechanism Manager)
    mechanismManager.addMechanism(manipulator);

    mechanismManager.addMechanism(arm);

    mechanismManager.addMechanism(intake);

    mechanismManager.addMechanism(spinner);



    drive.followTrajectorySequenceAsync(trajectory);
  }

  @Override
  public void startRobot() {}

  @Override
  public void updateRobot(Double timeStep) {
    drive.update();
  }
}
