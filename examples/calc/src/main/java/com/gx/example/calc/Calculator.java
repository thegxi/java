package com.gx.example.calc;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * @Author: thegx
 * @Date: 7/7/2022 2:26 PM
 * @Version: 1.0
 **/
public class Calculator {
    Stack<String> ops = new Stack<String>(); // 运算符栈
    Stack<BigDecimal> num = new Stack<BigDecimal>(); // 数据栈
    String express;
    BigDecimal result;

    public Calculator(String calcExp) {
        express = calcExp;
        calculate();
    }

    public void calculate() {
        String[] nums = getParam(express).split(" ");
        for (String str : nums) {
            if (str.matches("\\d+") || str.matches("\\d+.\\d+")) {
                BigDecimal n = new BigDecimal(str);
                num.push(n);
            } else if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                BigDecimal n1 = num.pop(), n2 = num.pop();
                switch(str) {
                    case "+":
                        num.push(n1.add(n2));
                        break;
                    case "-":
                        num.push(n2.subtract(n1));
                        break;
                    case "*":
                        num.push(n1.multiply(n2));
                        break;
                    case "/":
                        num.push(n2.divide(n1));
                        break;
                    default:
                        break;
                }
            } else {
                continue;
            }
        }
        result = num.peek();
        System.out.println("ans = " + result);
    }

    public String getParam(String exp) {
        exp = exp.replace("+", " + ");
        exp = exp.replace("-", " - ");
        exp = exp.replace("*", " * ");
        exp = exp.replace("/", " / ");
        String[] parts = exp.split(" ");
        String resultTemp = "";
        for (String s : parts) {
            if (s.matches("\\d+") || s.matches("\\d+.\\d+")) {
                resultTemp = resultTemp + " " + s;
            } else {
                if (ops.isEmpty()) { // 如果栈顶元素为空，把当前元素推至栈顶
                    ops.push(s);
                    continue;
                }
                String temp = ops.peek();  // 查看栈顶元素
                while (priority(temp) >= priority(s)) {
                    resultTemp = resultTemp + " " + temp;
                    ops.pop();  // 移除栈顶部的对象，并返回该对象
                    if (ops.isEmpty()) {
                        break;
                    }
                    temp = ops.peek();
                }
                ops.push(s);
            }
        }
        while (!ops.isEmpty()) {
            resultTemp = resultTemp + " " + ops.pop();
        }
        return resultTemp;
    }

    /**
     * 判断计算的优先级
     * @param sign 运算符号
     * @return
     */
    public int priority(String sign) {
        switch (sign) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                break;
        }
        return 0;
    }
}
