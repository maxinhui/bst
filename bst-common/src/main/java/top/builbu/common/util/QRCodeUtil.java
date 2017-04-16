package top.builbu.common.util;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Created by guoxinli on 2015/8/5.
 */
public class QRCodeUtil {

	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "JPG";
	
	// 二维码尺寸
	private static final int QRCODE_SIZE = 300;
    // LOGO宽度  
	private static final int WIDTH = 60;
    // LOGO高度  
	private static final int HEIGHT = 60;

	private static final int MULTIPLE = 3;

	private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE * MULTIPLE,
				QRCODE_SIZE * MULTIPLE, hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		if (imgPath == null || "".equals(imgPath)) {
			return image;
		}
		// 插入图片  
		QRCodeUtil.insertImage(image, imgPath, needCompress);
		return image;
	}
	  
    /** 
     * 插入LOGO 
     *  
     * @param source 
     *            二维码图片 
     * @param imgPath 
     *            LOGO图片地址 
     * @param needCompress 
     *            是否压缩 
     * @throws Exception 
     */
	private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
		File file = new File(imgPath);
		if (!file.exists()) {
			System.err.println("" + imgPath + "file not exits!");
			return;
		}
		Image src = ImageIO.read(new File(imgPath));

		if (needCompress) {
			// 压缩LOGO  
			Image image = src.getScaledInstance(WIDTH * MULTIPLE, HEIGHT * MULTIPLE, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(WIDTH * MULTIPLE, HEIGHT * MULTIPLE, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			// 绘制缩小后的图 
			g.drawImage(image, 0, 0, null);
			g.dispose();
			src = image;
		}
        // 插入LOGO  
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE * MULTIPLE - WIDTH * MULTIPLE) / 2;
		int y = (QRCODE_SIZE * MULTIPLE - HEIGHT * MULTIPLE) / 2;
		graph.drawImage(src, x, y, WIDTH * MULTIPLE, HEIGHT * MULTIPLE, null);
		Shape shape = new RoundRectangle2D.Float(x, y, WIDTH * MULTIPLE, WIDTH * MULTIPLE, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}
	
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param imgPath 
     *            LOGO地址 
     * @param destPath 
     *            存放目录 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */
	public static void encode(String content, String imgPath, String destPath, String fileName, boolean needCompress)
			throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
		mkdirs(destPath);
		// String file = new Random().nextInt(99999999) + ".jpg";
		ImageIO.write(image, FORMAT_NAME, new File(destPath + "/" + fileName));
	}
	  
    /** 
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常) 
     * @author lanyuan 
     * Email: mmm333zzz520@163.com 
     * @date 2013-12-11 上午10:16:36 
     * @param destPath 存放目录 
     */
	public static void mkdirs(String destPath) {
		File file = new File(destPath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}
	  
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param imgPath 
     *            LOGO地址 
     * @param destPath 
     *            存储地址 
     * @throws Exception 
     */
	public static void encode(String content, String imgPath, String destPath, String fileName) throws Exception {
		QRCodeUtil.encode(content, imgPath, destPath, fileName, false);
	}
	
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */
	public static void encode(String content, String destPath, String fileName, boolean needCompress) throws Exception {
		QRCodeUtil.encode(content, null, destPath, fileName, needCompress);
	}
	  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @throws Exception 
     */ 
	public static void encode(String content, String destPath) throws Exception {
		QRCodeUtil.encode(content, null, destPath, false);
	}
  
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param imgPath 
     *            LOGO地址 
     * @param output 
     *            输出流 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */
	public static void encode(String content, String imgPath, OutputStream output, boolean needCompress)
			throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
		ImageIO.write(image, FORMAT_NAME, output);
	}
  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param output 
     *            输出流 
     * @throws Exception 
     */  
	public static void encode(String content, OutputStream output) throws Exception {
		QRCodeUtil.encode(content, null, output, false);
	}
	  
    /** 
     * 解析二维码 
     *  
     * @param file 
     *            二维码图片 
     * @return 
     * @throws Exception 
     */
	public static String decode(String file) throws Exception {
		BufferedImage image;
		
		image = ImageIO.read(new URL(file));
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		return resultStr;
	}
	  
    /** 
     * 解析二维码 
     *  
     * @param path 
     *            二维码图片地址 
     * @return 
     * @throws Exception 
     */ 
//	public static String decode(String path) throws Exception {
//		return QRCodeUtil.decode(new File(path));
//	}
	
	

	  
    /** 
     * 生成二维码
     * 
     * @param projectName		项目名称
     * @param logoBasePath		logo路径前缀
     * @param destBasePath		二维码存储路径前缀
     * @param baseUrl			请求Url前缀
     * @param requestPath		请求
     * @param paramMap			参数列表
     * @param fileName				文件名
     * @param needCompress		是否压缩
     * @throws Exception
     */
	public static void encode(String projectName, String codeBasePath, String baseUrl, String requestPath, Map<String,String> paramMap, String fileName, boolean needCompress) throws Exception {
		
		String content = baseUrl;
		if(null!=requestPath){
			content=content+requestPath;
		}
		if(null!=paramMap&&!paramMap.isEmpty()){
			content=content+"?";
			Iterator<String> keyIterator = paramMap.keySet().iterator();
			String params ="";
			while(keyIterator.hasNext()){
				String key = keyIterator.next();
				params=params+"&"+key+"="+paramMap.get(key);
			}
			content=content+params.substring(1);
		}
		String imgPath=codeBasePath+projectName+"/logo/logo.png";
		String destBasePath=codeBasePath+projectName+"/";
		QRCodeUtil.encode(content, imgPath, destBasePath, fileName, false);
		System.out.println(fileName+"："+QRCodeUtil.decode("D:/chixun/sourcecode/chixun-framework/chixun-common/QRCode/"+projectName+"/"+fileName));
	}

	public static void main(String[] args) throws Exception {

		String projectName = "zhaoshaxian";
		
		
		String name ="注册";
		String requestPath = "/customer/lr";
		
//		String name ="首页";
//		String requestPath = "/sys/index";
//		
//		
//		String name ="求购信息";
//		String requestPath = "/order/purchase";
//
//		String name ="现货信息";
//		String requestPath = "/resource/spot";
//
//		String eam="18766396397";
//		
////		String name ="上海参展商推广首页";
////		String ec="20160316001";
//		
//		String name ="上海观展商推广首页";
//		String ec="20160316002";
//		name=ec+name;
//		String requestPath = "/sys/extensionIndex";
		
//		String url = "http://www.zhaoshaxian.com/zhaoshaxian-weixin"+requestPath;
		String baseUrl = "http://m.zhaoshaxian.com/zhaoshaxian-weixin";
//		http://www.zhaoshaxian.com/zhaoshaxian-weixin/resource/spot?from=singlemessage&isappinstalled=0
//		http://www.zhaoshaxian.com/zhaoshaxian-weixin/order/purchase?from=singlemessage&isappinstalled=0


		String codeBasePath="D:/chixun/sourcecode/chixun-framework/chixun-common/QRCode/";
		String fileName=name+".jpg";

		QRCodeUtil.encode(projectName, codeBasePath, baseUrl, requestPath, null, fileName, false);
		
		System.out.println("首页："+QRCodeUtil.decode("D:/chixun/sourcecode/chixun-framework/chixun-common/logo/niurendao/niurendao.jpg"));
//		System.out.println("注册："+QRCodeUtil.decode("D:/chixun/sourcecode/chixun-framework/chixun-common/logo/zhaoshaxian/注册.jpg"));
		
	}
}
