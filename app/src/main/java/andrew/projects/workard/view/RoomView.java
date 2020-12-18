package andrew.projects.workard.view;

import android.widget.LinearLayout;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface RoomView extends MvpView {
    void drawCinema(LinearLayout layout);
}
