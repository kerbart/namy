package com.kerbart.namy.namy.model;

import lombok.Data;

import java.util.List;

@Data
public class Firstname {

    private String id;

    /*
     * default value, normalized (upper case, without accent)
     */
    private String value;

    /*
     * List of alternative writing, with accents (UTF-8) lowercase
     */
    private List<String> alternatives;

    /*
     * all occurences (date, number of occurence, male, femal or both)
     */
    private List<Occurence> occurences;

    /*
     * Consolidated Maximum to give best years
     */
    private List<BestYear> bestYear;

    /*
     * Metadata (lenght, composed name, origines) needed for collaborative filtering
     */
    private Metadata metadata;

    /*
     * List of all known origins
     */
    private List<Origin> origins;

    /*
     * Epoch (OLD / MID / NEW)
     */
    private Epoch epoch;

    public BestYear findBestyear() {
        if (this.bestYear == null)
            return null;
        this.bestYear.sort((y1, y2) ->
                y1.getNumber() > y2.getNumber() ? 1 : -1
        );
        return this.bestYear.get(0);
    }

}
