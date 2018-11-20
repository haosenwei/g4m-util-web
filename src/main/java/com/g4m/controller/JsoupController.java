package com.g4m.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

@RestController
public class JsoupController {
	private RestTemplate restTemplate = new RestTemplate();

	private final static Logger logger = LoggerFactory.getLogger(JsoupController.class);

	@RequestMapping(value="/getjsoup")
	@ResponseBody
	public Object getJsoup(String url,String callback,HttpServletResponse response) throws IOException {
		Object o = "";
		try {
			o = restTemplate.getForObject(url, Object.class);
		} catch (RestClientException e) {
			logger.error("url报错:"+url,e);
		}
		logger.info("请求地址:"+url +",回调方法:"+callback+",返回:"+JSONObject.toJSONString(o));
		if(StringUtils.isEmpty(callback)) {
			return o;
		}
		writeBack(response,callback,o);
		return null;
	}	
	@RequestMapping(value="/postjsoup")
	@ResponseBody
	public Object postJsoup(String url,String param,String callback,HttpServletResponse response) throws IOException {
		MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<String, String>();
		if(param!=null&&param!=""){
			String[] split = param.split("&");
			for (String string : split) {
				try {
					String[] split2 = string.split("=");
					requestEntity.add(split2[0],split2[1]);
				} catch (Exception e) {
					logger.error("post请求参数错误", e);
				}
			}
		}
		Object o = "";
		try {
			o = restTemplate.postForObject(url, requestEntity, Object.class);
		} catch (RestClientException e) {
			logger.error("url报错:"+url,e);
		}
		logger.info("请求地址:"+url +",参数:"+param+",回调方法:"+callback+",返回:"+JSONObject.toJSONString(o));
		if(StringUtils.isEmpty(callback)) {
			return o;
		}
		writeBack(response,callback,o);
		return null;
	}	
	@RequestMapping(value="/ip")
	@ResponseBody
	public Object ip(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String ip = request.getHeader("X-Forwarded-For");
        logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print("var ip = '" + ip + "';");
		return null;
	}	

	private void writeBack(HttpServletResponse response, String callback, Object o) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(callback + "(" + o.toString() + ")" );
	}
}
