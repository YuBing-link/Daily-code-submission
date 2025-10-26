package com.byc.ip_spring_boot_starter.Server;

import com.byc.ip_spring_boot_starter.Properties.ipProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    }
    @Autowired
    private ipProperties ipproperties;
    @Scheduled(cron = "0/#{ipProperties.cycle} * * * * ?")
    public void print(){
        if(ipproperties.getModel().equals(ipProperties.logModel.DEFAULT.getValue())){
            System.out.println("|               IP监控                    |");
            for(Map.Entry<String, Integer> entry:ipCount.entrySet()){
                System.out.println(String.format("|IP:%18s  |,|访问次数:%5d  |",entry.getKey(),entry.getValue()));
            }
        }else if(ipproperties.getModel().equals(ipProperties.logModel.Simple.getValue())){
            System.out.println("|         IP监控         |");
            for(String entry:ipCount.keySet()){
                System.out.println(String.format("|IP:%18s  |",entry));
            }
        }


        if(ipproperties.getCycleReset()){
            ipCount.clear();
        }
    }




}
