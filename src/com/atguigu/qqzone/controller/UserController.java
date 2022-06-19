package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {

    private UserBasicService userBasicService ;
    private TopicService topicService ;

    public String login(String loginId , String pwd , HttpSession session){
        //1.登录验证
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if(userBasic!=null){
            //1-1 获取相关的好友信息
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            //1-2 获取相关的日志列表信息(但是，日志只有id，没有其他信息）
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);


            //userBasic这个key表示当前自己空间，friend这个key表示别人的空间
            session.setAttribute("userBasic",userBasic);
            session.setAttribute("friend",userBasic);
            return "index";
        }else{
            return "login";
        }
    }


    public  String friend(Integer id,HttpSession session){
        UserBasic currfriend = userBasicService.getUserBasicById(id);
        List<Topic> topicList = topicService.getTopicList(currfriend);
        currfriend.setTopicList(topicList);

        session.setAttribute("friend",currfriend);
        return "index";

    }
}
