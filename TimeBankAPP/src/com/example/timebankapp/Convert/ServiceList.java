package com.example.timebankapp.Convert;

public class ServiceList {
    private String name;
    private String phone;
    private String money;
    private String fame;
    private String id;
    private String image;

    public ServiceList(String a, String b, String c, String d, String e, String f) {

        name = b;
        phone = c;
        money = d;
        fame = e;
        id = a;
        image = f;
        
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

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }  

    public String getfame() {
        return fame;
    }

    public void setfame(String fame) {
        this.fame = fame;
    }
    
    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
    
    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }
}

