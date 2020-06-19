package com.example.springbootdemomytool.utils.timeutils;

/**
 * @ClassName FinalTest
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/21 18:52
 * @Version 1.0
 */
public class FinalTest {
    private int n;

    public FinalTest(int n) {
        this.n = n;
    }

    public void assertFinal() {
        if (n != n) {
            System.out.println("什么情况下 n 不等于 n");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 560; i++) {
            // System.out.println("i is: " + i);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        FinalTest finalTest = new FinalTest(1);

                        if (null != finalTest) {
                            finalTest.assertFinal();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    } finally {
                    }
                }
            }, "【thread】" + i).start();
        }
    }

}
