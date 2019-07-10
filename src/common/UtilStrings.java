package common;

public class UtilStrings {
	public static final String CLASS_NAME      = "org.sqlite.JDBC";
	public static final String URL_PRE         = "jdbc:sqlite:";
	public static final String SQL_SELMAIN     = "SELECT m.id, m.first_name, m.last_name, t.name AS type, m.phone FROM main m JOIN types t ON (m.type=t.id) ORDER BY m.id";
	public static final String SQL_SELTYPES    = "SELECT * FROM types ORDER BY id";
	public static final String SQL_DELETE      = "DELETE FROM main WHERE id=%d";
	public static final String SQL_ADD         = "INSERT INTO main (first_name, last_name, type, phone) VALUES (\"%s\",\"%s\",%d,\"%s\")";
	public static final String SQL_EDIT        = "UPDATE main SET first_name=\"%s\", last_name=\"%s\", type=%d, phone=\"%s\" WHERE id=%d";
	
	private UtilStrings() {
		throw new IllegalStateException("Utility class");
	}
	
}
