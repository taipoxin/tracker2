package by.tiranid.tracker.dao.model;

import java.sql.Date;
import java.sql.Time;

public class EntityUtils {

    /**
     * @param date  yyyy-[m]m-[d]d
     * @param iters 0-127
     * @param time  hh:mm:ss
     * @return
     */
    public static WorkDaysEntity createTestWorkDaysEntity(String date, Byte iters, String time)  {
        WorkDaysEntity record = new WorkDaysEntity();

        record.setWorkDate(Date.valueOf(date));
        record.setIterations(iters);
        record.setWorkTime(time);

        return record;
    }

    /**
     *
     * @param date yyyy-[m]m-[d]d
     * @param time hh:mm:ss
     * @param duration hh:mm:ss
     * @return
     * @throws Exception
     */
    public static WorkItersEntity createTestWorkItersEntity(String date, String time, String duration) throws Exception {
        WorkItersEntity record = new WorkItersEntity();
        // yyyy-[m]m-[d]d
        record.setDdate(Date.valueOf(date));
        // hh:mm:ss
        record.setTtime(Time.valueOf(time));
        // hh:mm:ss
        record.setDuration(Time.valueOf(duration));
        return record;
    }

    public static long getCurrentDateTimeWithRound() {
        long time = new java.util.Date().getTime();
        // Round up to a second (ms)
        long accuracy = 1000;
        int k = 0;
        if (time % accuracy >= 500) {
            k = 1000;
        }
        return (time / accuracy * accuracy + k);
    }


}
