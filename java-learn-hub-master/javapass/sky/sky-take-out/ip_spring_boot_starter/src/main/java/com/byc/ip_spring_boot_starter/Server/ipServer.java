package com.byc.ip_spring_boot_starter.Server;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ipServer {
    @Autowired
    private HttpServletRequest httpServletRequest;
    private Map<String, Integer> ipCount = new HashMap<>();
    public void ipCountServer() {
        String ip = httpServletRequest.getRemoteAddr();

        Integer count= ipCount.get(ip);
        if(count==null){
            ipCount.put(ip,1);
        }else{
            ipCount.put(ip,count+1);
        }

        System.out.println(ipCount);

    }


}
