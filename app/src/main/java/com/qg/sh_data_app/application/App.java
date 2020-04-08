package com.qg.sh_data_app.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.NotNull;

import java.net.ContentHandler;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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
        initLiveEventConfig();
        initLogger();
        initMapConfig();
    }

    public static Context getContext(){
        return context;
    }

    public void initLogger(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public void initFragmentation(){
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                // 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(@NotNull Exception e) {
                        // 建议在该回调处上传至我们的Crash监测服务器
                        // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                        ToastUtils.showShort(e.getMessage());
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

    private void initLiveEventConfig(){
        LiveEventBus
                .config()
                //配置在没有Observer关联的时候是否自动清除LiveEvent以释放内存（默认值false）
                .autoClear(true)
                .lifecycleObserverAlwaysActive(true);
    }

    private void initMapConfig(){
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }


}
