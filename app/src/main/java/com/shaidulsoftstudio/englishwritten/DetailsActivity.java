package com.shaidulsoftstudio.englishwritten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shaidulsoftstudio.englishwritten.databinding.ActivityDetailsBinding;
import com.shaidulsoftstudio.englishwritten.models.ParagraphModel;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding detailsBinding;
    Intent intent;
    String paradetailSt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding=ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(detailsBinding.getRoot());


        intent = getIntent();
      paradetailSt=intent.getStringExtra("paradetails");
        detailsBinding.paragraphDescription.setText(paradetailSt);

        detailsBinding.backimv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(DetailsActivity.this,ParagraphnameActivity.class));
            }
        });
        detailsBinding.shareimv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                   // String shareMessage = "\n" + parast + "\n\n";
                   // shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n" + "download this ";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, paradetailSt);
                    startActivity(Intent.createChooser(shareIntent, "Choose One"));
                } catch (Exception e) {

                }
                }
        });
        detailsBinding.copyimv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipbord = (ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("test", paradetailSt);
                clipbord.setPrimaryClip(clip);
                showtoast();
            }
        });


}
    private void showtoast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast));
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}
