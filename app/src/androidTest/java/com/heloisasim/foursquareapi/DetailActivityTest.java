package com.heloisasim.foursquareapi;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.heloisasim.foursquareapi.model.Venue;
import com.heloisasim.foursquareapi.networking.RestClient;
import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.OkHttpClient;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> mActivityRule = new ActivityTestRule<DetailActivity>(DetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Venue venue = new Venue();
            venue.setId("someid01");
            venue.setName("Example name");

            Intent intent = new Intent(targetContext, DetailActivity.class);
            intent.putExtra(DetailActivity.VENUE_EXTRA, venue);
            return intent;
        }
    };

    @Test
    public void testClickThirdItem() throws Exception {
        OkHttpClient okHttpClient = RestClient.getInstance().getOkHttpClient();
        OkHttp3IdlingResource resource = OkHttp3IdlingResource.create("OkHttp", okHttpClient);
        Espresso.registerIdlingResources(resource);

        onView(ViewMatchers.withId(R.id.detail_collapsing_toolbar)).check(matches(isDisplayed()));

        Espresso.unregisterIdlingResources(resource);

    }
}
