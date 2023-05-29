package com.shaidulsoftstudio.englishwritten;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.shaidulsoftstudio.englishwritten.adapters.FavouriteAdapter;
import com.shaidulsoftstudio.englishwritten.databinding.ActivityFavouriteBinding;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    ActivityFavouriteBinding favouriteBinding;
    private FirebaseFirestore firestore;
    private final List<FavouriteModel> itemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favouriteBinding=ActivityFavouriteBinding.inflate(getLayoutInflater());
        setContentView(favouriteBinding.getRoot());

        firestore = FirebaseFirestore.getInstance();

        favouriteBinding.backimgvFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavouriteActivity.this,MainActivity.class));
            }
        });
        initView();

    }

    private void initView() {
        favouriteBinding.rcvFavList.setHasFixedSize(true);
        loadFavList();
    }

    private void loadFavList() {
        firestore.collection("items")
                .whereEqualTo("checkFavorite", true)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        itemList.clear();
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                            FavouriteModel model = snapshot.toObject(FavouriteModel.class);
                            if (model != null) {
                                itemList.add(model);
                                FavouriteAdapter adapter = new FavouriteAdapter(FavouriteActivity.this,firestore,itemList, true);
                                adapter.OnRefreshListListener(new FavouriteAdapter.RefreshList() {
                                    @Override
                                    public void RefreshData() {
                                        loadFavList();
                                    }
                                });
                                favouriteBinding.rcvFavList.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}