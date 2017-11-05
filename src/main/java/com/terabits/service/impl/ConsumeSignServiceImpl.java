package com.terabits.service.impl;

import com.terabits.mapper.ConsumeSignMapper;
import com.terabits.meta.po.ConsumeSignPO;
import com.terabits.service.ConsumeSignService;
import com.terabits.service.MedalExchangeService;
import com.terabits.utils.ScoreRuleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/24.
 */
@Service("consumeSignService")
public class ConsumeSignServiceImpl implements ConsumeSignService {

    @Autowired(required = false)
    private ConsumeSignMapper consumeSignMapper;
    @Autowired
    private MedalExchangeService medalExchangeService;

    private static Logger logger = LoggerFactory.getLogger(ConsumeSignServiceImpl.class);

    public int insertConsumeSign(ConsumeSignPO consumeSignPO) throws Exception {
        return consumeSignMapper.insertConsumeSign(consumeSignPO);
    }

    public int updateConsumeSign(ConsumeSignPO consumeSignPO) throws Exception {
        return consumeSignMapper.updateConsumeSign(consumeSignPO);
    }

    public ConsumeSignPO selectConsumeSign(String openId) throws Exception {
        return consumeSignMapper.selectConsumeSign(openId);
    }

    //根据openId和最后消费时间更新消费历史记录表
    public int updateSign(String openId, String gmtModified) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ConsumeSignPO consumeSignPO = null;
        try {
            consumeSignPO = selectConsumeSign(openId);
        } catch (Exception e) {
            logger.error("consumeSignService.selectConsumeSign error in ConsumeSignService " + openId + gmtModified + e.toString());
            return 0;
        }
        if (consumeSignPO.getGmtModified().substring(0, 10).equals(gmtModified.substring(0, 10))) {
            //如果今天已经消费过了，那只需要更改一下最后消费时间
        } else {
            //如果是本月的消费，则要更新消费历史，左移间隔天数，末位置为1
            if (consumeSignPO.getGmtModified().substring(0, 7).equals(gmtModified.substring(0, 7))) {
                int dateRecord = Integer.parseInt(consumeSignPO.getGmtModified().substring(5, 7));
                int dateToday = Integer.parseInt(gmtModified.substring(5, 7));
                long signHistory = consumeSignPO.getSignHistory();
                consumeSignPO.setSignHistory(ScoreRuleUtil.moveByte(signHistory, dateToday - dateRecord));
                //判断是否是连续消费，更新连续消费天数
                if ((dateToday - dateRecord) == 1) {
                    consumeSignPO.setSignCount(consumeSignPO.getSignCount() + 1);
                } else {
                    consumeSignPO.setSignCount(1);
                }

            } else {
                //如果隔了一个月才消费，那么消费历史和消费天数都置为1即可
                consumeSignPO.setSignCount(1);
                consumeSignPO.setSignCount(1);
            }
        }
        //更改最后消费时间
        consumeSignPO.setGmtModified(gmtModified);
        try {
            updateConsumeSign(consumeSignPO);
            return 1;
        } catch (Exception e) {
            logger.error("cosumeSignService.updateConsumeSign gmtModified error " + consumeSignPO + e.toString());
            return 0;
        }
    }

    public int getRemainMedal(String openId) {
        ConsumeSignPO consumeSignPO = null;
        try {
            consumeSignPO = selectConsumeSign(openId);
        } catch (Exception e) {
            logger.error("getRemainMedal error in ConsumeSignServiceImpl" + openId);
            return 500;
        }
        Map<ScoreRuleUtil.MedalType, Integer> signMedal = ScoreRuleUtil.historyToMedal(consumeSignPO.getSignHistory());
        int exchangeMedal = medalExchangeService.getMonthlyExchange(openId);
        if (exchangeMedal == 500) {
            return exchangeMedal;
        }
        int totalMedal = signMedal.get(ScoreRuleUtil.MedalType.GOLD) * 100 + signMedal.get(ScoreRuleUtil.MedalType.SILVER) * 10
                + signMedal.get(ScoreRuleUtil.MedalType.BRONZE);
        return totalMedal - exchangeMedal;
    }

}
