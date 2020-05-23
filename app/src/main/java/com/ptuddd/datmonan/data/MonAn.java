package com.ptuddd.datmonan.data;

public class MonAn {
    private String tenMonAn;
    private String diaChi;
    private String linkWeb;
    private int image;
    private boolean isChoose=false;

    public MonAn(String tenMonAn, String diaChi, String linkWeb, int image) {
        this.tenMonAn = tenMonAn;
        this.diaChi = diaChi;
        this.linkWeb = linkWeb;
        this.image = image;
    }
    public MonAn(){

    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getLinkWeb() {
        return linkWeb;
    }

    public void setLinkWeb(String linkWeb) {
        this.linkWeb = linkWeb;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
