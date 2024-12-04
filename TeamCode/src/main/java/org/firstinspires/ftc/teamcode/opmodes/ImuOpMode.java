package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.BuildConfig;
import org.firstinspires.ftc.teamcode.LogFile;
import org.firstinspires.ftc.teamcode.gamepad.InputAutoMapper;
import org.firstinspires.ftc.teamcode.gamepad.InputHandler;
import org.firstinspires.ftc.teamcode.mechanisms.ImuTest;

@TeleOp
public class ImuOpMode extends OpMode {

    InputHandler inputHandler;

    ImuTest imuTest = new ImuTest();
    LogFile logFile = null;

    @Override
    public void init() {
        imuTest.init(hardwareMap);
        telemetry.addData("Compiled on:", BuildConfig.COMPILATION_DATE);

        inputHandler = InputAutoMapper.normal.autoMap(this);

        logFile = new LogFile();
        telemetry.addData("Path: ", logFile.fullPath);
        telemetry.update();
    }

    @Override
    public void loop() {
        handleInput();

        telemetry.addData("Heading: ", imuTest.getHeading(AngleUnit.DEGREES));
        telemetry.addData("Yaw:     ", imuTest.getYaw(AngleUnit.DEGREES));
        telemetry.addData("Pitch:   ", imuTest.getPitch(AngleUnit.DEGREES));
        telemetry.addData("Roll:    ", imuTest.getRoll(AngleUnit.DEGREES));
        telemetry.update();
    }

    @Override
    public void stop() {
        logFile.close();
    }
    public void handleInput() {
        inputHandler.loop();

        if(inputHandler.up("D1:Y")){
            logFile.log( "Heading", imuTest.getHeading(AngleUnit.DEGREES));
        } else if(inputHandler.up("D1:X")){
            logFile.log( "Yaw", imuTest.getYaw(AngleUnit.DEGREES));
        } else if(inputHandler.up("D1:A")){
            logFile.log( "Pitch", imuTest.getPitch(AngleUnit.DEGREES));
        } else if(inputHandler.up("D1:B")){
            logFile.log( "Roll", imuTest.getRoll(AngleUnit.DEGREES));
        }
    }
}