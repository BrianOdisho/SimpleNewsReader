package org.brianodisho.newsreader.util;


import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Formatter {

    public static String fromTimestampToString(String timestamp) {
        if (timestamp != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                Date timestampDate = simpleDateFormat.parse(timestamp);
                return DateUtils.getRelativeTimeSpanString(timestampDate.getTime()).toString();
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
