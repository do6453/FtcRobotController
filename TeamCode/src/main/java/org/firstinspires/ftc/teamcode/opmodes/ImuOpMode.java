package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.mechanisms.ImuTest;

@TeleOp
public class ImuOpMode extends OpMode {

    ImuTest imuTest = new ImuTest();

    @Override
    public void init() {
        imuTest.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Heading: ", imuTest.getHeading(AngleUnit.DEGREES));
        telemetry.addData("Yaw:     ", imuTest.getYaw(AngleUnit.DEGREES));
        telemetry.addData("Pitch:   ", imuTest.getPitch(AngleUnit.DEGREES));
        telemetry.addData("Roll:    ", imuTest.getRoll(AngleUnit.DEGREES));
        telemetry.update();
    }
}
