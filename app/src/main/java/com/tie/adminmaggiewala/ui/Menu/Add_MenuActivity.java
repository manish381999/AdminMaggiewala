package com.tie.adminmaggiewala.ui.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.tie.adminmaggiewala.ApiClient.ApiClient;
import com.tie.adminmaggiewala.ApiClient.ApiInterface;
import com.tie.adminmaggiewala.R;
import com.tie.adminmaggiewala.RealPathUtil;
import com.tie.adminmaggiewala.databinding.ActivityAddMenuBinding;

import java.io.File;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Add_MenuActivity extends AppCompatActivity {
ActivityAddMenuBinding binding;

ApiInterface apiInterface;

public String name,price,img,description,Ingredient;

Uri uri;

private final int REQ=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofit= ApiClient.getclient();
        apiInterface=retrofit.create(ApiInterface.class);

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        openGallery();

        binding.addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });

    }

    private void checkValidation() {
        name=binding.itemName.getText().toString();
        price=binding.itemPrice.getText().toString();
        description=binding.shortDescription.getText().toString();
        Ingredient=binding.ingredient.getText().toString();

        if (name.isEmpty()){
            binding.itemName.setError("Please enter match name");
            binding.itemName.requestFocus();
        }else if (price.isEmpty()){
            binding.itemPrice.setError("Please enter match price");
            binding.itemPrice.requestFocus();
        }else if (description.isEmpty()){
            binding.shortDescription.setError("Please enter match description");
            binding.shortDescription.requestFocus();
        }else if (Ingredient.isEmpty()){
            binding.ingredient.setError("Please enter match ingredient");
            binding.ingredient.requestFocus();
        }else {
            callInsertApi(name,price,description,Ingredient);
        }
    }

    private void callInsertApi(String name, String price, String description, String Ingredient) {

        File file=new File(img);
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part item_img=MultipartBody.Part.createFormData("item_img",file.getName(),requestBody);

        RequestBody item_name=RequestBody.create(MediaType.parse("multipart/form-data"),name);
        RequestBody item_price=RequestBody.create(MediaType.parse("multipart/form-data"),price);
        RequestBody short_description=RequestBody.create(MediaType.parse("multipart/form-data"),description);
        RequestBody ingredient=RequestBody.create(MediaType.parse("multipart/form-data"),Ingredient);

        apiInterface.insertMenuItem(item_name,item_price,item_img,short_description,ingredient).enqueue(new Callback<Add_Menu_Model>() {
            @Override
            public void onResponse(Call<Add_Menu_Model> call, Response<Add_Menu_Model> response) {
                if (response!=null){
                    Add_Menu_Model add_menu_model=response.body();
                    if (response.body().getStatus().equals("1")){
                        Toast.makeText(Add_MenuActivity.this, add_menu_model.getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Add_MenuActivity.this, add_menu_model.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<Add_Menu_Model> call, Throwable t) {
                Toast.makeText(Add_MenuActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void openGallery(){
        binding.addItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    Intent pickImgA=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickImgA,REQ);
                }else {
                    ActivityCompat.requestPermissions(Add_MenuActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ && resultCode==RESULT_OK && data !=null){
            uri =data.getData();

            Context context=Add_MenuActivity.this;
            img= RealPathUtil.getRealPath(context,uri);
         Bitmap   bitmap = BitmapFactory.decodeFile(img);

            binding.itemImage.setImageBitmap(bitmap);

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}