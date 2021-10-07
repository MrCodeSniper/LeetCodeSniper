package mo.gov.safp.portal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import mo.gov.safp.portal.soft.LoginUnScrollActivity;
import mo.gov.safp.portal.soft.LoginUnScrollHiddenActivity;
import mo.gov.safp.portal.soft.LoginUnScrollUnChangedActivity;

/**
 * @author CH
 * @date 2021/10/7
 * @desc 模式分为 SOFT_INPUT_STATE键盘显示模式 和 SOFT_INPUT_ADJUST 布局显示模式
 */
public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    /**
     * 默认模式非全屏滚动布局
     * 效果
     * 1.键盘弹出时 布局都可以滚动 焦点部分会自动滚动保证可见
     * SOFT_INPUT_STATE_UNSPECIFIED｜SOFT_INPUT_ADJUST_UNSPECIFIED
     * @param view 视图
     */
    public void scrollUnSpecified(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
    /**
     * 默认模式非全屏非滚动布局
     * 效果
     * 1.键盘弹出时 布局不能滚动 下面的元素被键盘遮挡
     * 2.键盘弹出 焦点部分如果可能被键盘挡住 布局会上移保证完全展示
     * 下面的元素仍然会被键盘遮挡 同样不能滚动
     * @param view 视图
     */
    public void unScrollUnSpecified(View view) {
        startActivity(new Intent(this, LoginUnScrollActivity.class));
    }

    /**
     * SOFT_INPUT_STATE_UNCHANGED模式布局
     * 效果
     * 1.跳转的页面键盘显示的效果 取决于上个页面
     * 2.跳转后的会议需要退出软键盘再退出页面
     * @param view
     */
    public void softInputUnChanged(View view) {
        startActivity(new Intent(this, LoginUnScrollUnChangedActivity.class));
    }

    /**
     * SOFT_INPUT_STATE_HIDDEN模式布局
     * 效果
     * 1.进入新页面总是会隐藏软键盘
     * 2.结束页面也会隐藏软键盘
     * @param view
     */
    public void softInputHidden(View view) {
        startActivity(new Intent(this, LoginUnScrollHiddenActivity.class));
    }
}
