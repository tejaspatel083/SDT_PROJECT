package com.example.myrecipe_sdt;

public class RecipeGetterSetter {
    String cname,ecost,etime,rdetail,rname;
    

    public RecipeGetterSetter() {

    }

    public RecipeGetterSetter(String cname, String ecost, String etime, String rdetail, String rname) {
        this.cname = cname;
        this.ecost = ecost;
        this.etime = etime;
        this.rdetail = rdetail;
        this.rname = rname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEcost() {
        return ecost;
    }

    public void setEcost(String ecost) {
        this.ecost = ecost;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getRdetail() {
        return rdetail;
    }

    public void setRdetail(String rdetail) {
        this.rdetail = rdetail;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }


    public String toString()
    {
        return this.rname;
    }
}
