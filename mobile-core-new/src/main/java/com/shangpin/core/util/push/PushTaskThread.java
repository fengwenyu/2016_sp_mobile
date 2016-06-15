package com.shangpin.core.util.push;


import com.shangpin.core.service.PushService;
import com.shangpin.core.util.spring.SpringUtil;

public class PushTaskThread extends Thread{
    
    private String sendType;
    private PushService pushService = (PushService) SpringUtil.getBean("pushService");
    
    /**
     * @param sendType
     */
    public PushTaskThread(String sendType) {
        super();
        this.sendType = sendType;
    }

    @Override
    public void run() {
        try {
            if(sendType != null){
                if(sendType.equals("1")){
                    // 发送尚品用户相关的push信息
                    pushService.updateAndSendIOSPersonPushInfo(2);
                }else if(sendType.equals("2")){
                    // 发送尚品全部接受的push广播信息
                    pushService.updateAndSendIOSPushInfo(2, 2);
                }else if(sendType.equals("3")){
                    // 发送奥莱用户相关的push信息
                    pushService.updateAndSendIOSPersonPushInfo(1);
                }else if(sendType.equals("4")){
                    // 发送奥莱全部接受的push广播信息
                    pushService.updateAndSendIOSPushInfo(1, 2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
