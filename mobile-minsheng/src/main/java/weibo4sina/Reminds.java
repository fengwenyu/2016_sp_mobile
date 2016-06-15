package weibo4sina;

import weibo4sina.model.PostParameter;
import weibo4sina.model.WeiboException;
import weibo4sina.org.json.JSONObject;
import weibo4sina.util.WeiboConfig;

public class Reminds extends Weibo{

	private static final long serialVersionUID = 1L;

	/**
	 * 获取某个用户的各种消息未读数 
	 * 
	 */
	public JSONObject getUnreadCountOfRemind () throws WeiboException {
		return client.get(WeiboConfig.getValue("rmURL") + "remind/unread_count.json").asJSONObject();
	}
	
	public JSONObject getUnreadCountOfRemind (String callback) throws WeiboException {
		return client.get(WeiboConfig.getValue("rmURL") + "remind/unread_count.json",new PostParameter[] {
			new PostParameter("callback", callback)
		}).asJSONObject();
	}
	
}
