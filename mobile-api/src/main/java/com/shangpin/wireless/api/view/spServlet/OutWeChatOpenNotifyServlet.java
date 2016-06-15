package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.util.SortedMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.SPBizOrderService;
import com.shangpin.utils.RequestUtils;
import com.shangpin.wechat.utils.openplatform.WeChatOpenUtil;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

public class OutWeChatOpenNotifyServlet extends BaseServlet {

	private static final long serialVersionUID = -1258996057839844086L;

	protected final Log log = LogFactory.getLog(OutWeChatOpenNotifyServlet.class);

	SPBizOrderService bizOrderService;

	@Override
	public void init() throws ServletException {
		bizOrderService = (SPBizOrderService) getBean(SPBizOrderService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号

		SortedMap<String, String> parameters = RequestUtils.getParamsMap(request);
		if (false == WeChatOpenUtil.isTenpaySign(parameters)) {
			log.debug("WeChatOpenNotify : Notify the signature verification failed !");
			sendMessage(response, "fail");
			return;
		}
		// 商户订单号
		String out_trade_no = request.getParameter("out_trade_no");
		// 财付通订单号
		String transaction_id = request.getParameter("transaction_id");
		// 金额,以分为单位
		String total_fee = request.getParameter("total_fee");
		// 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
		String discount = request.getParameter("discount");
		// 支付结果
		String trade_state = request.getParameter("trade_state");

		// 判断签名及结果
		if (!"0".equals(trade_state)) {
			FileUtil.addLog(request, "OutWeChatOpenNotifyServlet", "channelNo", channelNo, "out_trade_no", out_trade_no, "transaction_id", transaction_id, "total_fee", total_fee,
					"discount", discount, "trade_state", trade_state, "code", "fail", "msg", "trade_state is not zero !");
			sendMessage(response, "fail");
			return;
		}

		// 处理数据库逻辑
		// 注意交易单不要重复处理
		// 注意判断返回金额
		try {
			// 测试环境支付时修改配置里测试用户订单金额
			/*if (Constants.WEIXIN_PAYMENT) {
				OrderItem testOrder = bizOrderService.beOrderDetail(Constants.PAY_TEST_USER, out_trade_no).getResult();
				total_fee = testOrder.getPayamount();
			}*/
			
			boolean f = bizOrderService.updateOrderStatus("32", "110", out_trade_no, Constants.PAY_AMOUNT);

			if (!f) {
				FileUtil.addLog(request, "OutWeChatOpenNotifyServlet", "channelNo", channelNo, "out_trade_no", out_trade_no, "transaction_id", transaction_id, "total_fee", total_fee,
						"discount", discount, "trade_state", trade_state, "code", "fail", "msg", "WeChatOpenPay Synchronization order error !");
				sendMessage(response, "fail");
				return;
			}
			sendMessage(response, "success");
		} catch (Exception e) {
			FileUtil.addLog(request, "OutWeChatOpenNotifyServlet", "channelNo", channelNo, "out_trade_no", out_trade_no, "transaction_id", transaction_id, "total_fee", total_fee,
					"discount", discount, "trade_state", trade_state, "code", "fail", "msg", "WeChatOpenPay Synchronization order error !");
			log.error("ERROR:", e);
			sendMessage(response, "fail");
		}

	}

}

