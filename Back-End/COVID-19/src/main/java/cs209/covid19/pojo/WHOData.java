package cs209.covid19.pojo;

public class WHOData {

    private String countryCode;

    private String country;

    private String WHORegion;

    private int newCases;

    private int cumulativeCases;

    private int newDeaths;

    private int cumulativeDeaths;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWHORegion() {
        return WHORegion;
    }

    public void setWHORegion(String WHORegion) {
        this.WHORegion = WHORegion;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getCumulativeCases() {
        return cumulativeCases;
    }

    public void setCumulativeCases(int cumulativeCases) {
        this.cumulativeCases = cumulativeCases;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getCumulativeDeaths() {
        return cumulativeDeaths;
    }

    public void setCumulativeDeaths(int cumulativeDeaths) {
        this.cumulativeDeaths = cumulativeDeaths;
    }

    public WHOData(String countryCode, String country, String WHORegion, int newCases, int cumulativeCases, int newDeaths, int cumulativeDeaths) {
        this.countryCode = countryCode;
        this.country = country;
        this.WHORegion = WHORegion;
        this.newCases = newCases;
        this.cumulativeCases = cumulativeCases;
        this.newDeaths = newDeaths;
        this.cumulativeDeaths = cumulativeDeaths;
    }

    @Override
    public String toString() {
        return "WHOData{" +
                "countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                ", WHORegion='" + WHORegion + '\'' +
                ", newCases=" + newCases +
                ", cumulativeCases=" + cumulativeCases +
                ", newDeaths=" + newDeaths +
                ", cumulativeDeaths=" + cumulativeDeaths +
                '}';
    }
}
