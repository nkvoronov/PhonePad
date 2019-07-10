package common;

import java.util.ArrayList;

import gui.Messages;

public class DBFieldSet {	
	private ArrayList<DBField> fieldSet = new ArrayList<>();
	
    public DBFieldSet() {
        DBField fld = new DBField("id", Messages.getString("FieldIndex"));
        fld.setVisible(false);
        fieldSet.add(fld);
        fld = new DBField("first_name", Messages.getString("FieldFirstName"));
        fieldSet.add(fld);
        fld = new DBField("last_name", Messages.getString("FieldSecondName"));
        fieldSet.add(fld);
        fld = new DBField("type", Messages.getString("FieldType"));
        fieldSet.add(fld);
        fld = new DBField("phone", Messages.getString("FieldPhone"));
        fieldSet.add(fld);
    }

    public ArrayList<DBField> getFieldSet() {
        return fieldSet;
    }

}
