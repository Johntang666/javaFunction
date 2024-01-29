package com.tang.javafunction.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPAddressRangeChecker {
    public static boolean isIPInRange(String ipAddress, String startRange, String endRange) {
        try {
            long ipValue = ipToLong(ipAddress);
            long startRangeValue = ipToLong(startRange);
            long endRangeValue = ipToLong(endRange);

            return ipValue >= startRangeValue && ipValue <= endRangeValue;
        } catch (UnknownHostException e) {
            e.printStackTrace(); // 处理IP地址转换错误
            return false;
        }
    }

    private static long ipToLong(String ipAddress) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        byte[] addressBytes = inetAddress.getAddress();
        long result = 0;

        for (byte b : addressBytes) {
            result = (result << 8) | (b & 0xFF);
        }

        return result;
    }

    public static void main(String[] args) {
        String ipAddress = "192.168.1.90";
        String startRange = "192.168.1.1";
        String endRange = "192.168.1.10";

        if (isIPInRange(ipAddress, startRange, endRange)) {
            System.out.println(ipAddress + " is in the range [" + startRange + ", " + endRange + "]");
        } else {
            System.out.println(ipAddress + " is not in the range [" + startRange + ", " + endRange + "]");
        }
    }
}

