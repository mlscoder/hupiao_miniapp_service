package FactoryMethod;

public class FactoryTest {

    public static void main(String[] args) {

        //客户要产品 蓝球
        BasketBallFactory basketBall = new BasketBallFactory();
        basketBall.makeBall().play();
        //客户要产品 足球
        FootBallFactory footBallFactory = new FootBallFactory();
        footBallFactory.makeBall().play();
    }

}
