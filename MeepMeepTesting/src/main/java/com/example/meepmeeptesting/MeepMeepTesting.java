package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
  public static void main(String[] args) {
    MeepMeep meepMeep = new MeepMeep(600);

    Pose2d startingPosition = new Pose2d(-35, -64, 0);

    Pose2d allianceShippingPositioning = new Pose2d(-35, -23, Math.toRadians(0));
    Pose2d allianceShippingHubPosition = new Pose2d(-30, -23, Math.toRadians(0));
    Pose2d spinnerPosition = new Pose2d(-59, -55, Math.toRadians(90));
    Pose2d rubberDuckPositioning = new Pose2d(-54, -39, Math.toRadians(270));
    Pose2d rubberDuckPosition = new Pose2d(-55, -58, Math.toRadians(270));
    Pose2d barcodePrePositioning = new Pose2d(-17, 0, Math.toRadians(0));
    Pose2d barcodePositioning = new Pose2d(4,0, Math.toRadians(0));
    Pose2d barcodeDuckPosition = new Pose2d(12,-35, Math.toRadians(270));
    Pose2d barcodeDuckDelivery = new Pose2d(0, -39, Math.toRadians(135));
    Pose2d preloadedBoxPositioning = new Pose2d(-6, -52, Math.toRadians(240));
    Pose2d wareHousePrePositioning = new Pose2d(12, -64, Math.toRadians(0));
    Pose2d wareHousePosition = new Pose2d(50, -64, Math.toRadians(0));

    RoadRunnerBotEntity bot =
        new DefaultBotBuilder(meepMeep)
            .setConstraints(40, 37, 12.5, Math.toRadians(214.92695399999997), 10)
            .setDimensions(13.5, 15.5)
            .followTrajectorySequence(
                drive ->
                        drive
                                .trajectorySequenceBuilder(startingPosition)
                                .lineToLinearHeading(allianceShippingPositioning)
                                .lineToLinearHeading(allianceShippingHubPosition)
                                .lineToLinearHeading(spinnerPosition)
                                .lineToLinearHeading(rubberDuckPositioning)
                                .setReversed(true)
                                .lineToLinearHeading(rubberDuckPosition)
                                .lineToLinearHeading(allianceShippingPositioning)
                                .lineToLinearHeading(allianceShippingHubPosition)
                                .lineToLinearHeading(allianceShippingPositioning)
                                .lineToLinearHeading(barcodePrePositioning)
                                .lineToLinearHeading(barcodePositioning)
                                .lineToLinearHeading(barcodeDuckPosition)
                                .lineToLinearHeading(barcodeDuckDelivery)
                                .lineToLinearHeading(preloadedBoxPositioning)
                                .lineToLinearHeading(barcodeDuckDelivery)
                                .lineToLinearHeading(wareHousePrePositioning)
                                .lineToLinearHeading(wareHousePosition)
                                .build());

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.80f)
        .addEntity(bot)
        .start();
  }
}
