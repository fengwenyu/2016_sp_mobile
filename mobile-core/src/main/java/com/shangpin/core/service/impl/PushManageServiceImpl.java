package com.shangpin.core.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.entity.PushManageAndroid;
import com.shangpin.core.service.IPushManageAndroidService;
import com.shangpin.core.service.IPushManageService;
import com.shangpin.utils.DataContainerPool;
import com.shangpin.utils.GlobalConstants;

@Service
@Transactional
public class PushManageServiceImpl implements IPushManageService {

    @Autowired
    private IPushManageAndroidService pushManageAndroidService;

    @Override
    public String modifyAndGetAndroidPushInfo(String userId, String gender, Long productNum) {
        // 组装广播信息
        StringBuffer buffer = new StringBuffer("[");
        Object obj = null;
        Object objauto = null;
        if ("0".equals(gender)) {
            if ("101".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_AOLAI_0);
                objauto = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_AOLAI_AUTO_0);
            } else if ("102".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_SHANGPIN_0);
                objauto = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_0);
            }
            mergeBroadcast(buffer, obj, objauto);
        } else if ("1".equals(gender)) {
            if ("101".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_AOLAI_1);
                objauto = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_AOLAI_AUTO_1);
            } else if ("102".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_SHANGPIN_1);
                objauto = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_1);
            }
            mergeBroadcast(buffer, obj, objauto);
        } else if ("2".equals(gender)) {
            if ("101".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_AOLAI_2);
                objauto = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_AOLAI_AUTO_2);
            } else if ("102".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_SHANGPIN_2);
                objauto = DataContainerPool.pushManageContainer.get(GlobalConstants.PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_2);
            }
            mergeBroadcast(buffer, obj, objauto);
        }
        if (StringUtils.isNotEmpty(userId)) {
            // 获取与个人相关的push信息内容
            List<PushManageAndroid> list = pushManageAndroidService.findByUserId(userId, productNum);
            if (null != list && list.size() > 0) {
                if (buffer.length() > 1) {
                    buffer.append(",");
                }
                for (int i = 0; i < list.size(); i++) {
                    PushManageAndroid pushManageAndroid = list.get(i);
                    buffer.append(null == pushManageAndroid.getPushContent() ? "{}" : pushManageAndroid.getPushContent());
                    if (list.size() - 1 > i) {
                        buffer.append(",");
                    }
                    // 更新push状态为已发送
                    pushManageAndroidService.modifyTypeById(pushManageAndroid.getId());
                }
            }
        }
        buffer.append("]");
        return buffer.toString();
    }

    @Override
    public void modifyAndSendIOSPushInfo(int gender, Long productNum) {

    }

    @Override
    public void modifyAndSendIOSPersonPushInfo(Long productNum) {

    }

    @Override
    public String findAndroidPushInfo(int gender, Long productNum) {
        List<PushManageAndroid> list = pushManageAndroidService.findByGender(gender, productNum);
        if (null != list && list.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                PushManageAndroid pushManageAndroid = list.get(i);
                buffer.append(pushManageAndroid.getPushContent());
                if (list.size() - 1 > i) {
                    buffer.append(",");
                }
            }
            return buffer.toString();
        } else {
            return null;
        }
    }

    /**
     * 将手动录入push信息、系统自动push信息合并成一条json字符串（用逗号分割）
     * 
     * @param buffer
     * @param obj
     * @param objauto
     */
    private void mergeBroadcast(StringBuffer buffer, Object obj, Object objauto) {
        if (null != obj && null != objauto) {
            buffer.append(obj.toString()).append(",").append(objauto.toString());
        } else if (null != obj && null == objauto) {
            buffer.append(obj.toString());
        } else if (null == obj && null != objauto) {
            buffer.append(objauto.toString());
        }
    }

}
