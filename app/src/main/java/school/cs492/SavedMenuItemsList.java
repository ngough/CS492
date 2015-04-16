package school.cs492;

import android.content.Intent;
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

        // Get the number of
        int numOfItem = scannedQRs.size();

        String[] food_source_1 = {"Stir fried chicken and rice", "Bacon", "Lentil bean soup", "Tiramisu", "Fish & Chips"};
        String[] food_source_2 = {"BLT", "Spaghetti & meatball", "Hamburger & Fries", "Dumpling", "Buffalo Chicken Wings"};
        String[] foods = new String[numOfItem];

        // Loop through the ArrayList and figure out which items were scanned.
        for (int i = 0; i < numOfItem; i++) {

            if (scannedQRs.get(i).contains("restaurant1")) {
                if (scannedQRs.get(i).contains("dish1")) {
                    foods[i] = food_source_1[0];
                } else if (scannedQRs.get(i).contains("dish2")) {
                    foods[i] = food_source_1[1];
                } else if (scannedQRs.get(i).contains("dish3")) {
                    foods[i] = food_source_1[2];
                } else if (scannedQRs.get(i).contains("dish4")) {
                    foods[i] = food_source_1[3];
                } else if (scannedQRs.get(i).contains("dish5")) {
                    foods[i] = food_source_1[4];
                }
            } else {
                if (scannedQRs.get(i).contains("dish1")) {
                    foods[i] = food_source_2[0];
                } else if (scannedQRs.get(i).contains("dish2")) {
                    foods[i] = food_source_2[1];
                } else if (scannedQRs.get(i).contains("dish3")) {
                    foods[i] = food_source_2[2];
                } else if (scannedQRs.get(i).contains("dish4")) {
                    foods[i] = food_source_2[3];
                } else if (scannedQRs.get(i).contains("dish5")) {
                    foods[i] = food_source_2[4];
                }
            }
        }


//        for (int i = 0; i < numOfItem; i++) {
//            foods[i] = food_source[i];
//        }

        ListAdapter custAdapter = new CustomAdapter(this, foods,scannedQRs);
        ListView custListView = (ListView) findViewById(R.id.listView);
        custListView.setAdapter(custAdapter);

        // Onclick listener.
        custListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        String food = String.valueOf(parent.getItemAtPosition(position));
                        String foodItemPath = "http://goughn.ddns.net/restaurant1/dish1/pic1.jpg";
                        switch (food) {
                            case "Sir fried chicken and rice":
                                foodItemPath = "http://goughn.ddns.net/restaurant1/dish1/pic1.jpg";
                                break;
                            case "Bacon":
                                foodItemPath = "http://goughn.ddns.net/restaurant1/dish2/pic1.jpg";
                                break;
                            case "Lentil bean soup":
                                foodItemPath = "http://goughn.ddns.net/restaurant1/dish3/pic1.jpg";
                                break;
                            case "Tiramisu":
                                foodItemPath = "http://goughn.ddns.net/restaurant1/dish4/pic1.jpg";
                                break;
                            case "Fish & Chips":
                                foodItemPath = "http://goughn.ddns.net/restaurant1/dish5/pic1.jpg";
                                break;
                            case "BLT":
                                foodItemPath = "http://goughn.ddns.net/restaurant2/dish1/pic1.jpg";
                                break;
                            case "Spaghetti & meatball":
                                foodItemPath = "http://goughn.ddns.net/restaurant2/dish2/pic1.jpg";
                                break;
                            case "Hamburger & Fries":
                                foodItemPath = "http://goughn.ddns.net/restaurant2/dish3/pic1.jpg";
                                break;
                            case "Dumpling":
                                foodItemPath = "http://goughn.ddns.net/restaurant2/dish4/pic1.jpg";
                                break;
                            case "Buffalo Chicken Wings":
                                foodItemPath = "http://goughn.ddns.net/restaurant2/dish5/pic1.jpg";
                                break;
                        }

                        Intent intent = new Intent(SavedMenuItemsList.this, MenuItemActivity.class);
                        intent.putExtra("QR_RESULT", foodItemPath);
                        intent.putExtra("SCANNED_QR_LIST", scannedQRs);
                        intent.putExtra("CALLER", ActivityID.SavedMenuItemsList);
                        startActivity(intent);

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
