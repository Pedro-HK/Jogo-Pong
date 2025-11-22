
package com.example;

public class DecreaseBallSize implements GamePowers {

    private Ball ball;

    public DecreaseBallSize(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void activatePower() {
        ball.setSIZE(ball.getSIZE() / 2);
    }

    @Override
    public void deActivatePower() {
        ball.setSIZE(ball.getSIZE() * 2);
    }
}
