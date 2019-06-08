package PhonePad;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class PhonePadTableModel extends AbstractTableModel{
    private int countRows;
    private MainFieldSet mfs;
    private ArrayList<String[]> data;
    
    public void initData(ResultSet rs){
        countRows = 0;
        data.clear();
        int colcount = mfs.getFieldSet().size();
        try {
            while (rs.next()) {
                String[] rdata = new String[colcount];
                for (int c = 0; c < colcount; c++) {
                    rdata[c] = rs.getString(mfs.getFieldSet().get(c).getName());
                }
                data.add(rdata);
                countRows++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PhonePadTableModel(){
        super();
        mfs = new MainFieldSet();
        data = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return countRows;
    }

    @Override
    public int getColumnCount() {
        return mfs.getFieldSet().size();
    }

    public String getColumnName(int column){
        return mfs.getFieldSet().get(column).getTitle();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

}
