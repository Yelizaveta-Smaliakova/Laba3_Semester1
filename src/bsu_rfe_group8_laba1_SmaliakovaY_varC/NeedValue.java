package bsu_rfe_group8_laba1_SmaliakovaY_varC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NeedValue extends JFrame {

    private int WIDTG = 400;
    private int HEIGHT = 97;
    public JTextField textFieldForValue;
    public  JButton OK = new JButton("ОК");
    public static void main(String arg[]) {
        NeedValue frame = new NeedValue();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);

    }

    NeedValue()
    {
        super("Ввод значение многочлена");
        Toolkit kit = Toolkit.getDefaultToolkit();
        setSize(WIDTG,HEIGHT);
        setLocation((kit.getScreenSize().width - WIDTH) / 2, ((kit.getScreenSize()).height - HEIGHT) / 2);
        JLabel labelForValue = new JLabel("Значение многочлена");
        textFieldForValue  = new JTextField("0.0", 10);
        Box hboxValue = Box.createHorizontalBox();
        hboxValue.setBorder(BorderFactory.createBevelBorder(1));
        hboxValue.add(Box.createHorizontalGlue());
        hboxValue.add(Box.createHorizontalStrut(60));
        hboxValue.add(labelForValue);
        hboxValue.add(Box.createHorizontalStrut(10));
        hboxValue.add(textFieldForValue);
        hboxValue.add(Box.createHorizontalStrut(60));
        getContentPane().add(hboxValue, BorderLayout.CENTER);
        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.setBorder(BorderFactory.createBevelBorder(1));
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(OK);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(cancelButton);
        hboxButtons.add(Box.createHorizontalGlue());
        getContentPane().add(hboxButtons, BorderLayout.SOUTH);
    }
}