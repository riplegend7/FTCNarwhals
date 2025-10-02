package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Tank Drive TeleOp
 * Left stick Y = left wheels
 * Right stick Y = right wheels
 */
@TeleOp(name="Tank Drive", group="Linear OpMode")
public class TankDriveTeleOp extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor coreHex = null;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize motors (names must match your RC config)
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        // Reverse one side if needed so that pushing forward makes the robot go forward
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        coreHex = hardwareMap.get(DcMotor.class, "coreHex");
        coreHex.setDirection(DcMotor.Direction.FORWARD);  // change if reversed

        // Wait for start button
        waitForStart();
        runtime.reset();

        // Run until stop is pressed
        while (opModeIsActive()) {

            // Tank mode: each stick controls one side
            double leftPower  = -gamepad1.left_stick_y * 0.75;
            double rightPower = -gamepad1.right_stick_y * 0.75;

            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);

            // Use controller bumpers to move gate
            if (gamepad1.right_bumper) {
                coreHex.setPower(0.6);   // spin forward
            } else if (gamepad1.left_bumper) {
                coreHex.setPower(-0.6);  // spin backward
            } else {
                coreHex.setPower(0);     // stop
            }

            // Show data on Driver Station
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}