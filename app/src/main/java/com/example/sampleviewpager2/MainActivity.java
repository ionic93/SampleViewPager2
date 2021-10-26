package com.example.sampleviewpager2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    Button btnToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.pager);
        btnToggle = findViewById(R.id.btn);

        ArrayList<DataPage> list = new ArrayList<>();
        list.add(new DataPage(android.R.color.black,"1 Page"));
        list.add(new DataPage(android.R.color.holo_red_light, "2 Page"));
        list.add(new DataPage(android.R.color.holo_green_dark, "3 Page"));
        list.add(new DataPage(android.R.color.holo_orange_dark, "4 Page"));
        list.add(new DataPage(android.R.color.holo_blue_light, "5 Page"));
        list.add(new DataPage(android.R.color.holo_blue_bright, "6 Page"));

        viewPager2.setAdapter(new ViewPagerAdapter(list));

        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager2.getOrientation() == ViewPager2.ORIENTATION_VERTICAL) {
                    btnToggle.setText("가로로 슬라이드");
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                }else {
                    btnToggle.setText("세로로 슬라이드");
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                }
            }
        });
    }
    class ViewPagerAdapter extends RecyclerView.Adapter<ViewHolderPage> {
        private ArrayList<DataPage> listData;
        ViewPagerAdapter(ArrayList<DataPage> data) {
            this.listData = data;
        }

        @Override
        public ViewHolderPage onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager, parent, false);
            return new ViewHolderPage(view);
        }

        @Override
        public void onBindViewHolder(ViewHolderPage holder, int position) {
            if(holder instanceof ViewHolderPage){
                ViewHolderPage viewHolder = (ViewHolderPage) holder;
                viewHolder.onBind(listData.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }
    }
    class ViewHolderPage extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private RelativeLayout rl_layout;
        DataPage data;
        ViewHolderPage(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            rl_layout = itemView.findViewById(R.id.rl_layout);
        }

        public void onBind(DataPage data){
            this.data = data;
            tv_title.setText(data.getTitle());
            rl_layout.setBackgroundResource(data.getColor());
        }
    }
}