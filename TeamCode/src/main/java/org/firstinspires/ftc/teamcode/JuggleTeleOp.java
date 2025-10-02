package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="JuggleMode", group="Linear OpMode")
public class JuggleTeleOp extends LinearOpMode {

    private DcMotor coreHex = null;
    private DcMotor flywheel = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Map motors (names must match your RC config)
        coreHex  = hardwareMap.get(DcMotor.class, "coreHex");
        flywheel = hardwareMap.get(DcMotor.class, "flywheel");

        // Configure directions and behaviors
        coreHex.setDirection(DcMotor.Direction.FORWARD);
        flywheel.setDirection(DcMotor.Direction.FORWARD);
        coreHex.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        waitForStart();

        boolean juggleActive = false;

        while (opModeIsActive()) {
            // Toggle juggle mode
            if (gamepad1.a) {
                juggleActive = true;
            } else if (gamepad1.b) {
                juggleActive = false;
            }

            if (juggleActive) {
                // set these speeds experimentally until it “juggles” properly
                coreHex.setPower(1.0);    // gate motor speed
                flywheel.setPower(0.21);   // flywheel speed
            } else {
                coreHex.setPower(0);
                flywheel.setPower(0);
            }

            telemetry.addData("Juggle", juggleActive ? "ON" : "OFF");
            telemetry.addData("CoreHex Power", coreHex.getPower());
            telemetry.addData("Flywheel Power", flywheel.getPower());
            telemetry.update();
        }
    }
}