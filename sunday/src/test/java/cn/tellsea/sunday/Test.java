package cn.tellsea.sunday;

public class Test {

    public static void main(String[] args) {
        test();
        test(1);
        test(1, 1);
    }

    private static void test(int ... a) {
        int param = a.length;
        for (int i = 0; i < param; i++) {

        }
        System.out.println(param);
    }
}
