package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

   public static ThreadLocal<Properties> property=new ThreadLocal<Properties>();
  // public static    Properties prop;
    private static final String configpath="/src/test/resources/config/config.properties";

    public static void initializePropertyFile()  {
        Properties  prop =new Properties();
        try {
            InputStream inputStream = new FileInputStream(System.getProperty("user.dir") + configpath);
            prop.load(inputStream);
           property.set(prop);
        }catch (IOException e){
            throw new RuntimeException("Config file not found at: " + configpath, e);
        }

    }



}
