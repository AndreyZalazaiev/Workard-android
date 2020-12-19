package andrew.projects.workard.presenter;

import android.content.Context;

import andrew.projects.workard.view.RecommendationView;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class RecommendationPresenter extends MvpPresenter<RecommendationView> {
    public RecommendationPresenter(Context context) {
    }
}
