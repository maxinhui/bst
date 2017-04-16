package top.builbu.common.util;
/**
 * 拔取网html
 */
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
	

	    static String urlr="http://www.iqiyi.com/v_19rrldre6k.html?fv=044527ff3e897f86ae7f5547d8fa3b4d";
	    
	    static PriorityQueue<String> queue =null;   
	    
	    static ExecutorService pool = Executors.newFixedThreadPool(5); 
	    /**
	     * @param args
	     * @throws Exception
	     */
	    public static void main(String[] args) throws Exception {
	        
	    	article(urlr);
	
	    }

	    /**
	     * 获取指定HTML 文档指定的body
	     * @throws IOException
	     */
	    public static void BolgBody(String url) throws IOException {
	    	 
	     
	        Document doc2 = Jsoup.connect(url).get();
	        String title = doc2.body().toString();
	        System.out.println(title);
	    }

	    /**
	     * 
	     * @throws InterruptedException 
	     * @throws IOException 
	     */
	    public static void article(String url) throws InterruptedException, IOException {
	        Document doc;
	       
	          //  BolgBody(url);
	            doc = Jsoup.connect(url).get();
	            Elements ListDiv = doc.getElementsByTag("body");//获取一个页面的body内容
	            if(null!=ListDiv){
	            	 
	            for (Element element :ListDiv) {
	                Elements links = element.getElementsByTag("a");//获取body中所有a
//	                for (Element link : links) {
//	                    String linkHref = link.attr("href");
//	                    //String linkText = link.text().trim();
//	                    //System.out.println(linkHref);
//	                    //System.out.println(linkText);
//	                   queue.add(linkHref);
//	                    
//	                   // BolgBody(linkHref);
//	                   // article(linkHref);
//	                }
	                threadNumber(links);
	            }
	            }
	       

	    }

	/**
	 * 限定多线程的个数
	 * @param links
	 * @throws InterruptedException
	 */
	public static void threadNumber(Elements links ) throws InterruptedException{
        int threadNumber = 20;//线程数，在我的机器上20个线程效果最佳
        int queueSize = 20;  //queue个数
        int step = links.size() / threadNumber; //把数据分成20份
     
        for(int i=0;i<=links.size();i+=step){
        	if(i - step >=0){
        		 int min=i - step;
        		 queue = new PriorityQueue<String>(queueSize,Collections.reverseOrder());	
        		 for(int t=min;t<i;t++){
        			 String linkHref = links.get(t).attr("href");//获得a标签的href
        			 queue.add(linkHref);//装载数据
        			
        		 }
        		 synQueue();
        	}
        }
        pool.shutdown();
		pool.awaitTermination(500, TimeUnit.MILLISECONDS);
	}
	
	
	
	/**
	 * 线程池开启线程
	 * @throws InterruptedException
	 */
	public static void synQueue() throws InterruptedException{
	
				Consumer consumer = new Consumer(queue); 
     			pool.execute(consumer);
     			
     			
		
	}
	
}
