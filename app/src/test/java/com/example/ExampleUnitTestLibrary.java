package com.example;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kotlin.text.Regex;

/**
 * @author xmqian
 * @date 19-5-4
 * @desc 单元测试 使用JVM
 * Example local unit test, which will execute on the development machine (host).
 */
public class ExampleUnitTestLibrary {

    @Test
    public void test() {
        output output = (s) -> s + "参与";
        String bankNo = "6217001630047494627";
        String matcher = Matcher.quoteReplacement("/^(\\d{4})\\d+(\\d{4})$/");
        String rp = bankNo.replace(matcher, "$1 **** **** $2");
        System.out.print(output.i("这是测试")+"\n"+rp);

    }

    private interface output {
        public String i(String s);
    }
}