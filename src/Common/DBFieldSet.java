package Common;

import java.util.ArrayList;

public class DBFieldSet {	
	private ArrayList<DBField> fieldSet;
	
    public DBFieldSet() {
        fieldSet = new ArrayList<>();
        DBField fld = new DBField("id", Strings.FieldsName[0]);
        fld.setVisible(false);
        fieldSet.add(fld);
        fld = new DBField("first_name", Strings.FieldsName[1]);
        fieldSet.add(fld);
        fld = new DBField("last_name", Strings.FieldsName[2]);
        fieldSet.add(fld);
        fld = new DBField("type", Strings.FieldsName[3]);
        fieldSet.add(fld);
        fld = new DBField("phone", Strings.FieldsName[4]);
        fieldSet.add(fld);
    }

    public ArrayList<DBField> getFieldSet() {
        return fieldSet;
    }

}
