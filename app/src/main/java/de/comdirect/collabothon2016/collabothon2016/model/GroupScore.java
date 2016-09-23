package de.comdirect.collabothon2016.collabothon2016.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GroupScore {

    public long groupscore;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
