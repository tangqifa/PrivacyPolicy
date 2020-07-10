package cn.knowbox.privacypolicy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class PrivacyPolicyActivity extends Activity {
    private TextView mTvLeftBtn;
    private TextView mTvRightBtn;
    private TextView mContentView;
    public static final String KEY_PRIVACY_STATUS = "key_privacy_status";
    public int mPid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        mPid = android.os.Process.myPid();
        mTvLeftBtn = findViewById(R.id.tv_left_btn);
        mTvRightBtn = findViewById(R.id.tv_right_btn);
        mContentView = findViewById(R.id.content);

        boolean isPrivacy = AppPreferences.getBoolean(KEY_PRIVACY_STATUS, false);
        if (isPrivacy) {
            startMainActivity();
        } else {
            SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append("感谢您下载并使用罗亚方舟，我们非常重视您的个人信息和隐私保护。\n" +
                    "为了更好的保障您的权益，请您认真阅读《用户服务协议》和《用户隐私政策》的全部内容，同意并接受全部条款后开始我们的产品和服务。");
            //文字颜色
            ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(getResources().getColor(android.R.color.holo_red_dark));
            ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(getResources().getColor(android.R.color.holo_red_dark));
            builder.setSpan(colorSpan1, 50, 58, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            builder.setSpan(colorSpan2, 59, 67, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            //点击事件
            ClickableSpan clickableSpan1 = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse("http://www.baidu.com"));
                    intent.setAction(Intent.ACTION_VIEW);
                    startActivity(intent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setUnderlineText(false);
                }
            };
            ClickableSpan clickableSpan2 = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse("http://www.baidu.com"));
                    intent.setAction(Intent.ACTION_VIEW);
                    startActivity(intent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setUnderlineText(false);
                }
            };
            builder.setSpan(clickableSpan1, 50, 58, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            builder.setSpan(clickableSpan2, 59, 67, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            mContentView.setText(builder);
            mContentView.setHighlightColor(ContextCompat.getColor(this, android.R.color.transparent));
            mContentView.setMovementMethod(LinkMovementMethod.getInstance());

            mTvLeftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishCurrentProcess();
                }
            });
            mTvRightBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startMainActivity();
                }
            });
        }
    }

    private void startMainActivity() {
        startActivity(new Intent(PrivacyPolicyActivity.this, MainActivity.class));
        finishCurrentProcess();
    }


    private void finishCurrentProcess() {
        finish();
        android.os.Process.killProcess(mPid);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
