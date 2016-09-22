package de.comdirect.collabothon2016.collabothon2016.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by A3286390 on 22.09.2016.
 */
public class Group {

    public int id;
    public String scores;
//    private String groupname;
//    private List<Members> memberList;
//    private BigDecimal mmonthly


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
