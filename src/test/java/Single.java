public class Single {

    private static Single single = new Single();

    private Single() {
    }

    //用空间换区时间
    public static synchronized Single getInstance() {
        return single;
    }


}
