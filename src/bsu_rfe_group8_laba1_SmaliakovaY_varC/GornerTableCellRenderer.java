package bsu_rfe_group8_laba1_SmaliakovaY_varC;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class GornerTableCellRenderer implements TableCellRenderer
{
    private String needleMax = null;
    private String needleMin = null;
    private String needle = null;
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        String formatterDouble = formatter.format(value);
        label.setText(formatterDouble);
        label.setForeground(Color.white);
        if(col == 0 && needleMax != null && needleMin != null && Double.parseDouble(needleMax) >= Double.parseDouble(value.toString())-0.00001 && Double.parseDouble(needleMin) <= Double.parseDouble(value.toString())+0.00001 )
        {
            panel.setBackground(Color.RED);
        }
        else
        {
            if (col > 0 && col < 3 && needle != null && Double.parseDouble(needle) == Double.parseDouble(value.toString()))
            {
                panel.setBackground(Color.BLUE);
            }
            else {
                if ((col + row) % 2 == 0) {
                    panel.setBackground(Color.black);
                } else
                {
                    panel.setBackground(Color.white);
                    label.setForeground(Color.black);
                }
            }
        }
        return panel;
    }

    public void setNeedle(String needleMin, String needleMax)
    {
        this.needleMin = needleMin;
        this.needleMax = needleMax;
    }

    public void setNeedle(String needle)
    {
        this.needle = needle;
    }

    public GornerTableCellRenderer()
    {
        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
    }
}