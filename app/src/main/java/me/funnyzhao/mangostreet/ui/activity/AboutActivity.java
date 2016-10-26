package me.funnyzhao.mangostreet.ui.activity;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.R;

/**
 * Created by funnyzhao .
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.about_iv_back)
    ImageView ivBack;

    @Override
    protected void setNowContentView() {
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void initEvents() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
