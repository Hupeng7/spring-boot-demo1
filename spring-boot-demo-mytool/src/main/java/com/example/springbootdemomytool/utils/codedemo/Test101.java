package com.example.springbootdemomytool.utils.codedemo;

import org.junit.Test;

/**
 * @ClassName Test01
 * @Description
 * @Author hup
 * @Date 2020/9/22 16:01
 * @Version 1.0
 */
public class Test101 {
    public static void main(String[] args) {
//        int[] nums = new int[]{2, 3, 5, 6, 2, 4, 3};
//        int repeatNumber = findRepeatNumber(nums);
//        System.out.println(repeatNumber);

       // test01(7);
//        int[] nums = new int[]{2,7,11,15};
//        System.out.println( twoSum(nums,9));
//        List n = Arrays.asList(nums);
//        boolean contains = n.contains(7);
//        System.out.println(contains);

//        Stack stack = new Stack();
//        stack.push(1);
//        stack.push(2);
//        stack.push(4);
//        stack.push(3);
//        stack.push(13);
//        Object peek = stack.peek();
//        System.out.println(peek);
//        Object pop = stack.pop();
//        System.out.println(pop);
        int num = 99;
        int a = num/1000;
        System.out.println("这个数千位是"+a);
        int b =num/100%10;
        System.out.println("这个数百位是"+b);
        int c =num/10%10;
        System.out.println("这个数十位是"+c);
        int d =num%10;
        System.out.println("这个数个位是"+d);

        int m=16;
        int n=8;
        int k=1;
        int count = 0;
        for(int i=0;i<m;i++){
            for(int j = 0;j<n;j++){
                int curk = i/100%10 + i/10%10 + i%10
                        + j/100%10 + j/10%10 + j%10 ;
                if(curk<=k){
                    count++;
                }
            }
        }
        System.out.println(count);

        boolean[][] visited = new boolean[m][n];
        System.out.println(visited);
        visited[m][n] = true;
        System.out.println(visited);
    }

    /**
     * 参数区间 [0,n-1]
     *
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        String a = "dadsa  fdsfa";
        // ListNode

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[nums[i]]++;
//            0 = 0
//            1 = 0
//            2 = 1
//            3 = 1
//            4 = 0
//            5 = 1
//            6 = 0
            if (result[nums[i]] > 1) {
                return nums[i];
            }
        }
        return -1;
    }

    public static int test01(int n) {
        if (n == 0) {
            return 1;
        }
        int a, b, cur;
        a = b = cur = 1;
        for (int i = 2; i <= n; i++) {
            cur = (a + b) % 1000000007;
            a = b;
            b = cur;
        }
        return cur;
    }


    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int a = 0;
        int b = 0;
        for (int i = 0; i < nums.length; i++) {
            a = nums[i];
            b = target -a;
            for (int j = (i +1); j < nums.length; j++) {
                if (nums[j] == b){
                    return new int[]{i,j};
                }
            }
        }

        return result;
    }

    @Test
    public void test001(){
        cuttingRope(6);
    }

    public static int cuttingRope(int n) {
        int result = 1;
        for(int i=1;i<n;i++){
            int tar = 1;
            if(n == 2){
                continue;
            }
            int a = n % i;
            int ap = n/i;
            if(a == 0){
                tar =  (int)Math.pow(ap,i);
            }else{
                int b =  (int)Math.pow((ap+1),a);
                tar =  b * (n-(ap+1)*a);
            }

            if(tar > result){
                result = tar;
            }

        }
        return result;

    }


}
