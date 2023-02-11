package com.utm.msei.handler;

import com.utm.msei.persistence.service.ZiSaptamanaService;

public class ServiceHandler {

    private ZiSaptamanaService ziSaptamanaService;

    public void save(ZiSaptamanaService service) {
        this.ziSaptamanaService = service;
    }

    public ZiSaptamanaService getZiSaptamanaService() {
        return this.ziSaptamanaService;
    }
}
