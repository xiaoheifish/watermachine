package com.terabits.controller;


import com.terabits.config.Constants;
import com.terabits.manager.TerminalManager;
import com.terabits.meta.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/6/21.
 */
@Controller
public class TokenController {
  /*  @Autowired
    private TokenManager tokenManager;
    @Autowired
    private TerminalManager terminalManager;
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public String token(HttpServletRequest request) {
        long id = 215481;
        String status = request.getParameter("status");
        if(status.equals("create")){
            //TokenModel tokenModel =  tokenManager.createToken(id);
            //terminalManager.createNewTerminal(id,"2017-06-29 00:00:00", Constants.TWO_EXPIRES_HOUR);
            //System.out.println("create:"+tokenModel);
        }
        if(status.equals("query")){
            String token = terminalManager.getTerminalTime(000003);
            System.out.println(token == null);
            System.out.println("query:"+token);
        }
        if(status.equals("delete")){
            terminalManager.deleteTerminal(id);
        }
        return "pages/user.jsp";
    }
*/
}