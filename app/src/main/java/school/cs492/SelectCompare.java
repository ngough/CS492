package school.cs492;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import android.util.SparseBooleanArray;

public class SelectCompare extends ActionBarActivity {

    private ArrayList<String> scannedQRs;
    ArrayAdapter<String> adapter;

    private ListView scannedList;
    private Button compBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_compare);
        String[] food_source_1 = {"Stired Fried Chicked and Rice", "Bacon", "Lentil bean soup", "Tiramisu", "Fish and Chips"};
        String[] food_source_2 = {"BLT", "Spaghetti and Meatballs", "Hamburger and Fries", "Dumplings", "Buffalo Chicken Wings"};

        int callingActivity = getIntent().getIntExtra("CALLER", 0);
        switch (callingActivity) {

            case ActivityID.MainMenuActivity:
                scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_MAIN");
                break;

            case ActivityID.SavedMenuItemsList:
                scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_LIST");
                break;
        }
        // Get the number of items selected.
        int numOfItem = scannedQRs.size();
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

        // Now we have the selected items. We populate the list.
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, foods);
        scannedList=(ListView)findViewById(R.id.list);
        scannedList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        scannedList.setAdapter(adapter);

        // Find Button and assign listener. Wrap up the selected items.
        compBtn = (Button) findViewById(R.id.CompBtn);
        compBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CompBtnHandler((Button) v);
            }
        });
    }

    public void CompBtnHandler(Button btn){

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

        Intent intent = new Intent(this,CompareActivity.class);

        Bundle b = new Bundle();
        b.putStringArray("SELECTED_ITEMS",outputStrArr);

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
