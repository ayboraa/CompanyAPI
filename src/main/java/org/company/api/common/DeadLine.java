package org.company.api.common;

import org.springframework.util.Assert;

import java.util.Date;

public class DeadLine {
    private final Date date;

    public DeadLine(Date dt) {


        Assert.notNull(dt, "date cannot be null");

        this.date = dt;

    }

    public Date getDate() {
        return date;
    }

    public boolean isTimePassed(){
        return new Date().after(date);
    }


}
