package de.comdirect.collabothon2016.collabothon2016.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

public class Group {

    public int id;
    public List<Nutzer> user;
    public String interval;
    public double amount;
    public String nextPeriod;
    public String investingSince;
    public String groupName;
    public Map<Nutzer, GroupScore> userScores;
    public double amountOverall;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
