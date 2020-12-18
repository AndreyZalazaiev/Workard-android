package andrew.projects.workard.view;

import andrew.projects.workard.Domain.User;
import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface NavView extends MvpView {
    void getProfileData(User u);
}
