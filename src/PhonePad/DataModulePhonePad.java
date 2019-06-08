package PhonePad;

import java.sql.*;

public class DataModulePhonePad {	
    private Connection connectDB;
    private Statement statMain;
    private ResultSet rsMain;
    private Statement statTypes;
    private ResultSet rsTypes;
    private Statement statExec;
    private String url;

    public DataModulePhonePad(String DBDest) {
        this.url = PhonePadStrings.UrlPre + DBDest;
        connectDB = null;
        try {
            try {
                Class.forName("org.sqlite.JDBC");
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
            rsMain = statMain.executeQuery(PhonePadStrings.SqlSelMain);
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
            sqlEdit = "INSERT INTO main (first_name, last_name, type, phone) VALUES (\""+FirstName+"\",\""+LastName+"\","+types+",\""+Phone+"\")";
        } else {
            sqlEdit = "UPDATE main SET first_name=\""+FirstName+"\", last_name=\""+LastName+"\", type="+types+", phone=\""+Phone+"\" WHERE id="+id;
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
            res = statExec.executeUpdate(PhonePadStrings.SqlDelete+id);
            statExec.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void openTypes(){
        try {
            statTypes = connectDB.createStatement();
            rsTypes = statTypes.executeQuery(PhonePadStrings.SqlSelTypes);
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
