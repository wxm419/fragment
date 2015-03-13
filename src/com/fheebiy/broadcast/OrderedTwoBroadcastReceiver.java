package com.fheebiy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.fheebiy.util.CommonUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Lenovo on 15-3-12.
 * 后收到，优先级配置了较低
 */
public class OrderedTwoBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("msg");
        if (StringUtils.isNotEmpty(str)) {
            CommonUtil.toast(context, str);
        }
        Bundle bundle = getResultExtras(false);
        if (bundle != null) {
            CommonUtil.toast(context, bundle.getString("msg"));
        }
    }
}
