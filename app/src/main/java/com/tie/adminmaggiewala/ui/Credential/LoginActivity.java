package com.tie.adminmaggiewala.ui.Credential;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tie.adminmaggiewala.ApiClient.ApiClient;
import com.tie.adminmaggiewala.ApiClient.ApiInterface;
import com.tie.adminmaggiewala.MainActivity;
import com.tie.adminmaggiewala.R;
import com.tie.adminmaggiewala.SessionManagement;
import com.tie.adminmaggiewala.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding binding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Retrofit retrofit= ApiClient.getclient();
        apiInterface=retrofit.create(ApiInterface.class);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailData=  binding.loginEmail.getText().toString();
                String passwordData=  binding.loginPassword.getText().toString();
                checkValidation(emailData,passwordData);
            }
        });
    }

    private void checkValidation(String emailData, String passwordData) {

        if (emailData!=null && passwordData!=null){
            callApi(emailData,passwordData);
        }else {
            Toast.makeText(this, "Please Enter Data", Toast.LENGTH_SHORT).show();
        }
    }
    private void callApi(String email, String password){
        apiInterface.logincall(email,password).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response!=null){
                    LoginModel loginModel=response.body();
                    if (loginModel.getStatus().equals("1")){
                        User user = new User(email,password);
                        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                        sessionManagement.saveSession(user);
                        Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else {

                        Toast.makeText(getApplicationContext(), loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        String userEmail = sessionManagement.getSessionEmail();
        String userPassword = sessionManagement.getSessionPassword();

        if(userEmail != null && userPassword != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else{
            //do nothing
        }
    }
}