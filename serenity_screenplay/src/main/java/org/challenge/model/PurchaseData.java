package org.challenge.model;


/**
 * Model that represents the purchase data required to complete an order.
 */
public class PurchaseData {

    private String name;
    private String country;
    private String city;
    private String card;
    private String month;
    private String year;

    public PurchaseData() {}

    public PurchaseData(String name, String country, String city,
                        String card, String month, String year) {
        this.name    = name;
        this.country = country;
        this.city    = city;
        this.card    = card;
        this.month   = month;
        this.year    = year;
    }

    // ── Fluent builder ──────────────────────────────────────────────────────

    public static PurchaseData withName(String name) {
        PurchaseData data = new PurchaseData();
        data.name = name;
        return data;
    }

    public PurchaseData fromCountry(String country) {
        this.country = country;
        return this;
    }

    public PurchaseData inCity(String city) {
        this.city = city;
        return this;
    }

    public PurchaseData payingWithCard(String card) {
        this.card = card;
        return this;
    }

    public PurchaseData expiringMonth(String month) {
        this.month = month;
        return this;
    }

    public PurchaseData expiringYear(String year) {
        this.year = year;
        return this;
    }

    // ── Getters ─────────────────────────────────────────────────────────────

    public String getName()    { return name; }
    public String getCountry() { return country; }
    public String getCity()    { return city; }
    public String getCard()    { return card; }
    public String getMonth()   { return month; }
    public String getYear()    { return year; }

    @Override
    public String toString() {
        return String.format("PurchaseData{name='%s', country='%s', city='%s'}", name, country, city);
    }
}
