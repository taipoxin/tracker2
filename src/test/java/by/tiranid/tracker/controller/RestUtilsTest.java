package by.tiranid.tracker.controller;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;

public class RestUtilsTest {

    @Test
    public void testConvertStringToTime() throws Exception {

        String t1 = Long.toString(1234563312);
        Time tt1 = RestUtils.convertStringToTime(t1);
        Assert.assertNotNull(tt1);
        System.out.println("some time from long ms: " + tt1.toString());

        String t2 = "17:56:33";
        Time tt2 = RestUtils.convertStringToTime(t2);
        Assert.assertEquals(t2, tt2.toString());

        Time tt3 = RestUtils.convertStringToTime(null);
        Assert.assertNotNull(tt3);
        System.out.println("current time: " + tt3.toString());

        String t4 = "12342567J";
        Time tt4 = RestUtils.convertStringToTime(t4);
        Assert.assertNull(tt4);
    }

    @Test
    public void testConvertStringToDurationTime() throws Exception {


        String t2 = "17:56:33";
        Time tt2 = RestUtils.convertStringToDurationTime(t2);
        Assert.assertEquals(t2, tt2.toString());

        Time tt3 = RestUtils.convertStringToDurationTime(null);
        Assert.assertEquals(RestUtils.durationDefault, tt3.toString());
        System.out.println("pattern time: " + tt3.toString());

        String t4 = Long.toString(1234563312);
        Time tt4 = RestUtils.convertStringToDurationTime(t4);
        Assert.assertNull(tt4);

    }

    @Test
    public void testConvertStringToDate() throws Exception {

        String t1 = Long.toString(1234563312);
        Date tt1 = RestUtils.convertStringToDate(t1);
        Assert.assertNotNull(tt1);
        System.out.println("some date from long ms: " + tt1.toString());

        String t2 = "1995-10-30";
        Date tt2 = RestUtils.convertStringToDate(t2);
        Assert.assertEquals(t2, tt2.toString());

        Date tt3 = RestUtils.convertStringToDate(null);
        Assert.assertNotNull(tt3);
        System.out.println("current date: " + tt3.toString());

        String t4 = "12342567J";
        Date tt4 = RestUtils.convertStringToDate(t4);
        Assert.assertNull(tt4);
    }
}
