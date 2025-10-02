package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Tank Drive TeleOp
 * Left stick = left wheels
 * Right stick = right wheels
 * LB = Rotate gate backwards
 * RB = Rotate gate forwards
 * LT: Flywheel break
 * RT: Rotate flywheel
 */
@TeleOp(name="TankDrive", group="Linear OpMode")
public class TankDriveTeleOp extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor coreHex = null;
    private DcMotor flywheel = null;
    private Servo clawServo = null;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize motors (names must match your RC config)
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        flywheel = hardwareMap.get(DcMotor.class, "flywheel");
        clawServo = hardwareMap.get(Servo.class, "claw");
        clawServo.setPosition(0.2);

        // Reverse one side if needed so that pushing forward makes the robot go forward
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        flywheel.setDirection(DcMotor.Direction.FORWARD);
        flywheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        coreHex = hardwareMap.get(DcMotor.class, "coreHex");
        coreHex.setDirection(DcMotor.Direction.FORWARD);  // change if reversed

        // Wait for start button
        waitForStart();
        runtime.reset();

        // Run until stop is pressed
        while (opModeIsActive()) {

            // Tank mode: each stick controls one side
            double leftPower  = -gamepad1.left_stick_y * 0.70;
            double rightPower = -gamepad1.right_stick_y * 0.70;

            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);

            // Use triggers to move flywheel
            double rt = gamepad1.right_trigger * 0.55;
            double lt = gamepad1.left_trigger;

            if (rt > 0.05) {
                // spin forward; trigger amount is throttle
                flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                flywheel.setPower(rt); // use 1.0 for fixed full speed
            } else if (lt > 0.05) {
                // active brake to a stop
                flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                flywheel.setPower(0);
            } else {
                // coast to stop
                flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                flywheel.setPower(0);
            }

            // Use D-Pad left & right to rotate claw
            if (gamepad1.dpad_right) {
                clawServo.setPosition(1.0); // spin forward
            } else if (gamepad1.dpad_left) {
                clawServo.setPosition(0.0); // spin backwards
            } else {
                clawServo.setPosition(0.5);
            }
            // Use controller bumpers to move gate
            if (gamepad1.right_bumper) {
                coreHex.setPower(1.0);   // spin forward
            } else if (gamepad1.left_bumper) {
                coreHex.setPower(-1.0);  // spin backward
            } else {
                coreHex.setPower(0);     // stop
            }

            // Show data on Driver Station
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Flywheel", "power (%.2f)", flywheel.getPower());
            telemetry.update();
        }
    }
}