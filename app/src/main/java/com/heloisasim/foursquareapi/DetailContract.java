package com.heloisasim.foursquareapi;

/**
 * Created by heloisasim on 13/04/17.
 */

public interface DetailContract {

    interface Presenter {
        void onViewReady();

        void setView(View view);

        void loadVenue();

        void onGetDirectionsClicked();

        void setLoadRequest(boolean shouldLoadDetails);
    }

    interface View {
        void dismissAnimations();

        void showError(boolean isConnectionError);

        void showName(String name);

        void showHeaderPicture(String url);

        void showPhone(String phone);

        void hidePhone();

        void showAddress(String address);

        void hideAddress();

        void showEmptyInfo();

        void showDays(String days);

        void showHours(String hours);

        void showStatus(String status, int color);

        void openMaps(String uri);

    }
}
