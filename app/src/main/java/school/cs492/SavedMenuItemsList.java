package school.cs492;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * @author lingxi
 *         <p/>
 *         Edited by fiorfe01
 */
public class SavedMenuItemsList extends ActionBarActivity {

    private ArrayList<String> scannedQRs;

    private ListView custListView;

    /**
     * Gets the resource string identifier.
     *
     * @param context
     * @param name
     * @return string identifier for resource
     */
    public static int getStringIdentifier(Context context, String name) {

        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_menu_items_list);

        // Find compare button and assign an listener to it.
        Button compBtn = (Button) findViewById(R.id.CompButton);
        compBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CompBtnHandler((Button) v);
            }
        });

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

        setMenuItemCard();

        // Onclick listener.
        custListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String menuItemPath = scannedQRs.get(position);

                        Intent intent = new Intent(SavedMenuItemsList.this, MenuItemActivity.class);
                        intent.putExtra("QR_RESULT", menuItemPath);
                        intent.putExtra("SCANNED_QR_LIST", scannedQRs);
                        intent.putExtra("CALLER", ActivityID.SavedMenuItemsList);
                        startActivity(intent);
                    }
                }
        );
    }

    /**
     * Gets the restaurant data path used to get the data from resource.
     *
     * @return restaurant + "_" + dish
     */
    private String getRestaurantDataPath(String picPath) {

        String restaurant = picPath.substring(23, 34);

        String dish = picPath.substring(35, 40);

        return restaurant + "_" + dish;
    }

    /**
     * Sets the Menu Item Card.
     */
    private void setMenuItemCard() {

        // Get the number of
        int numOfItem = scannedQRs.size();

        String[] foods = new String[numOfItem];

        // Loop through the ArrayList and figure out which items were scanned.
        for (int i = 0; i < numOfItem; i++) {

            int titlePath = getStringIdentifier(this, "name_" + getRestaurantDataPath(scannedQRs.get(i)));

            foods[i] = getString(titlePath);
        }

        custListView = (ListView) findViewById(R.id.listView);
        custListView.setAdapter(new CustomAdapter(this, foods, scannedQRs));
    }

    public void CompBtnHandler(Button btn) {

        Intent intent = new Intent(this, SelectCompare.class);
        intent.putExtra("CALLER", ActivityID.SavedMenuItemsList);

        intent.putExtra("SCANNED_QR_LIST", scannedQRs);
        startActivity(intent);
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