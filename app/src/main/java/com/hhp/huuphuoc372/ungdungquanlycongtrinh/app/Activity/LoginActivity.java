package com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hhp.huuphuoc372.ungdungquanlycongtrinh.R;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Dao.AccountDao;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.model.Account;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSubmit;
    private EditText edtUser, edtPassword;
    private TextView tvSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tvSubtitle = (TextView) findViewById(R.id.tvSubTitle);
        btnSubmit.setOnClickListener(this);
        edtUser.setText("huuphuoc372");
        edtPassword.setText("12345");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                if (checkAccount(edtUser.getText().toString())) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private boolean checkAccount(String user) {
        AccountDao accountDao = new AccountDao(LoginActivity.this);
        Account account = accountDao.getAccountByUser(user);
        if (account == null) {
            tvSubtitle.setText("Thông tin đăng nhập không chính xác");
            tvSubtitle.setTextColor(getResources().getColor(R.color.red_color));
            return false;
        } else if (!account.getPassword().equalsIgnoreCase(edtPassword.getText().toString())) {
            tvSubtitle.setText("Thông tin đăng nhập không chính xác");
            tvSubtitle.setTextColor(getResources().getColor(R.color.red_color));
            return false;
        } else if (account.getPassword().equalsIgnoreCase(edtPassword.getText().toString())) {
            tvSubtitle.setText("Đăng nhập thành công!");
            tvSubtitle.setTextColor(getResources().getColor(R.color.success_color));
            return true;
        }
        return false;
    }
}