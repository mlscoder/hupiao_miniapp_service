public class DoubleCheck {

    private static DoubleCheck doubleCheck;

    private DoubleCheck() {

    }

    private static DoubleCheck getInstance() {
        if (doubleCheck == null) {
            synchronized (DoubleCheck.class) {

                if (doubleCheck == null) {
                    return new DoubleCheck();
                }
            }
        }
        return doubleCheck;

    }
}
