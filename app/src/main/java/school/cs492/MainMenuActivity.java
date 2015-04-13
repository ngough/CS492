package school.cs492;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Represents the Main Menu Activity. It displays the restaurant picture and lets the user choose to either
 * scan a new menu item, go to scanned items, and change restaurant.
 *
 * @author fiorfe01
 */
public class MainMenuActivity extends ActionBarActivity {

    private int SCAN_REQUEST_CODE = 1000; // Request code to scan.

    private Button scanBtn;
    private Button scannedItemBtn;

    private TextView restaurantTitle;

    private ArrayList<String> scannedQRs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // set the restaurant title TODO pass the title of the specific restaurant
        restaurantTitle = (TextView) findViewById(R.id.restaurant_title);
        restaurantTitle.setText("Insert Restaurant Title Here");

        // set the listener for the scan button
        scanBtn = (Button) findViewById(R.id.scan_button);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ScanButtonHandler((Button) v);
            }
        });

        // set the listener for the scanned item button.
        scannedItemBtn = (Button) findViewById(R.id.scanned_list_button);
        scannedItemBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                scannedItemBtnHandler((Button) v);
            }
        });

        // set the restaurant picture TODO retrieve the picture form the server
        ImageView imageView = (ImageView) findViewById(R.id.main_restaurant_image);
        Picasso.with(this).load("http://www.ipfw.edu/dotAsset/185440.JPG").into(imageView);
    }

    /**
     * Listener for the button. It calls CameraScanActivity which is a QR reader.
     *
     * @param btn
     */
    public void ScanButtonHandler(Button btn) {

        Intent intent = new Intent(this, CameraScanActivity.class);
        intent.putExtra("CALLER", ActivityID.MainMenuActivity);
        intent.putExtra("SCANNED_QR_MAIN", scannedQRs);
        startActivityForResult(intent, SCAN_REQUEST_CODE);
    }

    public void scannedItemBtnHandler(Button btn) {

        if (getIntent().getStringArrayListExtra("SCANNED_QR_ITEM") != null) {
            scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_ITEM");
            Intent intent = new Intent(this, SavedMenuItemsList.class);
            intent.putExtra("CALLER", ActivityID.MainMenuActivity);
            intent.putExtra("SCANNED_QR_MAIN", scannedQRs);
            startActivity(intent);
        } else if (scannedQRs.size() == 0) {
            Toast.makeText(MainMenuActivity.this, "No item has been scanned!", Toast.LENGTH_LONG).show();
        }


    }

    /**
     * Gets the QR Result from CameraScanActivity. It calls handleQrResult to do the actual work.
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SCAN_REQUEST_CODE) {
            handleQrResult(resultCode, intent);
        }
    }

    /**
     * Prints QR info on the textView.
     *
     * @param resultCode
     * @param intent
     */
    public void handleQrResult(int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            // TextView infoView = (TextView) findViewById(R.id.infoView);
            //String qrResult = intent.getStringExtra(CameraScanActivity.QR_RESULT_EXTRA);
            //infoView.setText(qrResult);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.opt_scan_new_item) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Calls RestaurantListActivity.
     *
     * @param view
     */
    public void goToRestaurantList(View view) {

        Intent intent = new Intent(this, RestaurantListActivity.class);

        startActivity(intent);
    }
}