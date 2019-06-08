package Common;

import java.io.File;
import java.nio.file.Paths;

public class Common {
    public static final String imageFolder = "images";
    
    public static String getImagesPatch() {
        return getCurrentPatch() + File.separator + imageFolder + File.separator;
    }

    public static String getCurrentPatch() {
        return Paths.get(".").toAbsolutePath().normalize().toString();
    }

}
