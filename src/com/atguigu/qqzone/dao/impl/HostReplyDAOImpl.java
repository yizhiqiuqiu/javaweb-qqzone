package com.atguigu.qqzone.dao.impl;

/*
 * @author 邱传泽
 * @version 1.0
 */

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.HostReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;

public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {



    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return load("select *from t_host_reply where reply =?",replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        executeUpdate("delete from t_host_reply where id =?",id);

    }
}
