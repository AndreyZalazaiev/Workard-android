package andrew.projects.workard.presenter;

import android.content.Context;

import andrew.projects.workard.Domain.Employee;
import andrew.projects.workard.view.EmployeeView;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@InjectViewState
public class EmployeePresenter extends MvpPresenter<EmployeeView> {

    public EmployeePresenter(Context context) {
    }
}
