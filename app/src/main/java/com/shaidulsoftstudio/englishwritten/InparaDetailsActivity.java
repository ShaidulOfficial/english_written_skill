package com.shaidulsoftstudio.englishwritten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shaidulsoftstudio.englishwritten.databinding.ActivityInparaDetailsBinding;

public class InparaDetailsActivity extends AppCompatActivity {

    ActivityInparaDetailsBinding inparaDetailsBinding;
    Intent intent;
    String paradetailSt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inparaDetailsBinding=ActivityInparaDetailsBinding.inflate(getLayoutInflater());
        setContentView(inparaDetailsBinding.getRoot());
        intent = getIntent();
        paradetailSt=intent.getStringExtra("inparadetails");
        inparaDetailsBinding.paragraphDetailsInpara.setText(paradetailSt);

        inparaDetailsBinding.backimvInpara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InparaDetailsActivity.this,
                        InparaActivity.class));
            }
        });

    }
}