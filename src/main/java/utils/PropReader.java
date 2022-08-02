package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Prop reader.
 */
public class PropReader {

    private static PropReader propertyInstance = null;
    private static final Properties prop = new Properties();

    private PropReader() throws IOException {
            prop.load((InputStream) loadResourceAsInputStream(RequestMethods.getEnv() + ".properties"));
    }

    /**
     * Load resource as input stream object.
     *
     * @param prop the prop
     * @return the object
     */
    public Object loadResourceAsInputStream(String prop) {
        return getClass().getClassLoader().getResourceAsStream(prop);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */

    //Singleton Design Pattern
    public static PropReader getInstance() {
        try {
            if (propertyInstance == null) {
                synchronized (PropReader.class) {
                    propertyInstance = new PropReader();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return propertyInstance;
    }

    /**
     * Gets host.
     *
     * @return the host
     */
    public String getHost() {
        return prop.getProperty("host");
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return prop.getProperty("userId");
    }

}


