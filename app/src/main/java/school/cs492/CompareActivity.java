package school.cs492;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class CompareActivity extends ActionBarActivity {

    ArrayAdapter<String> adapter;
    private ArrayList<String> scannedQRs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        String[] food_source_1 = {"Lasagna", "Bacon", "Lentil bean soup", "Tiramisu", "Fish and Chips"};
        String[] food_source_2 = {"BLT", "Spaghetti and Meatballs", "Hamburger and Fries", "Dumplings", "Buffalo Chicken Wings"};

        Bundle b = getIntent().getExtras();
        String[] resultArr = b.getStringArray("SELECTED_ITEMS"); // Here, we get the scanned menu items' names.

        int numOfItems = resultArr.length;
        String[] labels = getResources().getStringArray(R.array.nutrition_array);
        String[] nutritionLabels = new String[numOfItems];

        for (int i = 0; i < numOfItems; i++) {
            if (resultArr[i].equals("Lasagna")) {
                nutritionLabels[i] = labels[0];
            } else if (resultArr[i].equals("Bacon")) {
                nutritionLabels[i] = labels[1];
            } else if (resultArr[i].equals("Buffalo Chicken Wings")) {
                nutritionLabels[i] = labels[2];
            } else if (resultArr[i].equals("Lentil Bean Soup")) {
                nutritionLabels[i] = labels[3];
            } else if (resultArr[i].equals("Tiramisu")) {
                nutritionLabels[i] = labels[4];
            } else if (resultArr[i].equals("Fish and Chips")) {
                nutritionLabels[i] = labels[5];
            } else if (resultArr[i].equals("BLT")) {
                nutritionLabels[i] = labels[6];
            } else if (resultArr[i].equals("Spaghetti and Meatballs")) {
                nutritionLabels[i] = labels[7];
            } else if (resultArr[i].equals("Hamburger and Fries")) {
                nutritionLabels[i] = labels[8];
            } else if (resultArr[i].equals("Dumplings")) {
                nutritionLabels[i] = labels[9];
            }
        }
        ListView list = (ListView) findViewById(R.id.comp_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nutritionLabels);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compare, menu);
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
