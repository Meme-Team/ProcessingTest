/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usingprocessing;

import processing.core.PApplet;

/**
 *
 * @author chhit5249
 */
public class UsingProcessing extends PApplet {

    public static void main(String[] args) {
        PApplet.main("usingprocessing.UsingProcessing");
    }
    /*  22/01/15       ICS2O - Culminating Project          Chris H
     This program creates a simple version of the game pong. When
     the game is started by pressing the "Start" button, you use the 
     W and S keys for up and down, respectively, on the left side, and
     the Up and Down arrows for up and down, respectively, on the
     right side. When one player makes the ball go past their
     opponents paddle, that player gets a point. The first player to
     7 points wins! There is a "How-to-Play" button on the menu in 
     which you can read all of this, and then return to the menu and 
     start the game. When the game ends, you can exit the box or go 
     back to the main menu, using the "Main Menu" button, in which you
     can start another game. Enjoy! */

//Player 1 score and Player 2 score
    int leftScore = 0;
    int rightScore = 0;

//Ball & paddle positions
    float ballX = 500;
    float ballY = 275;
    float paddleX1 = 25;
    float paddleY1 = 275;
    float paddleX2 = 975;
    float paddleY2 = 275;

//Speed at which the paddles and ball are moving
    double paddleSpeed = 5;
    double xSpeed = 5;
    double ySpeed = 5;

//Keys being pressed - Up Arrow, Down Arrow, W Key, S Key - Respectively
    boolean arrowUp;
    boolean arrowDown;
    boolean keyW;
    boolean keyS;

//Which part of the game you are in - Menu, Rules, In Game, Post Gamme
    boolean gameStart = false;
    boolean gameRules;
    boolean menuButtons = true;
    boolean gameEnd = false;
    boolean menuCheck;

//Setting the Size
    public void settings() {
        size(1000, 550);
    }

    public void setup() {

    }

