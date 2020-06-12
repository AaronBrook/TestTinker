package com.uusafe.testtinker;

import android.content.Context;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerApplicationHelper;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

public class TinkerManager {

    //标记是否安装过Tinker
    private static boolean isInstalled =  false;

    private static ApplicationLike mAppLike;

    /**
     * 完成tinker的初始化
     * @param applicationLike
     */
    public static void installTinker(ApplicationLike applicationLike) {
        mAppLike = applicationLike;
        if (isInstalled){
            return;
        }

        //完成Tinker的初始化
        TinkerInstaller.install(mAppLike);
        isInstalled = true;
    }

    /**
     * 完成patch文件的加载
     * @param path
     */
    public static void loadPatch(String path){
        if (Tinker.isTinkerInstalled()){
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),path);
        }
    }

    private static Context getApplicationContext(){
        if (mAppLike != null){
            return mAppLike.getApplication().getApplicationContext();
        }
        return null;
    }
}

