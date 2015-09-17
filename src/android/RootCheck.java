package io.github.mehmetcodes.cordovaplugins;

import io.github.mehmetcodes.cordovaplugins.ExecShell.SHELL_CMD;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.pm.ApplicationInfo;
import java.io.*;
import java.util.ArrayList;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;


/**
 * Created by Mehmet Yilmaz .
 */
public class RootCheckPlugin extends CordovaPlugin {  {
    private static String LOG_TAG = RootCheckPlugin.class.getName();
    public static final String ACTION_ROOTCHECK = "RootCheck"; 

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
 
    }
    //Add debuggable check
    private static boolean isDebuggable(Context ctx){
        boolean debuggable = false;
        PackageManager pm =ctx.getPackageManager();
        try
        {
            ApplicationInfo appinfo = pm.getApplicationInfo(ctx.getPackageName(),0);
            debuggable = (0 !=(appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        }catch(PackageManager.NameNotFoundException nnfe){
            //Just keep it false and let it fall through
        }
        return debuggable;

    }


    //Add method to force crash

    public static boolean isDeviceRooted(Context ctx) {
        //Add code to check for if this is in release
        if (!RootCheck.isDebuggable(ctx) ) {
            Log.v(LOG_TAG,"This is not debuggable");
            if (checkTags()) {

                    return true;
                   //3rd party cert trust
            }
        }else{
            Log.v(LOG_TAG,"This is debuggable, so we ignore 3p cert check.");
        }
        if (checkSuperUserApp()){return true;}
        if (checkSUbin()){return true;}
        return false;
    }



    public static boolean checkTags(){
        String buildTags = android.os.Build.TAGS;

        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }
        return false;
    }

    public static boolean checkSuperUserApp(){
        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                return true;
            }
        } catch (Exception e) { }

        return false;
    }

    public static boolean checkSUbin() {
        //IF "ruhoh" is true, we have trouble
        boolean ruhoh = false;
        String[] places = {
                "/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/",
                "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"
        };
        for (final String path : places){
            String [] cmd = {"stat",path+"su"};
            ArrayList<String> results = new ExecShell().executeDirectCommand(cmd);
            if( results != null) {
                for (String r : results) {
                    if (r.contains("File:") && r.contains("su")) {
                        ruhoh = true;
                    }
                }
            }
        }
        ArrayList result = new ExecShell().executeCommand(SHELL_CMD.check_su_binary);
        if ( result != null){
            ruhoh=true;
        }
        return ruhoh;
    }


}