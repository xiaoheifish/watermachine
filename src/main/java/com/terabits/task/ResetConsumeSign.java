package com.terabits.task;

import com.terabits.service.ConsumeSignService;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/11/6.
 */
@Component
@Transactional
public class ResetConsumeSign {
    @Autowired
    private ConsumeSignService consumeSignService;

    private static Logger logger = LoggerFactory.getLogger(ResetConsumeSign.class);

    @Scheduled(cron = "0 0 1 1 * ?")
    void resetConsumeSign(){
        try{
            consumeSignService.resetConsumeSign();
        }catch (Exception e){
            logger.error("resetConsumeSign error in ResetConsumeSign task");
        }
    }
}
