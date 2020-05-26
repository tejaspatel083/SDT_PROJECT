package com.example.myrecipe_sdt;

public class RecipeGetterSetter {
    String rname,cname,etime,ecost,rdetail;

    public RecipeGetterSetter() {
    }

    public RecipeGetterSetter(String rname, String cname, String etime, String ecost, String rdetail) {
        this.rname = rname;
        this.cname = cname;
        this.etime = etime;
        this.ecost = ecost;
        this.rdetail = rdetail;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getEcost() {
        return ecost;
    }

    public void setEcost(String ecost) {
        this.ecost = ecost;
    }

    public String getRdetail() {
        return rdetail;
    }

    public void setRdetail(String rdetail) {
        this.rdetail = rdetail;
    }
}
