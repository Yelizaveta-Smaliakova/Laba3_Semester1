package bsu_rfe_group8_laba1_SmaliakovaY_varC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RangeWindow extends JFrame
{
    private final int WIDTH = 400;
    private final int HEIGHT = 100;
    public JTextField textFieldFrom;
    public JButton OK = new JButton("ОК");

    public JTextField textFieldTo;
    public int i = 1;
    public static void main(String arg[])
    {
        RangeWindow frame = new RangeWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    RangeWindow()
    {
        super("Ввод диапазона");
        Toolkit kit = Toolkit.getDefaultToolkit();
        setSize(WIDTH, HEIGHT);
        setLocation((kit.getScreenSize().width - WIDTH) / 2, ((kit.getScreenSize()).height - HEIGHT) / 2);
        JLabel labelForFrom = new JLabel("Диапазон от:");
        JLabel labelForTo = new JLabel("до: ");
        textFieldFrom = new JTextField("0.0", 10);
        textFieldTo = new JTextField("0.0", 10);
        Box hboxRange = Box.createHorizontalBox();
        hboxRange.setBorder(BorderFactory.createBevelBorder(1));
        hboxRange.add(Box.createHorizontalGlue());
        hboxRange.add(labelForFrom);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldFrom);
        hboxRange.add(Box.createHorizontalStrut(20));
        hboxRange.add(labelForTo);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldTo);
        hboxRange.add(Box.createHorizontalGlue());
        getContentPane().add(hboxRange, BorderLayout.CENTER);
        JButton cancelButton = new JButton(("Отмена"));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                i = 1;
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