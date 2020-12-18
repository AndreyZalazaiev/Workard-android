package andrew.projects.workard.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import andrew.projects.workard.Activity.NavActivity;
import andrew.projects.workard.Domain.Room;
import andrew.projects.workard.R;
import andrew.projects.workard.presenter.RoomPresenter;
import andrew.projects.workard.view.RoomView;
import lombok.val;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class RoomFragment extends MvpAppCompatFragment implements RoomView {

    @InjectPresenter
    RoomPresenter roomPresenter;
    List<Room> rooms;
    private LinearLayout roomsContainer;

    public RoomFragment(List<Room> rooms) {
        this.rooms = rooms;
    }

    @ProvidePresenter
    RoomPresenter provideDetailsPresenter() {
        return new RoomPresenter(getContext(), rooms);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_room, container, false);
        val actionBar = ((NavActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Room");
        actionBar.setIcon(R.drawable.ic_group_work_black_24dp);
        roomsContainer = v.findViewById(R.id.rooms);
        return v;
    }

    @Override
    public void drawCinema(LinearLayout layout) {
        this.roomsContainer.addView(layout);
    }
}
