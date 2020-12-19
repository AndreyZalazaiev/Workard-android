package andrew.projects.workard.view;

import android.widget.GridLayout;
import android.widget.LinearLayout;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface EmployeeView extends MvpView {
    void drawListOfCompanies(LinearLayout companies);
    void drawEmployeesForEachCompanyChecked(GridLayout employees);
}
