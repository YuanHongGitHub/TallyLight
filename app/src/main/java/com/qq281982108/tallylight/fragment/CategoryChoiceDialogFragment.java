package com.qq281982108.tallylight.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.adapter.ClassifyMainAdapter;
import com.qq281982108.tallylight.adapter.ClassifyMoreAdapter;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 19:47
 * 类名：TimeChoiceDialogFragment
 * 修改备注：
 */
public class CategoryChoiceDialogFragment extends DialogFragment implements View.OnClickListener {
    ClassifyMainAdapter mainAdapter;
    ClassifyMoreAdapter moreAdapter;
    private ListView mainlist;
    private ListView morelist;
    private String[] ClassifyMain;
    private String[][] ClassifyMore;
    private int get_id;
    private int a = -1;
    private int main_postion = -1;
    private int list_id;
    private OnCategorySelectedListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果setCancelable()中参数为true，若点击dialog覆盖不到的activity的空白或者按返回键，
        //则进行cancel，状态检测依次onCancel()和onDismiss()。如参数为false，则按空白处或返回键无反应。缺省为true
        setCancelable(true);
        //可以设置dialog的显示风格
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.doublelist_layout, null);
        initdata();
        init(rootView);

        return rootView;
    }

    private void initdata() {
//        Bundle bundle = new Bundle();
//        bundle = this.getIntent().getExtras();
        get_id = 0;
        ClassifyMain = getResources().getStringArray(R.array.main);
        ClassifyMore = new String[][]{
                getResources().getStringArray(R.array.more_0),
                getResources().getStringArray(R.array.more_1),
                getResources().getStringArray(R.array.more_2),
                getResources().getStringArray(R.array.more_3),
                getResources().getStringArray(R.array.more_4),
                getResources().getStringArray(R.array.more_5),
                getResources().getStringArray(R.array.more_6),
                getResources().getStringArray(R.array.more_7),
                getResources().getStringArray(R.array.more_8),
        };
    }

    private void init(View view) {
        mainlist = (ListView) view.findViewById(R.id.classify_mainlist);
        morelist = (ListView) view.findViewById(R.id.classify_morelist);
        mainAdapter = new ClassifyMainAdapter(getActivity(), ClassifyMain, get_id);
        mainAdapter.setSelectItem(get_id);
        mainlist.setAdapter(mainAdapter);
        mainlist.setSelection(get_id - 1);
        mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                initAdapter(ClassifyMore[position]);
                mainAdapter.setSelectItem(position);
                mainAdapter.notifyDataSetChanged();
                a = position * 100;
                main_postion = position;
            }
        });
        mainlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        initAdapter(ClassifyMore[get_id]);

        morelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                moreAdapter.setSelectItem(position);
                moreAdapter.notifyDataSetChanged();
                if (a == -1) {
                    a = get_id * 100;
                }
                if (main_postion == -1) {
                    main_postion = get_id;
                }
                list_id = a + position;
                mListener.onSelect(ClassifyMore[main_postion][position]);
                dismiss();
//                Bundle bundle = new Bundle();
//                bundle.putInt("list_id", list_id);
//                bundle.putString("list_item", ClassifyMore[main_postion][position]);
//                Intent intent = new Intent(DoubleListActivity.this,ResultActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
            }
        });
    }

    private void initAdapter(String[] array) {
        moreAdapter = new ClassifyMoreAdapter(getActivity(), array);
        morelist.setAdapter(moreAdapter);
        moreAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, dm.heightPixels / 2);
    }

    public void setOnCategorySelectedListener(OnCategorySelectedListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

    public interface OnCategorySelectedListener {
        void onSelect(String s);
    }

}
