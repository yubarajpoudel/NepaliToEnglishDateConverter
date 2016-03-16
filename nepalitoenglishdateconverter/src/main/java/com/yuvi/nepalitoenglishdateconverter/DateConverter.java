package com.yuvi.nepalitoenglishdateconverter;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


/**
 * @author i80429 Now,
 *         <p/>
 *         here's the algorithm to convert Nepali dates to English dates.
 *         <p/>
 *         # ALGORITHM:
 *         <p/>
 *         # Step 1: Define the least possible Nepali date from your valid range
 *         ( 2000/01/01 Wednesday) - Since, in our above list, the least
 *         possible valid Nepali year is 2000, let's take the least possible
 *         valid Nepali year be 2000/01/01 which is Wednesday according to a
 *         Nepali calendar.
 *         <p/>
 *         # Step 2: Define the equivalent English date ( 1943/04/14 Wednesday)
 *         - Here we should find out the actual English date equivalent to
 *         Nepali date that we picked up in step 1. You can find the equivalent
 *         English date from any Nepali to English date conversion tool, which
 *         we can easily find in internet. For my case, I got 1943/04/14.
 *         <p/>
 *         # Step 3: Pick up a Nepali date you want to convert.
 *         <p/>
 *         # Step 4: Count the total number of days in between the above two
 *         Nepali Dates. - Like for English dates, the Java date API like
 *         Jodatime, build-in java.util.Calender etc. won't work to directly
 *         determine the number of days in between two Nepali dates. You have to
 *         devise a logic on your own with the help of above Nepali Year-Month
 *         list.. But don't panic, I have already written a method for you.
 *         <p/>
 *         # Step 5: Add the total number of days (calculated in Step 4.) to the
 *         equivalent base English date (calculated in Step 2. ). - You can
 *         achieve this by simply using java.util.Calendar or any other
 *         date/time APIs like JodaTime.
 *         <p/>
 *         # Step 6: Congratulation, you have the converted English date on your
 *         hand.
 *         <p/>
 *         <p/>
 *         --------------------------------------------------------------------
 *         -----------------------------
 *         <p/>
 *         Now, here's the algorithm to convert English dates to Nepali dates.
 *         <p/>
 *         # ALGORITHM:
 *         <p/>
 *         # Step 1: Define the least possible English date from your valid
 *         range ( 1944/01/01 Saturday) - Since, in our above list, the least
 *         possible valid Nepali year is 2000, the corresponding English year
 *         would be 1944. So, let's suppose, the least possible valid English
 *         year be 1944/01/01.
 *         <p/>
 *         # Step 2: Define the equivalent Nepali date ( 2000/09/17 Saturday) -
 *         Here we should find out the actual Nepali date equivalent to English
 *         date that we picked up in step 1. You can find the equivalent Nepali
 *         date from any English to Nepali conversion tool. For my case, I got
 *         2000/09/17.
 *         <p/>
 *         # Step 3: Pick up an English Date you want to convert.
 *         <p/>
 *         # Step 4: Count the total number of days in between the above two
 *         English Dates. - For this you can use any Java API like Jodatime,
 *         build-in java.util.Calender or even you can have your own logic to
 *         calculate them.
 *         <p/>
 *         # Step 5: Add the total number of days (calculated in Step 4.) to the
 *         equivalent base Nepali date (calculated in Step 2. ) with the help of
 *         the above Nepali Year-Month list. - The addition of the number of
 *         days will be in a loop, one day at a time. Withing the loop, you need
 *         to increment the date by one day. For example, after one iteration,
 *         the base Nepali date would be 2000/09/18 Sunday, then 2000/09/19
 *         Monday, then 2000/09/20 Tuesday and so on.
 *         <p/>
 *         # Step 6: Congratulation, you have the converted Nepali date on your
 *         hand.
 */
public class DateConverter {

