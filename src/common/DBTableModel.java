package common;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class DBTableModel extends AbstractTableModel{
    private int countRows;
    private DBFieldSet dbFS;
    private ArrayList<String[]> data;
    
    public void initData(ResultSet rs){
        countRows = 0;
        data.clear();
        int colcount = dbFS.getFieldSet().size();
        try {
            while (rs.next()) {
                String[] rdata = new String[colcount];
                for (int c = 0; c < colcount; c++) {
                    rdata[c] = rs.getString(dbFS.getFieldSet().get(c).getName());
                }
                data.add(rdata);
                countRows++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DBTableModel(){
        super();
        dbFS = new DBFieldSet();
        data = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return countRows;
    }

    @Override
    public int getColumnCount() {
        return dbFS.getFieldSet().size();
    }

    @Override
    public String getColumnName(int column){
        return dbFS.getFieldSet().get(column).getTitle();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

}
