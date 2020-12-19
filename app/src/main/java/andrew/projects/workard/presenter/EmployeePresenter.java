package andrew.projects.workard.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import andrew.projects.workard.Domain.Company;
import andrew.projects.workard.Domain.Employee;
import andrew.projects.workard.R;
import andrew.projects.workard.Service.CompanyService;
import andrew.projects.workard.view.EmployeeView;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class EmployeePresenter extends MvpPresenter<EmployeeView> {

    private Context context;
    private SharedPreferences settings;
    private String authToken;
    private CompanyService companyService = new CompanyService();
    private ArrayList<Company> checkedCompanies = new ArrayList<>();

    public EmployeePresenter(Context context) {
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
                drawEmployees(response.body());
            }


            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                Log.v("Response result:", "wrong credentials");
            }
        });
    }

    private void drawEmployees(List<Company> body) {
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
                drawEmployeeStats();
            });
            ;
            container.addView(checkBox);
        }
        getViewState().drawListOfCompanies(container);
    }

    private void drawEmployeeStats() {
        GridLayout container = new GridLayout(context);
        container.setOrientation(GridLayout.VERTICAL);
        container.setRowCount(4);


        for (Company c : checkedCompanies
        ) {
            for (Employee e : c.getEmployees()
            ) {
                LinearLayout card = new LinearLayout(context);
                card.setOrientation(LinearLayout.VERTICAL);
                card.setBackgroundResource(R.drawable.round_corners);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(450, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(30,30,30,30);

                card.setLayoutParams(layoutParams);
                card.setPadding(40,40,40,40);

                TextView empInfo = new TextView(context);
                empInfo.setText(Html.fromHtml("<b>"+ e.getName() + "</b><br/>" + e.getOccupation()));
                empInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);
                empInfo.setTextColor(Color.BLACK);

                TextView RFIDtext = new TextView(context);
                RFIDtext.setText(Html.fromHtml("RFID:"+e.getRFIDtag()+""));
                RFIDtext.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);

                card.addView(empInfo);
                card.addView(RFIDtext);
                container.addView(card);
            }
        }
        getViewState().drawEmployeesForEachCompanyChecked(container);
    }

    private String getAuthToken() {
        return settings.getString("token", "empty");
    }
}
