package andrew.projects.workard.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import andrew.projects.workard.Domain.DTO.HotSpotsDTO;
import andrew.projects.workard.Domain.Device;
import andrew.projects.workard.Domain.Room;
import andrew.projects.workard.R;
import andrew.projects.workard.Service.CompanyService;
import andrew.projects.workard.Util.Constants;
import andrew.projects.workard.view.RoomView;
import lombok.val;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class RoomPresenter extends MvpPresenter<RoomView> {
    private Context context;
    private List<Room> rooms;
    private ArrayList<HotSpotsDTO> hotSpots;
    private SharedPreferences settings;
    private String authToken;
    private CompanyService companyService = new CompanyService();

    public RoomPresenter(Context context, List<Room> rooms) {
        this.context = context;
        this.rooms = rooms;
        settings = context.getSharedPreferences(Constants.prefName, Context.MODE_PRIVATE);
        authToken = getAuthToken();
        loadHotSpots();
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

            card.setBackgroundResource(R.drawable.round_corners);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(30, 20, 30, 0);
            card.setLayoutParams(layoutParams);

            LinearLayout hotSpotData = new LinearLayout(context);

            LinearLayout cardContent = new LinearLayout(context);
            cardContent.setOrientation(LinearLayout.VERTICAL);
            cardContent.setPadding(30, 30, 30, 30);
            cardContent.setOnClickListener(v -> {
                HotSpotsDTO roomStats = findRoomStats(r);
                TextView roomStatsText = new TextView(context);
                roomStatsText.setText("Employees now: " + roomStats.getEmployeesNow() + "/" + roomStats.getRecommendedValue());
                roomStatsText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                hotSpotData.removeAllViewsInLayout();
                hotSpotData.addView(roomStatsText);
            });

            TextView roomDetails = new TextView(context);
            roomDetails.setText(Html.fromHtml("<b>" + r.getName() + "</b><br/>Recommended value: " + r.getRecommendedValue()));
            roomDetails.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            roomDetails.setTextColor(Color.BLACK);

            TextView devices = new TextView(context);
            if (r.getDevices().size() != 0) {
                for (Device d : r.getDevices()
                ) {
                    devices.setText(devices.getText() + " " + d.getDeviceCode());
                }

                devices.setText("Device list: " + devices.getText().toString().trim().replaceAll(" ", ","));
            } else devices.setText("No devices connected");
            devices.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);


            cardContent.addView(roomDetails);
            cardContent.addView(devices);
            cardContent.addView(hotSpotData);

            card.addView(cardContent);

            container.addView(card);
        }

        return container;
    }

    void loadHotSpots() {
        getAuthToken();
        if (rooms.size() > 0)
            companyService.getHotSpots(authToken, rooms.get(0).getIdCompany()).enqueue(new Callback<ArrayList<HotSpotsDTO>>() {
                @Override
                public void onResponse(Call<ArrayList<HotSpotsDTO>> call, Response<ArrayList<HotSpotsDTO>> response) {
                    hotSpots = response.body();
                    getViewState().drawCinema(prepareRoomsToDrawing());
                }

                @Override
                public void onFailure(Call<ArrayList<HotSpotsDTO>> call, Throwable t) {
                    Log.v("Response result:", "wrong credentials");
                }
            });
    }

    public HotSpotsDTO findRoomStats(Room r) {
        val res = hotSpots.stream().filter(h -> h.getIdRoom() == r.getId()).collect(Collectors.toList());
        if (res.size() > 0)
            return res.get(0);
        return new HotSpotsDTO(1, 1, 1L);
    }

    private String getAuthToken() {
        return settings.getString("token", "empty");
    }

}
