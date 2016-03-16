package com.yuvi.nepalitoenglishdateconverter;

import android.content.Context;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class LocalCalendar {

	static String TAG = LocalCalendar.class.getName();
	static String TZ_NEPAL = "Asia/Kathmandu";
	static String TZ_SYS;
	static String HH_FORMAT = "hh:mm aa";

	public static Calendar getNepalCal() {

		if (TimeZone.getDefault().getID().equalsIgnoreCase(TZ_NEPAL)) {
			return Calendar.getInstance();
		}
		return LocalCalendar.getTZTime(Calendar.getInstance(), TZ_NEPAL);

	}

	public static String getSysTZ() {
		return TimeZone.getDefault().getID();
	}

	public static Calendar getTZTime(Calendar cal, String TZ) {

		TimeZone timeZone = TimeZone.getTimeZone(TZ);
		// Calendar targetCal = new GregorianCalendar(timeZone);
		Calendar targetCal = Calendar.getInstance();

		targetCal.setTimeInMillis(cal.getTimeInMillis() + timeZone.getOffset(cal.getTimeInMillis())
				- TimeZone.getDefault().getOffset(cal.getTimeInMillis()));

		// targetCal.setTimeZone(timeZone);
		return targetCal;
	}

	public static String getDateStr(Date date) {
		return (new SimpleDateFormat(LocalCalendar.HH_FORMAT + "   E, dd MMM ")).format(date);
	}

	public static String getDay(Date date) {
		return (new SimpleDateFormat("E")).format(date);
	}

	public static void getTZInfo() {
//		AppLog.logString(LocalCalendar.class.getName(), "Sys TimeZone :" + TimeZone.getDefault().getID());
//		AppLog.showLog(LocalCalendar.class.getName(), "Sys : " + Group.formatDate(Calendar.getInstance()) + "  |  "
//				+ "Nep : " + Group.formatDate(LocalCalendar.getNepalCal()));
	}

	// public static Calendar getADBS(Calendar cal, Context context) {
	// NLSDateConverter dateConverter = new NLSDateConverter();
	// String dateformat =
	// PreferenceManager.getDefaultSharedPreferences(context).getString("dateformat",
	// NLSConstants.DATEFORMAT_BS);
	// if (dateformat.equalsIgnoreCase(NLSConstants.DATEFORMAT_BS)) {
	// return dateConverter.convertADToBS(Group.easyDateFormat(cal,
	// "yyyy-MM-d"), "-");
	// } else if (dateformat.equalsIgnoreCase(NLSConstants.DATEFORMAT_AD)) {
	// return cal;
	// }
	// return cal;
	// }

	public static String getProperMonth(int mth, Context context) {
		String dateformat = PreferenceManager.getDefaultSharedPreferences(context).getString(
				"dateformat","B.S.");
		// Log.d(TAG, "Month to convert : " + mth);
		return dateformat.equalsIgnoreCase(DateConstant.DATEFORMAT_BS) ? DateConstant.monthsBS[mth]
				: DateConstant.monthsAD[mth];

	}
}
