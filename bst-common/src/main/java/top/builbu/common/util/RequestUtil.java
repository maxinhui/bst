package top.builbu.common.util;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestUtil {
	public static String getContentPathUrl(HttpServletRequest request){
		String contextPath = request.getContextPath();
		StringBuffer requestURL = request.getRequestURL();
		String [] urlParts = requestURL.toString().split(contextPath);
		String contentPathUrl =urlParts[0]+contextPath;
		log.debug("urlPrefix:"+contentPathUrl);
		return contentPathUrl;
	}
	
	public static String getClientIPAddress(HttpServletRequest httpservletrequest)
    {
        String s = httpservletrequest.getHeader("x-forwarded-for");
        if(s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
            s = httpservletrequest.getHeader("Proxy-Client-IP");
        if(s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
            s = httpservletrequest.getHeader("WL-Proxy-Client-IP");
        if(s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
            s = httpservletrequest.getHeader("http_client_ip");
        if(s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
            s = httpservletrequest.getRemoteAddr();
        if(s.indexOf(",") >= 0)
        {
            String as[] = s.split(",");
            int i = as == null ? 0 : as.length;
            for(int j = 0; j < i; j++)
            {
                String s1 = as[j].trim();
                if(!"".equalsIgnoreCase(s1))
                    s = s1;
            }

        }
        return s;
    }
}
