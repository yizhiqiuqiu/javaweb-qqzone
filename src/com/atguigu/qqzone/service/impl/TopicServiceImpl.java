package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null ;
    private ReplyService replyService;
    private UserBasicService userBasicService;


    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public  Topic getTopic(Integer id){
      //由于前面debug后显示author为空，所以来获取author的值

        Topic topic = topicDAO.getTopic(id);
        UserBasic author = topic.getAuthor();
        author = userBasicService.getUserBasicById(author.getId());
        topic.setAuthor(author);
        return topic;
    }

    @Override
    public void delTopic(Integer id) {

        Topic topic = topicDAO.getTopic(id);
        if (topic!=null){
            replyService.delReplyList(topic);
            topicDAO.delTopic(topic);
        }
    }


    @Override
    public Topic getTopicById(Integer id) {
        //我们要把评论和主任评论集成到TOpic里
        Topic topic = getTopic(id);//调用我们的getTopic函数赋给topic，这样topic里就有author的值了
        List<Reply> replyList= replyService.getReplyByTopicId(topic.getId());

        //reply是和hostreply关联的，topic把reply链接起来
        topic.setReplyList(replyList);

        return topic;
    }

}
