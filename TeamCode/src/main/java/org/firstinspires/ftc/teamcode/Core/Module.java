//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Core;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Module {

    private Telemetry telemetry;

    public Module(String moduleName, Object moduleType) {

        telemetry.addData(moduleName, moduleType);

    }

}
