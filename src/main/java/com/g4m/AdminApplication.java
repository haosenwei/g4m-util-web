package com.g4m;

import com.alibaba.fastjson.JSONObject;
import com.g4m.auth.entity.SysUser;
import com.g4m.auth.serivce.SysUserServiceImpl;
import com.g4m.base.entity.SysConfig;
import com.g4m.base.serivce.SysConfigServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.List;

@SpringBootApplication
@Controller
@EnableCaching
@EnableWebSocket
public class AdminApplication extends SpringBootServletInitializer {
    private static Logger logger = LoggerFactory.getLogger(AdminApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        logger.info("启动成功");
    }
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(G4mUtilWebApplication.class);
	}

    @Autowired
    private SysConfigServiceImpl sysConfigServiceImpl;

    @Autowired
    private SysUserServiceImpl sysUserServiceImpl;


    @RequestMapping("/base/add")
    @ResponseBody
    public String baseAdd() {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setName("aaa");
        sysConfigServiceImpl.addSysConfig(sysConfig);
        return "OK";
    }

    @RequestMapping("/auth/add")
    @ResponseBody
    public String authAdd() {
        SysUser sysUser = new SysUser();
        sysUser.setName("aa");
        sysUserServiceImpl.addSysUser(sysUser);
        return "Ok";
    }

    @RequestMapping("/auth/getById")
    @ResponseBody
    public String authGetById(long id) {
        logger.info("authGetById-id:"+id);
        SysUser sysUserById = sysUserServiceImpl.findSysUserById(id);
        return JSONObject.toJSONString(sysUserById);
    }

    @RequestMapping("/auth/get")
    @ResponseBody
    public String authGet() {
        List<SysUser> entityAll = sysUserServiceImpl.findEntityAll();
        return JSONObject.toJSONString(entityAll);
    }

    @RequestMapping("/base/get")
    @ResponseBody
    public String baseGet() {
        List<SysConfig> entityAll = sysConfigServiceImpl.findEntityAll();
        return JSONObject.toJSONString(entityAll);
    }

    @RequestMapping("/base/getById")
    @ResponseBody
    public String baseGetById(long id) {
        logger.info("baseGetById-id:"+id);
        SysConfig entityAll = sysConfigServiceImpl.findSysConfigById(id);
        return JSONObject.toJSONString(entityAll);
    }
}
