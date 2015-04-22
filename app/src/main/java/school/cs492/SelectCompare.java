package school.cs492;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * @author lingxi
 *         <p/>
 *         Edited by fiorfe01
 */
public class SelectCompare extends ActionBarActivity {

    private ArrayList<String> scannedQRs;
    private ArrayAdapter<String> adapter;
    private ListView scannedList;
    private Button compBtn;
    private String restaurantTitle;
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

        setContentView(R.layout.activity_select_compare);

        int callingActivity = getIntent().getIntExtra("CALLER", 0);
        restaurantTitle = getIntent().getStringExtra("RESTAURANT_NAME");
        switch (callingActivity) {

            case ActivityID.MainMenuActivity:
                scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_MAIN");

                break;

            case ActivityID.SavedMenuItemsList:
                scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_LIST");
                break;
        }

        setMenuItemList();

        // Find Button and assign listener. Wrap up the selected items.
        compBtn = (Button) findViewById(R.id.SelectCompButton);
        compBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CompBtnHandler((Button) v);
            }
        });
    }

    /**
     * Sets the Menu Item List.
     */
    private void setMenuItemList() {

        // Get the number of
        int numOfItem = scannedQRs.size();

        String[] foods = new String[numOfItem];

        // Loop through the ArrayList and figure out which items were scanned.
        for (int i = 0; i < numOfItem; i++) {

            int titlePath = getStringIdentifier(this, "name_" + getRestaurantDataPath(scannedQRs.get(i)));

            foods[i] = getString(titlePath);
        }

        // Now we have the selected items. We populate the list.
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, foods);
        scannedList = (ListView) findViewById(R.id.selected_list);
        scannedList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        scannedList.setAdapter(adapter);
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


    public void CompBtnHandler(Button btn) {

        SparseBooleanArray checked = scannedList.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();

        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }

        String[] outputStrArr = new String[selectedItems.size()];

        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
        }

        Intent intent = new Intent(this, CompareActivity.class);

        Bundle b = new Bundle();
        b.putStringArray("SELECTED_ITEMS", outputStrArr);

        intent.putExtras(b);

        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_compare, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}