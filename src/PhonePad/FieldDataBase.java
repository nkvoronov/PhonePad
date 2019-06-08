package PhonePad;

public class FieldDataBase {
    private String title;
    private String name;
    private int type = 0;
    private Boolean visible = true;
    
    public FieldDataBase(String name) {
        this.name = name;
    }

    public FieldDataBase(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

}
