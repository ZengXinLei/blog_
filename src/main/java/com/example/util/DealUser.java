package com.example.util;

import com.example.enity.User;

/**
 * @Author: zxl
 * @Time: 2020/05/27 15:06:46
 * @system: ASUS
 **/
public class DealUser {
    public static User encodeUser(User user) {
        User u= null;
        try {
            u = (User)user.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assert u != null;
        u.setUId((long) (Math.pow(u.getUId(),2)+5*u.getUId()+10000));
        u.setUTel(Base64Util.encodeData(u.getUTel()));
        u.setUPassword(Base64Util.encodeData(u.getUPassword()));
        return u;
    }


    public static User decoderUser(User user){
        User u= null;
        try {
            u = (User)user.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assert u != null;
        long c=10000-u.getUId();
        long d= (long) Math.sqrt(25-4*2*c);
        u.setUId((-5+d)/4>=0?(-5+d)/4:(-5-d)/4);
        u.setUTel(Base64Util.decodeData(u.getUTel()));
        u.setUPassword(Base64Util.decodeData(u.getUPassword()));

        return u;

    }
}
