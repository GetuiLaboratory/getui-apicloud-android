package com.getui.sdk;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import com.igexin.sdk.PushManager;
import com.igexin.sdk.Tag;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.annotation.UzJavascriptMethod;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

import android.content.Intent;
import android.util.Log;

/**
 * @author zhoucheng
 * @version V 1.0
 * @package_name com.getui.sdk
 * @file_name GetuiSDK.java
 * @creat 2014-12-17
 */
public class GetuiSDK extends UZModule {

    public static String mClientId;
    private static UZModuleContext mCommonCallback;
    private boolean isInitialized = false;

    /**
     * @param webView
     */
    public GetuiSDK(UZWebView webView) {
        super(webView);
    }

    public static UZModuleContext getCommonCallback() {
        return mCommonCallback;
    }

    /**
     * 初始化个推SDK <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的initialize函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.initialize();
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_initialize(final UZModuleContext moduleContext) {
        mCommonCallback = moduleContext;
        String moduleName = "pushGeTui";

        String appkey = getFeatureValue(moduleName, "android_appkey");
        String appid = getFeatureValue(moduleName, "android_appid");
        String appsecret = getFeatureValue(moduleName, "android_appsecret");
        String groupid = getFeatureValue(moduleName, "android_groupid");

        if (appkey != null)
            appkey = appkey.trim();
        if (appid != null)
            appid = appid.trim();
        if (appsecret != null)
            appsecret = appsecret.trim();
        if (groupid != null)
            groupid = groupid.trim();

        // 测试代码
        PushManager.getInstance().initialize(mContext, null, appkey, appid, appsecret, groupid);
//        PushManager.getInstance().initialize(mContext, appkey, appid, appsecret, groupid);
        isInitialized = true;
    }

    /**
     * 重新启动应用获取透传消息 <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的payloadMessage函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.payloadMessage();
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_payloadMessage(final UZModuleContext moduleContext) {
        Intent intent = getContext().getIntent();
        String param = intent.getStringExtra("appParam");

        Log.e("payloadMessage   0118", "  " + param);


        JSONObject resultJson = new JSONObject();
        try {
            if (param != null) {
                resultJson.put("payload", param);
                resultJson.put("result", "1");
                moduleContext.success(resultJson, false);
            } else {
                resultJson.put("result", "0");
            }
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 开启个推推送 <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的turnOnPush函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.turnOnPush();
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_turnOnPush(final UZModuleContext moduleContext) {
        PushManager.getInstance().turnOnPush(mContext);
        int value = isInitialized ? 1 : 0;
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", value);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 关闭个推推送 <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的turnOffPush函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.turnOffPush();
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_turnOffPush(final UZModuleContext moduleContext) {
        PushManager.getInstance().turnOffPush(mContext);
        int value = isInitialized ? 1 : 0;
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", value);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查SDK当前服务状态 <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的isPushTurnedOn函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.isPushTurnedOn();
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_isPushTurnedOn(final UZModuleContext moduleContext) {
        boolean result = PushManager.getInstance().isPushTurnedOn(mContext);
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", 1);
            resultJson.put("isOn", result);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取SDK版本号getVersion <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的getVersion函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.getVersion();
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_getVersion(final UZModuleContext moduleContext) {
        String version = PushManager.getInstance().getVersion(mContext);
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", 1);
            resultJson.put("version", version);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置用户标签setTag <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的setTag函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.setTag(argument);
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_setTag(final UZModuleContext moduleContext) {
        int code = -1;
        String tags = moduleContext.optString("tags");
        if (tags != null && !tags.equals("")) {
            String[] tags_list = tags.split(",");
            Tag[] tag_arr = new Tag[tags_list.length];

            for (int i = 0; i < tags_list.length; i++) {
                tag_arr[i] = new Tag();
                tag_arr[i].setName(tags_list[i]);
            }
            code = PushManager.getInstance().setTag(mContext, tag_arr, "sn");
        } else {
            code = PushManager.getInstance().setTag(mContext, null, "sn");
        }
        int value = code == 0 ? 1 : 0;
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", value);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置静默时间setSilentTime <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的setSilentTime函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.setSilentTime(argument);
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_setSilentTime(final UZModuleContext moduleContext) {
        int beginHour = moduleContext.optInt("beginHour");
        int duration = moduleContext.optInt("duration");
        boolean result = PushManager.getInstance().setSilentTime(mContext, beginHour, duration);
        int value = result ? 1 : 0;
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", value);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param moduleContext (Required)
     * @Title: setHeartbeatInterval
     * @Description: 手动心跳时间设定，若设置为0，则使用SDK默认心跳时间(单位秒) <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的setHeartbeatInterval函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.setHeartbeatInterval(argument);
     */
    @UzJavascriptMethod
    public void jsmethod_setHeartbeatInterval(final UZModuleContext moduleContext) {
        int interval = moduleContext.optInt("interval");
        boolean result = PushManager.getInstance().setHeartbeatInterval(mContext, interval);
        int value = result ? 1 : 0;
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", value);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送上行数据sendMessage <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的sendMessage函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.sendMessage(argument);
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_sendMessage(final UZModuleContext moduleContext) {
        boolean result = false;
        String taskid = moduleContext.optString("taskid");
        try {
            byte[] extraData = moduleContext.optString("extraData").getBytes("UTF-8");
            result = PushManager.getInstance().sendMessage(mContext, taskid, extraData);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int value = result ? 1 : 0;
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", value);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上行第三方自定义actionid <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的sendFeedbackMessage函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.sendFeedbackMessage(argument);
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_sendFeedbackMessage(final UZModuleContext moduleContext) {
        boolean result = false;
        String taskid = moduleContext.optString("taskid");
        String messageid = moduleContext.optString("messageid");
        int actionid = moduleContext.optInt("actionid");

        if (actionid >= 90001 && actionid <= 90999) {
            result = PushManager.getInstance().sendFeedbackMessage(mContext, taskid, messageid, actionid);
        }
        int value = result ? 1 : 0;
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", value);
            if (mCommonCallback != null) {
                mCommonCallback.success(resultJson, false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停SDK服务stopService <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的stopService函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.stopService(argument);
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_stopService(final UZModuleContext moduleContext) {
        PushManager.getInstance().stopService(mContext);
        mClientId = null;
        isInitialized = false;
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", 1);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绑定cid别名接口 <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的bindAlias函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.bindAlias(argument);
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_bindAlias(final UZModuleContext moduleContext) {
        String alias = moduleContext.optString("alias");
        PushManager.getInstance().bindAlias(mContext, alias);
        int value = isInitialized ? 1 : 0;
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", value);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解绑cid别名接口 <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的unBindAlias函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.unBindAlias(argument);
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_unBindAlias(final UZModuleContext moduleContext) {
        String alias = moduleContext.optString("alias");
        // liub for 2.7.5.0
        PushManager.getInstance().unBindAlias(mContext, alias, true);
        // liub for 2.3.5.0
        // PushManager.getInstance().unBindAlias(mContext, alias);

        int value = isInitialized ? 1 : 0;
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", value);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册设备 token <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的registerDeviceToken函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.registerDeviceToken();
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_registerDeviceToken(final UZModuleContext moduleContext) {
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", -100);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主动获取 clientId <strong>函数</strong><br>
     * <br>
     * 该函数映射至Javascript中getuiSDK对象的fetchClientId函数<br>
     * <br>
     * <strong>JS Example：</strong><br>
     * getuiSDK.fetchClientId();
     *
     * @param moduleContext (Required)
     */
    @UzJavascriptMethod
    public void jsmethod_fetchClientId(final UZModuleContext moduleContext) {
        int value = mClientId != null ? 1 : 0;
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("result", value);
            resultJson.put("cid", mClientId == null ? "" : mClientId);
            moduleContext.success(resultJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
