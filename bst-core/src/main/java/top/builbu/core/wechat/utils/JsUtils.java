package top.builbu.core.wechat.utils;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class JsUtils {

	

	public static final String download_media_url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	
    /** 
     * 获取媒体文件 
     * @param accessToken 接口访问凭证 
     * @param media_id 媒体文件id 
     * @param savePath 文件在服务器上的存储路径 
     * @throws IOException 
     * */  
    public static String downloadMedia(String accessToken, String mediaId, String fileSavePath) throws IOException {  
        String requestUrl = download_media_url.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
        System.out.print(requestUrl);
        	        URL url = new URL(requestUrl);
        
        	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        	        conn.setRequestMethod("GET");
        
        	        conn.setDoInput(true);
       
        	        conn.setDoOutput(true);
        
        	        InputStream in = conn.getInputStream();
        
        	         
        
        	        File dir = new File(fileSavePath);
        
        	        if (!dir.exists()) {
        
        	            dir.mkdirs();
       
        	        }
        
        	        if (!fileSavePath.endsWith("/")) {
        
        	            fileSavePath += "/";
        
        	        }
        
        	         
        
        	        String ContentDisposition = conn.getHeaderField("Content-disposition");
                    System.out.print(ContentDisposition);
        	        String weixinServerFileName = ContentDisposition.substring(ContentDisposition.indexOf("filename")+10, ContentDisposition.length() -1);
        
        	        fileSavePath += weixinServerFileName;
        
        	        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileSavePath));
       
        	        byte[] data = new byte[1024];
        
        	        int len = -1;
       
        	         
        
        	        while ((len = in.read(data)) != -1) {
        
        	            bos.write(data,0,len);
        
        	        }
        
        	         
       
        	        bos.close();
        
        	        in.close();
        
        	        conn.disconnect();
        
        	        System.out.print(fileSavePath);
        
        	        return fileSavePath;
       
        	    }
    
}
