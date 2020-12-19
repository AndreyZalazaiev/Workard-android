package andrew.projects.workard.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andrew.projects.workard.R;
import andrew.projects.workard.presenter.EmployeePresenter;
import andrew.projects.workard.presenter.RecommendationPresenter;
import andrew.projects.workard.presenter.RoomPresenter;
import andrew.projects.workard.view.CompanyView;
import andrew.projects.workard.view.RecommendationView;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class RecommendationFragment extends MvpAppCompatFragment implements RecommendationView {
    @InjectPresenter
    RecommendationPresenter recommendationPresenter;
    @ProvidePresenter
    RecommendationPresenter provideDetailsPresenter() {
        return new RecommendationPresenter(getContext());
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommendation, container, false);
    }
}
