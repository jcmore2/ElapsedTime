package com.jcmore2.elapsedtime;

import java.util.HashMap;

import android.content.Context;
import android.widget.Toast;

import com.jcmore2.elapsedtime.Log.LOG_TYPE;

/**
 * Library use to know elapsed time in your project, method, database....
 * 
 * @author jcmore2@gmail.com
 * 
 */
public class ElapsedTime {

	private final static String TAG = "ElapsedTime";

	/**
	 * Shared instance
	 */
	public static ElapsedTime sharedInstance = null;
	private Context context;

	private HashMap<String, Long> timeTable;

	private Log log;

	private boolean millis = false;

	/**
	 * Get instance of class.
	 * 
	 */
	public synchronized static ElapsedTime getSharedInstance(Context context) {

		if (sharedInstance == null) {

			sharedInstance = new ElapsedTime(context);

		}
		return sharedInstance;

	}

	private ElapsedTime(Context context) {
		this.context = context;
		timeTable = new HashMap<String, Long>();
		log = Log.getSharedInstance();
	}

	/**
	 * Set log enabled
	 * 
	 * @param enabled
	 */
	public ElapsedTime setLogEnabled(boolean enabled) {

		sharedInstance.log.LOG = enabled;

		return sharedInstance;
	}

	/**
	 * Set log type
	 * 
	 * @param type
	 */
	public ElapsedTime setLogType(LOG_TYPE type) {

		sharedInstance.log.log_type = type;

		return sharedInstance;

	}

	/**
	 * set time in millis (default seconds)
	 * 
	 * @param timeInMillis
	 */
	public ElapsedTime setMillis(boolean timeInMillis) {

		sharedInstance.millis = timeInMillis;

		return sharedInstance;
	}

	/**
	 * Start count time
	 * 
	 * @param id
	 */
	public void start(String id) {
		start(id, false);
	}

	/**
	 * Start count time
	 * 
	 * @param id
	 * @param showToast
	 */
	public void start(String id, boolean showToast) {
		if (id == null)
			return;

		if (showToast)
			Toast.makeText(context, TAG + ": " + id + "->Start",
					Toast.LENGTH_SHORT).show();
		log.log(TAG, id + "--->Start");
		long time = System.currentTimeMillis();
		timeTable.put(id, time);
	}

	/**
	 * Stop count time
	 * 
	 * @param id
	 */
	public void stop(String id) {

		stop(id, false);
	}

	/**
	 * Stop count time
	 * 
	 * @param id
	 * @param showToast
	 */
	public void stop(String id, boolean showToast) {
		if (id == null)
			return;
		if (millis) {
			long millis = System.currentTimeMillis()
					- timeTable.get(id).longValue();
			if (showToast)
				Toast.makeText(context,
						TAG + ": " + id + "->Stop (" + millis + " Millis)",
						Toast.LENGTH_SHORT).show();
			log.log(TAG, id + "--->Stop: " + millis + " Millis");
		} else {
			int seconds = (int) (((System.currentTimeMillis() / 1000) % 60) - ((timeTable
					.get(id).longValue() / 1000) % 60));
			if (showToast)
				Toast.makeText(context,
						TAG + ": " + id + "->Stop (" + seconds + " Sec)",
						Toast.LENGTH_SHORT).show();
			log.log(TAG, id + "--->Stop: " + seconds + " Sec");
		}
	}
}