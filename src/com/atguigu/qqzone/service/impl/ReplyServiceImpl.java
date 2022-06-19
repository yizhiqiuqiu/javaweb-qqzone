package com.atguigu.qqzone.service.impl;

/*
 * @author 邱传泽
 * @version 1.0
 */

import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;


public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO;

    //直接调用HostReplyService来用，虽然破环了解耦程度,但是这样就可以直接调用写好的业务逻辑方法，而不用去深入考虑使用的细节
    private HostReplyService hostReplyService;
    private UserBasicService userBasicService;

    @Override
    public List<Reply> getReplyByTopicId(Integer topicId) {

        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);
            //1.将reply关联的author设置进去
            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(author);


            //2.将reply关联的hostReply设置进去
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);

        }
        return replyList;


    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);

    }

    @Override
    public void delReply(Integer id) {
        //获取回复
        Reply reply = replyDAO.getReply(id);
        //删除回复，如果有主人回复的话，先删除主人回复
        if (reply != null) {
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            if (hostReply != null) {
                hostReplyService.delHostReply(hostReply.getId());
            }
            //删除回复
            replyDAO.delReply(id);
        }
    }

    @Override
    public void delReplyList(Topic topic) {

        List<Reply> replyList = replyDAO.getReplyList(topic);
        if (replyList != null) {
            for (Reply reply : replyList) {
                //调用函数遍历，一直删
                delReply(reply.getId());
            }

        }

    }


}
