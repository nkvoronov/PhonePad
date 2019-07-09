package Common;

import java.sql.*;

public class DataModule {	
    private Connection connectDB;
    private Statement statMain;
    private ResultSet rsMain;
    private Statement statTypes;
    private ResultSet rsTypes;
    private Statement statExec;
    private String url;

    public DataModule(String DBDest) {
        this.url = Strings.UrlPre + DBDest;
        connectDB = null;
        try {
            try {
                Class.forName(Strings.ClassName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connectDB = DriverManager.getConnection(this.url);
            statMain = null;
            rsMain = null;
            statTypes = null;
            rsTypes = null;
            statExec = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openMain(){
        try {
            statMain = connectDB.createStatement();
            rsMain = statMain.executeQuery(Strings.SqlSelMain);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeMain(){
        try {
            rsMain.close();
            statMain.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reopenMain(){
        closeMain();
        openMain();
    }

    public int editMain(int id, String FirstName, String LastName, int types, String Phone){
        String sqlEdit = "";
        int res = -1;
        if (id<0){
            sqlEdit = String.format(Strings.SqlAdd, FirstName, LastName, types, Phone);
        } else {
            sqlEdit = String.format(Strings.SqlEdit, FirstName, LastName, types, Phone, id);
        }
        try {
            statExec = connectDB.createStatement();
            res = statExec.executeUpdate(sqlEdit);
            statExec.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int deleteMain(int id){
        int res = -1;
        try {
            statExec = connectDB.createStatement();
            res = statExec.executeUpdate(String.format(Strings.SqlDelete, id));
            statExec.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void openTypes(){
        try {
            statTypes = connectDB.createStatement();
            rsTypes = statTypes.executeQuery(Strings.SqlSelTypes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeTypes(){
        try {
            rsTypes.close();
            statTypes.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reopenTypes(){
        closeTypes();
        openTypes();
    }

    public void closeDB(){
        try {
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeAll(){
        closeMain();
        closeTypes();
        closeDB();
    }

    public Connection getConnectDB() {
        return connectDB;
    }

    public ResultSet getRsMain() {
        return rsMain;
    }

    public ResultSet getRsTypes() {
        return rsTypes;
    }

}
