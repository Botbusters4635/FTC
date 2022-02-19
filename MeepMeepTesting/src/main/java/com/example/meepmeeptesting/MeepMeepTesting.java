package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
  public static void main(String[] args) {
    MeepMeep meepMeep = new MeepMeep(600);

    Pose2d startingPosition = new Pose2d(-35, 64, 0);
    Pose2d allianceShippingHubPosition = new Pose2d(-44, 22, Math.toRadians(0));
    Pose2d spinnerPosition = new Pose2d(-59, 55, Math.toRadians(0));
    Pose2d storageUnitPosition = new Pose2d(-60, 38, Math.toRadians(270));
    Pose2d warehouseP1Position = new Pose2d(-12, 40, Math.toRadians(0));
    Pose2d warehouseP2Position = new Pose2d(10, 63, Math.toRadians(0));
    Pose2d warehouseEndPosition = new Pose2d(39, 53, Math.toRadians(270));

    RoadRunnerBotEntity bot =
        new DefaultBotBuilder(meepMeep)
            .setConstraints(40, 37, 12.5, Math.toRadians(214.92695399999997), 10)
            .setDimensions(13.5, 15.5)
            .followTrajectorySequence(
                drive ->
                    drive
                        .trajectorySequenceBuilder(startingPosition)
                        .lineToLinearHeading(allianceShippingHubPosition)
                        .addDisplacementMarker(() -> {})
                        .forward(6)
                        .waitSeconds(1)
                        .back(0.5)
                        .addDisplacementMarker(() -> {})
                        .lineToLinearHeading(spinnerPosition)
                        .addDisplacementMarker(() -> {})
                        .strafeLeft(0.1)
                        .waitSeconds(4.5)
                        .lineToLinearHeading(storageUnitPosition)

                        // + FIN
                        .build());

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.80f)
        .addEntity(bot)
        .start();
  }
}
