package com.example.zupedustore.controller;

import com.example.zupedustore.model.Aplicativo;

public class DownloadAplicativoResponse {

    private String linkDownload;

    public DownloadAplicativoResponse(Aplicativo aplicativo) {
        this.linkDownload = aplicativo.getLink();
    }

    public DownloadAplicativoResponse() {
    }

    public String getLinkDownload() {
        return linkDownload;
    }
}
