package com.tang.javafunction.utils;

import java.util.regex.Pattern;

public class IPAddressMatcher {
    private static final String WILDCARD_PATTERN = ".*";

    public static boolean isMatch(String ipAddress, String wildcardPattern) {
        // 将通配符转换为正则表达式
        String regex = wildcardToRegex(wildcardPattern);

        // 使用正则表达式进行匹配
        return Pattern.matches(regex, ipAddress);
    }

    private static String wildcardToRegex(String wildcardPattern) {
        // 将通配符转换为正则表达式
        String regex = wildcardPattern
                .replace(".", "\\.")
                .replace("*", WILDCARD_PATTERN);

        return "^" + regex + "$";
    }

    public static void main(String[] args) {
        String ipAddress = "192.168.1.5";
        String wildcardPattern = "192.168.1.*";

        if (isMatch(ipAddress, wildcardPattern)) {
            System.out.println(ipAddress + " matches the pattern " + wildcardPattern);
        } else {
            System.out.println(ipAddress + " does not match the pattern " + wildcardPattern);
        }
    }
}

