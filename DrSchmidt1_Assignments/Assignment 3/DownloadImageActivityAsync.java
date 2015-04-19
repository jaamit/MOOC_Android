package vandy.mooc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import android.os.SystemClock;

/**
 * An Activity that downloads an image, stores it in a local file on
 * the local device, and returns a Uri to the image file.
 */
public class DownloadImageActivityAsync extends LifecycleLoggingActivity {
    /**
     * Debugging tag used by the Android logger.
     */
    private final String TAG = getClass().getSimpleName();
    
    private Uri filePath;
    
    //private DownloadImageTask mDownloadAsync;

    /**
     * Hook method called when a new instance of Activity is created.
     * One time initialization code goes here, e.g., UI layout and
     * some class scope variable initialization.
     *
     * @param savedInstanceState object that contains saved state information.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Always call super class for necessary
        // initialization/implementation.
        super.onCreate(savedInstanceState);    	

        // Get the URL associated with the Intent data.
        final Uri url = getIntent().getData();
        Log.d(TAG, "DownloadImageActivityAsync for " + url.toString());
        
        //setResult(RESULT_OK);
        //finish();
        
        
        // Plan to call execute() for the AsyncTask;
        new DownloadImageTask(getApplicationContext()).execute(url, null, filePath);
        //new DownloadImageTask(getApplicationContext()).execute(filePath, null, filePath);
        setResult(RESULT_OK);
        

    } // End onCreate
    
} // End DownloadImageActivityAsync
