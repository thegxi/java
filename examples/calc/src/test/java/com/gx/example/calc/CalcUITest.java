package com.gx.example.calc;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @Author: thegx
 * @Date: 30/6/2022 8:54 PM
 * @Version: 1.0
 **/
public class CalcUITest {
    @Test
    public void createUI() {
        Stack<String> ops = new Stack<String>();
        String[] opArray = new String[] {"+", "-", "*", "/"};
        for (String s : opArray) {
            ops.push(s);
            System.out.println("stack peek: " + ops.peek());
            String top = ops.pop();
            System.out.println("stack top: " + top);
            System.out.println("stack peek: " + ops.peek());
        }
    }
}