    public void draw() {

        noStroke();

        //Getting the Menu Buttons to work
        if (menuButtons == true) {
            menuCheck = true;
        }
        if (menuButtons == false) {
            menuCheck = false;
        }

        //"Main Menu"
        if ((gameStart == false) && (gameRules == false) && (menuButtons == true) && (gameEnd == false)) {
            background(0, 255, 0);
            fill(255, 0, 0);
            textSize(150);
            textAlign(CENTER);
            text("Pong", 500, 200);
            rectMode(CORNER);
            rect(250, 300, 200, 100);
            rect(550, 300, 200, 100);
            textSize(50);
            fill(0, 255, 0);
            text("Start", 340, 365);
            textSize(30);
            text("How-to-Play", 645, 365);
        }

        //How-to-Play Screen
        if ((gameRules == true) && (menuButtons == true) && (gameStart == false) && (gameEnd == false)) {
            background(0, 255, 0);
            fill(255, 0, 0);
            textSize(75);
            textAlign(CENTER);
            text("How-to-Play", 500, 100);
            rect(50, 50, 200, 100);
            textSize(50);
            fill(0, 255, 0);
            text("Back", 140, 115);
            textSize(25);
            fill(255, 0, 0);
            text("Left Paddle - W = Up --- S = Down", 500, 200);
            text("_________________________________", 500, 205);
            text("Right Paddle - Up Arrow = Up --- Down Arrow = Down", 500, 250);
            text("____________________________________________________", 500, 255);
            text("Get the ball past the opponents paddle to score a point", 500, 300);
            text("____________________________________________________", 500, 305);
            text("Score goes to 7", 500, 350);
            text("_______________", 500, 355);
            text("Slight speed increase per paddle bounce - Max speed of 15 pixels a frame", 500, 400);
            text("______________________________________________________________________", 500, 405);
            text("Good luck, have fun!", 500, 450);
            text("___________________", 500, 455);
        }

        //Game Start
        if ((gameStart == true) && (gameRules == false)) {

            //Redrawing Everything
            background(0, 255, 255);
            fill(255);
            rect(paddleX1, paddleY1, 25, 150);
            rect(paddleX2, paddleY2, 25, 150);
            ballX = ballX + (float) xSpeed;
            ballY = ballY + (float) ySpeed;
            fill(255, 0, 255);
            ellipse(ballX, ballY, 30, 30);
            textAlign(CENTER, CENTER);
            textSize(50);
            text(leftScore, 100, 50);
            text(rightScore, 900, 50);
            fill(255);

            //Making the ball bounce off of Paddle -  Speed Increase/Bounce
            if ((ballX <= paddleX1 + 30) && (ballY < paddleY1 + 90) && (ballY > paddleY1 - 90)) {
                xSpeed = xSpeed * -1;
                ballX = 60;
                xSpeed = xSpeed * 1.045;
                ySpeed = ySpeed * 1.045;
                paddleSpeed = paddleSpeed * 1.045;
                rect(paddleX1, paddleY1, 25, 150);
                rect(paddleX2, paddleY2, 25, 150);
            } else if ((ballX >= paddleX2 - 30) && (ballY < paddleY2 + 90) && (ballY > paddleY2 - 90)) {
                xSpeed = xSpeed * -1;
                ballX = 950;
                xSpeed = xSpeed * 1.045;
                ySpeed = ySpeed * 1.045;
                paddleSpeed = paddleSpeed * 1.045;
                rect(paddleX1, paddleY1, 25, 150);
                rect(paddleX2, paddleY2, 25, 150);
            }

            //Max Speed
            if ((xSpeed > 15) && (ySpeed > 15) && (paddleSpeed > 15)) {
                xSpeed = 15;
                ySpeed = 15;
                paddleSpeed = 15;
            } //If Ball Passes Paddle - Reset and + 1 Score 
            else if ((((ballY >= paddleY1 + 90) || ballY < paddleY1 - 90)) && (ballX < paddleX1 + 25)) {
                ballX = 500;
                ballY = 225;
                xSpeed = 5;
                ySpeed = 5;
                paddleSpeed = 5;
                rightScore = rightScore + 1;
            } else if ((((ballY >= paddleY2 + 90) || ballY < paddleY2 - 90)) && (ballX > paddleX2 - 25)) {
                ballX = 500;
                ballY = 500;
                xSpeed = 5;
                ySpeed = 5;
                xSpeed = xSpeed * -1;
                paddleSpeed = 5;
                leftScore = leftScore + 1;
            }

            //Y Boundary
            if ((ballY >= 535) || (ballY <= 15)) {
                ySpeed = ySpeed * -1;
                if (ballY >= 535) {
                    ballY = 520;
                }
                if (ballY <= 15) {
                    ballY = 30;
                }
            }

            //Left Paddle - W = Up and S = Down
            if (keyW == true) {
                background(0, 255, 255);
                paddleY1 = paddleY1 - (float) paddleSpeed;
                fill(255);
                rectMode(CENTER);
                rect(paddleX1, paddleY1, 25, 150);
                rect(paddleX2, paddleY2, 25, 150);
                fill(255, 0, 255);
                ellipse(ballX, ballY, 30, 30);
                textSize(50);
                text(leftScore, 100, 50);
                text(rightScore, 900, 50);
                if (paddleY1 <= 75) {
                    paddleY1 = 75;
                }
            }
            if (keyS == true) {
                background(0, 255, 255);
                paddleY1 = paddleY1 + (float) paddleSpeed;
                fill(255);
                rectMode(CENTER);
                rect(paddleX1, paddleY1, 25, 150);
                rect(paddleX2, paddleY2, 25, 150);
                fill(255, 0, 255);
                ellipse(ballX, ballY, 30, 30);
                textSize(50);
                text(leftScore, 100, 50);
                text(rightScore, 900, 50);
                if (paddleY1 > 475) {
                    paddleY1 = 475;
                }
            }

            //Right Paddle - Up Arrow = Up and Down Arrow = Down
            if (arrowUp == true) {
                background(0, 255, 255);
                paddleY2 = paddleY2 - (float) paddleSpeed;
                fill(255);
                rectMode(CENTER);
                rect(paddleX2, paddleY2, 25, 150);
                rect(paddleX1, paddleY1, 25, 150);
                fill(255, 0, 255);
                ellipse(ballX, ballY, 30, 30);
                textSize(50);
                text(leftScore, 100, 50);
                text(rightScore, 900, 50);
                if (paddleY2 <= 75) {
                    paddleY2 = 75;
                }
            }
            if (arrowDown == true) {
                background(0, 255, 255);
                paddleY2 = paddleY2 + (float) paddleSpeed;
                fill(255);
                rectMode(CENTER);
                rect(paddleX2, paddleY2, 25, 150);
                rect(paddleX1, paddleY1, 25, 150);
                fill(255, 0, 255);
                ellipse(ballX, ballY, 30, 30);
                textSize(50);
                text(leftScore, 100, 50);
                text(rightScore, 900, 50);
                if (paddleY2 > 475) {
                    paddleY2 = 475;
                }
            }

            //Game Endings - One side gets 7 Points - Declares winner and allows user to go back to the menu
            if (leftScore >= 7) {
                background(255, 0, 255);
                textSize(100);
                textAlign(CENTER, CENTER);
                fill(255, 255, 0);
                text("Player 1 Wins!", 500, 225);
                rectMode(CORNER);
                rect(400, 325, 200, 100);
                fill(255, 0, 255);
                textSize(35);
                text("Menu", 505, 375);
                ballX = 500;
                ballY = 225;
                gameEnd = true;
            } else if (rightScore >= 7) {
                background(255, 0, 255);
                textSize(100);
                textAlign(CENTER, CENTER);
                fill(255, 255, 0);
                text("Player 2 Wins!", 500, 225);
                rectMode(CORNER);
                rect(400, 325, 200, 100);
                fill(255, 0, 255);
                textSize(35);
                text("Menu", 505, 375);
                ballX = 500;
                ballY = 225;
                gameEnd = true;
            }
        }
    }

