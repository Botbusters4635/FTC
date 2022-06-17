package org.firstinspires.ftc.teamcode.Robots.MiniHog.Autonomous;

import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.clawConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.intakeConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.leftArmConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.rightArmConfig;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor.SimpleMotorMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.DualServoMechanism;

@Autonomous(name = "Auto", group = "Auto")
public class Auto extends EctoOpMode {

    RobotState currentRobotState = RobotState.Running;

    // + Trajectory
    TrajectorySequence trajectory;

    // + Positions
    Pose2d startingPosition = new Pose2d(-58, -58, 0);
    Pose2d centerHubPosition = new Pose2d(0, -40, 90);
    Pose2d preFreightZonePosition = new Pose2d(30, -47, 0);
    Pose2d endPosition = new Pose2d(30, -47, 90);

    // Mechanisms
    SampleMecanumDrive drive;

    SimpleMotorMechanism rightArm;
    SimpleMotorMechanism leftArm;
    DualServoMechanism claw;
    SimpleMotorMechanism intake;



    @Override
    public void initRobotClasses() {

        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startingPosition);


        trajectory =
                drive
                        .trajectorySequenceBuilder(startingPosition)
                        .addDisplacementMarker(() -> {
                            leftArm.set(1);
                            rightArm.set(1);
                        })
                        .forward(0.1)
                        .waitSeconds(0.9)
                        .addDisplacementMarker(() -> {
                            leftArm.set(0.1);
                            rightArm.set(0.1);
                        })
                        .forward(22)
                        .turn(Math.toRadians(85))
                        .forward(22)
                        .addDisplacementMarker(() -> {
                            intake.set(-1);
                        })
                        .back(0.05)
                        .waitSeconds(2)
                        .addDisplacementMarker(() -> {
                            intake.set(0);
                            leftArm.set(0);
                            rightArm.set(0);
                        })
                        .back(28)
                        .strafeLeft(40)
                        .build();

        rightArm = new SimpleMotorMechanism("rightArm", "Mechanism", rightArmConfig);
        leftArm = new SimpleMotorMechanism("leftArm", "Mechanism", leftArmConfig);
        claw = new DualServoMechanism("Claw", "Mechanism", clawConfig);
        intake = new SimpleMotorMechanism("intake", "Mechanism", intakeConfig);

        mechanismManager.addMechanism(rightArm);
        mechanismManager.addMechanism(leftArm);
        mechanismManager.addMechanism(claw);
        mechanismManager.addMechanism(intake);
    }

    @Override
    public void initRobot() {}

    @Override
    public void startRobot() {
        drive.followTrajectorySequenceAsync(trajectory);
    }

    @Override
    public void updateRobot(Double timeStep) {

        switch (currentRobotState) {
            case Running:
                if (!drive.isBusy()) {
                    currentRobotState = RobotState.IdleMode;
                }
                break;

            case IdleMode:
                if (!drive.isBusy()) {
                    mechanismManager.stopMechanisms();
                }
                break;
        }

        drive.update();
    }

    private enum RobotState {
        Running,
        IdleMode
    }
}