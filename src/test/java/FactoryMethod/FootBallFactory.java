package FactoryMethod;

public class FootBallFactory extends BallFactory {

    @Override
    public Ball makeBall() {
        return new FootBall();
    }
}
