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

    RoadRunnerBotEntity bot = new DefaultBotBuilder(meepMeep)
            .setConstraints(40, 37, 12.5, Math.toRadians(214.92695399999997), 10)
            .setDimensions(13.5, 15.5)


            .followTrajectorySequence(
                drive ->
                    drive.trajectorySequenceBuilder(Start)
                            .splineToConstantHeading(new Vector2d(-12.7, -39.2), Math.toRadians(90))
                            .splineTo(new Vector2d(.8,-61.8), Math.toRadians(0))
                        .build()

  );

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.80f)
        .addEntity(bot)
        .start();
  }
}
