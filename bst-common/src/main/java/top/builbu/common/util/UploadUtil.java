package top.builbu.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
	private static final Logger logger = LogManager.getLogger(UploadUtil.class);

	
	/**
	 * @author MA
	 * @param files
	 *            上传文件
	 * @param request
	 * @param upload
	 *            原始图存放文件夹名
	 * @param minUpload
	 *            压缩图存放文件夹名
	 * @return 压缩图的地址 多个以,隔开
	 * @throws Exception
	 */
	public static String uploadImg(MultipartFile file, String contextPath, String filePath) {
		File imgFile = new File(contextPath+filePath);
		if (!imgFile.exists()) {
			try {
				imgFile.mkdirs();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);  
			}
		} // 判断是否有词路径.没有新建
		filePath += "/" + new Date().getTime() + "." + file.getOriginalFilename().split("\\.")[1];
		String realFilePath=contextPath+filePath;
		// 转存文件
		try {
			file.transferTo(new File(realFilePath));
		} catch (IllegalStateException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return filePath;
	}
	
	public static String getPath(){
		return null;
	}
}

