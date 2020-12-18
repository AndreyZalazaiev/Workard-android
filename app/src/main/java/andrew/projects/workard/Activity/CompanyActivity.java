package andrew.projects.workard.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import andrew.projects.workard.Domain.Company;
import andrew.projects.workard.R;
import andrew.projects.workard.Service.CompanyService;
import lombok.val;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences settings;
    private String authToken;
    private CompanyService companyService = new CompanyService();
    private Context context ;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.log_out)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        setNavigationViewListener();
        context=getApplicationContext();
        settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        getCompaniesList();

    }

    public void getCompaniesList() {
        getAuthToken();
        companyService.getCompanies(authToken).enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                drawCompanies(response.body());
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                Log.v("Response result:", "wrong credentials");
            }

        });
    }

    public void drawCompanies(List<Company> companies) {
        LinearLayout container = findViewById(R.id.container);// с сериализацией невнятная хуйня

        for (Company c : companies
             ) {
            LinearLayout card = new LinearLayout(context);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setGravity(Gravity.CENTER);
            //MaterialCardView card= new MaterialCardView(context);

            TextView companyTitleText = new TextView(context);
            TextView roomCountText = new TextView(context);
            ImageView factoryImg = new ImageView(context);

            factoryImg.setImageResource(R.drawable.factory);
            factoryImg.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,250));

            roomCountText.setTextSize(TypedValue.COMPLEX_UNIT_SP,28);
            roomCountText.setTextColor(Color.BLACK);
            roomCountText.setText("\nCount of rooms: "+c.getRooms().stream().count());
            companyTitleText.setText(c.getName());
            companyTitleText.setTextSize(TypedValue.COMPLEX_UNIT_SP,28);

            card.addView(factoryImg);
            card.addView(companyTitleText);
            card.addView(roomCountText);
            container.addView(card);
        }



    }

    public void getAuthToken() {
        val token = settings.getString("token", "empty");
        if (token.equals("empty")) {
            switchActivity(AuthActivity.class);
        } else this.authToken = token;
    }

    public void switchActivity(Class activity) {
        Intent intent = new Intent(CompanyActivity.this, activity);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.company, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.log_out: {
                settings.edit().remove("token").commit();
                switchActivity(AuthActivity.class);
                break;
            }
        }
        return true;
    }
}
