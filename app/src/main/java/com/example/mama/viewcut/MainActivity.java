package com.example.mama.viewcut;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
/**
 *  create by dingkangkang 851615943@qq.com
 */
public class MainActivity extends AppCompatActivity {

    private ScrollView sv_scoll,sv_scoll2;
    private ImageView iv_picture,iv_picture2,iv_test;
    private Button btn_caijian;
    private int slideX=0;
    private int slideY=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        sv_scoll = (ScrollView) findViewById(R.id.sv_scoll);
        sv_scoll2 = (ScrollView) findViewById(R.id.sv_scoll2);
        iv_picture = (ImageView) findViewById(R.id.iv_picture);
        iv_picture2 = (ImageView) findViewById(R.id.iv_picture2);
        iv_test = (ImageView) findViewById(R.id.iv_test);
        btn_caijian = (Button) findViewById(R.id.btn_caijian);

        sv_scoll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                slideX = scrollX;
                slideY = scrollY;
                sv_scoll2.scrollTo(scrollX,scrollY);
            }
        });

        btn_caijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_test.setImageBitmap(getCutView(sv_scoll));
            }
        });
    }

    /**
     * 获取裁剪的bitmap
     * @param scrollView
     * @return
     */
    private Bitmap getCutView(ScrollView scrollView) {
        Bitmap bitmap=null;
        int height=0;
        for(int i=0;i<scrollView.getChildCount();i++){
            height+=scrollView.getChildAt(i).getHeight();
        }
        //绘制scrollview
        bitmap = Bitmap.createBitmap(scrollView.getWidth(),height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        //截取显示部分
        bitmap = Bitmap.createBitmap(bitmap,0,slideY,bitmap.getWidth(),dip2px(200));
        return bitmap;
    }

    /**
     * 根据手机分辨率从DP转成PX
     * @param dpValue
     * @return
     */
    public  int dip2px( float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
