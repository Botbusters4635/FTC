package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
  public static void main(String[] args) {
    MeepMeep meepMeep = new MeepMeep(800);
    Pose2d startingPosition = new Pose2d(12, -65.5, 0);

    RoadRunnerBotEntity myBot =
        new DefaultBotBuilder(meepMeep)
            // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
            .setConstraints(40, 37, 13.241691158998306, Math.toRadians(214.92695399999997), 10)
            .followTrajectorySequence(
                drive ->
                    drive
                        // 1st Cycle
                        .trajectorySequenceBuilder(startingPosition)
                        .lineToSplineHeading(new Pose2d(-12, -40, Math.toRadians(90)))
                        .lineToSplineHeading(startingPosition)
                        .forward(30)
                        .back(30)
                        // 2nd Cycle
                        .lineToSplineHeading(new Pose2d(-12, -40, Math.toRadians(90)))
                        .lineToSplineHeading(startingPosition)
                        .forward(30)
                        .back(30)
                        // 3rd Cycle
                        .lineToSplineHeading(new Pose2d(-12, -40, Math.toRadians(90)))
                        .lineToSplineHeading(startingPosition)
                        // Spinner
                        .lineToSplineHeading(new Pose2d(-52, -65.5, Math.toRadians(90)))
                        .waitSeconds(2)
                        .lineToSplineHeading(new Pose2d(-16, -65.5, Math.toRadians(0)))
                        .lineToSplineHeading(new Pose2d(42, -65.5, Math.toRadians(0)))
                        // FIN
                        .build());

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.95f)
        .addEntity(myBot)
        .start();
  }
}
