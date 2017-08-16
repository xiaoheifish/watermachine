package com.terabits.controller.xhr;

import com.terabits.meta.model.TerminalModel;
import com.terabits.config.Constants;
import com.terabits.config.WeixinGlobal;
import com.terabits.manager.TerminalManager;
import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.OperationPO;
import com.terabits.meta.po.OrderPO;
import com.terabits.service.OperationService;
import com.terabits.service.OrderService;
import com.terabits.service.TerminalService;
import com.terabits.utils.PayCommonUtil;
import com.terabits.utils.XMLUtil;
import com.terabits.utils.huawei.PlatformGlobal;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13.
 */
@Controller
@Transactional
public class TestNotify{
/*
    @Autowired
    private OrderService orderService;
    @Autowired
    private TerminalManager terminalManager;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private OperationService operationService;
    @RequestMapping(value="testnotify", method= RequestMethod.POST)
    public void callback(HttpSession session, HttpServletRequest request) throws Exception {

        System.out.println("test来确认了！！！！");
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String time = dfs.format(now);
        long displayId = Long.parseLong(request.getParameter("terminalId"));
        TerminalModel terminalModel = new TerminalModel();
        terminalModel.setTerminalId(request.getParameter("terminalId"));
        terminalModel.setTime(time);
        terminalModel.setHour(1);
        terminalManager.createNewTerminal(terminalModel);
        //更新终端状态
        TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
        terminalUpdateBO.setDisplayId("000002");
        terminalUpdateBO.setState(Constants.ON_STATE);
        terminalService.updateTerminal(terminalUpdateBO);
        CommunicationBO communicationBO = terminalService.getTerminalDeviceId("000002");
        //在数据库中添加此次操作记录
        OperationPO operationPO = new OperationPO();
        operationPO.setStatus(Constants.OFF_TO_ON);
        operationPO.setImei(communicationBO.getImei());
        operationService.insertOperation(operationPO);
        //下发开启插座命令给终端
        byte[] openbytes = new byte[3];
        openbytes[0] = Constants.SEND_COMMAND_START;
        openbytes[1] = Constants.POWER_ON_COMMAND;
        openbytes[2] = Constants.SEND_COMMAND_END;
        PlatformGlobal.command(openbytes, communicationBO.getDeviceId());
                        //返回ok结果给微信
    }

    private String parseWeixinCallback(HttpServletRequest request) throws IOException {
        // 获取微信调用我们的notify_url的返回信息
        String result = "";
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            result = new String(outSteam.toByteArray(), Charsets.UTF_8.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(outSteam != null){
                    outSteam.close();
                    outSteam = null; // help GC
                }
                if(inStream != null){
                    inStream.close();
                    inStream = null;// help GC
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }*/
}

