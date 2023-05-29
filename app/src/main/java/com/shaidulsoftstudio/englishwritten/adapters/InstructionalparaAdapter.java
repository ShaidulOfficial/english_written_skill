package com.shaidulsoftstudio.englishwritten.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shaidulsoftstudio.englishwritten.ItemClick;
import com.shaidulsoftstudio.englishwritten.R;
import com.shaidulsoftstudio.englishwritten.models.ParagraphModel;

import java.util.List;

public class InstructionalparaAdapter extends RecyclerView.Adapter<InstructionalparaAdapter.ViewHolderInpara> {

    Animation animation_inpara;
    Context context;
    List<ParagraphModel> inpara_modelList;
    ItemClick itemClickInpara;

    public InstructionalparaAdapter(Context context, List<ParagraphModel> inpara_modelList, ItemClick itemClickInpara) {
        this.context = context;
        this.inpara_modelList = inpara_modelList;
        this.itemClickInpara = itemClickInpara;
    }

    public void setFilterListInpara(List<ParagraphModel> filterList) {
        this.inpara_modelList = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderInpara onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteminpara, parent
                , false);

        return new ViewHolderInpara(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInpara holder, int position) {
        ParagraphModel model = inpara_modelList.get(position);
        holder.paragraph_name.setText(model.getParatitle());
        animation_inpara = AnimationUtils.loadAnimation(context, R.anim.animation1);
        holder.cardView.setAnimation(animation_inpara);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickInpara.itemClickListener(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inpara_modelList.size();
    }

    public class ViewHolderInpara extends RecyclerView.ViewHolder {
        TextView paragraph_name;
        CardView cardView;

        public ViewHolderInpara(@NonNull View itemView) {
            super(itemView);
            paragraph_name = itemView.findViewById(R.id.paragraph_name_id_inpara);
            cardView = itemView.findViewById(R.id.cardvId_inpara);
        }
    }
}
