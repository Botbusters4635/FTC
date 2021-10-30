//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Core;

public abstract class Robot extends Module{
    private String name;

    public Robot(String modName, String modType) {
        super(modName, modType);
        Module mod = new Module("Chassis", "System");
    }

    //Every Robot should be able to do this functions
    public abstract void robotInit();
    public abstract void disableInit();

    public abstract void robotUpdate();
    public abstract void disableUpdate();
}
