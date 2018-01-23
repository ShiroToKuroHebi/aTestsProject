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
	
	String getStandURL() throws IOException {
		String result = "";
		
		try {
			
			String propFileName = "settings.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath.");
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
