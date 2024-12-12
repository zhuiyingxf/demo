package org.example.juejin;

public class Question1 {


    /**
     * 在一个班级中，每位同学都拿到了一张卡片，上面有一个整数。有趣的是，除了一个数字之外，所有的数字都恰好出现了两次。
     * 现在需要你帮助班长小C快速找到那个拿了独特数字卡片的同学手上的数字是什么。
     * @param cards
     * @return
     */
    public static int solution(int[] cards) {
        // Edit your code here
        // 初始化 result 为 0
        int result = 0;

        // 遍历数组中的每一个数字
        for (int card : cards) {
            // 将 result 与当前数字进行异或运算
            result ^= card;
        }

        // 返回最终结果
        return result;
    }

    public static void main(String[] args) {
        // Add your test cases here

        System.out.println(solution(new int[]{1, 1, 2, 2, 3, 3, 4, 5, 5}) == 4);
        System.out.println(solution(new int[]{0, 1, 0, 1, 2}) == 2);
    }
}
