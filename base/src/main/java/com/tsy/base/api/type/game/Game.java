package com.tsy.base.api.type.game;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jay on 17/8/1.
 */

public class Game implements Parcelable {


    /**
     * id : 352
     * name : 梦幻西游
     * firstletter : M
     * spelling : menghuanxiyou
     * pic : /img/2016-05-16/23/7b3332a1f9380660b23a7af17ba6533b-pc-l.jpg
     * ishot : 1
     * ischarge : 1
     * isquick : 1
     * fromUser : 1
     * tradescount : 2862
     * firstdiscount : 73
     */

    public String id;
    public String name;
    public String firstletter;
    public String spelling;
    public String pic;
    public String ishot;
    public String ischarge;
    public String isquick;
    public String fromUser;
    public String tradescount;
    public String firstdiscount;
    public String goodsid;
    public String continuediscount;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.firstletter);
        dest.writeString(this.spelling);
        dest.writeString(this.pic);
        dest.writeString(this.ishot);
        dest.writeString(this.ischarge);
        dest.writeString(this.isquick);
        dest.writeString(this.fromUser);
        dest.writeString(this.tradescount);
        dest.writeString(this.firstdiscount);
    }

    public Game() {
    }

    protected Game(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.firstletter = in.readString();
        this.spelling = in.readString();
        this.pic = in.readString();
        this.ishot = in.readString();
        this.ischarge = in.readString();
        this.isquick = in.readString();
        this.fromUser = in.readString();
        this.tradescount = in.readString();
        this.firstdiscount = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}
