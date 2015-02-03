package com.fheebiy.activity.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import com.androidquery.AQuery;
import com.fheebiy.R;
import com.fheebiy.service.MyService;

/**
 *Created by bob zhou on 15-1-5.
 * service基本理论,包括Service的基本用法、Service和Activity进行通信、Service的销毁方式、Service与Thread的关系、以及如何创建前台Service。
 * 文字解释，告诉两个基本问题：service 和 Thread的关系;为什么要用service,不用Thread 两个基本问题，非常重要，务必牢记
 *
 *service 和 Thread的关系。
 *<p>不少Android初学者都可能会有这样的疑惑，Service和Thread到底有什么关系呢？什么时候应该用Service，什么时候又应该用Thread？答案可能会有点让你吃惊，因为Service和Thread之间没有任何关系！
 *之所以有不少人会把它们联系起来，主要就是因为Service的后台概念。Thread我们大家都知道，是用于开启一个子线程，在这里去执行一些耗时操作就不会阻塞主线程的运行。而Service我们最初理解的时候，
 *总会觉得它是用来处理一些后台任务的，一些比较耗时的操作也可以放在这里运行，这就会让人产生混淆了。但是，如果我告诉你Service其实是运行在主线程里的，你还会觉得它和Thread有什么关系吗？让我们看一下这个残酷的事实吧。
 *在MainActivity的onCreate()方法里加入一行打印当前线程id的语句：
 *Log.d("MyService", "MainActivity thread id is " + Thread.currentThread().getId());
 *然后在MyService的onCreate()方法里也加入一行打印当前线程id的语句：
 *Log.d("MyService", "MyService thread id is " + Thread.currentThread().getId());
 *现在重新运行一下程序，并点击Start Service按钮，会看到如下打印日志：
 *可以看到，它们的线程id完全是一样的，由此证实了Service确实是运行在主线程里的，也就是说如果你在Service里编写了非常耗时的代码，程序必定会出现ANR的。
 *</p>
 * <p>
 *你可能会惊呼，这不是坑爹么！？那我要Service又有何用呢？
 * 其实大家不要把后台和子线程联系在一起就行了，这是两个完全不同的概念。
 *Android的后台就是指，它的运行是完全不依赖UI的。即使Activity被销毁，或者程序被关闭，只要进程还在，Service就可以继续运行。
 *比如说一些应用程序，始终需要与服务器之间始终保持着心跳连接，就可以使用Service来实现。你可能又会问，前面不是刚刚验证过Service是运行在主线程里的么？
 *在这里一直执行着心跳连接，难道就不会阻塞主线程的运行吗？当然会，但是我们可以在Service中再创建一个子线程，然后在这里去处理耗时逻辑就没问题了。
 *额，既然在Service里也要创建一个子线程，那为什么不直接在Activity里创建呢？这是因为Activity很难对Thread进行控制，当Activity被销毁之后，
 *就没有任何其它的办法可以再重新获取到之前创建的子线程的实例。而且在一个Activity中创建的子线程，另一个Activity无法对其进行操作。
 *但是Service就不同了，所有的Activity都可以与Service进行关联，然后可以很方便地操作其中的方法，即使Activity被销毁了，之后只要重新与Service建立关联，就又能够获取到原有的Service中Binder的实例。
 *因此，使用Service来处理后台任务，Activity就可以放心地finish，完全不需要担心无法对后台任务进行控制的情况。
 * <p/>
 * Service demo
 */
public class ServiceMainActivity extends Activity implements View.OnClickListener {

    private AQuery aq;

    private MyService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_main);
        aq = new AQuery(this);
        bindListener();

    }

    public void bindListener() {
        aq.id(R.id.start_service).clicked(this);
        aq.id(R.id.stop_service).clicked(this);
        aq.id(R.id.bind_service).clicked(this);
        aq.id(R.id.unbind_service).clicked(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;

        }
    }
}