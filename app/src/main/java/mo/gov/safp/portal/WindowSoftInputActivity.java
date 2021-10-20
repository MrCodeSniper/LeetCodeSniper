package mo.gov.safp.portal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import mo.gov.safp.portal.soft.LoginScrollAdjustNothingActivity;
import mo.gov.safp.portal.soft.LoginScrollAdjustPanActivity;
import mo.gov.safp.portal.soft.LoginScrollDefaultActivity;
import mo.gov.safp.portal.soft.LoginScrollAdjustResizeActivity;
import mo.gov.safp.portal.soft.LoginScrollFullScreenAdjustResizeActivity;
import mo.gov.safp.portal.soft.LoginScrollFullScreenAdjustResizeSelfActivity;
import mo.gov.safp.portal.soft.LoginUnScrollActivity;
import mo.gov.safp.portal.soft.LoginUnScrollAdjustNothingActivity;
import mo.gov.safp.portal.soft.LoginUnScrollAdjustPanActivity;
import mo.gov.safp.portal.soft.LoginUnScrollAdjustResizeActivity;
import mo.gov.safp.portal.soft.LoginUnScrollAlwaysHiddenActivity;
import mo.gov.safp.portal.soft.LoginUnScrollHiddenActivity;
import mo.gov.safp.portal.soft.LoginUnScrollStateAlwaysVisibleActivity;
import mo.gov.safp.portal.soft.LoginUnScrollStateVisibleActivity;
import mo.gov.safp.portal.soft.LoginUnScrollUnChangedActivity;

/**
 * @author CH
 * @date 2021/10/7
 * @desc 模式分为 SOFT_INPUT_STATE键盘显示模式 和 SOFT_INPUT_ADJUST 布局显示模式
 */
public class WindowSoftInputActivity extends Activity {

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
        startActivity(new Intent(this, LoginScrollDefaultActivity.class));
    }
    /**
     * 默认模式非全屏非滚动布局
     * 效果
     * SOFT_INPUT_STATE_UNSPECIFIED｜SOFT_INPUT_ADJUST_UNSPECIFIED
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
     * 2.从展示键盘页面返回还保留
     * @param view
     */
    public void softInputHidden(View view) {
        startActivity(new Intent(this, LoginUnScrollHiddenActivity.class));
    }

    public void finish(View view) {
        finish();
    }

    /**
     * SOFT_INPUT_STATE_ALWAYS_HIDDEN模式布局
     * 效果
     * 1.进入新页面总是会隐藏软键盘
     * 2.从展示键盘页面返回隐藏键盘
     * @param view
     */
    public void softInputAlwaysHidden(View view) {
        startActivity(new Intent(this, LoginUnScrollAlwaysHiddenActivity.class));
    }

    /**
     * SOFT_INPUT_STATE_VISIBLE模式布局
     * 效果
     * 1.一进入Activity就弹出软键盘
     * 2.从其他界面返回保持状态不变
     * 3.从后台到前台保持弹出软键盘
     * @param view
     */
    public void softInputStateVisible(View view) {
        startActivity(new Intent(this, LoginUnScrollStateVisibleActivity.class));
    }

    /**
     * SOFT_INPUT_STATE_ALWAYS_VISIBLE模式布局
     * 效果
     * 1.要先获取焦点展示过键盘 从后续页面返回时或从后台到前台  会一直展示键盘
     * 2.不获取焦点就会失效
     * @param view
     */
    public void softInputStateAlwaysVisible(View view) {
        startActivity(new Intent(this, LoginUnScrollStateAlwaysVisibleActivity.class));
    }


    /**
     * SOFT_INPUT_ADJUST_RESIZE
     * 效果
     * 1。对于没有滚动的界面来说 整个布局被顶上去 activity的空间只有 顶部到键盘之间
     * 2.
     * @param view
     */
    public void softInputNoScroolAdjustResize(View view) {
        startActivity(new Intent(this, LoginUnScrollAdjustResizeActivity.class));
    }

    /**
     * SOFT_INPUT_ADJUST_RESIZE
     * 效果
     * 1。对于滚动的界面来说 与默认情况相同 布局高度不变 可以滑动 保证焦点控件完全展示
     * 2.
     * @param view
     */
    public void softInputScroolAdjustResize(View view) {
        startActivity(new Intent(this, LoginScrollAdjustResizeActivity.class));
    }


    /**
     * 全屏 沉浸式状态栏会导致 SOFT_INPUT_ADJUST_RESIZE 失效 必须自身调整布局大小适配
     * @param view
     */
    public void softInputScroolFullScreenAdjustResize(View view) {
        startActivity(new Intent(this, LoginScrollFullScreenAdjustResizeActivity.class));
    }

    /**
     * 全屏 沉浸式状态栏会导致 SOFT_INPUT_ADJUST_RESIZE 失效 必须自身调整布局大小适配
     * AndroidBug5497Workaround 系统bug解决方案
     * @param view
     */
    public void softInputScroolFullScreenAdjustResizeSelf(View view) {
        startActivity(new Intent(this, LoginScrollFullScreenAdjustResizeSelfActivity.class));
    }


    /**
     * SOFT_INPUT_ADJUST_PAN
     * 如果焦点位置距离屏幕底部距离小于软键盘高度，则把整个布局顶上去到焦点位置，不压缩多余空间
     * 与默认相同
     * @param view
     */
    public void softInputNoScroolAdjustPan(View view) {
        startActivity(new Intent(this, LoginUnScrollAdjustPanActivity.class));
    }

    /**
     * SOFT_INPUT_ADJUST_PAN
     * 如果焦点位置距离屏幕底部距离小于软键盘高度，则把整个布局顶上去到焦点位置，不压缩多余空间
     * 滚动布局下 可以从顶部滚动到焦点展示位置
     * @param view
     */
    public void softInputScroolAdjustPan(View view) {
        startActivity(new Intent(this, LoginScrollAdjustPanActivity.class));
    }

    /**
     * SOFT_INPUT_ADJUST_NOTHING
     * 覆盖不做任何处理
     * @param view
     */
    public void softInputNoScroolAdjustNothing(View view) {
        startActivity(new Intent(this, LoginUnScrollAdjustNothingActivity.class));
    }
    /**
     * SOFT_INPUT_ADJUST_NOTHING
     * 覆盖不做任何处理 仍然可以滚动
     * @param view
     */
    public void softInputScroolAdjustNothing(View view) {
        startActivity(new Intent(this, LoginScrollAdjustNothingActivity.class));
    }
}
