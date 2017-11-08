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
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="RedJewel", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
//@Disabled
public class RedTeamJewel extends LinearOpMode
{


    @Override
    public void runOpMode() {

        //--1-begin
        DcMotor wheelR;
        DcMotor wheelL;
        //--1-end

        //--2-begin
        Servo leftServo;
        Servo rightServo;
        //--2-end

        Servo jewelDisplacer;

        DcMotor lift;

        LightSensor legoLightSensor = hardwareMap.lightSensor.get("light sensor");

        //--1-begin
        wheelR = hardwareMap.dcMotor.get("motorR");
        wheelL = hardwareMap.dcMotor.get("motorL");

        wheelL.setDirection(DcMotorSimple.Direction.REVERSE);//This motor is pointing the wrong direction
        //--2-begin

        leftServo = hardwareMap.servo.get("left");
        rightServo = hardwareMap.servo.get("right");

        lift = hardwareMap.dcMotor.get("lift");

        jewelDisplacer = hardwareMap.servo.get("jewel");


        double UP_SPEED = -0.5; //tweak this


        double LEFT_CENTER = 0.24; //tweak this
        double RIGHT_CENTER = 0.22; //tweak this
        double LEFT_IN = 0.7; //tweak this
        double RIGHT_IN = 0.0; //tweak this
        double LEFT_OUT = 0; //tweak this
        double RIGHT_OUT = 0.65; //tweak this //done
        //--2-end

        double JEWEL_OUT = 0.5; //tweak this
        double JEWEL_IN = 0.0; //tweak this

        double LIGHT_THRESHOLD = 0.1;


        telemetry.addLine("Code is running.");
        telemetry.update();


        telemetry.addData(">", "Press Play to start");
        telemetry.update();


        ElapsedTime r = new ElapsedTime();


        leftServo.setPosition(LEFT_CENTER);
        rightServo.setPosition(RIGHT_CENTER);

        waitForStart();

        jewelDisplacer.setPosition(JEWEL_OUT);

        s(500, r);

        if (legoLightSensor.getLightDetected() > LIGHT_THRESHOLD) {
            wheelL.setPower(-0.2);
            wheelR.setPower(-0.2);
            s(400,r);
            wheelL.setPower(0);
            wheelR.setPower(0);
            s(200, r);

            wheelL.setPower(0.2);
            wheelR.setPower(0.2);
            s(800,r);
            wheelL.setPower(0);
            wheelR.setPower(0);
            s(200, r);
        } else {
            wheelL.setPower(0.2);
            wheelR.setPower(0.2);
            s(400,r);
            wheelL.setPower(0);
            wheelR.setPower(0);
        }

    }

    public void s(double time, ElapsedTime r){
        double t = r.milliseconds();
        while (opModeIsActive() && r.milliseconds() <= t + time){}
    }

}
// one second per foot at 0.2 power

//left: turn a smidgen more
//center: perfect
//right: turn a little less