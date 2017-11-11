package by.tiranid.tracker.dao.service;

import java.util.Date;

public class TestUtils {

    public static long getCurrentDateTimeWithRound() {
        long time = new Date().getTime();
        // Round up to a second (ms)
        long accuracy = 1000;
        int k = 0;
        if (time % accuracy >= 500) {
            k = 1000;
        }
        return (time / accuracy * accuracy + k);
    }
}
