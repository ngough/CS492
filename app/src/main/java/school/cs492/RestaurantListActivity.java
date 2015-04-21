package school.cs492;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Represents the Restaurant List Activity. It displays the list of available restaurants and lets the user choose the restaurant.
 *
 * @author fiorfe01
 */
public class RestaurantListActivity extends ActionBarActivity {

    private ArrayList<String> scannedQRs = new ArrayList<String>();

    private ListView restaurantList;

    private String[] paths = {"http://virtualtour.ipfw.edu/images/photoGallery/sized/et/et7-vert.jpg", "http://virtualtour.ipfw.edu/images/photoGallery/sized/kt/kt1-vert.jpg"};

    private String[] restaurants = {"CS Cafe", "Kettler Kitchen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurant_list);

        restaurantList = (ListView) findViewById(R.id.restaurant_list);

        restaurantList.setAdapter(new RestaurantAdapter(this, paths, restaurants, scannedQRs));

        if (getIntent().getStringArrayListExtra("SCANNED_QR_ITEM") != null) {

            scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_ITEM");
        }

        // Onclick listener.
        restaurantList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String restaurantName;

                        if (position == 0) {

                            restaurantName = "Welcome to CS Cafe";

                        } else if (position == 1) {

                            restaurantName = "Welcome to Kettler Kitchen";

                        } else {

                            restaurantName = "Unknown";
                        }

                        Intent intent_main = new Intent(RestaurantListActivity.this, MainMenuActivity.class);
                        intent_main.putExtra("restaurant", restaurantName);
                        intent_main.putExtra("SCANNED_QR_MAIN", scannedQRs);

                        startActivity(intent_main);
                    }
                }
        );
    }
}
