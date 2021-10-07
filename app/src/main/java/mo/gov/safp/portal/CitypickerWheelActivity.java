package mo.gov.safp.portal;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.CustomCityData;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CustomConfig;
import com.lljjcoder.style.citycustome.CustomCityPicker;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

import java.util.ArrayList;
import java.util.List;

import mo.gov.safp.portal.utils.LanguageUtil;

public class CitypickerWheelActivity extends Activity {

    EditText mProEt;

    EditText mCityEt;

    EditText mAreaEt;

    EditText mProVisibleCountEt;

    CheckBox mProCyclicCk;

    CheckBox mCityCyclicCk;

    CheckBox mAreaCyclicCk;

    CheckBox mHalfBgCk;
    CheckBox mGATCk;

    TextView mResetSettingTv;

    TextView mSubmitTv;

    TextView mResultTv;

    TextView mOneTv;

    TextView mTwoTv;

    TextView mThreeTv;

    private int visibleItems = 5;

    private boolean isProvinceCyclic = true;

    private boolean isCityCyclic = true;

    private boolean isDistrictCyclic = true;

    private boolean isShowBg = true;
    private boolean isShowGAT = true;

    private String defaultProvinceName = "江苏";

    private String defaultCityName = "常州";

    private String defaultDistrict = "新北区";

    public CityConfig.WheelType mWheelType = CityConfig.WheelType.PRO_CITY_DIS;

    CityPickerView mCityPickerView = new CityPickerView();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citypicker_wheel);
        findView();
//        mCityPickerView.init(this);
    }

    private List<CustomCityData> prepareData(){
        List<CustomCityData> dataList =  new ArrayList<>();
        dataList.add(new CustomCityData(LanguageUtil.CHINESE_TRADITIONAL,getResources().getString(R.string.account_language_zh_hant)));
        dataList.add(new CustomCityData(LanguageUtil.PORTUGAL,getResources().getString(R.string.account_language_pt)));
        dataList.add(new CustomCityData(LanguageUtil.ENGLISH,getResources().getString(R.string.account_language_en)));
        return dataList;
    }



    private void findView() {

        mResultTv = (TextView) findViewById(R.id.result_tv);
        mProEt = (EditText) findViewById(R.id.pro_et);
        mCityEt = (EditText) findViewById(R.id.city_et);
        mAreaEt = (EditText) findViewById(R.id.area_et);
        mProVisibleCountEt = (EditText) findViewById(R.id.pro_visible_count_et);
        mProCyclicCk = (CheckBox) findViewById(R.id.pro_cyclic_ck);
        mCityCyclicCk = (CheckBox) findViewById(R.id.city_cyclic_ck);
        mAreaCyclicCk = (CheckBox) findViewById(R.id.area_cyclic_ck);
        mGATCk = (CheckBox) findViewById(R.id.gat_ck);
        mHalfBgCk = (CheckBox) findViewById(R.id.half_bg_ck);
        mResetSettingTv = (TextView) findViewById(R.id.reset_setting_tv);
        mSubmitTv = (TextView) findViewById(R.id.submit_tv);
        mOneTv = (TextView) findViewById(R.id.one_tv);
        mTwoTv = (TextView) findViewById(R.id.two_tv);
        mThreeTv = (TextView) findViewById(R.id.three_tv);

        //提交
        mSubmitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wheel();
            }
        });

        //重置属性
        mResetSettingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        //一级联动，只显示省份，不显示市和区
        mOneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CityConfig.WheelType.PRO;
                setWheelType(mWheelType);
            }
        });

        //二级联动，只显示省份， 市，不显示区
        mTwoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CityConfig.WheelType.PRO_CITY;
                setWheelType(mWheelType);
            }
        });

        //三级联动，显示省份， 市和区
        mThreeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
                setWheelType(mWheelType);
            }
        });

        //省份是否循环显示
        mProCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isProvinceCyclic = isChecked;
            }
        });

        //市是否循环显示
        mCityCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCityCyclic = isChecked;
            }
        });

        //区是否循环显示
        mAreaCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDistrictCyclic = isChecked;
            }
        });

        //半透明背景显示
        mHalfBgCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isShowBg = isChecked;
            }
        });

        //港澳台数据显示
        mGATCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isShowGAT = isChecked;
            }
        });

        setWheelType(mWheelType);
    }

    /**
     * 重置属性
     */
    private void reset() {
        visibleItems = 5;
        isProvinceCyclic = true;
        isCityCyclic = true;
        isDistrictCyclic = true;
        isShowBg = true;
        isShowGAT = true;

        defaultProvinceName = "江苏";
        defaultCityName = "常州";
        defaultDistrict = "新北区";

        mWheelType = CityConfig.WheelType.PRO_CITY_DIS;

        setWheelType(mWheelType);

        mProCyclicCk.setChecked(true);
        mCityCyclicCk.setChecked(true);
        mAreaCyclicCk.setChecked(true);
        mGATCk.setChecked(true);

        mProEt.setText("" + defaultProvinceName);
        mCityEt.setText("" + defaultCityName);
        mAreaEt.setText("" + defaultDistrict);
        mProVisibleCountEt.setText("" + visibleItems);

        mHalfBgCk.setChecked(isShowBg);
        mProCyclicCk.setChecked(isProvinceCyclic);
        mAreaCyclicCk.setChecked(isDistrictCyclic);
        mCityCyclicCk.setChecked(isCityCyclic);
        mGATCk.setChecked(isShowGAT);

        setWheelType(mWheelType);

    }

    /**
     * @param wheelType
     */
    private void setWheelType(CityConfig.WheelType wheelType) {
        if (wheelType == CityConfig.WheelType.PRO) {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mOneTv.setTextColor(Color.parseColor("#ffffff"));
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));
        } else if (wheelType == CityConfig.WheelType.PRO_CITY) {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mOneTv.setTextColor(Color.parseColor("#333333"));
            mTwoTv.setTextColor(Color.parseColor("#ffffff"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));
        } else {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mOneTv.setTextColor(Color.parseColor("#333333"));
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    /**
     * 弹出选择器
     */
    private void wheel() {

        defaultProvinceName = mProEt.getText().toString();
        defaultCityName = mCityEt.getText().toString();
        defaultDistrict = mAreaEt.getText().toString();

        visibleItems = (Integer.parseInt(mProVisibleCountEt.getText().toString()));

        List<CustomCityData> dataList = prepareData();
        CustomCityPicker customCityPicker =new CustomCityPicker(this);
        CustomConfig cityConfig = new CustomConfig.Builder()
                .title("")
                .visibleItemsCount(dataList.size())
                .setCityData(dataList)
                .province("繁体中文")
                .provinceCyclic(false)
                .titleBackgroundRes(R.drawable.common_citypicker_titlestyle)
                .setCityWheelType(CustomConfig.WheelType.PRO)
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_city_name_tv)
                .build();

        customCityPicker.setCustomConfig(cityConfig);
        customCityPicker.showCityPicker();
    }
}
