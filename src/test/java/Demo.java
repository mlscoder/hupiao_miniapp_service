public class Demo {


    public static void main(String[] args) {
        int i;
        for (i = 0; i < 9; i++) {
            if (i == 0)
                break;
        }
        System.out.println("i=" + i);
    }

    public static String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            //拿第一个做模板，匹配到数组中第一个元素的最长公共前缀字符串，然后以这个为模板，以此类推匹配下边所有的元素，最后返回最终模板
            //indexOf是检测子串并返回子串起始位置的函数
            while (strs[i].indexOf(pre) != 0)
                //如果pre不是子串，就去掉pre末尾一位重新比较，直到是子串或者pre长度0时就会跳出本次循环去匹配下一轮外循环
                pre = pre.substring(0, pre.length() - 1);
            i++;
        }
        return pre;
    }
}


