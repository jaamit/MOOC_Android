package vandy.mooc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Implementation of Advanced Downloaded Image Viewer (P1 - Assignment 3)
 * It is based on Downloaded Image Viewer (P1 - Assignment 1) i.e. 
 * uses manifest, layout, strings and some java code from it.
 * 
 * A main Activity that prompts the user for a URL to an image and
 * then uses Intents and other Activities to download the image and
 * view it.
 */
public class MainActivity extends LifecycleLoggingActivity {
    /**
     * Debugging tag used by the Android logger.
     */
    private final String TAG = getClass().getSimpleName();

    /**
     * A value that uniquely identifies the request to download an
     * image.
     */
    private static final int DOWNLOAD_IMAGE_REQUEST = 1;
    
    /**
     * EditText field for entering the desired URL to an image.
     */
    private EditText mUrlEditText;

    /**
     * URL for the image that's downloaded by default if the user
     * doesn't specify otherwise.
     */
    private Uri mDefaultUrl =
        Uri.parse("http://www.dre.vanderbilt.edu/~schmidt/robot.png");
    
    /**
     * Hook method called when a new instance of Activity is created.
     * One time initialization code goes here, e.g., UI layout and
     * some class scope variable initialization.
     *
     * @param savedInstanceState object that contains saved state information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Always call super class for necessary
        // initialization/implementation.
        // @@ TODO -- DONE. you fill in here.
    	super.onCreate(savedInstanceState);

        // Set the default layout.
        setContentView(R.layout.main_activity);

        // Cache the EditText that holds the urls entered by the user
        // (if any).        
    	mUrlEditText = (EditText) findViewById(R.id.url);    	
    } /* End onCreate */
    
    /**
     * Called by the Android Activity framework when the user clicks
     * the "Download Image" button.
     *
     * @param view The view.
     */
    public void downloadImage(View view) {
        try {
        	Log.d(TAG, "Download Image Button Pressed");
        	Utils.showToast(getApplicationContext(), "downloadImage", Gravity.CENTER);
        	
            // Call the makeDownloadImageIntent() factory method to
            // create a new Intent to an Activity that can download an
            // image from the URL given by the user.  In this case
            // it's an Intent that's implemented by the
            // AdvancedDownloadImageActivity.
        	// Using mDefaultUrl till getURL() gets fixed.
            Intent intent = 
            		makeDownloadImageIntent(mDefaultUrl);

            // Start the Activity associated with the Intent, which
            // will download the image and then return the Uri for the
            // downloaded image file via the onActivityResult() hook
            // method.
            //startActivityForResult(intent, DOWNLOAD_IMAGE_REQUEST);
            startActivity(intent);
        } catch (Exception e) {        	
            e.printStackTrace();
            finish();
        }
    } /* End downloadImage() */
    
    /**
     * Factory method that returns an implicit Intent for downloading
     * an image.
     */
    private Intent makeDownloadImageIntent(Uri url) {
        // Create an intent that will download the image from the web.    	
        return new Intent(Intent.ACTION_WEB_SEARCH, url);
    } // End makeDownloadImageIntent
    
    /**
     * Get the URL to download based on user input.
     */
    protected Uri getUrl() {
        Uri url = mDefaultUrl;

        // Get the text the user typed in the edit text (if anything).
        if(mUrlEditText.getText().toString() != null) {
        	url = Uri.parse(mUrlEditText.getText().toString());
        	Log.d(TAG, " Hello");
        }
        else {
        	//url = mDefaultUrl;
        	Log.d(TAG, "WORLD");
        }
      
    	Log.d(TAG, "DownloadImageActivityAsync for " + mUrlEditText.getText().toString());
        // Do a sanity check to ensure the URL is valid, popping up a
        // toast if the URL is invalid.
        if (URLUtil.isValidUrl(url.toString())) {
        	return url;
        }           
        else {
        	Utils.showToast(getApplicationContext(), "Invalid URL", Gravity.FILL);
            return null;
        } 
    } // End getUrl()

    
	
	
} /* End MainActivity */