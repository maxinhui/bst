package top.builbu.website.wechat.controller;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Slf4j
@Component
public class ConfigJob {


	
	//@Scheduled(cron="0 0/1 * * * ?")
	public void updateWinningChance(){
		Date now = new Date();
		
		log.info("now：["+now+"] ");
	}
}
