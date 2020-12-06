package bsu_rfe_group8_laba1_SmaliakovaY_varC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainFrame extends JFrame {
    private int WIDTH = 600;
    private int HEIGHT = 400;
    private JFileChooser fileChooser = null;
    private JMenuItem saveToTextMenuItem;
    private JMenuItem saveToGraphicsMenuItem;
    private JMenuItem searchValueMenuItem;
    private JMenuItem saveToCSVMenuItem;
    private JMenuItem searchNeedValueMenuItem;

    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JTextField textFieldStep;

    private String From = null, To = null;

    private String NeedValue  = null;

    private RangeWindow window = new RangeWindow();

    private NeedValue windowNeed = new NeedValue();

    private Box hBoxResult;

    private GornerTableCellRenderer render = new GornerTableCellRenderer();

    private GornerTableModel data;

    private Double[] coefficients;

    public static void main(String arg[]) {
        if (arg.length == 0)
        {
            System.out.println("Невозможно табулировать многочлен, для которого не задано ни одного коэффицента!!!");
            System.exit(-1);
        }
        Double[] coefficients = new Double[arg.length];
        int i = 0;
        try{
            for(String args:arg)
            {
                coefficients[i++] = Double.parseDouble(args);
            }
        }catch (NumberFormatException ex)
        {
            System.out.println("Ошибка преобразования строки в число типа Double");
            System.exit(-2);
        }
        MainFrame frame = new MainFrame(coefficients);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public MainFrame(Double[] coefficients) {
        super("Вычисление значения многочлена");
        this.coefficients = coefficients;
        Toolkit kit = Toolkit.getDefaultToolkit();
        setSize(WIDTH, HEIGHT);
        setLocation((kit.getScreenSize().width - WIDTH) / 2, ((kit.getScreenSize()).height - HEIGHT) / 2);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);
        JMenu tableMenu = new JMenu("Таблица");
        menuBar.add(tableMenu);
        JMenu spravkaMenu = new JMenu("Справка");
        menuBar.add(spravkaMenu);
        Action aboutProgram = new AbstractAction("О программе") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainFrame.this, "Бла-Бла-Бла", "Информация о разработчике", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        spravkaMenu.add(aboutProgram);
        Action saveToTextAction = new AbstractAction("Сохранить в текстовый файл") {
            public void actionPerformed(ActionEvent e) {
                if (fileChooser == null) {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                }
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    saveToTextFile(fileChooser.getSelectedFile());
                }
            }
        };
        saveToTextMenuItem = fileMenu.add(saveToTextAction);
        saveToTextMenuItem.setEnabled(false);

        Action saveToGraphicsAction = new AbstractAction("Сохранить данные для построения графика") {
            public void actionPerformed(ActionEvent e)
            {
                if (fileChooser == null)
                {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                }
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    saveToGraphicsFile(fileChooser.getSelectedFile());
                }
            }

        };
        saveToGraphicsMenuItem = fileMenu.add(saveToGraphicsAction);
        saveToGraphicsMenuItem.setEnabled(false);

        Action saveToCSVAction = new AbstractAction("Сохранить данные в СSV файл") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileChooser == null)
                {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                }
                if(fileChooser.showSaveDialog(MainFrame.this)== JFileChooser.APPROVE_OPTION)
                {
                    saveToCSVFile(fileChooser.getSelectedFile());
                }
            }
        };
        saveToCSVMenuItem = fileMenu.add(saveToCSVAction);
        saveToCSVMenuItem.setEnabled(false);
        Action searchNeeedValue = new AbstractAction("Найти значение") {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowNeed.setVisible(true);
                windowNeed.OK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        windowNeed.setVisible(false);
                        NeedValue = windowNeed.textFieldForValue.getText();
                        render.setNeedle(NeedValue);
                        getContentPane().repaint();
                    }
                });
            }
        };
        searchNeedValueMenuItem = tableMenu.add(searchNeeedValue);
        searchNeedValueMenuItem.setEnabled(false);


        Action seachValueAction = new AbstractAction("Найти из диапазона") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(true);
                window.OK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        window.setVisible(false);
                        From = window.textFieldFrom.getText();
                        To = window.textFieldTo.getText();
                        render.setNeedle(From, To);
                        getContentPane().repaint();
                    }
                });
            }
        };
        searchValueMenuItem = tableMenu.add(seachValueAction);
        searchValueMenuItem.setEnabled(false);

        JLabel labelForFrom  = new JLabel("X изменяется от:");
        textFieldFrom = new JTextField("0.0", 10);
        textFieldFrom.setMaximumSize(textFieldFrom.getPreferredSize());
        JLabel labelForTo  = new JLabel("до:");
        textFieldTo = new JTextField("1.0", 10);
        textFieldTo.setMaximumSize(textFieldTo.getPreferredSize());
        JLabel labelForStep = new JLabel("с шагом:");
        textFieldStep = new JTextField("0.1", 10);
        textFieldStep.setMaximumSize(textFieldStep.getPreferredSize());
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
        hboxRange.add(Box.createHorizontalStrut(20));
        hboxRange.add(labelForStep);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldStep);
        hboxRange.add(Box.createHorizontalGlue());
        //hboxRange.setPreferredSize(new Dimension(new  Double(hboxRange.getMaximumSize().getWidth()).intValue(),new Double(hboxRange.getMaximumSize().getHeight()).intValue()*2));
        getContentPane().add(hboxRange, BorderLayout.NORTH);
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Double from = Double.parseDouble(textFieldFrom.getText());
                    Double to = Double.parseDouble(textFieldTo.getText());
                    Double step = Double.parseDouble(textFieldStep.getText());
                    data = new GornerTableModel(from, to, step, coefficients);
                    JTable table = new JTable(data);
                    table.setDefaultRenderer(Double.class, render);
                    table.setRowHeight(30);
                    hBoxResult.removeAll();
                    hBoxResult.add(new JScrollPane(table));
                    getContentPane().validate();
                    saveToTextMenuItem.setEnabled(true);
                    saveToGraphicsMenuItem.setEnabled(true);
                    searchValueMenuItem.setEnabled(true);
                    saveToCSVMenuItem.setEnabled(true);
                    searchNeedValueMenuItem.setEnabled(true);
                }catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибка формата числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldFrom.setText("0.0");
                textFieldStep.setText("0.1");
                textFieldTo.setText("1.0");
                hBoxResult.removeAll();
                hBoxResult.add(new JPanel());
                saveToTextMenuItem.setEnabled(false);
                saveToGraphicsMenuItem.setEnabled(false);
                searchValueMenuItem.setEnabled(false);
                saveToCSVMenuItem.setEnabled(false);
                searchNeedValueMenuItem.setEnabled(false);
                getContentPane().validate();
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.setBorder(BorderFactory.createBevelBorder(1));
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        //hboxButtons.setPreferredSize(new Dimension(new Double(hboxButtons.getMaximumSize().getWidth()).intValue(), new Double(hboxButtons.getMaximumSize().getHeight()).intValue()*2));
        getContentPane().add(hboxButtons, BorderLayout.SOUTH);
        hBoxResult = Box.createHorizontalBox();
        hBoxResult.add(new JPanel());
        getContentPane().add(hBoxResult,BorderLayout.CENTER);
    }

    protected void saveToTextFile(File selectedFile)
    {
        try {
            PrintStream out = new PrintStream(selectedFile);
            out.println("Результаты табулирования многочлена по схеме Горнера");
            out.print("Многочлен: ");
            for (int i = 0; i < coefficients.length; i++)
            {
                out.print(coefficients[i] + "*X^"+(coefficients.length-i-1));
                if (i!= coefficients.length-1)
                    out.print(" + ");
            }
            out.println("");
            out.println("Интервал от " + data.getFrom() + "до " + data.getTo() + " с шагом " + data.getStep());
            out.println("=================================================================");
            for (int i = 0; i < data.getRowCount(); i++)
            {
                out.println("Значение в точке " + data.getValueAt(i,0)+ " равно " + data.getValueAt(i,1));
            }
            out.close();
        }catch (FileNotFoundException ex)
        {

        }

    }

    protected void saveToGraphicsFile(File selectedFile)
    {
        try{
            DataOutputStream out = new DataOutputStream(new FileOutputStream(selectedFile));
            for (int i = 0; i < data.getRowCount(); i++)
            {
                out.writeDouble((Double)data.getValueAt(i,0));
                out.writeDouble((Double)data.getValueAt(i,1));
            }
            out.close();
        }catch (Exception ex)
        {

        }
    }

    protected void saveToCSVFile(File selectedFile) {
        try {
            PrintStream out = new PrintStream(selectedFile);
            for (int i = 0; i < data.getRowCount(); i++) {
                out.println(data.getValueAt(i, 0) + "," + data.getValueAt(i, 1)+ "," + data.getValueAt(i, 2)+ "," + data.getValueAt(i, 3));
            }
            out.close();
        } catch (FileNotFoundException ex) {
        }
    }
}
