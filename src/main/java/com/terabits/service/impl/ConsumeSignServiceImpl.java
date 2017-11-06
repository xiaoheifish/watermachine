package com.terabits.service.impl;

import com.terabits.mapper.ConsumeSignMapper;
import com.terabits.meta.po.ConsumeSignPO;
import com.terabits.service.ConsumeSignService;
import com.terabits.service.MedalExchangeService;
import com.terabits.utils.ScoreRuleUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public int resetConsumeSign() throws Exception{
        return consumeSignMapper.resetConsumeSign();
    }

    public ConsumeSignPO selectConsumeSign(String openId) throws Exception {
        return consumeSignMapper.selectConsumeSign(openId);
    }

    //根据openId和最后消费时间更新消费历史记录表
    public int updateSign(String openId, String gmtModified) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ConsumeSignPO consumeSignPO = new ConsumeSignPO();
        try {
            consumeSignPO = selectConsumeSign(openId);
        } catch (Exception e) {
            logger.error("consumeSignService.selectConsumeSign error in ConsumeSignService " + openId + gmtModified + e.toString());
            return 0;
        }
        //如果consumesign表中没有这条用户的签到记录，表明是第一次签到，所以新增一条记录
        if(consumeSignPO == null){
            consumeSignPO = new ConsumeSignPO();
            consumeSignPO.setOpenId(openId);
            consumeSignPO.setSignHistory(1);
            consumeSignPO.setExchangeHistory(1);
            consumeSignPO.setSignCount(1);
            try{
                insertConsumeSign(consumeSignPO);
            }catch (Exception e){
                logger.error("consumeSignService.insertConsumeSign error in ConsumeSignService" + consumeSignPO);
                return 0;
            }
            return 1;
        }
        if (consumeSignPO.getGmtModified().substring(0, 10).equals(gmtModified.substring(0, 10))) {
            //如果今天已经消费过了，那只需要更改一下最后消费时间, 后面代码里写了，所以这里为空
        } else {
            //如果是本月的消费，则要更新消费历史，以及兑换勋章后的历史，左移间隔天数，末位置为1
            if (consumeSignPO.getGmtModified().substring(0, 7).equals(gmtModified.substring(0, 7))) {
                int dateRecord = Integer.parseInt(consumeSignPO.getGmtModified().substring(8, 10));
                int dateToday = Integer.parseInt(gmtModified.substring(8, 10));
                long signHistory = consumeSignPO.getSignHistory();
                long exchangeHistory = consumeSignPO.getExchangeHistory();
                consumeSignPO.setSignHistory(ScoreRuleUtil.moveByte(signHistory, dateToday - dateRecord));
                consumeSignPO.setExchangeHistory(ScoreRuleUtil.moveByte(exchangeHistory, dateToday - dateRecord));
                //判断是否是连续消费，更新连续消费天数
                if ((dateToday - dateRecord) == 1) {
                    consumeSignPO.setSignCount(consumeSignPO.getSignCount() + 1);
                } else {
                    consumeSignPO.setSignCount(1);
                }

            } else {
                //如果隔了一个月才消费，那么消费历史和消费天数都置为1即可
                consumeSignPO.setSignHistory(1);
                consumeSignPO.setExchangeHistory(1);
                consumeSignPO.setSignCount(1);
            }
        }
        //更改最后消费时间
        consumeSignPO.setGmtModified(gmtModified);
        try {
            updateConsumeSign(consumeSignPO);
            return 1;
        } catch (Exception e) {
            logger.error("consumeSignService.updateConsumeSign gmtModified error " + consumeSignPO + e.toString());
            return 0;
        }
    }
    //根据openId获取某个用户本月剩余的勋章数量和连续签到天数
    public JSONObject getRemainMedal(String openId) {
        ConsumeSignPO consumeSignPO = new ConsumeSignPO();
        JSONObject jsonObject = new JSONObject();
        try {
            consumeSignPO = selectConsumeSign(openId);
        } catch (Exception e) {
            logger.error("getRemainMedal error in ConsumeSignServiceImpl" + openId);
            jsonObject.put("number", "500");
            jsonObject.put("day", "32");
            return jsonObject;
        }
        if(consumeSignPO == null){
            jsonObject.put("number", "0");
            jsonObject.put("day", "0");
            return jsonObject;
        }
        Map<ScoreRuleUtil.MedalType, Integer> signMedal = ScoreRuleUtil.historyToMedal(consumeSignPO.getExchangeHistory());

        int totalMedal = signMedal.get(ScoreRuleUtil.MedalType.GOLD) * 100 + signMedal.get(ScoreRuleUtil.MedalType.SILVER) * 10
                + signMedal.get(ScoreRuleUtil.MedalType.BRONZE);
        int signCount = consumeSignPO.getSignCount();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String currentTime = sdf.format(now);
        String gmtModified = consumeSignPO.getGmtModified();
        int currentMonth = Integer.parseInt(currentTime.substring(8, 10));
        int modifiedMonth = Integer.parseInt(gmtModified.substring(8, 10));
        if((currentMonth - modifiedMonth) >= 2){
            signCount = 0;
        }
        jsonObject.put("number", totalMedal);
        jsonObject.put("day", signCount);
        return jsonObject;
    }

    //提交兑换勋章请求时，修改exchangehistory
    public int pullExchangeRequest(String openId, List<ScoreRuleUtil.MedalType> medalType){
        ConsumeSignPO consumeSignPO = new ConsumeSignPO();
        try{
            consumeSignPO = selectConsumeSign(openId);
        }catch (Exception e){
            logger.error("consumeSignService.pullExchangeRequest error " + openId + e.toString());
            return 500;
        }
        long exchangeResult = ScoreRuleUtil.modifyExchangeHistory(consumeSignPO.getExchangeHistory(), medalType);
        consumeSignPO.setExchangeHistory(exchangeResult);
        try{
            updateConsumeSign(consumeSignPO);
        }catch (Exception e){
            logger.error("consumeSignService.pulllExchangeRequest update error" + consumeSignPO + e.toString());
            return 500;
        }
        return 200;
    }


}
