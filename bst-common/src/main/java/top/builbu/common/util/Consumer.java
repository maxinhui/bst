package top.builbu.common.util;


import java.util.PriorityQueue;
/**
 * 多线程执行
 * @author Administrator
 *
 */
public class Consumer implements Runnable{
	private PriorityQueue<String> queue = null;  
	
    public Consumer(PriorityQueue<String> queue){  
        this.queue=queue;  
    }  
    
    private void consume(){  
    	

        while(queue.size()>0){  
            synchronized (queue) {  //首先锁定对象  
          //      如果队列为空，那么消费者无法消费，必须等待生产者产生商品，所以需要释放对象锁，并让自己进入等待状态  
              //  System.out.println("当前队列中剩余数据个数："+queue.size());  
            	
                while(queue.size()==0) {  
                	
                 try {
					queue.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  //使用wait()这个方法的时候，对象必须是获取锁的状态，调用了这个方法后，线程会释放该对象锁  
                }  
             //   如果不为空,取出第一个对象 
               
                String url= queue.poll();
              
                System.out.println(url);
                
//                try {
//					jsoupUtil.BolgBody(url);
//				} catch (IOException e1) {
//					
//					e1.printStackTrace();
//				}
                
             //   注意notify()方法就是释放这个对象的锁，从而其他需要这个对象的线程中就会有一个能够获得锁，但是不能指定具体的线程  
                queue.notify();  
                try {  
                    Thread.sleep(100);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
               // System.out.println("消费一个数据后，队列中剩余数据个数："+queue.size()+"线程");
                
            }  
               
        }  
    }  
    
    
 
    public void run() {
    	System.out.println("启动: " );
        this.consume();
        System.out.println("结束: ");
 
    }
    
    
    
}
