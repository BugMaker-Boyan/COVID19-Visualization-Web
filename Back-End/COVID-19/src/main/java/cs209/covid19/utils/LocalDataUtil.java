package cs209.covid19.utils;

import cs209.covid19.pojo.OWIDData;
import cs209.covid19.pojo.WHOData;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;

@Component
public class LocalDataUtil {

    private static final ClassPathResource OWIDDataFileResource = new ClassPathResource("static/owid-covid-data.csv");

    private static final ClassPathResource WHODataFileResource = new ClassPathResource("static/WHO-COVID-19-global-data.csv");

    private static final HashMap<LocalDate, ArrayList<OWIDData>> localOWIDData = new HashMap<>();

    private static final HashMap<LocalDate, ArrayList<WHOData>> localWHOData = new HashMap<>();


    public static void loadOWIDData() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(OWIDDataFileResource.getInputStream()))) {
            String line;
            // ignore the head information
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineInfo = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", 67);
                String isoCode = lineInfo[0];
                String continent = lineInfo[1];
                String location = lineInfo[2];
                LocalDate date = LocalDate.parse(lineInfo[3]);
                int totalCases = lineInfo[4].length() == 0 ? 0 : (int) Double.parseDouble(lineInfo[4]);
                int newCases = lineInfo[5].length() == 0 ? 0 : (int) Double.parseDouble(lineInfo[5]);
                int totalDeaths = lineInfo[7].length() == 0 ? 0 : (int) Double.parseDouble(lineInfo[7]);
                int newDeaths = lineInfo[8].length() == 0 ? 0 : (int) Double.parseDouble(lineInfo[8]);

                if (!localOWIDData.containsKey(date)) {
                    localOWIDData.put(date, new ArrayList<>());
                }
                if (continent.equals("")) continue;
                localOWIDData.get(date).add(new OWIDData(isoCode, continent, location, totalCases, newCases, totalDeaths, newDeaths));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LocalDate getOWIDDataLatestDate() {
        return localOWIDData.keySet().stream().max(LocalDate::compareTo).orElse(null);
    }

    public static ArrayList<OWIDData> getLatestOWIDData() {
        LocalDate localDate = getOWIDDataLatestDate();
        if (localDate == null) {
            return null;
        }
        return localOWIDData.get(localDate);
    }

    public static ArrayList<OWIDData> getLatestOWIDDataByLocation(String location) {
        ArrayList<OWIDData> latestLocalOWIDData = getLatestOWIDData();
        if (latestLocalOWIDData == null) {
            return null;
        }
        ArrayList<OWIDData> result = new ArrayList<>();
        for (OWIDData owidData: latestLocalOWIDData) {
            if (owidData.getLocation()!=null && owidData.getLocation().toLowerCase().contains(location.toLowerCase())) {
                result.add(owidData);
            }
        }
        return result;
    }

    public static ArrayList<OWIDData> getOrderedOWIDDataList(ArrayList<OWIDData> arrayList, String orderAttr, int reverseOrder) {
        switch (orderAttr) {
            case "isoCode": {
                arrayList.sort(Comparator.comparing(OWIDData::getIsoCode));
                break;
            }
            case "continent": {
                arrayList.sort(Comparator.comparing(OWIDData::getContinent));
                break;
            }
            case "location": {
                arrayList.sort(Comparator.comparing(OWIDData::getLocation));
                break;
            }
            case "totalCases": {
                arrayList.sort(Comparator.comparing(OWIDData::getTotalCases));
                break;
            }
            case "newCases": {
                arrayList.sort(Comparator.comparing(OWIDData::getNewCases));
                break;
            }
            case "totalDeaths": {
                arrayList.sort(Comparator.comparing(OWIDData::getTotalDeaths));
                break;
            }
            case "newDeaths": {
                arrayList.sort(Comparator.comparing(OWIDData::getNewDeaths));
                break;
            }
        }
        if (reverseOrder == 0) {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    public static ArrayList<Object> getMapOWIDData() {
        ArrayList<OWIDData> latestLocalOWIDData = getLatestOWIDData();
        if (latestLocalOWIDData == null) return null;
        ArrayList<Object> arrayList = new ArrayList<>();
        for (OWIDData owidData: latestLocalOWIDData) {
            HashMap<String, Object> temp = new HashMap<>();
            temp.put("name", owidData.getLocation());
            temp.put("value", owidData.getTotalCases());
            arrayList.add(temp);
        }
        return arrayList;
    }

    public static ArrayList<ArrayList<Object>> getOWIDDataTotalDeathsForOneMonthByLocations(LocalDate localDate, ArrayList<String> locations) {
        ArrayList<ArrayList<Object>> arrayList = new ArrayList<>();
        ArrayList<Object> head = new ArrayList<>();
        head.add("date");
        boolean firstFlag = true;
        for (LocalDate date: localOWIDData.keySet()) {
            if (date.getYear() == localDate.getYear() && date.getMonthValue() == localDate.getMonthValue()) {
                ArrayList<Object> temp = new ArrayList<>();
                temp.add(date);
                for (OWIDData owidData: localOWIDData.get(date)) {
                    if (locations.contains(owidData.getLocation())) {
                        temp.add(owidData.getTotalDeaths());
                        if (firstFlag) head.add(owidData.getLocation());
                    }
                }
                arrayList.add(temp);
                firstFlag = false;
            }
        }
        arrayList.add(head);
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static ArrayList<ArrayList<Object>> getOWIDDataForOneMonth(LocalDate localDate) {
        ArrayList<ArrayList<Object>> arrayList = new ArrayList<>();
        for (LocalDate date: localOWIDData.keySet()) {
            if (date.getYear() == localDate.getYear() && date.getMonthValue() == localDate.getMonthValue()) {
                for (OWIDData owidData: localOWIDData.get(date)) {
                    ArrayList<Object> temp = new ArrayList<>();
                    temp.add(owidData.getTotalCases());
                    temp.add(owidData.getNewCases());
                    temp.add(owidData.getTotalDeaths());
                    temp.add(owidData.getNewDeaths());
                    temp.add(owidData.getLocation());
                    temp.add(date.getDayOfMonth());
                    arrayList.add(temp);
                }
            }
        }
        ArrayList<Object> head = new ArrayList<>();
        head.add("totalCases");
        head.add("newCases");
        head.add("totalDeaths");
        head.add("newDeaths");
        head.add("location");
        head.add("day");
        arrayList.add(head);
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static HashMap<String, Object> getOWIDDataPieTotalCasesData(ArrayList<String> locations) {
        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList<HashMap<String, Object>> seriesData = new ArrayList<>();
        ArrayList<String> legendData = new ArrayList<>();
        ArrayList<OWIDData> arrayList = localOWIDData.get(getOWIDDataLatestDate());
        for (OWIDData owidData: arrayList){
            if (!locations.contains(owidData.getLocation())) continue;
            legendData.add(owidData.getLocation());
            HashMap<String, Object> tempHashMap = new HashMap<>();
            tempHashMap.put("name", owidData.getLocation());
            tempHashMap.put("value", owidData.getTotalCases());
            seriesData.add(tempHashMap);
        }
        hashMap.put("legendData", legendData);
        hashMap.put("seriesData", seriesData);
        return hashMap;
    }

    public static HashMap<String, Object> getOWIDDataPieTotalDeathsData(ArrayList<String> locations) {
        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList<HashMap<String, Object>> seriesData = new ArrayList<>();
        ArrayList<String> legendData = new ArrayList<>();
        ArrayList<OWIDData> arrayList = localOWIDData.get(getOWIDDataLatestDate());
        for (OWIDData owidData: arrayList){
            if (!locations.contains(owidData.getLocation())) continue;
            legendData.add(owidData.getLocation());
            HashMap<String, Object> tempHashMap = new HashMap<>();
            tempHashMap.put("name", owidData.getLocation());
            tempHashMap.put("value", owidData.getTotalDeaths());
            seriesData.add(tempHashMap);
        }
        hashMap.put("legendData", legendData);
        hashMap.put("seriesData", seriesData);
        return hashMap;
    }

    public static ArrayList<ArrayList<Object>> getLatestNewCasesNewDeathsOWIDDataForLocations(ArrayList<String> locations) {
        ArrayList<OWIDData> latestLocalOWIDData = getLatestOWIDData();
        ArrayList<ArrayList<Object>> arrayList = new ArrayList<>();
        if (latestLocalOWIDData == null) return null;
        for (OWIDData owidData: latestLocalOWIDData) {
            if (locations.contains(owidData.getLocation())) {
                ArrayList<Object> temp = new ArrayList<>();
                temp.add(owidData.getLocation());
                temp.add(owidData.getNewCases());
                temp.add(owidData.getNewDeaths());
                arrayList.add(temp);
            }
        }
        return arrayList;
    }


    public static void loadWHOData() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(WHODataFileResource.getInputStream()))) {
            String line;
            //ignore the head information
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineInfo = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", 8);
                LocalDate date = LocalDate.parse(lineInfo[0]);
                String countryCode = lineInfo[1];
                String country = lineInfo[2].replaceAll("\"", "");
                String WHORegion = lineInfo[3];
                int newCases = Integer.parseInt(lineInfo[4]);
                int cumulativeCases = Integer.parseInt(lineInfo[5]);
                int newDeaths = Integer.parseInt(lineInfo[6]);
                int cumulativeDeaths = Integer.parseInt(lineInfo[7]);

                if (!localWHOData.containsKey(date)) {
                    localWHOData.put(date, new ArrayList<>());
                }
                localWHOData.get(date).add(new WHOData(countryCode, country, WHORegion, newCases, cumulativeCases, newDeaths, cumulativeDeaths));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LocalDate getWHODataLatestDate() {
        return localWHOData.keySet().stream().max(LocalDate::compareTo).orElse(null);
    }

    public static ArrayList<WHOData> getLatestWHOData() {
        LocalDate localDate = getWHODataLatestDate();
        if (localDate == null) {
            return null;
        }
        return localWHOData.get(localDate);
    }

    public static ArrayList<WHOData> getLatestWHODataByCountry(String country) {
        ArrayList<WHOData> latestLocalWHOData = getLatestWHOData();
        if (latestLocalWHOData == null) {
            return null;
        }
        ArrayList<WHOData> result = new ArrayList<>();
        for (WHOData whoData: latestLocalWHOData) {
            if (whoData.getCountry()!=null && whoData.getCountry().toLowerCase().contains(country.toLowerCase())) {
                result.add(whoData);
            }
        }
        return result;
    }

    public static ArrayList<WHOData> getOrderedWHODataList(ArrayList<WHOData> arrayList, String orderAttr, int reverseOrder) {
        switch (orderAttr) {
            case "countryCode": {
                arrayList.sort(Comparator.comparing(WHOData::getCountryCode));
                break;
            }
            case "country": {
                arrayList.sort(Comparator.comparing(WHOData::getCountry));
                break;
            }
            case "whoregion": {
                arrayList.sort(Comparator.comparing(WHOData::getWHORegion));
                break;
            }
            case "newCases": {
                arrayList.sort(Comparator.comparing(WHOData::getNewCases));
                break;
            }
            case "cumulativeCases": {
                arrayList.sort(Comparator.comparing(WHOData::getCumulativeCases));
                break;
            }
            case "newDeaths": {
                arrayList.sort(Comparator.comparing(WHOData::getNewDeaths));
                break;
            }
            case "cumulativeDeaths": {
                arrayList.sort(Comparator.comparing(WHOData::getCumulativeDeaths));
                break;
            }
        }
        if (reverseOrder == 0) {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    public static ArrayList<Object> getMapWHOData() {
        ArrayList<WHOData> latestLocalWHOData = getLatestWHOData();
        if (latestLocalWHOData == null) return null;
        ArrayList<Object> arrayList = new ArrayList<>();
        for (WHOData whoData: latestLocalWHOData) {
            HashMap<String, Object> temp = new HashMap<>();
            temp.put("name", whoData.getCountry());
            temp.put("value", whoData.getCumulativeCases());
            arrayList.add(temp);
        }
        return arrayList;
    }

    public static ArrayList<ArrayList<Object>> getWHODataTotalDeathsForOneMonthByCountries(LocalDate localDate, ArrayList<String> countries) {
        ArrayList<ArrayList<Object>> arrayList = new ArrayList<>();
        ArrayList<Object> head = new ArrayList<>();
        head.add("date");
        boolean firstFlag = true;
        for (LocalDate date: localWHOData.keySet()) {
            if (date.getYear() == localDate.getYear() && date.getMonthValue() == localDate.getMonthValue()) {
                ArrayList<Object> temp = new ArrayList<>();
                temp.add(date);
                for (WHOData whoData: localWHOData.get(date)) {
                    if (countries.contains(whoData.getCountry())) {
                        temp.add(whoData.getCumulativeDeaths());
                        if (firstFlag) head.add(whoData.getCountry());
                    }
                }
                arrayList.add(temp);
                firstFlag = false;
            }
        }
        arrayList.add(head);
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static ArrayList<ArrayList<Object>> getWHODataForOneMonth(LocalDate localDate) {
        ArrayList<ArrayList<Object>> arrayList = new ArrayList<>();
        for (LocalDate date: localWHOData.keySet()) {
            if (date.getYear() == localDate.getYear() && date.getMonthValue() == localDate.getMonthValue()) {
                for (WHOData whoData: localWHOData.get(date)) {
                    ArrayList<Object> temp = new ArrayList<>();
                    temp.add(whoData.getCumulativeCases());
                    temp.add(whoData.getNewCases());
                    temp.add(whoData.getCumulativeDeaths());
                    temp.add(whoData.getNewDeaths());
                    temp.add(whoData.getCountry());
                    temp.add(date.getDayOfMonth());
                    arrayList.add(temp);
                }
            }
        }
        ArrayList<Object> head = new ArrayList<>();
        head.add("totalCases");
        head.add("newCases");
        head.add("totalDeaths");
        head.add("newDeaths");
        head.add("location");
        head.add("day");
        arrayList.add(head);
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static HashMap<String, Object> getWHODataPieTotalCasesData(ArrayList<String> countries) {
        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList<HashMap<String, Object>> seriesData = new ArrayList<>();
        ArrayList<String> legendData = new ArrayList<>();
        ArrayList<WHOData> arrayList = localWHOData.get(getWHODataLatestDate());
        for (WHOData whoData: arrayList){
            if (!countries.contains(whoData.getCountry())) continue;
            legendData.add(whoData.getCountry());
            HashMap<String, Object> tempHashMap = new HashMap<>();
            tempHashMap.put("name", whoData.getCountry());
            tempHashMap.put("value", whoData.getCumulativeCases());
            seriesData.add(tempHashMap);
        }
        hashMap.put("legendData", legendData);
        hashMap.put("seriesData", seriesData);
        return hashMap;
    }

    public static HashMap<String, Object> getWHODataPieTotalDeathsData(ArrayList<String> countries) {
        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList<HashMap<String, Object>> seriesData = new ArrayList<>();
        ArrayList<String> legendData = new ArrayList<>();
        ArrayList<WHOData> arrayList = localWHOData.get(getWHODataLatestDate());
        for (WHOData whoData: arrayList){
            if (!countries.contains(whoData.getCountry())) continue;
            legendData.add(whoData.getCountry());
            HashMap<String, Object> tempHashMap = new HashMap<>();
            tempHashMap.put("name", whoData.getCountry());
            tempHashMap.put("value", whoData.getCumulativeDeaths());
            seriesData.add(tempHashMap);
        }
        hashMap.put("legendData", legendData);
        hashMap.put("seriesData", seriesData);
        return hashMap;
    }

    public static ArrayList<ArrayList<Object>> getLatestNewCasesNewDeathsWHODataForCounties(ArrayList<String> countries) {
        ArrayList<WHOData> latestLocalWHOData = getLatestWHOData();
        ArrayList<ArrayList<Object>> arrayList = new ArrayList<>();
        if (latestLocalWHOData == null) return null;
        for (WHOData whoData: latestLocalWHOData) {
            if (countries.contains(whoData.getCountry())) {
                ArrayList<Object> temp = new ArrayList<>();
                temp.add(whoData.getCountry());
                temp.add(whoData.getNewCases());
                temp.add(whoData.getNewDeaths());
                arrayList.add(temp);
            }
        }
        return arrayList;
    }
}
