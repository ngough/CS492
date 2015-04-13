package school.cs492;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class SavedMenuItemsList extends ActionBarActivity {

    private ArrayList<String> scannedQRs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_menu_items_list);

        // Get the scannedQRs.
        // If it's clicked by button in MainMenu.

        int callingActivity = getIntent().getIntExtra("CALLER", 0);
        switch (callingActivity) {

            case ActivityID.MainMenuActivity:
                scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_MAIN");
                break;

            case ActivityID.MenuItemActivity:
                scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_ITEM");
                break;
        }


        int numOfItem = scannedQRs.size();
        String[] food_source = {"Bacon", "Ham", "Tuna", "Candy", "Meatball", "Potato", "Tomato", "Banana", "Burger", "Fries"};
        String[] foods = new String[numOfItem];
        for (int i = 0; i < numOfItem; i++) {
            foods[i] = food_source[i];
        }
        ListAdapter custAdapter = new CustomAdapter(this, foods);
        ListView custListView = (ListView) findViewById(R.id.listView);
        custListView.setAdapter(custAdapter);

        custListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(SavedMenuItemsList.this, food, Toast.LENGTH_LONG).show();


                    }
                }
        );


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saved_menu_items_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.opt_scan_new_item) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
