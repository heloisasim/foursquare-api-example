package com.heloisasim.foursquareapi;

import com.heloisasim.foursquareapi.model.Venue;

import java.util.ArrayList;

/**
 * Created by heloisasim on 13/04/17.
 */

public interface MainContract {

    interface Presenter {
        void onViewReady();

        void loadVenues();

        void setView(View view);
    }

    interface View {
        void dismissAnimations();

        void showError(boolean isConnectionError);

        void updateList(ArrayList<Venue> venues);
    }
}
