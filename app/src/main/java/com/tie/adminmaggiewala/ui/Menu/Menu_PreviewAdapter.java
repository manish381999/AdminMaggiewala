package com.tie.adminmaggiewala.ui.Menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tie.adminmaggiewala.R;
import com.tie.adminmaggiewala.databinding.PreviewItemLayoutBinding;

import java.util.List;

public class Menu_PreviewAdapter extends RecyclerView.Adapter<Menu_PreviewAdapter.MenuViewHolder>{
Context context;
List<Menu_List_Model.LightDetails> list;

    public Menu_PreviewAdapter(Context context, List<Menu_List_Model.LightDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.preview_item_layout,parent,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
Menu_List_Model.LightDetails details=list.get(position);


        holder.binding.itemName.setText(details.getItem_name());

        holder.binding.itemPrice.setText("\u20B9 "+ details.getItem_price());

Glide.with(context).load("https://maggiewala.000webhostapp.com/Images/"+details.getItem_img()).into(holder.binding.itemImg);





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder{

        PreviewItemLayoutBinding binding;
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=PreviewItemLayoutBinding.bind(itemView);
        }
    }
}
