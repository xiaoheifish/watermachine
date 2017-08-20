package com.terabits.controller;

import com.terabits.meta.po.TerminalPO;
import com.terabits.service.TerminalService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/7/14.
 */
@Controller
public class ExistenceController {
    @Autowired
    private TerminalService terminalService;
    //输入编号判断该编号是否存在,用于首页用户输好编号后，跳转前判断，防止用户乱输编号
    @RequestMapping(value="/existence/{displayId}",method= {RequestMethod.GET, RequestMethod.POST})
    public void existence(@PathVariable("displayId") String displayId, HttpServletResponse response) throws Exception{
        TerminalPO terminalPO = terminalService.selectOneTerminal(displayId);
        JSONObject jsonObject = new JSONObject();
        if(terminalPO == null){
            jsonObject.put("existence","no");
        }else {
            jsonObject.put("existence","yes");
        }
        response.getWriter().print(jsonObject);
    }
    //扫码跳转，根据二维码包含的webId信息查询displayId，前端据此跳转到正确的信息页
    @RequestMapping(value="/weburl/{webId}", method = RequestMethod.GET)
    public void webIdToDisplayId(@PathVariable("webId")String webId, HttpServletResponse response) throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (webId.equals("undefined")){
            jsonObject.put("displayId", "null");
        }
        else {
            String displayId = terminalService.getTerminalDisplayId(webId);
            if(displayId == null){
                displayId = "null";
            }
            jsonObject.put("displayId", displayId);
            response.getWriter().print(jsonObject);
        }
    }
}
