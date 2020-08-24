package com.yang.javabase.drawing.swing;

import javax.swing.*;
import java.awt.*;

public class JFrameDemo {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("我的第一个窗口DEMO");

        jFrame.setSize(250,250);

        jFrame.setBackground(Color.ORANGE);
        JButton jButton = new JButton("BUTTON");
        jFrame.getContentPane().add(jButton);



        jFrame.setVisible(true);
    }

}
