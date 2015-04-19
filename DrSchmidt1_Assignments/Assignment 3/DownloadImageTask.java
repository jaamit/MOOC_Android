package vandy.mooc;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;

public class DownloadImageTask extends AsyncTask<Uri, Integer, Uri>{
    /**
     * Debugging tag used by the Android logger.
     */
    private final String TAG = getClass().getSimpleName();
    
    private final Context mContext; 
    
    /**
     * Constructor to pass activity context from Activity to AsyncTask
     * @param context
     */
    
    public DownloadImageTask(Context context) {
    	mContext = context;    	
    }

	@Override
	protected void onPreExecute() {
		Utils.showToast(mContext, "downloadImage", Gravity.CENTER);
		Log.d(TAG, "onPreExecute");
	}
	
	@Override
	protected Uri doInBackground(Uri... params) {
		// TODO Auto-generated method stub
		Log.d(TAG, "doInBackground");
		return Utils.downloadImage(mContext, params[0]);
	}
	
	@Override
	protected void onPostExecute(Uri url){
		Log.d(TAG, "onPostExecute");
		Utils.showToast(mContext, url.toString(), 0);		
	}
	
}
