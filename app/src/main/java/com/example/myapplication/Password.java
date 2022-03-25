package com.example.myapplication;



import java.util.Scanner;
import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Password {
    private static final String lowStr = "abcdefghijklmnopqrstuvwxyz";
    private static final String specialStr = "~!@#$%^&*()_+/-=[]{};:'<>?.";
    private static final String numStr = "0123456789";

    int len;
    //int ness_num;
    //int ness_cap;
    //int ness_low;
    //int ness_spec;
    int []para= new int[4];

    public Password(){
        this.len=10;
        this.para[0]=1;
        this.para[1]=1;
        this.para[2]=1;
        this.para[3]=1;

    }

    // 随机获取字符串字符
    private static char getRandomChar(String str) {
        SecureRandom random = new SecureRandom();
        return str.charAt(random.nextInt(str.length()));
    }

    // 随机获取小写字符
    private static char getLowChar() {
        return getRandomChar(lowStr);
    }

    // 随机获取大写字符
    private static char getUpperChar() {
        return Character.toUpperCase(getLowChar());
    }

    // 随机获取数字字符
    private static char getNumChar() {
        return getRandomChar(numStr);
    }

    // 随机获取特殊字符
    private static char getSpecialChar() {
        return getRandomChar(specialStr);
    }

    //指定调用字符函数
    private static char getRandomChar(int funNum) {
        switch (funNum) {
            case 2:
                return getLowChar();
            case 1:
                return getUpperChar();
            case 0:
                return getNumChar();
            default:
                return getSpecialChar();
        }
    }

    // 指定长度，随机生成复杂密码
    private   String  getRandomPwd() {
        if (this.len > 20 || this.len < 6) {
            System.out.println("长度不满足要求");
            return "";
        }
        List<Character> list = new ArrayList<Character>(len);
        //list.add(getLowChar());
        //list.add(getUpperChar());
        //list.add(getNumChar());
        //list.add(getSpecialChar());

        int i=0;
        while(i<this.len)
        {
            SecureRandom random = new SecureRandom();
            int funNum = random.nextInt(4);
            if(this.para[funNum]==0)
            {
                continue;
            }
            else {
                list.add(getRandomChar(funNum));
                i++;
            }

        }
        Collections.shuffle(list);   // 打乱排序
        StringBuilder stringBuilder = new StringBuilder(list.size());
        for (Character c : list) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

    public   String generate(int len,int a,int b,int c,int d)
    {
        Scanner scan = new Scanner(System.in);
        //System.out.println("请输入密码长度:" );
        this.len=len;
        //System.out.println("请输入要包含哪几种字符:" );
        //System.out.println("包含数字？（输入0or1）：" );
        this.para[0]=a;
        //System.out.println("包含大写字母？（输入0or1）：" );
        this.para[1]=b;
        //System.out.println("包含小写字母？（输入0or1）：" );
        this.para[2]=c;
        //System.out.println("包含特殊字符？（输入0or1）：" );
        this.para[3]=d;

        return  this.getRandomPwd();
    }
    /*public static void main(String[] args) {
        Password password = new Password();
        System.out.println(password.generate());
    }*/
}

