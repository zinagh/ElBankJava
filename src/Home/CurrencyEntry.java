package Home;

public class CurrencyEntry {
    private String currency;
    private String value;

    public CurrencyEntry(String currency, String value) {
        this.currency = currency;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public String getValue() {
        return value;
    }
    public String toString() {
        return "CurrencyEntry [currency=" + currency + ", value=" + value + "]";
    }

}
