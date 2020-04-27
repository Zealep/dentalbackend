package com.zealep.dental.app.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    public static LocalDate convertStringToLocalDate(String date){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateConvert= LocalDate.parse(date,formatter);
            return dateConvert;
    }

}
