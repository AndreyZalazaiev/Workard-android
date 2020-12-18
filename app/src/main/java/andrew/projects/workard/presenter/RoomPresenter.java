package andrew.projects.workard.presenter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import andrew.projects.workard.Domain.Device;
import andrew.projects.workard.Domain.Room;
import andrew.projects.workard.R;
import andrew.projects.workard.view.RoomView;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class RoomPresenter extends MvpPresenter<RoomView> {
    Context context;
    List<Room> rooms;

    public RoomPresenter(Context context, List<Room> rooms) {
        this.context = context;
        this.rooms = rooms;
        getViewState().drawCinema(prepareRoomsToDrawing());

    }

    public LinearLayout prepareRoomsToDrawing() {

        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);

        if (rooms.size() == 0) {
            return new LinearLayout(context);
        }

        for (Room r : rooms
        ) {
            MaterialCardView card = new MaterialCardView(context);

            card.setBackgroundResource(R.drawable.gradient);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(30, 20, 30, 0);
            card.setLayoutParams(layoutParams);

            LinearLayout cardContent = new LinearLayout(context);
            cardContent.setOrientation(LinearLayout.HORIZONTAL);
            cardContent.setPadding(30, 30, 30, 30);


            TextView roomDetails = new TextView(context);
            roomDetails.setText(Html.fromHtml("<b>" + r.getName() + "</b><br/>Recommended value: " + r.getRecommendedValue()));
            roomDetails.setTextColor(Color.BLACK);

            TextView devices = new TextView(context);
            for (Device d : r.getDevices()
            ) {
                devices.setText(devices.getText() + " "+d.getDeviceCode());
            }
            devices.setText("Device list: " + devices.getText());


            cardContent.addView(roomDetails);
            cardContent.addView(devices);

            card.addView(cardContent);

            container.addView(card);
        }

        return container;
    }

}
