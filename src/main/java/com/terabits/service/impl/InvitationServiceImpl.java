package com.terabits.service.impl;

import com.terabits.mapper.InvitationMapper;
import com.terabits.meta.po.InvitationPO;
import com.terabits.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service("invitationService")
public class InvitationServiceImpl implements InvitationService {

    @Autowired(required = false)
    private InvitationMapper invitationMapper;

    //插入新的邀请信息
    public int insertInvitation(String inviterPhone, String inviteePhone) throws Exception{
        InvitationPO invitationPO = new InvitationPO();
        invitationPO.setInviterPhone(inviterPhone);
        invitationPO.setInviteePhone(inviteePhone);
        return invitationMapper.insertInvitation(invitationPO);
    }

    //根据手机号查询某用户的全部邀请
    public List<InvitationPO> selectInvitation(String inviterPhone)throws Exception{
        return invitationMapper.selectInvitation(inviterPhone);
    }

}
