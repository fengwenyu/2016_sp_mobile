package com.shangpin.wireless.api.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shangpin.wireless.api.dao.IOSDownloadActiveDao;
import com.shangpin.wireless.api.domain.IOSDownloadActive;
import com.shangpin.wireless.api.service.IOSDownloadService;
import com.shangpin.wireless.api.util.HqlHelper;
import com.shangpin.wireless.api.util.StringUtil;

@Service("iosDownloadService")
public class IOSDownloadServiceImpl implements IOSDownloadService {

	@Resource(name = "iosDownloadActiveDao")
	private IOSDownloadActiveDao downloadActiveDao;

	public IOSDownloadActive getIOSDownloadActive(IOSDownloadActive iosDownloadActive) throws Exception{
		String ifa = iosDownloadActive.getIfa();
		Integer id = iosDownloadActive.getId();
		String mac = iosDownloadActive.getMac();
		String appType = iosDownloadActive.getAppType();
		String channel = iosDownloadActive.getChannel();
		String version = iosDownloadActive.getVersion();
		String imei = iosDownloadActive.getImei();
		boolean flag = false;
		//StringBuffer sb = new StringBuffer("select ifa,callbackUrl,mac,appType,downloadTime,activeTime,isActive,channel,imei,version,channelName");
		StringBuffer sb = new StringBuffer();		
		sb.append(" from IOSDownloadActive where version = " + version);
		
		if(!StringUtils.isEmpty(imei)){
			sb.append(" and imei='" + imei + "'");
		}
		
		if(!StringUtils.isEmpty(channel)){
			sb.append(" and channel='" + channel + "'");
		}
		
		if(!StringUtils.isEmpty(ifa)){
			sb.append(" and ifa='" + ifa + "'");
		}
		
		if(!StringUtils.isEmpty(id) && id.intValue() != 0){
			sb.append(" and id=" + id.intValue() + "");
		}
		
		if(!StringUtils.isEmpty(mac)){
			sb.append(" and mac='" + mac + "'");
		}
		
		if(!StringUtils.isEmpty(appType)){
			sb.append(" and appType='" + appType + "'");
		}
		
		/*if (StringUtil.isNotEmpty(ifa)) {
			if (flag) {
				sb.append(" and ifa='" + ifa + "'");
			} else {
				sb.append(" ifa='" + ifa + "'");
			}
			flag = true;
		}

		if (StringUtil.isNotEmpty(imei)) {
			if (flag) {
				sb.append(" and imei='" + imei + "'");
			} else {
				sb.append(" imei='" + imei + "'");
			}
			flag = true;
		}
		if (StringUtil.isNotEmpty(version)) {
			if (flag) {
				sb.append(" and version='" + version + "'");
			} else {
				sb.append(" version='" + version + "'");
			}
			flag = true;
		}
		
		if (StringUtil.isNotEmpty(channel)) {
			if (flag) {
				sb.append(" and channel='" + channel + "'");
			} else {
				sb.append(" channel='" + channel + "'");
			}
			flag = true;
		}
		
		if (id != null && id.intValue() != 0) {
			if (flag) {
				sb.append(" and id=" + id.intValue() + "");
			} else {
				sb.append("  id=" + id.intValue() + "");
			}
			flag = true;
		}

		if (StringUtil.isNotEmpty(mac)) {
			if (flag) {
				sb.append(" and mac='" + mac + "'");
			} else {
				sb.append("  mac='" + mac + "'");
			}
			flag = true;
		}

		if (StringUtil.isNotEmpty(appType)) {
			if (flag) {
				sb.append(" and appType='" + appType + "'");
			} else {
				sb.append(" appType='" + appType + "'");
			}
			flag = true;
		}*/
		/*if (flag) {*/
			try {
				List<IOSDownloadActive> list = downloadActiveDao.executeHqlPage(1, 1, sb.toString(), "");
				if (list != null && list.size() > 0) {
					return list.get(0);
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		/*} else {
			return null;
		}*/
		return null;
	}
	
	@Override
	public boolean saveOrUpdate(IOSDownloadActive iosDownloadActive) throws Exception {
		try {
			IOSDownloadActive temp = getIOSDownloadActive(iosDownloadActive);
			if(temp != null && temp.getId() != null && temp.getId().intValue() != 0){
				String isActive = iosDownloadActive.getIsActive();
				if(StringUtil.isNotEmpty(isActive)){
					temp.setIsActive(isActive);
				}
				Date activeTime = iosDownloadActive.getActiveTime();
				if(activeTime != null){
					temp.setActiveTime(activeTime);
				}
				String imei = iosDownloadActive.getImei();
				if(imei != null){
					temp.setImei(imei);
				}
				downloadActiveDao.update(temp, "");
			}else{
				//下载记录
				iosDownloadActive.setDownloadTime(new Date());
				downloadActiveDao.save(iosDownloadActive);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<IOSDownloadActive> getIOSDownloadActiveList(IOSDownloadActive iosDownloadActive) throws Exception {
        String ifas = iosDownloadActive.getIfas();
        Integer id = iosDownloadActive.getId();
        String mac = iosDownloadActive.getMac();
        String appType = iosDownloadActive.getAppType();
        String channel = iosDownloadActive.getChannel();
        String version = iosDownloadActive.getVersion();
        String imei = iosDownloadActive.getImei();
        boolean flag = false;
        //StringBuffer sb = new StringBuffer("select ifa,callbackUrl,mac,appType,downloadTime,activeTime,isActive,channel,imei,version,channelName");
        StringBuffer sb = new StringBuffer();       
        sb.append(" from IOSDownloadActive where ");
        if (StringUtil.isNotEmpty(ifas)) {
            StringBuffer stringBuffer=new StringBuffer();
            String [] ifaArray=ifas.split(",");
            for (int i = 0; i < ifaArray.length; i++) {
                stringBuffer.append("'"+ifaArray[i]+"'");
                if (i<ifaArray.length-1) {
                    stringBuffer.append(",");
                }
            }
            if (flag) {
                sb.append(" and ifa in (" + stringBuffer + ")");
            } else {
                sb.append(" ifa in (" + stringBuffer + ")");
            }
            flag = true;
        }

        if (StringUtil.isNotEmpty(imei)) {
            if (flag) {
                sb.append(" and imei='" + imei + "'");
            } else {
                sb.append(" imei='" + imei + "'");
            }
            flag = true;
        }
        if (StringUtil.isNotEmpty(version)) {
            if (flag) {
                sb.append(" and version='" + version + "'");
            } else {
                sb.append(" version='" + version + "'");
            }
            flag = true;
        }
        
        if (StringUtil.isNotEmpty(channel)) {
            if (flag) {
                sb.append(" and channel='" + channel + "'");
            } else {
                sb.append(" channel='" + channel + "'");
            }
            flag = true;
        }
        
        if (id != null && id.intValue() != 0) {
            if (flag) {
                sb.append(" and id=" + id.intValue() + "");
            } else {
                sb.append("  id=" + id.intValue() + "");
            }
            flag = true;
        }

        if (StringUtil.isNotEmpty(mac)) {
            if (flag) {
                sb.append(" and mac='" + mac + "'");
            } else {
                sb.append("  mac='" + mac + "'");
            }
            flag = true;
        }

        if (StringUtil.isNotEmpty(appType)) {
            if (flag) {
                sb.append(" and appType='" + appType + "'");
            } else {
                sb.append(" appType='" + appType + "'");
            }
            flag = true;
        }
        if (flag) {
            try {
                List<IOSDownloadActive> list = downloadActiveDao.executeHql(sb.toString(), "");
                if (list != null && list.size() > 0) {
                    return list;
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }
}