    /**
     * The 0s at index 0 are dummy values so as to make the int array of
     * days in months seems more intuitive that index 1 refers to first
     * month "Baisakh", index 2 refers to second month "Jesth" and so on.
     */
    final static String TAG = DateConverter.class.getName();
    Map<Integer, int[]> nepaliMap = new HashMap<Integer, int[]>();
    String days[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public DateConverter() {
        initNepaliMap();
    }

	/*public static void main(String[] arg) {
        int engYear = 1984;
		int engMonth = 8;
		int engDay = 30;

		// NLSDateConverter np = new NLSDateConverter();
		// String bs = np.convertADToBS("1984-8-30", "");
		// System.out.println("AD : " + engYear + "-" + engMonth + "-" + engDay
		// + " = > " + bs);

		int nepYear = 2041;
		int nepMonth = 5;
		int nepDay = 14;

		// String ad = np.convertBSToAD("2041-05-14", "-");
		// System.out.println("BS : " + nepYear + "-" + nepMonth + "-" + nepDay
		// + " = > " + ad);
	}*/

    public void initNepaliMap() {
        nepaliMap.put(2000, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2001, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2002, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2003, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2004, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2005, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2006, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2007, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2008, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        nepaliMap.put(2009, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2010, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2011, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2012, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        nepaliMap.put(2013, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2014, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2015, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2016, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        nepaliMap.put(2017, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2018, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2019, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2020, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2021, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2022, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        nepaliMap.put(2023, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2024, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2025, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2026, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2027, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2028, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2029, new int[]{0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2030, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2031, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2032, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2033, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2034, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2035, new int[]{0, 30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        nepaliMap.put(2036, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2037, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2038, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2039, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        nepaliMap.put(2040, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2041, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2042, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2043, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        nepaliMap.put(2044, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2045, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2046, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2047, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2048, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2049, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        nepaliMap.put(2050, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2051, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2052, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2053, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        nepaliMap.put(2054, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2055, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2056, new int[]{0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2057, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2058, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2059, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2060, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2061, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2062, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31});
        nepaliMap.put(2063, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2064, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2065, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2066, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        nepaliMap.put(2067, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2068, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2069, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2070, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        nepaliMap.put(2071, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2072, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2073, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2074, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2075, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2076, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        nepaliMap.put(2077, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2078, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2079, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2080, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        nepaliMap.put(2081, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2082, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2083, new int[]{0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2084, new int[]{0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2085, new int[]{0, 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2086, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2087, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2088, new int[]{0, 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2089, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2090, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
    }

    public ArrayList<DateHolder> getLast7Days(String nepaliDate, String spliiiter) {
        String items[] = nepaliDate.split(spliiiter);
        int engYear = Integer.parseInt(items[0]);
        int engMonth = Integer.parseInt(items[1]);
        int engDay = Integer.parseInt(items[2]);

        int startingEngYear = 2013;
        int startingEngMonth = 12;
        int startingEngDay = 1;
        int dayOfWeek = Calendar.SUNDAY; // 1944/1/1 is Saturday
        int startingNepYear = 2070;
        int startingNepMonth = 8;
        int startingNepDay = 16;

        Calendar currentEngDate = new GregorianCalendar();
        currentEngDate.set(engYear, engMonth - 1, engDay);
        Calendar baseEngDate = new GregorianCalendar();

        baseEngDate.set(startingEngYear, startingEngMonth - 1, startingEngDay);
        long totalEngDaysCount = daysBetween(baseEngDate, currentEngDate);

        //

        // initialize required Nepali date variables with starting Nepali date

        int nepYear = startingNepYear;
        int nepMonth = startingNepMonth;
        int nepDay = startingNepDay;

        // ArrayList<Calendar> allCal = new ArrayList<Calendar>();
        ArrayList<DateHolder> allCal = new ArrayList<DateHolder>();
        // decrement totalEngDaysCount until its value becomes 0
        boolean done = false;
        while (totalEngDaysCount != 0) {

            // getting the total number of days in month nepMonth in year
            // nepYear
            int daysInIthMonth = nepaliMap.get(nepYear)[nepMonth];

            nepDay++; // incrementing nepali day

            if (nepDay > daysInIthMonth) {
                nepMonth++;
                nepDay = 1;
            }
            if (nepMonth > 12) {
                nepYear++;
                nepMonth = 1;
            }

            dayOfWeek++; // count the days in terms of 7 days
            if (dayOfWeek > 7) {
                dayOfWeek = 1;
            }

            DateHolder mydate = new DateHolder(nepYear, nepMonth - 1, nepDay);
            // 0-11 months requird while nepMonth is 1-12

            totalEngDaysCount--;
            if (totalEngDaysCount == 0 && !done) {
                // Log.d(TAG, "Week Day " +
                // NLSCal.getNepalCal().get(Calendar.DAY_OF_WEEK));
                totalEngDaysCount = 7 - LocalCalendar.getNepalCal().get(Calendar.DAY_OF_WEEK);
                done = true;
            }

            if (allCal.size() == 7) {
                allCal.remove(0);
            }
            // Log.d(TAG, totalEngDaysCount + ">> " + nepYear + "-" + nepMonth +
            // "-" + nepDay + " B.S.");
            allCal.add(mydate);

        }

        // for (int i = 0; i < allCal.size(); i++) {
        // //Log.d(TAG, "VAR : " + allCal.get(i).year + "-" +
        // allCal.get(i).month + "-" + allCal.get(i).day + " B.S.");
        // }

        return allCal;
    }

    public DateHolder convertADToBS(String nepaliDate, String spliiiter) {
        String items[] = nepaliDate.split(spliiiter);
        int engYear = Integer.parseInt(items[0]);
        int engMonth = Integer.parseInt(items[1]);
        int engDay = Integer.parseInt(items[2]);

        int startingEngYear = 1943;
        int startingEngMonth = 4;
        int startingEngDay = 14;
        int dayOfWeek = Calendar.SUNDAY; // 1944/1/1 is Saturday

        int startingNepYear = 2000;
        int startingNepMonth = 1;
        int startingNepDay = 1;

        // int startingEngYear = 2013;
        // int startingEngMonth = 11;
        // int startingEngDay = 16;
        // int dayOfWeek = Calendar.SATURDAY; // 1944/1/1 is Saturday
        //
        // int startingNepYear = 2070;
        // int startingNepMonth = 8;
        // int startingNepDay = 1;

        Calendar currentEngDate = Calendar.getInstance();
        currentEngDate.set(engYear, engMonth - 1, engDay);

        Calendar baseEngDate = Calendar.getInstance();
        baseEngDate.set(startingEngYear, startingEngMonth - 1, startingEngDay);
        // long totalEngDaysCount = getUnsignedDiffInDays(baseEngDate.getTime(),
        // currentEngDate.getTime());
        long totalEngDaysCount = daysBetween(baseEngDate, currentEngDate);

        //

        // initialize required Nepali date variables with starting Nepali date

        int nepYear = startingNepYear;
        int nepMonth = startingNepMonth;
        int nepDay = startingNepDay;

        // decrement totalEngDaysCount until its value becomes 0
        while (totalEngDaysCount != 0) {

            // getting the total number of days in month nepMonth in year
            // nepYear
            int daysInIthMonth = nepaliMap.get(nepYear)[nepMonth];
            // System.out.println("nepYear / nepMonth / daysInIthMonth : " +
            // nepYear + "/" + nepMonth + "/" + daysInIthMonth);

            nepDay++; // incrementing nepali day
            // System.out.println("\tnepDay : " + nepDay);
            if (nepDay > daysInIthMonth) {
                nepMonth++;
                nepDay = 1;
            }
            if (nepMonth > 12) {
                nepYear++;
                nepMonth = 1;
            }

            dayOfWeek++; // count the days in terms of 7 days
            if (dayOfWeek > 7) {
                dayOfWeek = 1;
            }

            totalEngDaysCount--;
        }

        return new DateHolder(nepYear, nepMonth, nepDay, dayOfWeek);
//		return new DateHolder(nepYear, nepMonth, nepDay);
//		return new DateHolder(nepYear, nepMonth - 1, nepDay);//original

        // return calBS;
    }

    public long daysBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
        // return (endDate.getTimeInMillis() - startDate.getTimeInMillis()) /
        // (1000 * 60 * 60 * 24);

    }

    public String convertBSToAD(String nepaliDate, String spliiiter) {
        String items[] = nepaliDate.split(spliiiter);
        int nepYear = Integer.parseInt(items[0]);
        int nepMonth = Integer.parseInt(items[1]);
        int nepDay = Integer.parseInt(items[2]);

        // standard nepali date
        int startingNepYear = 2000;
        int startingNepMonth = 1;
        int startingNepDay = 1;
        int dayOfWeek = Calendar.SUNDAY; // 2000/1/1 is Wednesday
        // standard english date
        int startingEngYear = 1943;
        int startingEngMonth = 4;
        int startingEngDay = 14;

        long totalNepDaysCount = 0;

        // count total days in-terms of year
        for (int i = startingNepYear; i < nepYear; i++) {
            for (int j = 1; j <= 12; j++) {
                totalNepDaysCount += nepaliMap.get(i)[j];
            }
        }

        // count total days in-terms of month
        for (int j = startingNepMonth; j < nepMonth; j++) {
            totalNepDaysCount += nepaliMap.get(nepYear)[j];
        }

        // count total days in-terms of date
        totalNepDaysCount += nepDay - startingNepDay;

		/*
		 * Step 5: Now, let's add "totalNepDaysCount" to starting base English
		 * date. But I don't know why, unfortunately, adding the days in the
		 * base English date using java.util.Calendar didn't work for me. That's
		 * why I had to write my logic to add the days in an English date as
		 * follows:
		 */
        int[] daysInMonth = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] daysInMonthOfLeapYear = new int[]{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // calculation of equivalent english date...
        int engYear = startingEngYear;
        int engMonth = startingEngMonth;
        int engDay = startingEngDay;

        int endDayOfMonth = 0;

        while (totalNepDaysCount != 0) {
            if (isLeapYear(engYear)) {
                endDayOfMonth = daysInMonthOfLeapYear[engMonth];
            } else {
                endDayOfMonth = daysInMonth[engMonth];
            }
            engDay++;
            dayOfWeek++;
            if (engDay > endDayOfMonth) {
                engMonth++;
                engDay = 1;
                if (engMonth > 12) {
                    engYear++;
                    engMonth = 1;
                }
            }
            if (dayOfWeek > 7) {
                dayOfWeek = 1;
            }
            totalNepDaysCount--;
        }

         return engYear + "-" + (engMonth < 10 ? "0" + engMonth : engMonth) +
         "-" + (engDay < 10 ? "0" + engDay : engDay)
         + " A.D.";

//        return new int[]{engYear, engMonth, engDay};

//         Calendar calAD = Calendar.getInstance();
//         calAD.set(Calendar.YEAR, engYear);
//         calAD.set(Calendar.MONTH, engMonth-1);
//         calAD.set(Calendar.DAY_OF_MONTH, engDay);
//         calAD.set(Calendar.DAY_OF_WEEK, dayOfWeek);
//         return calAD;
    }

    public static boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        } else {
            return year % 4 == 0;
        }
    }

}
