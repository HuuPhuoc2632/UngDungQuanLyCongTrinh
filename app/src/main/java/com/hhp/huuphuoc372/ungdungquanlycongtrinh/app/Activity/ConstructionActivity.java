package com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hhp.huuphuoc372.ungdungquanlycongtrinh.R;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Dao.ConstructionDao;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.adapter.ConstructionAdapter;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.model.Construction;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class ConstructionActivity extends AppCompatActivity implements View.OnClickListener, ConstructionAdapter.ConstructionListener {
    private EditText edtFind, edtName, edtAddress, edtDate, edtEmpl, edtStatus;
    private Button btnFind, btnAdd, btnAddNew, btnUpdate, btnCloseUpdate;
    private ImageView imvEdit;
    private ListView lvConstruction;
    private Spinner spinner;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private List<Construction> constructionList = new ArrayList<>();
    private ConstructionAdapter constructionAdapter;

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
        lvConstruction.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogConfirm(position);
                return false;
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        mappingSpiner();
        try {
            mappingConstructionList();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showDialogConfirm(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ConstructionActivity.this);
        builder.setMessage("Bạn có chắc muốn xóa công trình này chứ");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(new ConstructionDao(ConstructionActivity.this).deleteConstruction(constructionList.get(position).getMaCT())){
                    Toast.makeText(ConstructionActivity.this, "Đã xóa thành công", Toast.LENGTH_LONG).show();
                    constructionList.remove(position);
                    constructionAdapter.notifyDataSetChanged();
                    
                }
                else{
                    Toast.makeText(ConstructionActivity.this, "Đã xảy ra lỗi", Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Không làm gì cả
            }
        });
        builder.show();
    }

    private void mappingSpiner() {
        ArrayList<String> listSortOption = new ArrayList<String>();
        listSortOption.add("Mặc định(tên công trình)");
        listSortOption.add("Ngày khởi công");
        listSortOption.add("Số nhân sự(giảm dần)");
        listSortOption.add("Số nhân sự(tăng dần)");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listSortOption);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        sortIncreByName();
                        constructionAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        sortDecreaseByDate();
                        constructionAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        sortDecreaseByNhanSu();
                        constructionAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                        sortIncreaseByNhanSu();
                        constructionAdapter.notifyDataSetChanged();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void sortIncreaseByNhanSu(){
        Collections.sort(constructionList, new Comparator<Construction>() {
            @Override
            public int compare(Construction o1, Construction o2) {
                return o1.getSoLuongNS() - o2.getSoLuongNS();
            }
        });
    }
    public void sortDecreaseByNhanSu(){
        Collections.sort(constructionList, new Comparator<Construction>() {
            @Override
            public int compare(Construction o1, Construction o2) {
                return o2.getSoLuongNS() - o1.getSoLuongNS();
            }
        });
    }
    public void sortDecreaseByDate(){
        Collections.sort(constructionList, new Comparator<Construction>() {
            @Override
            public int compare(Construction o1, Construction o2) {
                return o2.getNgayKhoiCong().compareTo(o1.getNgayKhoiCong());
            }
        });
    }
    public void sortIncreByName(){
        Collections.sort(constructionList, new Comparator<Construction>() {
            @Override
            public int compare(Construction o1, Construction o2) {
                return o1.getTenCT().compareTo(o2.getTenCT());
            }
        });
    }



    private void mappingConstructionList() throws ParseException {
        constructionList = new ConstructionDao(this).getAllConstruction();
        constructionAdapter = new ConstructionAdapter(constructionList, this);
        lvConstruction.setAdapter(constructionAdapter);
        constructionAdapter.setConstructionListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                try {
                    showDialogAdd();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    @SuppressLint("SetTextI18n")
    public void showEditDialog(int position){
        Dialog dialog = new Dialog(ConstructionActivity.this);
        dialog.setContentView(R.layout.dialog_update_construction);
        edtName = dialog.findViewById(R.id.edtName);
        edtAddress = dialog.findViewById(R.id.edtAddressNew);
        edtDate = dialog.findViewById(R.id.editTextDate);
        edtEmpl = dialog.findViewById(R.id.edtTotalEmplUpdate);
        edtStatus = dialog.findViewById(R.id.edtStatus);
        btnUpdate = dialog.findViewById(R.id.btnUpdate);
        btnCloseUpdate = dialog.findViewById(R.id.btnCloseUpdate);
        Construction construction = constructionList.get(position);

        edtName.setText(construction.getTenCT());
        edtAddress.setText(construction.getDiaChi());
        edtDate.setText(format.format(construction.getNgayKhoiCong()));
        edtEmpl.setText(construction.getSoLuongNS()+"");
        edtStatus.setText(construction.getTrangThai());
        setEnableEdit(false);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnUpdate.getText().toString().equalsIgnoreCase("Cập nhật")){
                    setEnableEdit(true);
                    btnUpdate.setText("Xác nhận");
                }
                else if(btnUpdate.getText().toString().equalsIgnoreCase("Xác nhận")){
                    try {
                        construction.setTenCT(edtName.getText().toString());
                        construction.setDiaChi(edtAddress.getText().toString());
                        construction.setNgayKhoiCong(format.parse(String.valueOf(edtDate.getText())));
                        construction.setTrangThai(edtStatus.getText().toString());
                        construction.setSoLuongNS(Integer.parseInt(edtEmpl.getText()+""));
                        if(new ConstructionDao(ConstructionActivity.this).updateConstruction(construction)){
                            Toast.makeText(ConstructionActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ConstructionActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            }
        });
        btnCloseUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        
        dialog.show();
    }
    public void setEnableEdit(boolean b){
        edtName.setEnabled(b);
        edtAddress.setEnabled(b);
        edtDate.setEnabled(b);
        edtEmpl.setEnabled(b);
        edtStatus.setEnabled(b);
    }

    private void showDialogAdd() throws ParseException {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_construction);
        edtName = dialog.findViewById(R.id.edtName);
        edtAddress = dialog.findViewById(R.id.edtAddressNew);
        edtDate = dialog.findViewById(R.id.editTextDate);
        btnAddNew = dialog.findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (insertNewConstruction(edtName.getText().toString().trim(), edtAddress.getText().toString().trim(), edtDate.getText().toString().trim())){
                        Toast.makeText(ConstructionActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        mappingConstructionList();
                    }
                    else{
                        Toast.makeText(ConstructionActivity.this, "Thêm thất bại!!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private boolean insertNewConstruction(String name, String address, String date) throws ParseException {
        Construction construction = new Construction(
                null, name, address, "Chờ khởi công", format.parse(date), 0
        );
        return new ConstructionDao(this).insertConstruction(construction);

    }
    @Override
    public void onItemClick(View view, int position) {
        showEditDialog(position);

    }
}