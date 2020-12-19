package andrew.projects.workard.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import andrew.projects.workard.Domain.User;
import andrew.projects.workard.Fragments.CompanyFragment;
import andrew.projects.workard.Fragments.EmployeeFragment;
import andrew.projects.workard.Fragments.RecommendationFragment;
import andrew.projects.workard.R;
import andrew.projects.workard.Service.CompanyService;
import andrew.projects.workard.presenter.NavPresenter;
import andrew.projects.workard.view.NavView;
import lombok.val;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class NavActivity extends MvpAppCompatActivity implements NavView, NavigationView.OnNavigationItemSelectedListener {

    @InjectPresenter
    NavPresenter navPresenter;

    private LinearLayout companiesContainer;
    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences settings;
    private CompanyService companyService = new CompanyService();
    private Context context;

    @ProvidePresenter
    NavPresenter provideDetailsPresenter() {
        settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        return new NavPresenter(settings.getString("token", "empty"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setNavigationViewListener();
        context = getApplicationContext();
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_company));
    }

    @Override
    public void getProfileData(User u) {
        TextView login = findViewById(R.id.Login);
        TextView email = findViewById(R.id.Email);

        login.setText(u.getUsername());
        email.setText(u.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav, menu);
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
        val actionBar = getSupportActionBar();
        switch (item.getItemId()) {

            case R.id.nav_log_out: {
                settings.edit().remove("token").commit();
                switchActivity(AuthActivity.class);
                break;
            }
            case R.id.nav_company: {
                actionBar.setTitle("Company");
                actionBar.setIcon(R.drawable.ic_domain_black_24dp);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new CompanyFragment())
                        .commit();
                break;
            }
            case R.id.nav_empl: {
                actionBar.setTitle("Employees");
                actionBar.setIcon(R.drawable.ic_person_white_24dp);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new EmployeeFragment())
                        .commit();
                break;
            }
            case R.id.nav_recom: {
                actionBar.setTitle("Recommendations");
                actionBar.setIcon(R.drawable.ic_watch_later_white_24dp);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new RecommendationFragment())
                        .commit();
                break;
            }
        }
        return true;
    }

    public void switchActivity(Class activity) {
        Intent intent = new Intent(NavActivity.this, activity);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }


}
