package andrew.projects.workard.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import andrew.projects.workard.Domain.Room;
import andrew.projects.workard.R;
import andrew.projects.workard.presenter.CompanyPresenter;
import andrew.projects.workard.view.CompanyView;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class CompanyFragment extends MvpAppCompatFragment implements CompanyView {

    @InjectPresenter
    CompanyPresenter companyPresenter;
    private LinearLayout companiesContainer;

    @ProvidePresenter
    CompanyPresenter provideDetailsPresenter() {
        return new CompanyPresenter(getContext());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        companiesContainer = view.findViewById(R.id.container);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getCompanies(LinearLayout companies) {
        this.companiesContainer.addView(companies);
    }

    @Override
    public void showFragment(List<Room> rooms) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,new RoomFragment(rooms))
                .commit();
    }




}
