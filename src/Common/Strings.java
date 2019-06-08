package Common;

public class Strings {
	public static final String TitleApp       = "Телефонный справочник";
	public static final String TitleBtAdd     = "Добавить";
	public static final String TitleBtEdt     = "Изменить";
	public static final String TitleBtDel     = "Удалить";
	public static final String TitleDlgAdd    = "Добавить";
	public static final String TitleDlgEdt    = "Редактировать";
	
	public static final String[] FieldsName   = {"№", "Имя", "Фамилия", "Тип", "Телефон"};
	
	public static final String TitleBtOk      = "Выполнить";
	public static final String TitleBtCancel  = "Отмена";
	
	public static final String UrlPre         = "jdbc:sqlite:";
	public static final String SqlSelMain     = "SELECT m.id, m.first_name, m.last_name, t.name AS type, m.phone FROM main m JOIN types t ON (m.type=t.id) ORDER BY m.id";
	public static final String SqlSelTypes    = "SELECT * FROM types ORDER BY id";
	public static final String SqlDelete      = "DELETE FROM main WHERE id=";

}
