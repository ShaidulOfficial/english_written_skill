package com.shaidulsoftstudio.englishwritten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Config;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.shaidulsoftstudio.englishwritten.R;
import com.shaidulsoftstudio.englishwritten.databinding.ActivityDeveloperBinding;


public class DeveloperActivity extends AppCompatActivity {

    ActivityDeveloperBinding developerBinding;
    Animation animation;
    String number = "+8801787780147";
    String greet_text = "Hey! \nHow Are You?";
    String buy_text = "Hey! \nI want to buy your Apps\nHow can I buy your Apps? \nPlease help me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        developerBinding = ActivityDeveloperBinding.inflate(getLayoutInflater());
        setContentView(developerBinding.getRoot());

        animation = AnimationUtils.loadAnimation(DeveloperActivity.this, R.anim.animation1);
        developerBinding.imageviewCall.setAnimation(animation);
        developerBinding.imageviewEmail.setAnimation(animation);
        developerBinding.imageviewMessage.setAnimation(animation);
        developerBinding.imageviewWhatsapp.setAnimation(animation);


        developerBinding.backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this,
                        MainActivity.class));
            }
        });

        developerBinding.imageviewWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=number.replace("+","").replace(" ","");
                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.putExtra("jid", number + "@s.whatsapp.net");
                sendIntent.putExtra(Intent.EXTRA_TEXT, greet_text);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });

//        ************************************************************************************
//        ************** jkono messege er jonno************************


//        developerBinding.imageviewWhatsapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_TEXT,"");
//                intent.setType("text/plain");
//                intent.setPackage("com.whatsapp");
//                startActivity(intent);
//
//            }
//        });
        developerBinding.buyAppLinlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=number.replace("+","").replace(" ","");
                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.putExtra("jid", number + "@s.whatsapp.net");
                sendIntent.putExtra(Intent.EXTRA_TEXT, buy_text);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        developerBinding.imageviewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = "+8801860696999";
                Uri uri = Uri.parse("tel:" + mobile);
                Intent callintent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(callintent);
            }
        });

        developerBinding.imageviewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = "+8801860696999";
                Uri uri = Uri.parse("sms:" + mobile);
                Intent messageIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(messageIntent);
            }
        });
        developerBinding.imageviewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                mailIntent.putExtra(Intent.EXTRA_EMAIL, "");
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                mailIntent.putExtra(Intent.EXTRA_TEXT, "");
                mailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(mailIntent, "Choose an Option"));
            }
        });
    }



}