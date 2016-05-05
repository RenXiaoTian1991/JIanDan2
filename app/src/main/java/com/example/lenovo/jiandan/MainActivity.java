package com.example.lenovo.jiandan;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jiandan.Bean.FreshNews;
import com.example.lenovo.jiandan.adapter.RecycleAdapter;
import com.example.lenovo.jiandan.parser.MyJone;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int[] imgs = {R.drawable.tab_assistant,R.drawable.tab_center,
            R.drawable.tab_contest,R.drawable.tab_counter};
    private String[] tags = {"首页","运动","订单","个人"};
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        SampleFragmentPagerAdapter pagerAdapter =  new SampleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        /*
        tablayout的tab初始化
         */
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(pagerAdapter.getTabView(i));
            }
        }
        viewPager.setCurrentItem(1);

    }
    public void init(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }


    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 4;
        private String tabTitles[] = new String[]{"首页","运动","订单","个人"};
        private Context context;


        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) v.findViewById(R.id.textview);
            tv.setText(tabTitles[position]);
            ImageView img = (ImageView) v.findViewById(R.id.imageview);
            img.setImageResource(imgs[position]);
            return v;
        }

        public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            if(position==1){
                return TwoFragment.newInstance(position);
            }else if(position== 2){
                return  ThreeFragment.newInstance(position);
            }
            return PageFragment.newInstance(position + 1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            ViewGroup parent = (ViewGroup) v.getParent();
//            if (parent != null) {
//                parent.removeAllViews();
//            }
//            container.addView(v);

            return super.instantiateItem(container, position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

            public static class PageFragment extends Fragment {
                public static final String ARG_PAGE = "ARG_PAGE";

                private int mPage;
                private View view;

                public static PageFragment newInstance(int page) {
                    Bundle args = new Bundle();
                    args.putInt(ARG_PAGE, page);
                    PageFragment fragment = new PageFragment();
                    fragment.setArguments(args);
                    return fragment;
                }

                @Override
                public void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    mPage = getArguments().getInt(ARG_PAGE);
                }

                @Override
                public void onHiddenChanged(boolean hidden) {

                    super.onHiddenChanged(hidden);
                    if (hidden) {
                        getActivity().getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.toolbar);
                    }
                }

                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                         Bundle savedInstanceState) {
                    if (view == null) {
                        view = inflater.inflate(R.layout.fragment_page, container, false);
                    }

                    // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
                    ViewGroup parent = (ViewGroup) view.getParent();
                    if (parent != null) {
                        parent.removeView(view);
                    }
                    TextView textView = (TextView) view.findViewById(R.id.fram_txt);
                    textView.setText("Fragment #" + mPage);
                    Ronud round = (Ronud) view.findViewById(R.id.round);
                    round.startAnim();
                    return view;
                }

            }

    public static class TwoFragment extends Fragment {
        public static final String ARG_PAGE = "ARG_PAGE";

        private int mPage;
        private View view;

        public static TwoFragment newInstance(int page) {
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, page);
            TwoFragment fragment = new TwoFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPage = getArguments().getInt(ARG_PAGE);
        }

        @Override
        public void onHiddenChanged(boolean hidden) {

            super.onHiddenChanged(hidden);
            if (hidden) {
                getActivity().getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.toolbar);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if (view == null) {
                view = inflater.inflate(R.layout.fragment_two, container, false);
            }

            // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            Loading load = (Loading) view.findViewById(R.id.loading);
            load.startAnim();
            return view;
        }

    }

    public static class ThreeFragment extends Fragment {
        public static final String ARG_PAGE = "ARG_PAGE";

        private int mPage;
        private RecyclerView recyclerView;
        private SwipeRefreshLayout refreshLayout;
        private RecycleAdapter adapter= null;
        private ArrayList<FreshNews> mDatas;
        private View view;
        private int page=0;
        private LinearLayoutManager linearLayoutManager;

        public static ThreeFragment newInstance(int page) {
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, page);
            ThreeFragment fragment = new ThreeFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPage = getArguments().getInt(ARG_PAGE);
            mDatas = new ArrayList<FreshNews>();
            linearLayoutManager = new LinearLayoutManager(getActivity());
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            OkHttpUtils.get().url(FreshNews.URL_FRESH_NEWS+page).build().execute(new MyJone() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(ArrayList<FreshNews> response) {
                    if (response != null) {
                        if (adapter == null) {
                            //Log.i("abc", response.toString());
                            Log.i("abc",response.get(3).toString());
                            mDatas.addAll(new ArrayList<FreshNews>(response));
                            adapter = new RecycleAdapter(mDatas, getActivity());
                            recyclerView.setAdapter(adapter);
                        } else {
                            mDatas.addAll(response);
                            adapter.notifyDataSetChanged();
                        }
                    }

                }
            });
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if (view == null) {
                view = inflater.inflate(R.layout.fragment_three, container, false);
            }

            // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }

            recyclerView  = (RecyclerView) view.findViewById(R.id.recyclerView);
            refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
            recyclerView.setLayoutManager(linearLayoutManager);
            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpUtils.get().url(FreshNews.URL_FRESH_NEWS+0).build().execute(new MyJone() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    refreshLayout.setRefreshing(false);
                                }

                                @Override
                                public void onResponse(ArrayList<FreshNews> response) {
                                    if (response != null) {
                                        if (adapter == null) {
                                            return;
                                        } else {
                                            mDatas.addAll(0,response);
                                            adapter.notifyDataSetChanged();
                                            Toast.makeText(getActivity(),"成功更新"+response.size()+"条数据",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    refreshLayout.setRefreshing(false);
                                }
                            });
                        }
                    }, 500);
                }
            });

            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                int lastVisibleItem;
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                        page++;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                OkHttpUtils.get().url(FreshNews.URL_FRESH_NEWS+page).build().execute(new MyJone() {
                                    @Override
                                    public void onError(Request request, Exception e) {

                                    }

                                    @Override
                                    public void onResponse(ArrayList<FreshNews> response) {
                                        if (response != null) {
                                            if (adapter == null) {
                                                return;
                                            } else {
                                                adapter.setData(response);
                                            }
                                        }

                                    }
                                });
                            }
                        }, 500);
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                }
            });
            return view;
        }

    }


}



