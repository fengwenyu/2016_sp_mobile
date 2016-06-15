package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.api.domain.IOSDownloadActive;

/**
 * ios客户端下载激活服务类
 * @author xupengcheng
 *
 */
public interface IOSDownloadService {

	/**
	 * 增加或者修改下载信息
	 * @throws Exception
	 */
	public boolean saveOrUpdate(IOSDownloadActive iosDownloadActive) throws Exception;
	
	/**
	 * 获取下载记录信息
	 * @param iosDownloadActive
	 * @return
	 * @throws Exception
	 */
	public IOSDownloadActive getIOSDownloadActive(IOSDownloadActive iosDownloadActive) throws Exception;
	/**
     * 获取多个下载记录信息
     * @param iosDownloadActive
     * @return
     * @throws Exception
     */
    public List<IOSDownloadActive> getIOSDownloadActiveList(IOSDownloadActive iosDownloadActive) throws Exception;
}
