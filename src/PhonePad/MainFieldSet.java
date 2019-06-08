package PhonePad;

import java.util.ArrayList;

public class MainFieldSet {	
	private ArrayList<FieldDataBase> fieldSet;
	
    public MainFieldSet() {
        fieldSet = new ArrayList<>();
        FieldDataBase fld = new FieldDataBase("id", PhonePadStrings.FieldsName[0]);
        fld.setVisible(false);
        fieldSet.add(fld);
        fld = new FieldDataBase("first_name", PhonePadStrings.FieldsName[1]);
        fieldSet.add(fld);
        fld = new FieldDataBase("last_name", PhonePadStrings.FieldsName[2]);
        fieldSet.add(fld);
        fld = new FieldDataBase("type", PhonePadStrings.FieldsName[3]);
        fieldSet.add(fld);
        fld = new FieldDataBase("phone", PhonePadStrings.FieldsName[4]);
        fieldSet.add(fld);
    }

    public ArrayList<FieldDataBase> getFieldSet() {
        return fieldSet;
    }

}
