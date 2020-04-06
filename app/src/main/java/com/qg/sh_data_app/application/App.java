package com.qg.sh_data_app.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.net.ContentHandler;
import java.util.logging.Logger;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import me.yokeyword.fragmentation.BuildConfig;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

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
        initFragmentation();
    }

    public static Context getContext(){
        return context;
    }

    public void initLogger(){

    }

    public void initFragmentation(){
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                // 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 建议在该回调处上传至我们的Crash监测服务器
                        // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
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
