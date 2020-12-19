package andrew.projects.workard.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return inflater.inflate(R.layout.fragment_employee, container, false);
    }
}
