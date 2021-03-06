package school.cs492;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
        String restaurantName;
        restaurantName = getIntent().getStringExtra("RESTAURANT_NAME");
        if (restaurantName == null || restaurantName == "") {
            restaurantTitle.setText("Name not received");
        } //End if.
        else {

            if (restaurantName.equals("Welcome to CS Cafe")) {

                // set the restaurant picture
                ImageView imageView = (ImageView) findViewById(R.id.main_restaurant_image);
                Picasso.with(this).load("http://virtualtour.ipfw.edu/images/photoGallery/sized/et/et7-vert.jpg").into(imageView);

            } else if (restaurantName.equals("Welcome to Kettler Kitchen")) {

                // set the restaurant picture
                ImageView imageView = (ImageView) findViewById(R.id.main_restaurant_image);
                Picasso.with(this).load("http://virtualtour.ipfw.edu/images/photoGallery/sized/kt/kt1-vert.jpg").into(imageView);
            }

            restaurantTitle.setText(restaurantName);
        }

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
        if(restaurantTitle.getText().equals("Welcome to CS Cafe")){
            intent.putExtra("RESTAURANT_NAME","Welcome to CS Cafe");
        }else{
            intent.putExtra("RESTAURANT_NAME","Welcome to Kettler Kitchen");
        }

        startActivityForResult(intent, SCAN_REQUEST_CODE);
    }

    public void scannedItemBtnHandler(Button btn) {

        if (getIntent().getStringArrayListExtra("SCANNED_QR_ITEM") != null) {
            scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_ITEM");
            Intent intent = new Intent(this, SavedMenuItemsList.class);
            intent.putExtra("CALLER", ActivityID.MainMenuActivity);
            if(restaurantTitle.getText().equals("Welcome to CS Cafe")){
                intent.putExtra("RESTAURANT_NAME","Welcome to CS Cafe");
            }else{
                intent.putExtra("RESTAURANT_NAME","Welcome to Kettler Kitchen");
            }
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

    /**
     * Calls RestaurantListActivity.
     *
     * @param view
     */
    public void goToRestaurantList(View view) {

        Intent intent = new Intent(this, RestaurantListActivity.class);
        intent.putExtra("CALLER", ActivityID.MainMenuActivity);
        intent.putExtra("SCANNED_QR_MAIN", scannedQRs);

        startActivity(intent);
    }
}