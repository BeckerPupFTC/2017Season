/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.Season2017;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="ReallyBadMeccanumOp", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
//@Disabled
public class MeccanumOp extends LinearOpMode
{
    DcMotor wheelF;
    DcMotor wheelB;
    DcMotor wheelR;
    DcMotor wheelL;



    @Override
    public void runOpMode() {
        wheelF = hardwareMap.dcMotor.get("wheelF");
        wheelB = hardwareMap.dcMotor.get("wheelB");
        wheelR = hardwareMap.dcMotor.get("wheelR");
        wheelL = hardwareMap.dcMotor.get("wheelL");

        wheelL.setDirection(DcMotorSimple.Direction.REVERSE);
        wheelF.setDirection(DcMotorSimple.Direction.REVERSE);

        double rp;
        double lp;
        double fp;
        double bp;
        waitForStart();

        while(opModeIsActive()) {

            rp = ((double)gamepad1.right_stick_x)*0.5;
            lp = ((double)gamepad1.right_stick_x)*0.5;
            fp = ((double)-gamepad1.right_stick_y)*0.5;
            bp = ((double)-gamepad1.right_stick_y)*0.5;

            fp += ((double)gamepad1.left_stick_x)*0.5;
            bp -= ((double)gamepad1.left_stick_x)*0.5;
            lp += ((double)gamepad1.left_stick_x)*0.5;
            rp -= ((double)gamepad1.left_stick_x)*0.5;

            wheelB.setPower(bp);
            wheelF.setPower(fp);
            wheelL.setPower(lp);
            wheelR.setPower(rp);
        }
    }
}