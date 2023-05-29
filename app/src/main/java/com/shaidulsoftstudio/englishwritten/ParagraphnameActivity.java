package com.shaidulsoftstudio.englishwritten;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shaidulsoftstudio.englishwritten.adapters.ParagraphAdapter;
import com.shaidulsoftstudio.englishwritten.databinding.ActivityMainBinding;
import com.shaidulsoftstudio.englishwritten.databinding.ActivityParagraphnameBinding;
import com.shaidulsoftstudio.englishwritten.models.ParagraphModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParagraphnameActivity extends AppCompatActivity implements ItemClick {
    ParagraphAdapter adapter;
    DatabaseReference databaseReference;
    List<ParagraphModel> modelList;
    ActivityParagraphnameBinding paragraphnameBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paragraphnameBinding = ActivityParagraphnameBinding.inflate(getLayoutInflater());
        setContentView(paragraphnameBinding.getRoot());

        paragraphnameBinding.backimgv.setOnClickListener(new View.OnClickListener() {
                                                             @Override
                                                             public void onClick(View v) {
                                                                 startActivity(new Intent(ParagraphnameActivity.this,
                                                                         MainActivity.class));
                                                             }
                                                         }
        );
        paragraphnameBinding.paraSearchId.clearFocus();
        paragraphnameBinding.paraSearchId.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("Paragraphs");
        modelList = new ArrayList<>();
        adapter = new ParagraphAdapter(ParagraphnameActivity.this, modelList, this);

        paragraphnameBinding.paraRCV.setAdapter(adapter);
        paragraphnameBinding.paraRCV.setHasFixedSize(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ParagraphModel model = ds.getValue(ParagraphModel.class);
                    modelList.add(model);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void filterList(String text) {
        List<ParagraphModel> filterlist = new ArrayList<>();
        for (ParagraphModel model : modelList) {
            if (model.getParaname().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(model);
            }
        }
        if (filterlist.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilterList(filterlist);
        }
    }

    @Override
    public void itemClickListener(ParagraphModel paragraphModel) {
        Intent intent = new Intent(ParagraphnameActivity.this, DetailsActivity.class);
        intent.putExtra("paradetails", paragraphModel.getPdescription());
        startActivity(intent);

    }
}