package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="AutonomousDrive", group="Linear OpMode")
public class AutonomousDrive extends LinearOpMode {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor flywheel;
    private DcMotor coreHex; // assuming this is a DC motor, not a servo

    @Override
    public void runOpMode() {

        // Map hardware
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        flywheel  = hardwareMap.get(DcMotor.class, "flywheel");
        coreHex      = hardwareMap.get(DcMotor.class, "coreHex"); // or whatever you named it

        // Reverse one drive motor if needed for forward motion
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        if (opModeIsActive()) {

            // Step 1: Drive forward 1 sec
            leftDrive.setPower(-0.5);
            rightDrive.setPower(-0.5);
            sleep(1000);
// note
            leftDrive.setPower(0);
            rightDrive.setPower(0);

            // Step 2: Spin up flywheel to 60% power
            flywheel.setPower(0.60);
            sleep(1500); // give it one and half a second to spin up

            // Step 3: Run gate for 1 sec
            coreHex.setPower(1);
            sleep(1000);
            coreHex.setPower(0);

            // Step 4: Stop flywheel
            flywheel.setPower(0);

            // Step 5: Drive backward 1 sec
            leftDrive.setPower(0.5);
            rightDrive.setPower(0.5);
            sleep(1000);

            // Step 6: Stop
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }
    }
}
