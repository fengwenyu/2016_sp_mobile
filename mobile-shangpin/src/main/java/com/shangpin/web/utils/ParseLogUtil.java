package com.shangpin.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParseLogUtil {

    private static final String SPACE = " ";
    private static final String SEMICOLON = ";";
    private static final String BRACKET_LEFT = "(";
    private static final String BRACKET_RIGHT = ")";
    private static final String LINUX = "Linux";
    private static final String ANDROID = "Android";

    private static final List<String> logStrList = new ArrayList<String>();
    private static final List<String> logStrOutList = new ArrayList<String>();
    private static final List<LogObject> logObjList = new ArrayList<LogObject>();
    private static final Log log = LogFactory.getLog(ParseLogUtil.class);

    /**
     * 递归读取文件
     * 
     * @Author zhouyu
     * @CreateDate 2012-10-24
     * @param file
     * @Return
     */
    public static void readFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                readFileByLines(file);
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    readFile(files[i]);
                }
            }
        } else {
            log.warn("To read the file does not exist" + '\n');
        }
    }

    /**
     * 以行为单位读取文件
     * 
     * @Author zhouyu
     * @CreateDate 2012-10-24
     * @param fileName
     * @Return
     */
    public static void readFileByLines(File file) {
        // File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                logStrList.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 将log封装成实体
     * 
     * @Author zhouyu
     * @CreateDate 2012-10-24
     * @param list
     * @Return
     */
    public static void logToBean(List<String> list, String type) {
        if ("wap".equals(type)) {
            for (int i = 0; i < list.size(); i++) {
                logObjList.add(new LogObject(list.get(i), type));
            }
        }
    }

    /**
     * 解析UA
     * 
     * @Author zhouyu
     * @CreateDate 2012-12-11
     * @param list
     * @Return
     */
    public static Map<String, String> parseUA(String ua) {
        Map<String, String> map = new HashMap<String, String>();
        final int androidIndex = ua.indexOf(ANDROID);
        final int linuxIndex = ua.indexOf(LINUX);

        if (ua.indexOf(ANDROID) > -1) {
            map.put("platform", ANDROID);

            /*** 记录系统版本号和手机型号 start ***/
            findAndroidPhoneModel(ua, map, androidIndex, linuxIndex);
            /*** 记录系统版本号和手机型号 end ***/
        } else if (ua.indexOf("iPhone OS") > -1) {
            map.put("platform", "ios");// 平台
            findIOSPhoneModel(ua, map, "iPhone OS", ua.indexOf("iPhone OS"));
        } else if (ua.indexOf("iPad") > -1 && ua.indexOf("OS") > -1) {
            map.put("platform", "ios");// 平台
            findIOSPhoneModel(ua, map, "OS", ua.indexOf("OS"));
        } else if (ua.indexOf("Intel Mac OS") > -1) {
            map.put("platform", "mac");// 平台
            findIOSPhoneModel(ua, map, "Mac OS X", ua.indexOf("Mac OS X"));
        } else if (ua.indexOf("MQQBrowser") > -1) {
            if (ua.indexOf("Adr") > -1) {
                map.put("platform", ANDROID);
                findAndroidPhoneModel(ua, map, androidIndex, linuxIndex);
            } else if (ua.indexOf("iOS") > -1) {
                map.put("platform", "ios");
                findIOSPhoneModel(ua, map, "iOS", ua.indexOf("iOS"));
            }
        } else if (ua.indexOf("UC") > -1) {
            if (ua.indexOf("JUC") > -1) {
                map.put("platform", ANDROID);
                findAndroidPhoneModel(ua, map, androidIndex, linuxIndex);
            } else if (ua.indexOf("IUC") > -1) {
                map.put("platform", "ios");
                findIOSPhoneModel(ua, map, "iOS", ua.indexOf("iOS"));
            }
        } else if (ua.indexOf("Chrome") > -1) {
            final int left = ua.indexOf(BRACKET_LEFT);
            final int right = ua.indexOf(BRACKET_RIGHT);
            if (left > -1) {
                String temp = ua.substring(left + 1, right);
                String[] splits = temp.split(SPACE);
                if (splits.length > 1) {
                    map.put("platform", splits[0]);
                }
            }
        } else {
            logStrOutList.add(ua);
        }
        findBrowserInfo(ua, map, ANDROID.equals(map.get("platform")));

        final int starIndex = ua.indexOf("*");
        int resStrat = 0, resEnd = 0;
        if (starIndex > -1) {
            for (int i = starIndex - 1; i >= 0; i--) {
                char c = ua.charAt(i);
                if (c < '0' || c > '9') {
                    resStrat = i + 1;
                    break;
                }
            }
            for (int i = starIndex + 1; i < ua.length(); i++) {
                char c = ua.charAt(i);
                if (c < '0' || c > '9') {
                    resEnd = i;
                    break;
                }
            }
            map.put("resolution", ua.substring(resStrat, resEnd));
        }
        return map;
    }

    private static void findAndroidPhoneModel(final String ua, final Map<String, String> out, final int androidIndex, final int linuxIndex) {
        int osvStart = 0, osvEnd = 0;
        if (androidIndex > -1) {
            if (ua.charAt(androidIndex + ANDROID.length()) == '/') {
                osvStart = androidIndex + ANDROID.length() + 1;
                osvEnd = ua.indexOf(SPACE, osvStart);
                if (osvEnd == -1) {
                    osvEnd = ua.length();
                }
            } else {
                osvStart = androidIndex + ANDROID.length();
                osvEnd = ua.indexOf(SEMICOLON, osvStart);
                if (osvEnd == -1) {
                    osvEnd = ua.indexOf(BRACKET_RIGHT, osvStart);
                    if (osvEnd == -1) {
                        osvEnd = ua.length();
                    }
                }
            }
        } else if (linuxIndex > -1) {
            for (int i = linuxIndex + LINUX.length(); i < ua.length(); i++) {
                char c = ua.charAt(i);
                if (c >= '0' && c <= '9') {
                    osvStart = i;
                    break;
                }
            }
            osvEnd = ua.indexOf(SEMICOLON, osvStart);
            if (osvEnd == -1) {
                osvEnd = ua.length();
            }
        }
        final int buildIndex = ua.indexOf("Build");
        if (buildIndex > -1) {
            String temp = ua.substring(0, buildIndex);
            int modelStart = temp.lastIndexOf(SEMICOLON);
            out.put("phoneModel", temp.substring(modelStart + 1).trim());// 手机型号
            out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
        } else {
            final int motoIndex = ua.toUpperCase().indexOf("MOT");
            final int samsungIndex = ua.toUpperCase().indexOf("SAMSUNG");
            final int coolpadIndex = ua.toUpperCase().indexOf("COOLPAD");
            final int zteIndex = ua.toUpperCase().indexOf("ZTE");
            final int huaweiIndex = ua.toUpperCase().indexOf("HUAWEI");
            final int lenovoIndex = ua.toUpperCase().indexOf("LENOVO");
            if (motoIndex > -1) {
                String subua = ua;
                if (motoIndex < linuxIndex) {
                    subua = ua.substring(0, linuxIndex);
                }
                int modelEnd = findEndIndex(subua, motoIndex);
                out.put("phoneModel", ua.substring(motoIndex, modelEnd).trim());// 手机型号
                out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
            } else if (samsungIndex > -1) {
                String subua = ua;
                if (samsungIndex < linuxIndex) {
                    subua = ua.substring(0, linuxIndex);
                }
                int modelEnd = findEndIndex(subua, samsungIndex);
                out.put("phoneModel", ua.substring(samsungIndex, modelEnd).trim());// 手机型号
                out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
            } else if (coolpadIndex > -1) {
                String subua = ua;
                if (coolpadIndex < linuxIndex) {
                    subua = ua.substring(0, linuxIndex);
                }
                int modelEnd = findEndIndex(subua, coolpadIndex);
                out.put("phoneModel", ua.substring(coolpadIndex, modelEnd).trim());// 手机型号
                out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
            } else if (zteIndex > -1) {
                String subua = ua;
                if (zteIndex < linuxIndex) {
                    subua = ua.substring(0, linuxIndex);
                }
                int modelEnd = findEndIndex(subua, zteIndex);
                out.put("phoneModel", ua.substring(zteIndex, modelEnd).trim());// 手机型号
                out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
            } else if (huaweiIndex > -1) {
                String subua = ua;
                if (huaweiIndex < linuxIndex) {
                    subua = ua.substring(0, linuxIndex);
                }
                int modelEnd = findEndIndex(subua, huaweiIndex);
                out.put("phoneModel", ua.substring(huaweiIndex, modelEnd).trim());// 手机型号
                out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
            } else if (lenovoIndex > -1) {
                String subua = ua;
                if (lenovoIndex < linuxIndex) {
                    subua = ua.substring(0, linuxIndex);
                }
                int modelEnd = findEndIndex(subua, lenovoIndex);
                out.put("phoneModel", ua.substring(lenovoIndex, modelEnd).trim());// 手机型号
                out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
            } else {
                out.put("phoneModel", "");// 手机型号
                out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
            }
        }
    }

    private static void findIOSPhoneModel(final String ua, final Map<String, String> out, final String osName, final int osIndex) {
        int osvStart = 0, osvEnd = 0;
        if (osIndex > -1) {
            osvStart = osIndex + osName.length();
            final int semiIndex = ua.indexOf(SEMICOLON, osvStart + 1);
            final int spaceIndex = ua.indexOf(SPACE, osvStart + 1);
            if (semiIndex > -1 && spaceIndex > -1) {
                osvEnd = semiIndex < spaceIndex ? semiIndex : spaceIndex;
            } else if (semiIndex > -1) {
                osvEnd = semiIndex;
            } else if (spaceIndex > -1) {
                osvEnd = spaceIndex;
            }
            if (osvEnd == -1) {
                osvEnd = ua.length();
            }
        }
        final int iphoneIndex = ua.toUpperCase().indexOf("IPHONE");
        final int ipodIndex = ua.toUpperCase().indexOf("IPOD");
        final int ipadIndex = ua.toUpperCase().indexOf("IPAD");
        final int macintoshIndex = ua.toUpperCase().indexOf("MACINTOSH");
        if (ipodIndex > -1) {
            int modelEnd = findEndIndex(ua, ipodIndex);
            out.put("phoneModel", ua.substring(ipodIndex, modelEnd).trim());// 手机型号
            out.put("osv", ua.substring(osvStart, osvEnd).trim().replace('_', '.')); // 系统版本
        } else if (iphoneIndex > -1) {
            int modelEnd = findEndIndex(ua, iphoneIndex);
            out.put("phoneModel", ua.substring(iphoneIndex, modelEnd).trim());// 手机型号
            out.put("osv", ua.substring(osvStart, osvEnd).trim().replace('_', '.')); // 系统版本
        } else if (ipadIndex > -1) {
            int modelEnd = findEndIndex(ua, ipadIndex);
            out.put("phoneModel", ua.substring(ipadIndex, modelEnd).trim());// 手机型号
            out.put("osv", ua.substring(osvStart, osvEnd).trim().replace('_', '.')); // 系统版本
        } else if (macintoshIndex > -1) {
            int modelEnd = findEndIndex(ua, macintoshIndex);
            out.put("phoneModel", ua.substring(macintoshIndex, modelEnd).trim());// 手机型号
            out.put("osv", ua.substring(osvStart, osvEnd).trim().replace('_', '.')); // 系统版本
        } else {
            out.put("phoneModel", "");// 手机型号
            out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
        }
    }

    private static void findBrowserInfo(final String ua, final Map<String, String> out, boolean isAndroid) {
        final int ucIndex = ua.indexOf("UC");
        final int ucwebIndex = ua.indexOf("UCWEB");
        final int mqqIndex = ua.indexOf("MQQBrowser");
        final int qqIndex = ua.indexOf("QQBrowser");
        final int chromeIndex = ua.indexOf("Chrome");
        final int criosIndex = ua.indexOf("CriOS");
        final int _360Index = ua.indexOf("360");
        final int ieIndex = ua.indexOf("MSIE");
        final int safariIndex = ua.indexOf("Safari");
        final int browserIndex = ua.indexOf("Browser");
        final int mobileIndex = ua.indexOf("Mobile");
        if (ucwebIndex > -1) {
            int infoEnd = findEndIndex(ua, ucwebIndex);
            out.put("browser", "UCWEB");
            out.put("browserInfo", ua.substring(ucwebIndex, infoEnd));
        } else if (ucIndex > -1) {
            int infoEnd = findEndIndex(ua, ucIndex);
            out.put("browser", "UC");
            out.put("browserInfo", ua.substring(ucIndex, infoEnd));
        } else if (mqqIndex > -1) {
            int infoEnd = findEndIndex(ua, mqqIndex);
            out.put("browser", "MQQBrowser");
            out.put("browserInfo", ua.substring(mqqIndex, infoEnd));
        } else if (qqIndex > -1) {
            int infoEnd = findEndIndex(ua, qqIndex);
            out.put("browser", "QQBrowser");
            out.put("browserInfo", ua.substring(qqIndex, infoEnd));
        } else if (chromeIndex > -1) {
            int infoEnd = findEndIndex(ua, chromeIndex);
            out.put("browser", "Chrome");
            out.put("browserInfo", ua.substring(chromeIndex, infoEnd));
        } else if (criosIndex > -1) {
            int infoEnd = findEndIndex(ua, criosIndex);
            out.put("browser", "CriOS");
            out.put("browserInfo", ua.substring(criosIndex, infoEnd));
        } else if (_360Index > -1) {
            int infoEnd = findEndIndex(ua, _360Index);
            out.put("browser", "360browser");
            out.put("browserInfo", ua.substring(_360Index, infoEnd));
        } else if (ieIndex > -1) {
            int infoEnd = findEndIndex(ua, ieIndex);
            out.put("browser", "MSIE");
            out.put("browserInfo", ua.substring(ieIndex, infoEnd));
        } else if (browserIndex > -1) {
            int infoEnd = findEndIndex(ua, browserIndex);
            out.put("browser", "Browser");
            out.put("browserInfo", ua.substring(browserIndex, infoEnd));
        } else {
            if (!isAndroid && safariIndex > -1) {
                int infoEnd = findEndIndex(ua, safariIndex);
                out.put("browser", "Safari");
                out.put("browserInfo", ua.substring(safariIndex, infoEnd));
            } else if (mobileIndex > -1) {
                int infoEnd = ua.indexOf(SPACE, safariIndex > mobileIndex ? safariIndex : mobileIndex);
                if (infoEnd > -1) {
                    out.put("browser", ua.substring(infoEnd + 1));
                    out.put("browserInfo", ua.substring(infoEnd + 1));
                } else {
                    out.put("browser", "");
                    out.put("browserInfo", "");
                }

            } else {
                out.put("browser", "");
                out.put("browserInfo", "");
            }
        }
    }

    private static final int findEndIndex(final String ua, final int startIndex) {
        int infoEnd = ua.indexOf(SEMICOLON, startIndex);
        if (infoEnd == -1) {
            infoEnd = ua.indexOf(SPACE, startIndex);
            if (infoEnd == -1) {
                infoEnd = ua.length();
            }
        }
        return infoEnd;
    }

    public static void main(String[] args) {
        List<String> uaList = new ArrayList<String>();
        try {
            final String directory = "D:\\log\\";
            readFile(new File(directory));
            logToBean(logStrList, "wap");
            // String ua =
            // "IUC(U;iOS 6.0.1;Zh-cn;320*480;)/UCWEB8.8.1.218/44/997";
            // Map<String, String> map = parseUA(ua);
            // for (Entry<String, String> entry : map.entrySet()) {
            // }
            for (int i = 0; i < logObjList.size(); i++) {
                Header header = logObjList.get(i).getHeader();
                if (header != null) {
                    String ua = logObjList.get(i).getHeader().getUa();
                    if (ua != null) {
                        if (!uaList.contains(ua)) {
                            uaList.add(ua);
                            Map<String, String> map = parseUA(ua);
                            for (Entry<String, String> entry : map.entrySet()) {
                                log.debug(entry.getKey() + "------" + entry.getValue());
                            }
                        }
                    }
                }
            }
            for (String str : logStrOutList) {
                log.debug("No inductive UA：" + str);
            }
            logStrOutList.clear();
            logObjList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // String str =
        // "Mozilla/5.0 (Linux; U; Android 4.1.1; zh-cn; GT-N7100 Build/JRO03C) UC AppleWebKit/534.31 (KHTML, like Gecko) Mobile Safari/534.31";
        // str = str.substring(0, str.indexOf("Build"));
        // String[] split = str.split(" ");
    }
}
