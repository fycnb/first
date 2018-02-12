package com.example.timebankapp.Convert;

public class GoodsList {
    private String image;
    private String name;
    private String money;
    private String site;
    private String number;
    private String id;

    public GoodsList(String a, String b, String c, String d, String e, String f) {
        id = a;
    	name = b;
        money = c;
        site = d;
        number = e;
        image = f;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getmoney() {
        return money;
    }

    public void setmoney(String money) {
        this.money = money;
    }

    public String getsite() {
        return site;
    }

    public void setsite(String site) {
        this.site = site;
    }  

    public String getnumber() {
        return number;
    }

    public void setnumber(String number) {
        this.number = number;
    }
    
    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }
    
    
}

