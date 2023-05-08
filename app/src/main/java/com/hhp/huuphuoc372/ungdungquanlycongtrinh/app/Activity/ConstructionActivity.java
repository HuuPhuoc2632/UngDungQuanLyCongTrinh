package com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.hhp.huuphuoc372.ungdungquanlycongtrinh.R;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.adapter.ConstructionAdapter;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.model.Construction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConstructionActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtFind;
    private Button btnFind, btnAdd;
    private ListView lvConstruction;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);
        mapping();
        btnAdd.setOnClickListener(this);
        edtFind.addTextChangedListener(textWatcher);

    }
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Được gọi khi kí tự đang được nhập vào EditText trước khi thay đổi
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println(edtFind.getText());
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Được gọi sau khi kí tự trong EditText đã bị thay đổi
        }
    };

    private void mapping() {
        edtFind = (EditText) findViewById(R.id.edtFind);
//        btnFind = (Button) findViewById(R.id.btnFind);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        lvConstruction = (ListView) findViewById(R.id.lvConstruction);
        spinner = (Spinner) findViewById(R.id.spinner);
        mappingSpiner();
        try {
            mappingConstructionList();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void mappingSpiner() {
        ArrayList<String> listSortOption = new ArrayList<String>();
        listSortOption.add("Mặc định");
        listSortOption.add("Ngày khởi công");
        listSortOption.add("Số nhân sự(giảm dần)");
        listSortOption.add("Số nhân sự(tăng dần)");
        listSortOption.add("Ngày khởi công");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listSortOption);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }

    private void mappingConstructionList() throws ParseException {
        List<Construction> constructionList = new ArrayList<>();
        Date dateStart;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dateStart = format.parse("30/04/2023");
        Construction construction = new Construction(
                null,
                "Nhà ở",
                "HoChiMinh City",
                "Đang thi công",
                dateStart,
                25
        );
        Construction construction2 = new Construction(
                null,
                "Công viên",
                "HoChiMinh City",
                "Đang thi công",
                dateStart,
                25
        );
        Construction construction3 = new Construction(
                null,
                "Hồ bơi",
                "HoChiMinh City",
                "Hoàn thành",
                dateStart,
                25
        );
        constructionList.add(construction);
        constructionList.add(construction2);
        constructionList.add(construction3);
        constructionList.add(construction);
        ConstructionAdapter constructionAdapter = new ConstructionAdapter(constructionList, this);
        lvConstruction.setAdapter(constructionAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                showDialogAdd();
                break;
            
        }
    }

    private void showDialogAdd() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_construction);
        dialog.show();
    }
}