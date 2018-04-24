package com.getui.sdk;

import android.content.Context;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息
 * onReceiveMessageData 处理透传消息
 * onReceiveClientId 接收 cid
 * onReceiveOnlineState cid 离线上线通知
 * onReceiveCommandResult 各种事件处理回执
 */
public class APICloudIntentService extends GTIntentService {
    private static final String TAG = "APICloudIntentService";

    public APICloudIntentService() { }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        Log.d(TAG, "onReceiveServicePid -> " + pid);
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String taskId = msg.getTaskId();
        String messageId = msg.getMessageId();
        byte[] payload = msg.getPayload();

        if (payload != null) {
            String data = new String(payload);
            Log.e(TAG , "payload  "+data);

            JSONObject json = new JSONObject();
            try {
                json.put("result", 1);
                json.put("type", "payload");
                json.put("taskId", taskId);
                json.put("messageId", messageId);
                json.put("payload", data);

                if (GetuiSDK.getCommonCallback() != null) {
                    GetuiSDK.getCommonCallback().success(json, false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);

        GetuiSDK.mClientId = clientid;
        JSONObject json = new JSONObject();

        try {
            json.put("result", 1);
            json.put("type", "cid");
            json.put("cid", clientid);

            if (GetuiSDK.getCommonCallback() != null) {
                GetuiSDK.getCommonCallback().success(json, false);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        Log.d(TAG, "onReceiveOnlineState -> " + (online ? "online" : "offline"));
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.d(TAG, "onReceiveCommandResult -> " + cmdMessage);
    }

    /**
     * 新增通知到达，点击回调
     * */

    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage message) {
        Log.d(TAG, "onNotificationMessageClicked -> " + "appid = " + message.getAppid() + "\ntaskid = " + message.getTaskId() + "\nmessageid = "
                + message.getMessageId() + "\npkg = " + message.getPkgName() + "\ncid = " + message.getClientId() + "\ntitle = "
                + message.getTitle() + "\ncontent = " + message.getContent());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type","onNotificationMessageClicked");
            jsonObject.put("taskid",message.getTaskId());
            jsonObject.put("messageid",message.getMessageId());
            jsonObject.put("title",message.getTitle());
            jsonObject.put("content",message.getContent());

           if (GetuiSDK.getCommonCallback() != null) {
               GetuiSDK.getCommonCallback().success(jsonObject, false);
           }
        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage message) {
        Log.d(TAG, "onNotificationMessageArrived -> " + "appid = " + message.getAppid() + "\ntaskid = " + message.getTaskId() + "\nmessageid = "
                + message.getMessageId() + "\npkg = " + message.getPkgName() + "\ncid = " + message.getClientId() + "\ntitle = "
                + message.getTitle() + "\ncontent = " + message.getContent());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type","onNotificationMessageArrived");
            jsonObject.put("taskid",message.getTaskId());
            jsonObject.put("messageid",message.getMessageId());
            jsonObject.put("title",message.getTitle());
            jsonObject.put("content",message.getContent());

            if (GetuiSDK.getCommonCallback() != null) {
                GetuiSDK.getCommonCallback().success(jsonObject, false);
            }
        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
