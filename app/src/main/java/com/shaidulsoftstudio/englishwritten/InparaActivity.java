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
import com.shaidulsoftstudio.englishwritten.adapters.InstructionalparaAdapter;
import com.shaidulsoftstudio.englishwritten.adapters.ParagraphAdapter;
import com.shaidulsoftstudio.englishwritten.databinding.ActivityInparaBinding;
import com.shaidulsoftstudio.englishwritten.databinding.ActivityParagraphnameBinding;
import com.shaidulsoftstudio.englishwritten.models.ParagraphModel;

import java.util.ArrayList;
import java.util.List;

public class InparaActivity extends AppCompatActivity implements ItemClick{
    InstructionalparaAdapter adapter_inpara;
    DatabaseReference databaseReference_inpara;
    List<ParagraphModel> modelList_inpara;
   ActivityInparaBinding inparaBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inparaBinding=ActivityInparaBinding.inflate(getLayoutInflater());
        setContentView(inparaBinding.getRoot());

        inparaBinding.backimgvInpara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InparaActivity.this,
                        MainActivity.class));
            }
        });
        inparaBinding.paraSearchIdInpara.clearFocus();
        inparaBinding.paraSearchIdInpara.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        databaseReference_inpara = FirebaseDatabase.getInstance().getReference("InstructionalParagraph");
        modelList_inpara = new ArrayList<>();
        adapter_inpara = new InstructionalparaAdapter(InparaActivity.this, modelList_inpara, this);

        inparaBinding.paraRCVInpara.setAdapter(adapter_inpara);
        inparaBinding.paraRCVInpara.setHasFixedSize(true);

        databaseReference_inpara.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ParagraphModel model = ds.getValue(ParagraphModel.class);
                    modelList_inpara.add(model);

                }
                adapter_inpara.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void filterList(String paratext) {
        List<ParagraphModel> filterlist_inpara = new ArrayList<>();
        for (ParagraphModel model : modelList_inpara) {
            if (model.getParatitle().toLowerCase().contains(paratext.toLowerCase())) {
                filterlist_inpara.add(model);
            }
        }
        if (filterlist_inpara.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            adapter_inpara.setFilterListInpara(filterlist_inpara);
        }
    }

    @Override
    public void itemClickListener(ParagraphModel paragraphModel) {
        Intent intent = new Intent(InparaActivity.this, InparaDetailsActivity.class);
        intent.putExtra("inparadetails", paragraphModel.getParadetails());
        startActivity(intent);
    }
}