package andrew.projects.workard.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import andrew.projects.workard.R;
import andrew.projects.workard.presenter.EmployeePresenter;
import andrew.projects.workard.presenter.RoomPresenter;
import andrew.projects.workard.view.CompanyView;
import andrew.projects.workard.view.EmployeeView;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class EmployeeFragment extends MvpAppCompatFragment implements EmployeeView {
    @InjectPresenter
    EmployeePresenter employeePresenter;
    private View v;

    @ProvidePresenter
    EmployeePresenter provideDetailsPresenter() {
        return new EmployeePresenter(getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_employee, container, false);
        return v;
    }

    @Override
    public void drawListOfCompanies(LinearLayout companies) {
        LinearLayout container =v.findViewById(R.id.companies);
        container.addView(companies);
    }

    @Override
    public void drawEmployeesForEachCompanyChecked(GridLayout employees) {
        LinearLayout container =v.findViewById(R.id.employees);
        container.removeAllViewsInLayout();
        container.addView(employees);
    }
}
