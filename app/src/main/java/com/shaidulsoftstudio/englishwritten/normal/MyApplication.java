package com.shaidulsoftstudio.englishwritten.normal;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MyApplication {


    public static void addToFavoriteTextPara(Context context, String paraname) {
        long timestamp = System.currentTimeMillis();
        //setup data to add Fb to RDB of current user for favourite book
        HashMap<String, Object> favouriteMap = new HashMap<>();
        favouriteMap.put("paraname", paraname);
        favouriteMap.put("timestamp", timestamp);

        //save to RDB
        DatabaseReference dbrefer = FirebaseDatabase.getInstance().getReference("Paragraphs");
        dbrefer.child("paraname").child("Favorites").setValue(favouriteMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "added to your favourite list", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "failed to add favourite" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void removeFromFavorite(Context context, String paraname){
        DatabaseReference dbrefer_delete = FirebaseDatabase.getInstance().getReference("Paragraphs");
        dbrefer_delete.child("paraname").child("Favorites").removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Remove your favourite list", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "failed to remove favourite"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