    public void mousePressed() {
        //Goes from the end screen to the menu - press "Main Menu" button on end screen
        if ((gameEnd == true) && (menuButtons == false)) {
            if ((mouseX >= 400) && (mouseX <= 600) && (mouseY >= 325) && (mouseY <= 425)) {
                gameEnd = false;
                menuButtons = true;
                gameStart = false;
            }
        }

        //Starts Game - Press "Start" button on the menu
        if ((menuCheck == true) && (gameEnd == false) && (gameStart == false) && (menuButtons == true) && (gameRules == false)) {
            if ((mouseX > 250) && (mouseX < 450) && (mouseY > 300) && (mouseY < 400)) {
                gameStart = true;
                menuButtons = false;
                rectMode(CENTER);
                rightScore = 0;
                leftScore = 0;
                paddleX1 = 25;
                paddleY1 = 275;
                paddleX2 = 975;
                paddleY2 = 275;
            }
        }

        //How-to-Play Screen - Press "How-to-Play" button on the menu
        if ((menuCheck == true) && (gameRules == false) && (menuButtons == true) && (gameStart == false) && (gameEnd == false)) {
            if ((mouseX > 550) && (mouseX < 750) && (mouseY > 300) && (mouseY < 400)) {
                gameRules = true;
            }
        }

        //Back button on How-to-Play screen - Returns to menu
        if (gameRules == true) {
            if ((mouseX > 50) && (mouseX < 250) && (mouseY > 50) && (mouseY < 150)) {
                gameRules = false;
            }
        }
    }

//For when the WS/Up Down Keys are pressed activates paddle movement
    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == UP) {
                arrowUp = true;
            }
        }
        if (key == CODED) {
            if (keyCode == DOWN) {
                arrowDown = true;
            }
        }
        if ((key == 'w') || (key == 'W')) {
            keyW = true;
        }
        if ((key == 's') || (key == 'S')) {
            keyS = true;
        }
    }

//For when the WS/Up Down keys are released deactivates paddle movement
    public void keyReleased() {
        if (key == CODED) {
            if (keyCode == UP) {
                arrowUp = false;
            }
        }
        if (key == CODED) {
            if (keyCode == DOWN) {
                arrowDown = false;
            }
        }
        if ((key == 'w') || (key == 'W')) {
            keyW = false;
        }
        if ((key == 's') || (key == 'S')) {
            keyS = false;
        }
    }

}
