package com.tie.adminmaggiewala.ui.Menu;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.tie.adminmaggiewala.ApiClient.ApiClient;
import com.tie.adminmaggiewala.ApiClient.ApiInterface;
import com.tie.adminmaggiewala.R;
import com.tie.adminmaggiewala.databinding.ActivityPreviewBinding;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PreviewActivity extends AppCompatActivity {
ActivityPreviewBinding binding;
Menu_PreviewAdapter menu_previewAdapter;
ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofit= ApiClient.getclient();
       apiInterface=retrofit.create(ApiInterface.class);

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

       getMenu();

    }



    @SuppressLint("NotifyDataSetChanged")
    private void setRecyclerView(List<Menu_List_Model.LightDetails> list){


        binding.rvPreview.setHasFixedSize(true);
        menu_previewAdapter=new Menu_PreviewAdapter(this,list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.rvPreview.setLayoutManager(layoutManager);
        binding.rvPreview.setAdapter(menu_previewAdapter);
        menu_previewAdapter.notifyDataSetChanged();
    }


    private void getMenu() {
        apiInterface.getMenuList().enqueue(new Callback<Menu_List_Model>() {
            @Override
            public void onResponse(Call<Menu_List_Model> call, Response<Menu_List_Model> response) {
                if (response!=null){
                    Menu_List_Model menu_list_model= response.body();
                    if (menu_list_model.getStatus().equals("1")){
                            setRecyclerView(menu_list_model.getData());
                    }else {
                        Toast.makeText(getApplicationContext(), menu_list_model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Menu_List_Model> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}