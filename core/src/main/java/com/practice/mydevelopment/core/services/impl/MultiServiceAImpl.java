package com.practice.mydevelopment.core.services.impl;

import com.practice.mydevelopment.core.services.MultiService;
import org.osgi.service.component.annotations.Component;

@Component(service = MultiService.class,immediate = true)
public class MultiServiceAImpl implements MultiService {
    @Override
    public String getName() {
        return "MultiService A Impl";
    }
}
