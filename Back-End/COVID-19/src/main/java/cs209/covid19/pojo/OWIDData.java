package cs209.covid19.pojo;

public class OWIDData {

    private String isoCode;

    private String continent;

    private String location;

    private int totalCases;

    private int newCases;

    private int totalDeaths;

    private int newDeaths;

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public OWIDData(String isoCode, String continent, String location, int totalCases, int newCases, int totalDeaths, int newDeaths) {
        this.isoCode = isoCode;
        this.continent = continent;
        this.location = location;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.totalDeaths = totalDeaths;
        this.newDeaths = newDeaths;
    }

    @Override
    public String toString() {
        return "OWIDData{" +
                "isoCode='" + isoCode + '\'' +
                ", continent='" + continent + '\'' +
                ", location='" + location + '\'' +
                ", totalCases=" + totalCases +
                ", newCases=" + newCases +
                ", totalDeaths=" + totalDeaths +
                ", newDeaths=" + newDeaths +
                '}';
    }
}
