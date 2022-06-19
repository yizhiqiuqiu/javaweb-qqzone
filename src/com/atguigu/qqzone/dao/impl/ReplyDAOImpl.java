package com.atguigu.qqzone.dao.impl;

/*
 * @author 邱传泽
 * @version 1.0
 */

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {

    @Override
    public List<Reply> getReplyList(Topic topic) {//回复列表，而且无void，才需要返回
        return executeQuery("select *from t_reply where topic =?", topic.getId());
    }

    @Override
    public void addReply(Reply reply) {//插入且有void则无需要return
        executeUpdate("insert into t_reply values(0,?,?,?,?)", reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic().getId());

    }

    @Override
    public void delReply(Integer id) { //删除且有void则无需要return
        executeUpdate("delete from t_reply where id =?", id);

    }

    @Override
    public Reply getReply(Integer id) {
        return load("select * from t_reply where id=?", id);
    }
}
