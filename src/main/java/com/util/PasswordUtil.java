package com.util;

/**
 * 密码工具
 */
public class PasswordUtil {

    /**
     * 安全比较传入的两个字符串
     *
     * @param first  第一个
     * @param second 第二个
     * @return 是否相等
     */
    public static boolean compare(String first, String second) {


        int length = first.length() >= second.length() ? first.length() : second.length();

        byte[] firstBytes = first.getBytes();
        byte[] secondBytes = second.getBytes();

        byte[] resultBytes = new byte[length];

        //将两个字符串进行异或操作，较短的一边以1填充，如果两边相等则异或结果全为0
        for (int i = 0; i < length; i++) {
            resultBytes[i] = (byte) ((i >= firstBytes.length ? 1 : firstBytes[i]) ^ (i >= secondBytes.length ? 0 : secondBytes[i]));
        }

        //将结果数组合并
        int result = 0;

        for (byte resultByte : resultBytes) {
            result += resultByte;
        }

        return result == 0;
    }
}
