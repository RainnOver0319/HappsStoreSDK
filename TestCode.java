package com.happsstore.cs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.happsstore.sdk.SDK;

public class TextCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化SDK
        SDK.init("app_xxx", "key_xxx");
        //发起登录
        SDK.launchLogin(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        SDK.fetchUserInfo(intent, new SDK.UserInfoCallback() {
            @Override
            public void onSuccess(SDK.UserInfo userInfo) {
                runOnUiThread(() -> {
                    //登录成功
                    String userInfoStr = "用户昵称："+userInfo.getNickname()+"\n\n用户头像："+userInfo.getUsertx()+"\n\n用户签名："+userInfo.getSignature();
                    Toast.makeText(TextCode.this, "登录成功", Toast.LENGTH_SHORT).show();
                    ((TextView)findViewById(R.id.textView1)).setText(userInfoStr);
                });
            }

            @Override
            public void onError(String errorMsg) {
                runOnUiThread(() -> {
                    Toast.makeText(TextCode.this, "登录失败: " + errorMsg, Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}