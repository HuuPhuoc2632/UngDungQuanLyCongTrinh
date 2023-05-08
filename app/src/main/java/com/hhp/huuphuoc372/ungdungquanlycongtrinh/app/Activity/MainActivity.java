package com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hhp.huuphuoc372.ungdungquanlycongtrinh.R;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.adapter.OptionAdapter;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.model.Option;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gvOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        gvOption = (GridView) findViewById(R.id.gvOption);
        showOption();
        gvOption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choseOption(position);
            }
        });

    }

    private void choseOption(int position) {
        switch (position){
            case 0:
                Intent intent = new Intent(MainActivity.this, ConstructionActivity.class);
                startActivity(intent);
                break;


        }

    }

    private void showOption() {

        List<Option> optionList = new ArrayList<>();
        Option option1 = new Option(R.drawable.construction, "Công trình");
        Option option2 = new Option(R.drawable.business_people, "Nhân sự");
        Option option3 = new Option(R.drawable.tasks, "Phân công");
        Option option4 = new Option(R.drawable.user, "Cá nhân");
        Option option5 = new Option(R.drawable.trend, "Thống kê");
        Option option6 = new Option(R.drawable.logout, "Đăng xuất");
        optionList.add(option1);
        optionList.add(option2);
        optionList.add(option3);
        optionList.add(option4);
        optionList.add(option5);
        optionList.add(option6);
        OptionAdapter optionAdapter = new OptionAdapter(optionList, this);
        gvOption.setAdapter(optionAdapter);
    }
}