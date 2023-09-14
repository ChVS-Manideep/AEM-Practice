package com.practice.mydevelopment.core.services.impl;

import com.practice.mydevelopment.core.services.MultiService;
import org.osgi.service.component.annotations.Component;

@Component(service = MultiService.class,immediate = true,name = "bmulti")
public class MultiServiceBImpl implements MultiService {
    @Override
    public String getName() {
        return "MultiService B Implll";
    }
}
