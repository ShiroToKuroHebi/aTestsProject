package vtbStand;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertyValues {
	private static Logger logger = Logger.getLogger(PropertyValues.class);
	private Properties properties = new Properties();
	private static InputStream inputStream;
	
	private final static String PROP_FILE_NAME = "settings.properties";
	
	String getStandURL() throws IOException {
		String result = "";
		
		try {
			
			inputStream = getClass().getClassLoader().getResourceAsStream(PROP_FILE_NAME);
			
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + PROP_FILE_NAME + "' not found in the classpath.");
			}
			
			result = properties.getProperty("standURL");
			
		} catch (Exception e) {
			logger.info("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
		return result;
	}
	
}
