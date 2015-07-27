package com.jcmore2.elapsedtime;

/**
 * Custom use of Log
 * 
 * @author jcmore2@gmail.com
 * 
 */
class Log {

	/**
	 * Shared instance
	 */
	public static Log sharedInstance = null;

	public boolean LOG = true;

	public LOG_TYPE log_type = LOG_TYPE.DEBUG;

	public enum LOG_TYPE {

		INFO, ERROR, DEBUG, VERBOSE, WARNING
	}

	/**
	 * Get instance of class.
	 * 
	 */
	public synchronized static Log getSharedInstance() {

		if (sharedInstance == null) {

			sharedInstance = new Log();

		}
		return sharedInstance;

	}

	private Log() {

	}

	public void log(String tag, String string) {
		if (log_type == LOG_TYPE.DEBUG)
			d(tag, string);
		if (log_type == LOG_TYPE.INFO)
			i(tag, string);
		if (log_type == LOG_TYPE.ERROR)
			e(tag, string);
		if (log_type == LOG_TYPE.VERBOSE)
			v(tag, string);
		if (log_type == LOG_TYPE.WARNING)
			w(tag, string);

	}

	private void i(String tag, String string) {
		if (LOG)
			android.util.Log.i(tag, string);
	}

	private void e(String tag, String string) {
		if (LOG)
			android.util.Log.e(tag, string);
	}

	private void d(String tag, String string) {
		if (LOG)
			android.util.Log.d(tag, string);
	}

	private void v(String tag, String string) {
		if (LOG)
			android.util.Log.v(tag, string);
	}

	public void w(String tag, String string) {
		if (LOG)
			android.util.Log.w(tag, string);
	}
}
