package com.springboot.springweb.controller;

public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(reverseInt(1));
        System.out.println(reverseInt(10));
        System.out.println(reverseInt(100));
        System.out.println(reverseInt(101));
        System.out.println(reverseInt(1000));
        System.out.println(reverseInt(1002));
        System.out.println(reverseInt(10134));
        System.out.println(reverseInt(101834));
        System.out.println(reverseInt(1017344));
        System.out.println(reverseInt(10879654));
    }

    public static int reverseInt(int num) {

        double numDigitsNum = Math.log10((double) num);
        int intnumDigitsNum = (int) numDigitsNum + 1;
//        System.out.println("digits number:" + intnumDigitsNum);
        double result = 0;
        int remainder = num;
        int mod = num % 10;
        int loop = 1;
        while (remainder > 0) {
            result = result + mod * Math.pow(10, intnumDigitsNum - loop);
            loop++;
            remainder = remainder / 10;
            mod = remainder % 10;
        }
        result = result + mod;
        int r = (int) result;

        return r;
    }


}
