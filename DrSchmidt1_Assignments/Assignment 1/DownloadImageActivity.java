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
public class DownloadImageActivity extends LifecycleLoggingActivity {
    /**
     * Debugging tag used by the Android logger.
     */
    private final String TAG = getClass().getSimpleName();

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
        // @@ TODO -- you fill in here.
    	super.onCreate(savedInstanceState);    	

        // Get the URL associated with the Intent data.
        // @@ TODO -- you fill in here.
        final Uri url = getIntent().getData();
    	
    	final boolean flag = true;
    	
    	if(flag) {
    	// My Code for Understanding
    	Toast.makeText(getApplicationContext(), "DownloadImageActivity", 
    			Toast.LENGTH_SHORT).show();

    	
    	final Runnable sleepHere = new Runnable() {
    		public void run() {
    			Integer tId = android.os.Process.getThreadPriority(android.os.Process.myTid());    			
    			Log.d(TAG, tId.toString());
    			Log.d(TAG, "Sleeping in this activity");
    			SystemClock.sleep(15000);
    	        Log.d(TAG, "Slept enough... now call finish");
    	        
    	        setResult(RESULT_OK);
    	        
    	        runOnUiThread(new Runnable(){
    	        	@Override
    	        	public void run() {
    	        		Toast toast1 = Toast.makeText(getApplicationContext(), "runOnUiThread", Toast.LENGTH_SHORT);
    	        		toast1.setGravity(Gravity.TOP, 0, 0);
    	        		toast1.show();
    	        		//finish();
    	        	}    	        	
    	        });
    	        
    			}    		
    	};
   	
    	Log.d(TAG, "Start the worker thread");
    	new Thread(sleepHere).start();
    	}

    	// Download the image in the background, create an Intent that
        // contains the path to the image file, and set this as the
        // result of the Activity.

    	
    	else{
    		

        	
        // @@ TODO -- you fill in here using the Android "HaMeR"
        // concurrency framework.  Note that the finish() method
        // should be called in the UI thread, whereas the other
        // methods should be called in the background thread.  See
        // http://stackoverflow.com/questions/20412871/is-it-safe-to-finish-an-android-activity-from-a-background-thread
        // for more discussion about this topic.
    	final Runnable downloadImageTask = new Runnable () {
    		public void run() {
    			final Uri  imagePath = 
    					DownloadUtils.downloadImage(DownloadImageActivity.this, url);
    			
    			int result = imagePath == null
    					? RESULT_CANCELED
    					: RESULT_OK;
    			
    			setResult(result, new Intent("", imagePath));
    			
    			runOnUiThread(new Runnable() {					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						finish();						
					}
				}); 		
    		}
    	};
    	new Thread(downloadImageTask).start();
    	Toast.makeText(getApplicationContext(), "downloadImageTask", 
    			Toast.LENGTH_SHORT).show();
    	}

    }
    
}
