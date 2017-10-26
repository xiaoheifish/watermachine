package com.terabits.task;

import com.github.wxpay.sdk.WXPay;
import com.terabits.config.MyConfig;
import com.terabits.meta.po.RefundRecordPO;
import com.terabits.service.RefundRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/25.
 */
@Component
@Transactional
public class ManageRefund {
    @Autowired
    private RefundRecordService refundRecordService;

    private static Logger logger = LoggerFactory.getLogger(ManageRefund.class);

    //每隔2分钟查询待处理退款，完成退款操作
    @Scheduled(cron = "0 0/2 * * * *")
    void manageConsumeRefund()throws Exception{
        System.out.println("12346");
        List<RefundRecordPO> refundRecordPOList = new ArrayList<RefundRecordPO>();
        try{
            refundRecordPOList = refundRecordService.selectUnsolveRefund();
        }catch (Exception e){
            logger.error(e.toString());
        }
        for(RefundRecordPO refundRecordPO : refundRecordPOList){
            if(computeTimeDiff(refundRecordPO.getGmtCreate()) > 120){
                MyConfig config = new MyConfig();
                WXPay wxpay = new WXPay(config);
                String refundFee = computeRefundFee(refundRecordPO.getMoney());
                Map<String, String> data = new HashMap<String, String>();
                data.put("transaction_id", refundRecordPO.getTradeNo());
                data.put("out_refund_no", refundRecordPO.getRefundNo());
                data.put("total_fee", refundFee);
                data.put("refund_fee",refundFee);
                try {
                    Map<String, String> resp = wxpay.refund(data);
                    try{
                        refundRecordService.updateRefundStatus(resp.get("refund_id"),refundRecordPO.getRefundNo());
                    }catch (Exception e){
                        logger.error("refundRecordService.updateRefundStatus error in ManageRefund, refundRecordPO = " + refundRecordPO + "refund_id = "+ resp.get("refund_id"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private long computeTimeDiff(String gmtCreate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        long timeDiff = 0;
        try{
            timeDiff = (now.getTime() - (sdf.parse(gmtCreate)).getTime()) / 1000;
        }catch (Exception e){
            e.printStackTrace();
        }
        return timeDiff;
    }

    private String computeRefundFee(double money){
        int refund = (int)(money * 100);
        return String.valueOf(refund);
    }
}
