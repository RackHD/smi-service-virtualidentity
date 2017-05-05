/**
 * Copyright � 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.virtualidentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dell.isg.smi.virtualidentity.service.IoIdentityManager;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    IoIdentityManager ioIdentityManager;


    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        ioIdentityManager.initailizePools();
        return;
    }

}
