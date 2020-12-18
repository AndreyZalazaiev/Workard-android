package andrew.projects.workard.view;

import android.widget.LinearLayout;

import java.util.List;

import andrew.projects.workard.Domain.Room;
import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CompanyView extends MvpView {

    void getCompanies(LinearLayout companies);
    void showFragment(List<Room> rooms);
}