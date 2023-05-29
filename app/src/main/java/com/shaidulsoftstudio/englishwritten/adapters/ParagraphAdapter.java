package com.shaidulsoftstudio.englishwritten.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shaidulsoftstudio.englishwritten.ItemClick;
import com.shaidulsoftstudio.englishwritten.R;
import com.shaidulsoftstudio.englishwritten.models.ParagraphModel;

import java.util.List;

public class ParagraphAdapter extends RecyclerView.Adapter<ParagraphAdapter.ParaViewholder> {

    Animation animation;
    Context context;
    List<ParagraphModel> modelList;
    ItemClick itemClick;
    boolean isMyfavorite = false;

    public ParagraphAdapter(Context context, List<ParagraphModel> modelList, ItemClick itemClick) {
        this.context = context;
        this.modelList = modelList;
        this.itemClick = itemClick;
    }

    public void setFilterList(List<ParagraphModel> filterList) {
        this.modelList = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ParaViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemparagraph, parent, false);

        return new ParaViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParaViewholder holder, int position) {

        ParagraphModel model = modelList.get(position);
        holder.paragraph_name.setText(model.getParaname());
        animation = AnimationUtils.loadAnimation(context,R.anim.animation1);
        holder.cardView.setAnimation(animation);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClickListener(model);
            }
        });
        checkfavourite(model, holder);

    }

    private void checkfavourite(ParagraphModel model, ParaViewholder holder) {
    }

//    private void checkfavourite(ParagraphModel model, ParaViewholder holder) {
//                DatabaseReference dbrefer2 = FirebaseDatabase.getInstance().getReference("Paragraphs");
//
//        dbrefer2.child("paraname").child("Favorites").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                isMyfavorite = snapshot.exists();
//                if (isMyfavorite) {
//
//                    holder.favicon.setCompoundDrawablesRelativeWithIntrinsicBounds(0,
//                            R.drawable.favorite_24, 0, 0);
//                    binding.textView32.setText(R.string.removefavo);
//
//                } else {
//                    binding.imageView3.setCompoundDrawablesRelativeWithIntrinsicBounds(0,
//                            R.drawable.ic_baseline_favorite_border_24, 0, 0);
//                    binding.textView32.setText(R.string.addfavo);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ParaViewholder extends RecyclerView.ViewHolder {

        TextView paragraph_name;
        CardView cardView;
        ImageView favicon;

        public ParaViewholder(@NonNull View itemView) {
            super(itemView);
            paragraph_name = itemView.findViewById(R.id.paragraph_name_id);
            cardView = itemView.findViewById(R.id.cardvId);
            favicon = itemView.findViewById(R.id.favourite_icon_id);
        }
    }
}
