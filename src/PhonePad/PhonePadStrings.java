package PhonePad;

public class PhonePadStrings {
	static final String TitleApp       = "Телефонный справочник";
	static final String TitleBtAdd     = "Добавить";
	static final String TitleBtEdt     = "Изменить";
	static final String TitleBtDel     = "Удалить";
	static final String TitleDlgAdd    = "Добавить";
	static final String TitleDlgEdt    = "Редактировать";
	
	static final String[] FieldsName   = {"№", "Имя", "Фамилия", "Тип", "Телефон"};
	
	static final String TitleBtOk      = "Выполнить";
	static final String TitleBtCancel  = "Отмена";
	
    static final String UrlPre         = "jdbc:sqlite:";
    static final String SqlSelMain     = "SELECT m.id, m.first_name, m.last_name, t.name AS type, m.phone FROM main m JOIN types t ON (m.type=t.id) ORDER BY m.id";
    static final String SqlSelTypes    = "SELECT * FROM types ORDER BY id";
    static final String SqlDelete      = "DELETE FROM main WHERE id=";

}
