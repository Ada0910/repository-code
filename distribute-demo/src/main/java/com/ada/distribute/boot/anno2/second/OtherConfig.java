package com.ada.distribute.boot.anno2.second;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtherConfig {

    //iff ...
    //else ..
    @Bean
    public OtherBean otherBean(){
        return new OtherBean();
    }
}
