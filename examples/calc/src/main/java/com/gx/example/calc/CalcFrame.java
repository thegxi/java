package com.gx.example.calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

/**
 * @Author: thegx
 * @Date: 30/6/2022 8:49 PM
 * @Version: 1.0
 **/
public class CalcFrame extends JFrame{
    private boolean isClean = false; // true: 清除JTextField文本内容，false不清除
    JButton[] bts = new JButton[20];
    String[] keys = new String[] {"ANS", "DEL", "EC", "C", "1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", "0", ".", "=", "/"};
    JPanel keyPanel = new JPanel();
    JTextField text = new JTextField();
    BigDecimal ans = BigDecimal.ZERO;

    public static void main(String[] args) {
        CalcFrame calcFrame = new CalcFrame("Thegx's Calculator");
    }

    public CalcFrame(String title) {
        super(title);
        this.setVisible(true);
        this.setLocation(500, 200);
        this.setSize(300, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }
    public void init() {
        text.setHorizontalAlignment(JTextField.RIGHT);
        text.setPreferredSize(new Dimension(300, 80));
        text.setFont(new Font("宋体", Font.BOLD, 20));
        for (int i = 0; i < bts.length; i++) {
            bts[i] = new JButton(keys[i]);
        }
        this.add(text, BorderLayout.NORTH);
        // 面板布局，5行4列，列间隙: 4, 行间隙: 3
        keyPanel.setLayout(new GridLayout(5, 4, 3, 3));
        this.add(keyPanel, BorderLayout.CENTER);
        for (int i = 0; i < bts.length; i++) {
            keyPanel.add(bts[i]);
            bts[i].addActionListener((ActionEvent e) -> {
                if (isClean) {
                    text.setText("");
                    isClean = false;
                }
                String cmd = e.getActionCommand();
                String content = text.getText();
                if ("01234567890.+-*/".indexOf(cmd) != -1) {
                    text.setText(text.getText() + cmd);
                } else if (cmd.equals("DEL")) {
                    if (content.equals("") || content.length() == 0) {
                        return;
                    }
                    text.setText(content.substring(0, content.length() - 1));
                } else if (cmd.equals("C") || cmd.equals("EC")) {
                    text.setText("");
                } else if (cmd.equals("ANS")) {
                    text.setText(text.getText() + ans);
                    if (ans.compareTo(BigDecimal.ZERO) == 0) {
                        text.setText(" ");
                    }
                } else if (cmd.equals("=")) {
                    ans = new Calculator(content).result;
                    text.setText(text.getText() + "=" + ans);
                    isClean = true;
                }
            });
        }
    }
}
