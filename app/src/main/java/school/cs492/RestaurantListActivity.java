package school.cs492;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantListActivity extends ActionBarActivity {
    ArrayList<String> scannedQRs = new ArrayList<String>();
    ListView restaurantList;

    String[] restaurants = {"CS Cafe", "Kettler Kitchen"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurant_list);

        restaurantList = (ListView) findViewById(R.id.restaurant_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, restaurants);

        restaurantList.setAdapter(adapter);

        if (getIntent().getStringArrayListExtra("SCANNED_QR_ITEM") != null) {
            scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_ITEM");
        } //End if.
    } //End onCreate(Bundle) method.


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String restaurantName;

        //noinspection SimplifiableIfStatement
        if (id == R.id.opt_scan_new_item) {
            return true;
        }
        if (id == 0) {
            restaurantName = "CS Cafe";
        } //End if.
        else if (id == 1) {
            restaurantName = "Kettler Kitchen";
        } //End else if.
        else {
            restaurantName = "Unknown";
        } //End else.

        Intent intent_main = new Intent(RestaurantListActivity.this, MainMenuActivity.class);
        intent_main.putExtra("restaurant", restaurantName);
        intent_main.putExtra("SCANNED_QR_MAIN", scannedQRs);

        //TODO Finish this activity so it calls the main menu properly.
        startActivity(intent_main);

        return super.onOptionsItemSelected(item);

    }
}
