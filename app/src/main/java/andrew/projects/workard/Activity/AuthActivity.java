package andrew.projects.workard.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import andrew.projects.workard.R;
import andrew.projects.workard.Service.LoginService;
import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {
    LoginService loginService = new LoginService();
    SharedPreferences settings;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        if (isLoggedIn()) {
            toCompanyActivity();
        }

    }

    public void login(String log,String pass) {
        loginService.login(log, pass).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                settings.edit().putString("token", response.body()).apply();
                Log.v("Response result:", "token stored");
                toCompanyActivity();

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.v("Response result:", "wrong credentials");
            }

        });
    }

    public boolean isLoggedIn() {
        String token = settings.getString("token", "empty");
        if (token.equals("empty")) {
            return false;
        }
        return true;
    }
    public void onLoginClick(View v) {
        EditText login = findViewById(R.id.login);
        EditText pass = findViewById(R.id.pass);
        login(login.getText().toString(),pass.getText().toString());

    }
    public void toCompanyActivity(){
        Intent intent = new Intent(AuthActivity.this, NavActivity.class);
        startActivity(intent);
    }
}