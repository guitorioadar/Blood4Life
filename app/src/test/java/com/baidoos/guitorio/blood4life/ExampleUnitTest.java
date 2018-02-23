package com.baidoos.guitorio.blood4life;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void textCount(){
        String in = "ABCMKLABCGFLABCDNQABC";
        int i = 0;
        Pattern p = Pattern.compile("ABC");
        Matcher m = p.matcher( in );
        while (m.find()) {
            i++;
        }
        System.out.println(i);
    }


    int fact(int n)
    {
        int result;
        if(n<=1)
            return 1;

        return fact(n-1) * n;
    }

    @Test
    public void recursion(){
        System.out.print(fact(7));
    }

}