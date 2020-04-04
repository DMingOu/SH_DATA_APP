package com.qg.sh_data_app.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.net.ContentHandler;
import java.util.logging.Logger;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * @description: Application实现类
 * @author: ODM
 * @date: 2020/4/5
 */
public class App extends Application {

    private static Context context ;

    @Override
    public void onCreate() {
        super.onCreate();
        context  = getApplicationContext();
        initRxJavaOnErrorHandle();
    }

    public static Context getContext(){
        return context;
    }

    public void initLogger(){

    }

    /**
     * 全局捕获RxJava异常
     */
    private void  initRxJavaOnErrorHandle() {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if(throwable.getLocalizedMessage() != null){
                    Log.e("RxJavaException" ,throwable.getLocalizedMessage());
                }
            }
        });
    }
}
