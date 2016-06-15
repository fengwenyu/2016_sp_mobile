package com.shangpin.wechat.bo.reply;

/**
 * 发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
 * Created by cuibinqiang on 2015/12/11.
 */
public class ReplyNews extends CustomReply {

    private String news;

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

//    "news":{
//        "articles": [
//        {
//            "title":"Happy Day",
//                "description":"Is Really A Happy Day",
//                "url":"URL",
//                "picurl":"PIC_URL"
//        },
//        {
//            "title":"Happy Day",
//                "description":"Is Really A Happy Day",
//                "url":"URL",
//                "picurl":"PIC_URL"
//        }
//        ]
//    }
    
}
