package Items;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ItemProps {
    public static final Properties props = new Properties();

    public static boolean loadProperties(String path) {
        try (InputStream input = new FileInputStream(path)) {
            // load a properties file
            ItemProps.props.load(input);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
