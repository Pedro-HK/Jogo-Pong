
public class IncreaseBallSpeed implements GamePowers {

    Ball ball;

    public IncreaseBallSpeed(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void activatePower() {
        ball.setSPEED(ball.getSPEED() * 2);
    }

    @Override
    public void deActivatePower() {
        ball.setSPEED(ball.getSPEED() / 2);
    }

}
