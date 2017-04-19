package com.heloisasim.foursquareapi;

import com.heloisasim.foursquareapi.model.Venue;
import com.heloisasim.foursquareapi.networking.ApiService;

import java.util.List;

/**
 * Created by heloisasim on 13/04/17.
 */

public interface MainContract {

    interface Presenter {
        void onViewReady();

        void loadVenues();

        void setView(View view);

        void setService(ApiService apiService);
    }

    interface View {
        void dismissAnimations();

        void showError(boolean isConnectionError);

        void updateList(List<Venue> venues);
    }
}
