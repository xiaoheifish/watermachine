package com.terabits.manager;

import com.terabits.config.Constants;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.WechatConsumePO;
import com.terabits.service.TerminalService;
import com.terabits.service.WechatConsumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class QueryPayStatus {

    @Autowired
    private WechatConsumeService wechatConsumeService;
    @Autowired
    private TerminalService terminalService;
    private static Logger logger = LoggerFactory
            .getLogger(QueryPayStatus.class);

    //隔15s后去查该笔订单是否被支付，如果没有支付，则将termianl的状态改为10，允许其他用户下单
    @Async("asyncExecutor")
    public void getStatus(String orderId, String displayId) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("thread sleep error in QueryPayStatus");
        }
        try {
            WechatConsumePO wechatConsumePO = wechatConsumeService.selectWechatConsumeByOrderId(orderId);
            if(wechatConsumePO.getTradeNo() == null){
                TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
                terminalUpdateBO.setState(Constants.OFF_STATE);
                terminalUpdateBO.setDisplayId(displayId);
                try{
                    terminalService.updateTerminal(terminalUpdateBO);
                }catch (Exception e){
                    logger.error("terminalUpdate error in QueryPayStatus" + e.toString());
                }
            }
        }catch (Exception e){
            logger.error("wechatConsume error in QueryPayStatus" + e.toString());
        }
    }
}