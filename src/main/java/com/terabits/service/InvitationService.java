package com.terabits.service;

import com.terabits.meta.po.InvitationPO;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
public interface InvitationService {

    //插入新的邀请信息
    public int insertInvitation(String inviterPhone, String inviteePhone) throws Exception;

    //根据手机号查询某用户的全部邀请
    public List<InvitationPO> selectInvitation(String inviterPhone)throws Exception;

}
