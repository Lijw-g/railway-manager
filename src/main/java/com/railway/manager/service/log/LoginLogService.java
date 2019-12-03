package com.railway.manager.service.log;

import com.railway.manager.model.User;
import com.railway.manager.model.log.LoginLog;
import com.railway.manager.service.AbstractService;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description: 登录日志服务类
 * @author: chenglin
 * @create: 2019/12/3
 * @version 1.0
 **/
@Service
public class LoginLogService extends AbstractService {

    /**
     * Description: 添加登录日志1
     * @param loginLog
     * @return
     */
    public int add(LoginLog loginLog) {
        return sqlSession.insert("loginLog.insert", loginLog);
    }

    /**
     * Description: 添加登录日志2
     * @param request
     * @param user
     * @return
     */
    public int add(HttpServletRequest request, User user) {

        LoginLog loginLog = new LoginLog();

        loginLog.setUserName(user.getUserName());
        loginLog.setDisplayName(user.getDisplayName());
        loginLog.setIp(getIpAddr(request));

        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //获取系统信息
        OperatingSystem os = userAgent.getOperatingSystem();
        loginLog.setDeviceType(os.getDeviceType().getName());
        //系统名称
        loginLog.setDevice(os.getName());
        //浏览器名称
        loginLog.setBrowser(browser.getName() + " " + userAgent.getBrowserVersion().getVersion());

        return sqlSession.insert("loginLog.insert", loginLog);
    }

    /**
     * Description: 获取登录日志列表数据
     * @param map
     * @return
     */
    public List<LoginLog> getList(Map<String,Object> map) {
        return sqlSession.selectList("loginLog.selectList", map);
    }

    /**
     * Description: 获取登录日志列表总数
     * @param map
     * @return
     */
    public int selectCount(Map<String,Object> map) {
        return sqlSession.selectOne("loginLog.selectCount", map);
    }

    /**
     * 获取当前网络ip
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
