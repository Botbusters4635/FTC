package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
  public static void main(String[] args) {
    MeepMeep meepMeep = new MeepMeep(600);
    Pose2d Start = new Pose2d(7.8, -61.8, Math.toRadians(90));
    Pose2d Start2 = new Pose2d(-35, -61.8, Math.toRadians(90));

    RoadRunnerBotEntity bot = new DefaultBotBuilder(meepMeep)
            .setConstraints(40, 37, 12.5, Math.toRadians(214.92695399999997), 10)
            .setDimensions(13.5, 15.5)


            .followTrajectorySequence(
                drive ->
                    drive.trajectorySequenceBuilder(Start)

                            //first cicle
                            .lineToConstantHeading(new Vector2d(-12.7, -39.2))
                            .waitSeconds(.2)
                            .setReversed(true)
                            .lineToSplineHeading(new Pose2d(.8, -64, Math.toRadians(0)))
                            .forward(39.6)
                            .waitSeconds(.2)
                            //Second cicle
                            .back(39.6)
                            .setReversed(true)
                            .lineToSplineHeading(new Pose2d(-12, -39.2, Math.toRadians(90)))
                            .waitSeconds(.2)
                            .lineToSplineHeading(new Pose2d(.8, -64, Math.toRadians(0)))
                            .forward(39.6)
                            .waitSeconds(.2)

                            //Third cicle
                            .back(39.6)
                            .setReversed(true)
                            .lineToSplineHeading(new Pose2d(-12, -39.2, Math.toRadians(90)))
                            .waitSeconds(.2)
                            .lineToSplineHeading(new Pose2d(.8, -64, Math.toRadians(0)))

                            //Fourth cicle
                            .back(39.6)
                            .setReversed(true)
                            .lineToSplineHeading(new Pose2d(-12, -39.2, Math.toRadians(90)))
                            .waitSeconds(.2)
                            .lineToSplineHeading(new Pose2d(.8, -64, Math.toRadians(0)))
                            //Ware House parking
                            .forward(39.6)//parking for alliance hub

                           .build()
  );

    RoadRunnerBotEntity bot2 = new DefaultBotBuilder(meepMeep)
            .setConstraints(40, 37, 12.5, Math.toRadians(214.92695399999997), 10)
            .setDimensions(13.5, 15.5)

            .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(Start2)
                                    .waitSeconds(3)
                                    .lineToConstantHeading(new Vector2d(-12.7, -39.2))
                                    .waitSeconds(.2)
                                    .setReversed(true)
                                    .lineToConstantHeading(new Vector2d(-35, -61.8))
                                    .strafeLeft(17.4)
                                    .waitSeconds(7)
                                    .lineToSplineHeading(new Pose2d(-47.2,-43.2, Math.toRadians(270)))
                                    .forward(18.8)
                                    .lineToSplineHeading(new Pose2d(-60.2,-35.3, Math.toRadians(0)))
                    .build()
            );

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.80f)
        .addEntity(bot)
            .addEntity(bot2)
        .start();
  }
}
