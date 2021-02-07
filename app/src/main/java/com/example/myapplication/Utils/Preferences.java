package com.example.myapplication.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String SP_ADMIN_APP = "spadmin";
    public static final String SP_NAMA = "spNama";
    public static final String SP_LEVELID = "spLevelid";
    public static final String SP_LEVELNAME = "spLevelname";
    public static final String SP_LOCATIONID = "spLocationid";
    public static final String SP_LOCATIONNAME = "spLocationname";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public Preferences(Context context){
        sp = context.getSharedPreferences(SP_ADMIN_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSpLevelId(){
        return sp.getString(SP_LEVELID, "");
    }

    public String getSpLevelname(){
        return sp.getString(SP_LEVELNAME, "");
    }

    public String getSpLocationid(){
        return sp.getString(SP_LOCATIONID, "");
    }

    public String getSpLocationname(){
        return sp.getString(SP_LOCATIONNAME, "");
    }


    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
