package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import com.acmerobotics.roadrunner.ftc.LazyImu;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class ImuTest {

    public IMU imu;
    public LazyImu lazyImu;
    Orientation angles;

    public RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
            RevHubOrientationOnRobot.LogoFacingDirection.UP;
    public RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

    public void init(HardwareMap hwMap) {

        lazyImu = new LazyImu(hwMap, "imu", new RevHubOrientationOnRobot(
                logoFacingDirection, usbFacingDirection));

        imu = lazyImu.get();
    }

    private Orientation getOrientation(AngleUnit units) {
        angles = imu.getRobotOrientation(AxesReference.INTRINSIC,AxesOrder.ZYX, units);
        return angles;
    }

    public double getHeading(AngleUnit angleUnit) {
        Orientation angles = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
        return angles.firstAngle;
    }

    public double getYaw(AngleUnit units) {
        return getOrientation(units).firstAngle;
    }

    public double getPitch(AngleUnit units) {
        return getOrientation(units).thirdAngle;
    }

    public double getRoll(AngleUnit units) {
        return getOrientation(units).secondAngle;
    }
}
