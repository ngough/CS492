/**
 * This class is built upon ZBar bar code library.
 * Full Credit goes to ZBar library development crew.
 */
package school.cs492;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

/* Import ZBar Class files */

public class CameraScanActivity extends Activity {
    public static final String QR_RESULT_EXTRA = "qrResultInfo";
    TextView scanText;
    //    Button scanButton;
    Button OkButton;
    ImageScanner scanner;
    private String qrInfo = "";
    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;
    private boolean barcodeScanned = false;
    private boolean previewing = true;

    static {
        System.loadLibrary("iconv");
    }

    PreviewCallback previewCb = new PreviewCallback() {

        public void onPreviewFrame(byte[] data, Camera camera) {


            Camera.Parameters parameters = camera.getParameters();
            Size size = parameters.getPreviewSize();

            Image barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(data);

            int result = scanner.scanImage(barcode);

            if (result != 0) {
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();

                SymbolSet syms = scanner.getResults();
                for (Symbol sym : syms) {
                    qrInfo += sym.getData();
//                    scanText.setText("barcode result " + sym.getData());
                    barcodeScanned = true;
                }
            }
        }
    };

    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
        }
        return c;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera_scan);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoFocusHandler = new Handler();
        mCamera = getCameraInstance();

        /* Instance barcode scanner */
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        mPreview = new CameraPreview(this, mCamera, previewCb, autoFocusCB);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
        preview.addView(mPreview);

//        scanText = (TextView)findViewById(R.id.scanText);

//        scanButton = (Button)findViewById(R.id.ScanButton);
//
//        scanButton.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                if (barcodeScanned) {
//                    barcodeScanned = false;
//                    scanText.setText("Scanning...");
//                    mCamera.setPreviewCallback(previewCb);
//                    mCamera.startPreview();
//                    previewing = true;
//                    mCamera.autoFocus(autoFocusCB);
//                }
//            }
//        });
        OkButton = (Button) findViewById(R.id.OkButton);

        OkButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                handleButtonClick((Button) v);
            }
        });
    }

    public void handleButtonClick(Button btn) {
        Intent intent = new Intent(this, MenuItemActivity.class);
        intent.putExtra("QR_RESULT", qrInfo);
        startActivity(intent);
//        intent.putExtra(QR_RESULT_EXTRA, qrInfo);
//        setResult(RESULT_OK, intent);
//        finish();
    }

    public void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };


    // Mimic continuous auto-focusing
    AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };
}
