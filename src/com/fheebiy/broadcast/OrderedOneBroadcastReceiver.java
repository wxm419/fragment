package com.fheebiy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.fheebiy.util.CommonUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Lenovo on 15-3-12.
 * 先收到,优先级配置较高
 */
public class OrderedOneBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("msg");
        if (StringUtils.isNotEmpty(str)) {
            Bundle bundle = new Bundle();
            bundle.putString("msg", "from OrderedOneBroadcastReceiver" + str);
            setResultExtras(bundle);
        }

    }
}
