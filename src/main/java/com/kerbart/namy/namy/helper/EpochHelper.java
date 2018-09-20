package com.kerbart.namy.namy.helper;

import com.kerbart.namy.namy.model.Epoch;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class EpochHelper {


    private static Epoch toEpoch(LocalDate date) {
        if (date == null) {
            return Epoch.UNKNOW;
        }
        Integer year = date.getYear();
        log.info("Finding epoch for year {} (from date {})", year, date);
        if (1900 <= year && year <= 1930) {
            return Epoch.OLD;
        }
        if (1930 <= year && year <= 1960) {
            return Epoch.QUITE_OLD;
        }
        if (1960 <= year && year <= 1980) {
            return Epoch.CASUAL;
        }
        if (1980 <= year && year <= 2000) {
            return Epoch.RECENT;
        }
        if (2000 <= year && year <= 2020) {
            return Epoch.TRENDY;
        }
        return Epoch.UNKNOW;
    }


}
