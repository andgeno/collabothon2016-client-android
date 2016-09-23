package de.comdirect.collabothon2016.collabothon2016.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Leader {

    public int position;
    public int nutzerId;
    public double performance;
    public String name;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
