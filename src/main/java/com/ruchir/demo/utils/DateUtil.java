package com.ruchir.demo.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@UtilityClass
public class DateUtil {

    @UtilityClass
    public class DateFormat {

        public final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        public final String YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        public final String YYYY_MM_DD = "yyyy-MM-dd";
        public final String DD_MM_YY_HH_MM_A = "dd/MM/yy, hh:mm a";
        public final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
        public final String DD_MM_YYYY= "dd-MM-yyyy";
        public final String DD_MM_YY = "dd/MM/yy";
        public final String YYYYMMDD = "yyyyMMdd";
        public final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
    }

    public static String getDateAsStringWithTheFormat(Date date, String format) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return localDateTime.format(dateFormat);
    }

    public static Date getDate(String date, String format) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, dateFormat);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime getLocalDateTime(Long fieldValue){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(fieldValue), ZoneId.systemDefault());
    }

    public static LocalDateTime getLocalDateFromDate(Date date){
        return LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
    }


    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date removeDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1 * days);
        return cal.getTime();
    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);//minus number would decrement the days
        return cal.getTime();
    }

    public static LocalDateTime plusMinutes(LocalDateTime localDateTime, Integer minutes){
        return localDateTime.plusMinutes(minutes);
    }

    public static Date setHoursAndMinutes(Date date, int hours, int mins) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, mins);
        return cal.getTime();
    }

    public static LocalDateTime setHoursAndMinutes(LocalDateTime date, int hours, int mins) {
        return date.with(LocalTime.of(hours, mins));
    }

    public static Date getDateFromLocalTime(LocalTime time, Integer year , Integer month, Integer day){
        Date today = Date.from(Instant.now());
        Instant instant = time.atDate(LocalDate.of(year, month, day)).
                atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static List<String> getDatesList(Date startingDay, Integer numberOfDays, String dateFormat){
        List<String> dates = new ArrayList<String>();
        for (int day = 0; day < numberOfDays; day++) {
            dates.add(DateUtil.getDateAsStringWithTheFormat(DateUtil.addDays(startingDay, day), dateFormat));
        }
        return dates;
    }

    public static LocalDateTime appendHoursAndMinutesForDate(String date, String dateFormat,Integer hours, Integer minutes) throws ParseException {
        Date dateObject = getDate(date, dateFormat);
        LocalDateTime localDateTime = getLocalDateFromDate(dateObject);
        localDateTime = localDateTime.plusHours(hours).plusMinutes(minutes);
        return localDateTime;
    }

    public static boolean isAfterOrEquals(LocalDateTime currentTime, LocalDateTime localDateTime){
        return (currentTime.equals(localDateTime) || currentTime.isAfter(localDateTime));
    }
    public static boolean isAfter(LocalDateTime localDateTime1, LocalDateTime localDateTime2){
        return localDateTime1.isAfter(localDateTime2);
    }

    public static boolean isBefore(LocalDateTime localDateTime1, LocalDateTime localDateTime2){
        return localDateTime1.isBefore(localDateTime2);
    }

    public static LocalDateTime getEndOfTheDay(){
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.with(LocalTime.MAX);
        return localDateTime;
    }

    public static boolean isBetween(LocalDateTime givenDate, LocalDateTime localDateTime1, LocalDateTime localDateTime2){
        return (!localDateTime1.isAfter(givenDate) || localDateTime1.equals(givenDate)) && (localDateTime2.isAfter(givenDate) || localDateTime2.equals(givenDate));
    }

    public static LocalDateTime plusDays(LocalDateTime localDateTime, Integer days){
        return localDateTime.plusDays(days);
    }

    public static String getDateInFormat(LocalDateTime currentDate, String format) {
        var dtf = DateTimeFormatter.ofPattern(format);
        if (Objects.isNull(currentDate)) {
            currentDate = LocalDateTime.now();
        }
        return currentDate.format(dtf);
    }

    public static String formatTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static String convertTo12HourFormat(String time24Hour) {
        LocalTime localTime = LocalTime.parse(time24Hour, DateTimeFormatter.ofPattern("HH:mm"));
        if (localTime.getMinute() == 0) {
            return localTime.format(DateTimeFormatter.ofPattern("h a"));
        } else {
            return localTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        }
    }
    public static String getDiffInMinutes(LocalDateTime sourceDate, LocalDateTime targetDate) {
        return String.valueOf(Duration.between(targetDate, sourceDate).toMinutes());
    }

    public static String getRemainingTime(LocalDateTime time) {
        if(Objects.nonNull(time)) {
            return getDiffInMinutes(time, LocalDateTime.now());
        }
        return null;
    }

    public static long getDaysDifferenceFromCurrentDate(String date) {
        LocalDate providedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate currentDate = LocalDate.now();
        return Duration.between(currentDate, providedDate).toDays();
    }

    public static String convertToFormattedDate(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM");
        return outputFormatter.format(date);
    }
}
