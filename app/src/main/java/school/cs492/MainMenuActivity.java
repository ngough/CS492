package school.cs492;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainMenuActivity extends ActionBarActivity {

    private int SCAN_REQUEST_CODE = 1000; // Request code to scan.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Get the scan button. Id id button. the other one is button2.
        // Hook it up with listener.
        Button scanBtn = (Button) findViewById(R.id.button);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleBtn((Button) v);
            }
        });
    }

    // This is the listener for the button. It calls CameraScanActivity which is a QR reader.
    public void handleBtn(Button btn) {
        Intent intent = new Intent(this, CameraScanActivity.class);
        startActivityForResult(intent, SCAN_REQUEST_CODE);
    }

    // Get the QR Result from CameraScanActivity. It calls handleQrResult to do the actual work.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SCAN_REQUEST_CODE) {
            handleQrResult(resultCode, intent);
        }
    }

    // Print QR info on the textView.
    public void handleQrResult(int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            TextView infoView = (TextView) findViewById(R.id.infoView);
            String qrResult = intent.getStringExtra(CameraScanActivity.QR_RESULT_EXTRA);
            infoView.setText(qrResult);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
