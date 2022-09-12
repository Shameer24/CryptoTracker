package com.tequip.cryptotracker.Model;

import static java.sql.Types.NULL;

import java.io.Serializable;
import java.text.DecimalFormat;

public class CurrencyModal implements Serializable {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private String name;
    private String symbol;
    public int id;
    private double circulating_supply;
    private double max_supply;
    private double low_limit = NULL;
    public Quote quote;


    public Quote getQuote() {
        return quote;
    }

    public class Quote implements Serializable{
        public currency getUSD() {
            return USD;
        }

        public currency USD;
        public class currency implements Serializable{
            double price;
            double percent_change_1h;
            double percent_change_24h;
            double percent_change_7d;
            double volume_24h;
            double market_cap;

            public currency() {
                this.price = price;
                this.percent_change_1h = percent_change_1h;
                this.percent_change_24h = percent_change_24h;
                this.percent_change_7d = percent_change_7d;
                this.market_cap= market_cap;
                this.volume_24h = volume_24h;
            }

            public long getVolume_24h() {
                return (long) volume_24h;
            }

            public void setVolume_24h(int volume_24h) {
                this.volume_24h = volume_24h;
            }

            public long getMarket_cap() {
                long longvar = (long) market_cap;
                return longvar;
            }

            public void setMarket_cap(int market_cap) {
                this.market_cap = market_cap;
            }

            //getter and setter
            public double getPrice() {
                return Double.parseDouble(df.format(price));
            }

            public void setPrice(double currencyRate) { this.price = currencyRate; }

            public double getChange1h() {
                return Double.parseDouble(df.format(percent_change_1h));
            }

            public void setChange1h(double change1h) {
                this.percent_change_1h = change1h;
            }

            public double getChange24h() {
                return Double.parseDouble(df.format(percent_change_24h));
            }

            public void setChange24h(double change24h) {
                this.percent_change_24h = change24h;
            }

            public double getChange7d() {
                return Double.parseDouble(df.format(percent_change_7d));
            }

            public void setChange7d(double change7d) {
                this.percent_change_7d = change7d;
            }
        }
    }


    public long getCirculating_supply() {
        return (long)circulating_supply;
    }

    public void setCirculating_supply(long circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public long getMax_supply() {
        return (long)max_supply;
    }

    public void setMax_supply(long max_supply) {
        this.max_supply = max_supply;
    }

    public CurrencyModal(String currencyName, String currencySymbol, double currencyRate, double percent_change_1h, double percent_change_24h, double percent_change_7d, int id, long circulating_supply, long max_supply)
    {
        this.name = currencyName;
        this.symbol = currencySymbol;
        this.id = id;
        this.circulating_supply=circulating_supply;
        this.max_supply= max_supply;
    }

    public  int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public double getLowerLimit() {
        return low_limit; }

    public void setLowerLimit(double lower_limit) {
        this.low_limit = lower_limit;
    }

    public String getCurrencyName() {
        return name;
    }

    public void setCurrencyName(String currencyName) {
        this.name = currencyName;
    }

    public String getCurrencySymbol() {
        return symbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.symbol = currencySymbol;
    }

    public double getLow_limit() {
        return low_limit; }

    public void setLow_limit(double low_limit) { this.low_limit = low_limit; }

}
