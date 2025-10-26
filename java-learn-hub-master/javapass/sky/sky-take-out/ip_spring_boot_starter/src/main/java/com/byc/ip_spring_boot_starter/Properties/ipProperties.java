package com.byc.ip_spring_boot_starter.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("ipProperties")
@ConfigurationProperties(prefix = "tools.ip")
public class ipProperties {

    private Long cycle=10L;
    private Boolean cycleReset=false;
    private String model=logModel.DEFAULT.getValue();
    public enum logModel{
        /**
         * 默认(明细模式)
         */
        DEFAULT("detail"),
        /**
         * 简化模式
         */
        Simple("simple"),
        ;


        private  String value;
        logModel(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }




    }
    public Boolean getCycleReset() {
        return cycleReset;
    }

    public void setCycleReset(Boolean cycleReset) {
        this.cycleReset = cycleReset;
    }

    public Long getCycle() {
        return cycle;
    }

    public void setCycle(Long cycle) {
        this.cycle = cycle;
    }
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
