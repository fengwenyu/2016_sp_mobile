package com.shangpin.manager.controller;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.core.entity.main.Channel;
import com.shangpin.core.entity.main.OnlineManage;
import com.shangpin.core.entity.main.Onlines;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.ChannelService;
import com.shangpin.core.service.OnlineManageService;
import com.shangpin.core.service.ProductService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.persistence.DynamicSpecifications;
import com.shangpin.manager.log.impl.LogUitl;

@Controller
@RequestMapping("/management/manager/online")
public class OnlineManageController {
    
    @Autowired
    private OnlineManageService onlineManageService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ChannelService channelService;
    
    
    private static final String LIST = "management/admin/onlineManage/list";
    private static final String CREATE = "management/admin/onlineManage/create";
    private static final String FIND = "management/admin/onlineManage/find_channel_name";
    
    @RequiresPermissions("OnlineManage:view")
    @RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
    public String list(Page page,Map<String, Object> map) {
        List<OnlineManage> onlinManages=onlineManageService.findAll(page);
        List<Onlines> list=new ArrayList<Onlines>();
        for (OnlineManage onlineManage : onlinManages) {
           Onlines onlines=new Onlines();
           String productName=productService.findProductNameByNum(onlineManage.getProductNum());
           String channelName=channelService.findChannelNameByNum(onlineManage.getChannelNum());
           onlines.setOnline(onlineManage);
           if(productName!=null)
           onlines.setProductName(productName);
           if(channelName!=null)
           onlines.setChannelName(channelName);
           list.add(onlines);
        }
        
        map.put("page", page);
        map.put("onlinManages", list);
        return LIST;
    }
     
    @RequiresPermissions("OnlineManage:save")
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String preCreate(Map<String, Object> map) {
        map.put("products", productService.findAll());
        return CREATE;
    }
    
    /**
     * get download file name base on product id which defined by moss.shangpin.com
     * */
    private String getDownloadFileName(int productId,int channelId){
        String fileName = null;
        final int AOLAI_ANDROID = 101;
        final int SHANGPIN_ANDROID = 102;
        
        switch ((int)productId) {
        case AOLAI_ANDROID:
            fileName = "aolai_"+channelId+".apk";
            break;
        case SHANGPIN_ANDROID:
            fileName = "shangpin_"+channelId+".apk";
            break;
        default:
            break;
        }
        return fileName;
    }
    
    
    @Log(message="上传了id={0}产品。")
    @RequiresPermissions("OnlineManage:save")
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public @ResponseBody String create(@Valid OnlineManage online, ServletRequest request,HttpServletRequest requests) {
        try {
        String basePath = "/home/OnlineManage/product/" + online.getProductNum() + "/" + online.getChannelNum() + "/";
        File basePathFile = new File(basePath);
        if (!basePathFile.exists())
            basePathFile.mkdirs();
        
        OnlineManage onlines = onlineManageService.findOnline(online.getProductNum(), online.getChannelNum(), 1);
        long chan=online.getChannelNum();
        int prod=online.getProductNum();
        String fileName=getDownloadFileName(prod,(int)chan);
        String loalPath = "";
        String localIP = getLocalIP();
        if ("172.20.10.253".equals(localIP))
            loalPath = "http://moss.shangpin.com";
        else
            loalPath = requests.getScheme() + "://" + localIP + ":" + requests.getServerPort() + requests.getContextPath();
        String path = loalPath + "/" + "download.action" + "?p=" + online.getProductNum() + "&ch=" + online.getChannelNum() + "&fileName="+fileName;// + uploadFileName;
        if (onlines != null) {
            onlines.setInuse(0);
            onlineManageService.update(onlines);
        }
        if (1 == online.getProductNum() && 2 == online.getChannelNum()) { // 奥莱iPhone客户端
            online.setDownloadPath("http://itunes.apple.com/cn/app/id432489082?mt=8&ign-mpt=uo%3D4");
        } else if (2 == online.getProductNum() && 2 == online.getChannelNum()) {
            online.setDownloadPath("https://itunes.apple.com/us/app/shang-pin-gao-duan-shi-shang/id598127498?ls=1&mt=8");// 尚品iPhone客户端
        } else {
            online.setDownloadPath(path);
        }
        online.setInuse(1);
        online.setFileName(fileName);
        online.setCreateTime(new Date());
        
            onlineManageService.save(online);
        } catch (ExistedException e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{online.getId()}));
        return AjaxObject.newOk("产品上传成功！").toString();
    }
    
    /**
     * 查询列表页：组合条件
     * 
     * @param page
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/find", method = { RequestMethod.GET, RequestMethod.POST })
    public String list(Page page, ServletRequest request, Map<String, Object> map) {
        Specification<Channel> spec = DynamicSpecifications.bySearchFilter(request, Channel.class);
        List<Channel> channel = channelService.findByExample(spec, page);
        map.put("page", page);
        map.put("channel", channel);
        return FIND;
    }
    
    @RequiresPermissions("OnlineManage:delete")
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public @ResponseBody String deleteMany(Long[] ids) {
        AjaxObject ajaxObject = new AjaxObject("删除产品成功！");
        for (Long id : ids) {
            onlineManageService.delete(id);
        }
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{ids.length}));
        ajaxObject.setCallbackType("");
        return ajaxObject.toString();
    }
    
    //获取本机的ip地址
    public String getRemortIP(HttpServletRequest request) {
        String header = request.getHeader("x-forwarded-for");
        if (header == null) {
            String ip = request.getRemoteAddr();
            //System.out.println(ip);
        return ip;
        }
        return header;
        } 
    
    /**
     * 判断当前操作是否Windows
     * 
     * @Author: zhouyu
     * @CreatDate: 2012-08-01
     * @param
     * @Return true 是Windows操作系统
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本机IP地址，并自动区分Windows还是Linux操作系统
     * 
     * @Author: zhouyu
     * @CreatDate: 2012-08-01
     * @param
     * @Return String
     */
    public String getLocalIP() {
        String sIP = "";
        InetAddress ip = null;
        try {
            // 如果是Windows操作系统
            if (isWindowsOS()) {
                ip = InetAddress.getLocalHost();
            }
            // 如果是Linux操作系统
            else {
                boolean bFindIP = false;
                Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (bFindIP) {
                        break;
                    }
                    NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                    // ----------特定情况，可以考虑用ni.getName判断
                    // 遍历所有ip
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = (InetAddress) ips.nextElement();
                        if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 127.开头的都是lookback地址
                            bFindIP = true;
                            break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != ip) {
            sIP = ip.getHostAddress();
        }
        return sIP;
    }


    
}
