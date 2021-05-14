package FactoryMethod;

public class BasketBallFactory extends BallFactory {
    @Override
    public Ball makeBall() {
        return new BasketBall();
    }
}
