package de.comdirect.collabothon2016.collabothon2016.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Vote {

    public long userId;
    public long indexInVote;
    public String title; // stock name
    public String comment;
    public long votes;
    public String wertpapier; // stock isin

//    public boolean isInvested;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
