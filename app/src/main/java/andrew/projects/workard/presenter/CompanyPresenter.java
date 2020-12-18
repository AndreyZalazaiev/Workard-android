package andrew.projects.workard.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import andrew.projects.workard.Domain.Company;
import andrew.projects.workard.Fragments.CompanyFragment;
import andrew.projects.workard.R;
import andrew.projects.workard.Service.CompanyService;
import andrew.projects.workard.view.CompanyView;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@InjectViewState
public class CompanyPresenter extends MvpPresenter<CompanyView> {

    Context context;
    private SharedPreferences settings;
    private String authToken;
    private CompanyService companyService = new CompanyService();

    public CompanyPresenter(Context context) {
        this.context = context;
        settings = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        authToken = getAuthToken();
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
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        for (Company c : companies
        ) {

            MaterialCardView card = new MaterialCardView(context);

            card.setBackgroundResource(R.drawable.gradient);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(30, 20, 30, 0);

            card.setLayoutParams(layoutParams);
            card.setOnClickListener(v -> {
                getViewState().showFragment(c.getRooms());
            });

            LinearLayout cardContent = new LinearLayout(context);
            cardContent.setOrientation(LinearLayout.HORIZONTAL);
            cardContent.setPadding(30, 30, 30, 30);


            TextView text = new TextView(context);
            ImageView factoryImg = new ImageView(context);

            factoryImg.setImageResource(R.drawable.factory);
            factoryImg.setBackgroundColor(Color.TRANSPARENT);
            factoryImg.setLayoutParams(new LinearLayout.LayoutParams(450, 200));


            text.setText(Html.fromHtml("<b>" + c.getName() + "</b><br/>Count of rooms: " + c.getRooms().stream().count()));
            text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);


            cardContent.addView(factoryImg);
            cardContent.addView(text);

            card.addView(cardContent);
            container.addView(card);
        }
        getViewState().getCompanies(container);
    }

    private String getAuthToken() {
        return settings.getString("token", "empty");
    }
}



