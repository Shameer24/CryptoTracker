package com.tequip.cryptotracker.Model;

import java.util.Date;

public class Status {
    public Date timestamp;
    public int error_code;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public Object getError_message() {
        return error_message;
    }

    public void setError_message(Object error_message) {
        this.error_message = error_message;
    }

    public int getElapsed() {
        return elapsed;
    }

    public void setElapsed(int elapsed) {
        this.elapsed = elapsed;
    }

    public int getCredit_count() {
        return credit_count;
    }

    public void setCredit_count(int credit_count) {
        this.credit_count = credit_count;
    }

    public Object getNotice() {
        return notice;
    }

    public void setNotice(Object notice) {
        this.notice = notice;
    }

    public Object error_message;
    public int elapsed;
    public int credit_count;
    public Object notice;
}
