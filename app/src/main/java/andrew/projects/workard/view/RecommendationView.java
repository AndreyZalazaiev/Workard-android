package andrew.projects.workard.view;

import android.widget.LinearLayout;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface RecommendationView extends MvpView {
    void drawCompanies(LinearLayout companies);
    void drawRecommendations(LinearLayout  recommendations);
}
