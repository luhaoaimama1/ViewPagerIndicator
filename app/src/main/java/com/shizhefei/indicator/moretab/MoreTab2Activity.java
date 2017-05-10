package com.shizhefei.indicator.moretab;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.indicator.DisplayUtil;
import com.shizhefei.indicator.demo.R;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.RoundRectangleBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

/**
 * Created by LuckyJayce on 2016/6/25.
 */
public class MoreTab2Activity extends FragmentActivity {
    private IndicatorViewPager indicatorViewPager;
    private MyAdapter myAdapter;
    private ScrollIndicatorView scrollIndicatorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moretab2);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
        scrollIndicatorView = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);

        float unSelectSize = 12;
        float selectSize = unSelectSize;
//        float selectSize = unSelectSize * 1.3f;
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener()
//                .setColor(0xFF2196F3, Color.GRAY)
                .setColor(Color.GRAY, Color.GRAY)
                .setSize(selectSize, selectSize));
        scrollIndicatorView.setScrollBar(new RoundRectangleBar(this, 0xFF2196F3, 30,60));
        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        indicatorViewPager.setAdapter(myAdapter = new MyAdapter());
        indicatorViewPager.setClickIndicatorAnim(false);
        scrollIndicatorView.setOnItemSelectListener(new Indicator.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View selectItemView, int select, int preSelect) {
                System.out.println("select:" + select + "\t preSelect:" + preSelect);
                viewPager.setCurrentItem(select, false);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("--->onPageScrolled:" + position +
                        "\t positionOffset:" + positionOffset + "\t positionOffsetPixels:" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

                if (scrollIndicatorView.getPreSelectItem() != -1)
                    ((TextView) scrollIndicatorView.getItemView(scrollIndicatorView.getPreSelectItem())).getPaint().setFakeBoldText(false);

                ((TextView) scrollIndicatorView.getItemView(position)).getPaint().setFakeBoldText(true);
                System.out.println("onPageSelected:"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("state:" + state);
            }
        });
        indicatorViewPager.setCurrentItem(0,false);
        try {
            ((TextView) scrollIndicatorView.getItemView(0)).getPaint().setFakeBoldText(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
        private String[] versions = {"Cupcake", "Donut", "Éclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lolipop", "Marshmallow"};
        private String[] names = {"纸杯蛋糕", "甜甜圈", "闪电泡芙", "冻酸奶", "姜饼", "蜂巢", "冰激凌三明治", "果冻豆", "奇巧巧克力棒", "棒棒糖", "棉花糖"};

        @Override
        public int getCount() {
            return versions.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(versions[position]);

            int witdh = getTextWidth(textView);
            int padding = DisplayUtil.dipToPix(getApplicationContext(), 8);
            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
            //1.3f是根据上面字体大小变化的倍数1.3f设置
//            textView.setWidth((int) (witdh * 1.3f) + padding);

            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new TextView(container.getContext());
            }
            TextView textView = (TextView) convertView;
            textView.setText(names[position]);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.GRAY);
            return convertView;
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_UNCHANGED;
        }

        private int getTextWidth(TextView textView) {
            if (textView == null) {
                return 0;
            }
            Rect bounds = new Rect();
            String text = textView.getText().toString();
            Paint paint = textView.getPaint();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int width = bounds.left + bounds.width();
            return width;
        }

    }
}
