//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Core;

import android.util.Log;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Module {
    private String modName;
    private String modType;

    private Telemetry te

    public Module(String moduleName, String moduleType) {
        this.modName = moduleName;
        this.modType = moduleType;
        Log.i("Creating " + moduleType + " ", "Module name: " + moduleName);
    }


}
