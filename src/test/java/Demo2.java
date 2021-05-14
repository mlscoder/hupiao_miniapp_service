public class Demo2 {


    public static void main(String[] args) {
        System.out.println(test());

    }

    public static String test() {
        try {
            System.out.println("111");
            return "1123";
        } catch (Exception e) {

        } finally {
            System.out.println("333");
            return "222";
        }
    }
}
