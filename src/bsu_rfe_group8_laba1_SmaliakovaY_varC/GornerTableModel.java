package bsu_rfe_group8_laba1_SmaliakovaY_varC;
import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel
{

    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    private Double[] result = new Double[3];
    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients)
    {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom()
    {
        return from;
    }

    public Double getTo()
    {
        return to;
    }

    public Double getStep()
    {
        return step;
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }

    public Object getValueAt(int row, int col) {
        double x = from + step*row;
        if (col == 0)
        {
            return x;
        }
        if (col == 1)
        {
            result[0] = 0.0;
            for(int i = 0; i < coefficients.length; i++){
                result[0] += Math.pow(x, coefficients.length-1-i)*coefficients[i];
            }
            return result[0];
        }
        if (col == 2)
        {
            result[1] = 0.0;
            int p = coefficients.length-1;
            for(int i = 0; i < coefficients.length; i++){
                result[1] += Math.pow(x, coefficients.length-1-i)*coefficients[p--];
            }
            return result[1];
        }
        else
        {
            return result[2] = result[0] - result[1];
        }
    }

    public Class<?> getColumnClass(int col)
    {
        return Double.class;
    }

    public String getColumnName(int col)
    {
        switch (col)
        {
            case 0:
                return "Значение Х:";
            case 1:
                return "Значение многочлена";
            case 2:
                return "Значение многочлена";
            default:
                return "Разница 1 и 2 столбца";
        }
    }
}
