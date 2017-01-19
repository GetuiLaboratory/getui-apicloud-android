package com.getui.sdk;

import org.json.JSONException;
import org.json.JSONObject;

import com.igexin.sdk.PushConsts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class GetuiSDKReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {

            case PushConsts.GET_MSG_DATA:
                // 获取透传数据
                byte[] payload = bundle.getByteArray("payload");
                String taskid = bundle.getString("taskid");
                String messageid = bundle.getString("messageid");

                if (payload != null) {
                    String data = new String(payload);
//                    Log.e("onReceive:  payload", " "+data);

                    JSONObject json = new JSONObject();
                    try {
                        json.put("result", 1);
                        json.put("type", "payload");
                        json.put("taskId", taskid);
                        json.put("messageId", messageid);
                        json.put("payload", data);

                        if (GetuiSDK.getCommonCallback() != null)
                            GetuiSDK.getCommonCallback().success(json, false);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case PushConsts.GET_CLIENTID:
                // 获取ClientID(CID)
                // 第三方应用需要将CID上传到第三方服务器，并且将当前用户帐号和CID进行关联，以便日后通过用户帐号查找CID进行消息推送
                String cid = bundle.getString("clientid");
//                Log.e("onReceive:  cid", " "+cid);

                JSONObject json = new JSONObject();
                try {
                    json.put("result", 1);
                    json.put("type", "cid");
                    json.put("cid", cid);

                    GetuiSDK.mClientId = cid;

                    if (GetuiSDK.getCommonCallback() != null)
                        GetuiSDK.getCommonCallback().success(json, false);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case PushConsts.GET_SDKONLINESTATE:
                // 获取SDK在线状态
                boolean isOnline = bundle.getBoolean("onlineState");
                // Log.d("GexinSdkDemo", "Got Sdk online state change :" + isOnline);
                break;
            case PushConsts.GET_SDKSERVICEPID:
                // 获取SDK service 进程id
                int pid = bundle.getInt("pid");
                // Log.d("GexinSdkDemo", "Got Sdk service pid :" + pid);
                break;
            case PushConsts.THIRDPART_FEEDBACK:
                String appid = bundle.getString("appid");
                String taskid2 = bundle.getString("taskid");
                String actionid = bundle.getString("actionid");
                String result = bundle.getString("result");
                long timestamp = bundle.getLong("timestamp");

                break;
            default:
                break;
        }
    }
}
