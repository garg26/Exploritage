package simplifii.framework.utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Nayan on 16-Oct-15.
 */
public class Prefs {

    static Context mContext;

    private String fileName = "GFile";

    public void setPreferencesString(Context context, String key, String value){

        mContext=context;
        SharedPreferences.Editor editor=mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPreferencesString(Context context, String key){

        mContext=context;
        SharedPreferences prefs=mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        String position=prefs.getString(key, "");
        return position;
    }

    public void setPreferencesBoolean(Context context, String key, Boolean value){

        mContext=context;
        SharedPreferences.Editor editor=mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean getPreferencesBoolean(Context context, String key){

        mContext=context;
        SharedPreferences prefs=mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Boolean flag=prefs.getBoolean(key, false);
        return flag;
    }

    public void setPreferencesInt(Context context,String key, int value){

        mContext=context;
        SharedPreferences.Editor editor=mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getPreferencesInt(Context context,String key){

        mContext=context;
        SharedPreferences prefs=mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        int position=prefs.getInt(key, 0);
        return position;
    }

    public void clearAppData(Context context){
        mContext=context;
        SharedPreferences.Editor editor=mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

}
