package school.cs492;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.location.Location;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by lingxi on 2/21/2015.
 * Location reading components added by Nate.
 */
public class SplashActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener {
    private static long SLEEP_TIME = 5;
    private IntentLauncher launcher;
    private static final String TAG = "SplashActivity";
    protected GoogleApiClient myGoogleApiClient;
    protected Location myLocation;
    protected String myLatitudeText;
    protected String myLongitudeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int available = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        Log.i(TAG, ""+available+"");

        //Create Google API Client.
        myGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        //disable title bar and notification bar.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        launcher = new IntentLauncher();
    } //End onCreate(Bundle) method.

    @Override
    protected void onStart() {
        super.onStart();
        myGoogleApiClient.connect();

        launcher.start();
    } //End onStart() method.

    @Override
    protected void onStop() {
        super.onStop();
        if (myGoogleApiClient.isConnected()) {
            myGoogleApiClient.disconnect();
        } //End if.
    } //End onStop() method.

    @Override
    public void onConnected(Bundle bundle) {
        myLocation = LocationServices.FusedLocationApi.getLastLocation(myGoogleApiClient);

        if(myLocation != null) {
            myLatitudeText = String.valueOf(myLocation.getLatitude());
            myLongitudeText = String.valueOf(myLocation.getLongitude());
        } //End if.
        else {
            myLatitudeText = "Location unreadable";
            myLongitudeText = "Location unreadable";
        } //End else.

        Log.i(TAG, myLatitudeText);
        Log.i(TAG, myLongitudeText);
    } //End onConnected(Bundle) method.

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        myGoogleApiClient.connect();
    } //End onConnectionSuspended(int) method.

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    } //End onConnectionFailed(ConnectionResult) method.

    private class IntentLauncher extends Thread {

        public void run() {
            try {
                //sleeping...
                Thread.sleep(SLEEP_TIME * 1000);
            } catch (Exception e) {
                Log.e("Splash class", e.getMessage());
            }

            //start main activity...
            Intent intent = new Intent(SplashActivity.this, ConfirmationActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        } //End run() method.
    } //End IntentLauncher class.
} //End SplashActivity class.
