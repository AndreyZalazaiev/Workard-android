package andrew.projects.workard.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import andrew.projects.workard.Domain.Company;
import andrew.projects.workard.Domain.Recommendation;
import andrew.projects.workard.R;
import andrew.projects.workard.Service.CompanyService;
import andrew.projects.workard.Util.Constants;
import andrew.projects.workard.view.RecommendationView;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class RecommendationPresenter extends MvpPresenter<RecommendationView> {

    Context context;
    private SharedPreferences settings;
    private String authToken;
    private CompanyService companyService = new CompanyService();
    private ArrayList<Company> checkedCompanies = new ArrayList<>();

    public RecommendationPresenter(Context context) {
        this.context = context;
        settings = context.getSharedPreferences(Constants.prefName, Context.MODE_PRIVATE);
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

    private void drawCompanies(List<Company> body) {
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.HORIZONTAL);

        for (Company c : body
        ) {
            CheckBox checkBox = new CheckBox(context);
            checkBox.setText(c.getName());
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    checkedCompanies.add(c);
                } else {
                    checkedCompanies.remove(c);
                }
                drawRecommendations();
            });
            container.addView(checkBox);
        }
        getViewState().drawCompanies(container);
    }

    private void drawRecommendations() {
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        for (Company c : checkedCompanies
        ) {
            for (Recommendation r : c.getRecommendations()
            ) {
                LinearLayout card = new LinearLayout(context);
                card.setOrientation(LinearLayout.VERTICAL);
                card.setBackgroundResource(R.drawable.round_corners);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(30, 30, 30, 30);

                card.setLayoutParams(layoutParams);
                card.setPadding(40, 40, 40, 40);

                TextView recommend = new TextView(context);
                recommend.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                recommend.setText(r.getText());
                recommend.setTextColor(Color.BLACK);

                card.addView(recommend);

                container.addView(card);
            }
        }
        getViewState().drawRecommendations(container);
    }

    private String getAuthToken() {
        return settings.getString("token", "empty");
    }
}
