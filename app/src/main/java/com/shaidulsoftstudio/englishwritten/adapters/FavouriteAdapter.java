package com.shaidulsoftstudio.englishwritten.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shaidulsoftstudio.englishwritten.R;
import com.shaidulsoftstudio.englishwritten.databinding.FavouriteItemBinding;
import com.shaidulsoftstudio.englishwritten.models.ParagraphModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    Context context;
    List<ParagraphModel>paragraphModel;


    public FavouriteAdapter(Context context, List<ParagraphModel> paragraphModel) {
        this.context = context;
        this.paragraphModel = paragraphModel;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favourite_item,
                parent, false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {

        ParagraphModel model=paragraphModel.get(position);
        loadfavouriteDetails(model, holder);

    }

    private void loadfavouriteDetails(ParagraphModel model, FavouriteViewHolder holder) {

        DatabaseReference dbref1 = FirebaseDatabase.getInstance().getReference("Paragraphs");
        dbref1.child("paraname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String pname =  snapshot.child("paraname").getValue().toString();
                String pdetails =  snapshot.child("pdescription").getValue().toString();

                model.setParaname(pname);
                model.setPdescription(pdetails);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return paragraphModel.size();
    }


    public class FavouriteViewHolder extends RecyclerView.ViewHolder {

        private final FavouriteItemBinding itemBinding;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = FavouriteItemBinding.bind(itemView);
        }

    }
}
