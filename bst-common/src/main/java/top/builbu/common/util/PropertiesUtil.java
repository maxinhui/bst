package top.builbu.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesUtil {
	public static void loadProperties(Properties properties,String filePath) {
		try {
			InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			log.error("配置文件《"+filePath+"》没找到！", e);
		} catch (IOException e) {
			log.error("读取配置文件《"+filePath+"》", e);
		}
	}
}
