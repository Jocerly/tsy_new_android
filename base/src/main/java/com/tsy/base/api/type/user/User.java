package com.tsy.base.api.type.user;

/**
 * Created by jay on 17/8/16.
 */

public class User {


    /**
     * user : {"id":1991942,"username":"9920598805f45b48c","password":"827093af0531352484d6207b8b92f837","passwordlev":2,"authKey":null,"accessToken":null,"realyname":"张鹏","certtype":1,"certno":"630105198809141910","certapproved":2,"certpic":"/img/2017-08-10/27/ff74d14a0f89deb4022af85b22e11b07-pc-l.jpg","check_remarks":"","email":null,"mobile":"18180996043","ischeckmobile":1,"sex":0,"nickname":null,"pic":null,"authkey":"45bdb3","account_weibo":null,"account_qq":null,"qq_openid":null,"buyercredit":null,"sellercredit":null,"newpm":0,"Islocked":0,"Islockeddate":null,"lastlogindate":"2017-08-16 10:58:42","lastloginip":"172.0.0.168","usergroupids":null,"lastpasswordchangeddate":null,"failedpasswordattemptcount":0,"failedpasswordattemptwindowstart":null,"failedpasswordanswerattemptcount":0,"failedpasswordanswerattemptwindowstart":null,"comment":null,"regip":"172.0.0.149","addtime":"2017-08-07 14:17:24","isdel":0,"regsource":0,"certapprovetime":0}
     * AppToken : U2FsdGVkX1%2FxqzBReC7zFu%2BCkfCGJye8rB%2BjDg5iWOeGlVtCIQm%2FMk4JfVeyWi7B1q0Bsjp%2BdHpuGvRXUCx%2BTStzIV8oNmMyDmou8BqxFPs%3D
     * userSign : 1991942_b09e5892240e5fb91c2166c5d092a7aa
     */

    public UserEntity user;
    public String AppToken;
    public String userSign;

    public static class UserEntity {
        /**
         * id : 1991942
         * username : 9920598805f45b48c
         * password : 827093af0531352484d6207b8b92f837
         * passwordlev : 2
         * authKey : null
         * accessToken : null
         * realyname : 张鹏
         * certtype : 1
         * certno : 630105198809141910
         * certapproved : 2
         * certpic : /img/2017-08-10/27/ff74d14a0f89deb4022af85b22e11b07-pc-l.jpg
         * check_remarks :
         * email : null
         * mobile : 18180996043
         * ischeckmobile : 1
         * sex : 0
         * nickname : null
         * pic : null
         * authkey : 45bdb3
         * account_weibo : null
         * account_qq : null
         * qq_openid : null
         * buyercredit : null
         * sellercredit : null
         * newpm : 0
         * Islocked : 0
         * Islockeddate : null
         * lastlogindate : 2017-08-16 10:58:42
         * lastloginip : 172.0.0.168
         * usergroupids : null
         * lastpasswordchangeddate : null
         * failedpasswordattemptcount : 0
         * failedpasswordattemptwindowstart : null
         * failedpasswordanswerattemptcount : 0
         * failedpasswordanswerattemptwindowstart : null
         * comment : null
         * regip : 172.0.0.149
         * addtime : 2017-08-07 14:17:24
         * isdel : 0
         * regsource : 0
         * certapprovetime : 0
         */

        public String id;
        public String username;
        public String password;
        public String passwordlev;
        public String authKey;
        public String accessToken;
        public String realyname;
        public String certtype;
        public String certno;
        public String certapproved;
        public String certpic;
        public String check_remarks;
        public String email;
        public String mobile;
        public String ischeckmobile;
        public String sex;
        public String nickname;
        public String pic;
        public String authkey;
        public String account_weibo;
        public String account_qq;
        public String qq_openid;
        public String buyercredit;
        public String sellercredit;
        public String newpm;
        public String Islocked;
        public String Islockeddate;
        public String lastlogindate;
        public String lastloginip;
        public String usergroupids;
        public String lastpasswordchangeddate;
        public String failedpasswordattemptcount;
        public String failedpasswordattemptwindowstart;
        public String failedpasswordanswerattemptcount;
        public String failedpasswordanswerattemptwindowstart;
        public String comment;
        public String regip;
        public String addtime;
        public String isdel;
        public String regsource;
        public String certapprovetime;
    }
}
