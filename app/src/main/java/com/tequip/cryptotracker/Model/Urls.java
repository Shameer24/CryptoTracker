package com.tequip.cryptotracker.Model;

import java.util.ArrayList;

public class Urls {
    public ArrayList<String> website;
    public ArrayList<Object> twitter;
    public ArrayList<String> message_board;
    public ArrayList<Object> chat;
    public ArrayList<Object> facebook;
    public ArrayList<String> explorer;
    public ArrayList<String> reddit;
    public ArrayList<String> technical_doc;

    public ArrayList<String> getWebsite() {
        return website;
    }

    public void setWebsite(ArrayList<String> website) {
        this.website = website;
    }

    public ArrayList<Object> getTwitter() {
        return twitter;
    }

    public void setTwitter(ArrayList<Object> twitter) {
        this.twitter = twitter;
    }

    public ArrayList<String> getMessage_board() {
        return message_board;
    }

    public void setMessage_board(ArrayList<String> message_board) {
        this.message_board = message_board;
    }

    public ArrayList<Object> getChat() {
        return chat;
    }

    public void setChat(ArrayList<Object> chat) {
        this.chat = chat;
    }

    public ArrayList<Object> getFacebook() {
        return facebook;
    }

    public void setFacebook(ArrayList<Object> facebook) {
        this.facebook = facebook;
    }

    public ArrayList<String> getExplorer() {
        return explorer;
    }

    public void setExplorer(ArrayList<String> explorer) {
        this.explorer = explorer;
    }

    public ArrayList<String> getReddit() {
        return reddit;
    }

    public void setReddit(ArrayList<String> reddit) {
        this.reddit = reddit;
    }

    public ArrayList<String> getTechnical_doc() {
        return technical_doc;
    }

    public void setTechnical_doc(ArrayList<String> technical_doc) {
        this.technical_doc = technical_doc;
    }

    public ArrayList<String> getSource_code() {
        return source_code;
    }

    public void setSource_code(ArrayList<String> source_code) {
        this.source_code = source_code;
    }

    public ArrayList<Object> getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(ArrayList<Object> announcement) {
        this.announcement = announcement;
    }

    public ArrayList<String> source_code;
    public ArrayList<Object> announcement;
}
