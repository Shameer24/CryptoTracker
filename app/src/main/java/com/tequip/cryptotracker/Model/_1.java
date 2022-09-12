package com.tequip.cryptotracker.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.ArrayList;

public class _1 {
    public ArrayList<_1> dataid;
    public int id;
    public String name;
    public String symbol;
    public String category;
    public String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(ArrayList<String> tagNames) {
        this.tagNames = tagNames;
    }

    public ArrayList<String> getTagGroups() {
        return tagGroups;
    }

    public void setTagGroups(ArrayList<String> tagGroups) {
        this.tagGroups = tagGroups;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public Object getPlatform() {
        return platform;
    }

    public void setPlatform(Object platform) {
        this.platform = platform;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    public String getTwitter_username() {
        return twitter_username;
    }

    public void setTwitter_username(String twitter_username) {
        this.twitter_username = twitter_username;
    }

    public int getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(int is_hidden) {
        this.is_hidden = is_hidden;
    }

    public Object getDate_launched() {
        return date_launched;
    }

    public void setDate_launched(Object date_launched) {
        this.date_launched = date_launched;
    }

    public ArrayList<Object> getContract_address() {
        return contract_address;
    }

    public void setContract_address(ArrayList<Object> contract_address) {
        this.contract_address = contract_address;
    }

    public Object getSelf_reported_circulating_supply() {
        return self_reported_circulating_supply;
    }

    public void setSelf_reported_circulating_supply(Object self_reported_circulating_supply) {
        this.self_reported_circulating_supply = self_reported_circulating_supply;
    }

    public Object getSelf_reported_tags() {
        return self_reported_tags;
    }

    public void setSelf_reported_tags(Object self_reported_tags) {
        this.self_reported_tags = self_reported_tags;
    }

    public Object getSelf_reported_market_cap() {
        return self_reported_market_cap;
    }

    public void setSelf_reported_market_cap(Object self_reported_market_cap) {
        this.self_reported_market_cap = self_reported_market_cap;
    }

    public String slug;
    public String logo;
    public String subreddit;
    public String notice;
    public ArrayList<String> tags;
    @JsonProperty("tag-names")
    public ArrayList<String> tagNames;
    @JsonProperty("tag-groups")
    public ArrayList<String> tagGroups;
    public Urls urls;
    public Object platform;
    public Date date_added;
    public String twitter_username;
    public int is_hidden;
    public Object date_launched;
    public ArrayList<Object> contract_address;
    public Object self_reported_circulating_supply;
    public Object self_reported_tags;
    public Object self_reported_market_cap;
}
