package cs209.covid19.controller;


import cs209.covid19.pojo.WHOData;
import cs209.covid19.pojo.WHOData;
import cs209.covid19.utils.LocalDataUtil;
import cs209.covid19.utils.OnlineDataUtil;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/who")
public class WHODataController {
    @CrossOrigin
    @RequestMapping("latestLocalData")
    public ArrayList<WHOData> getLatestLocalData() {
        return LocalDataUtil.getLatestWHOData();
    }

    @CrossOrigin
    @RequestMapping("latestLocalDate")
    public LocalDate getLatestLocalDate() {
        return LocalDataUtil.getWHODataLatestDate();
    }

    @CrossOrigin
    @RequestMapping("latestLocalDataByLocation/{searchCountryName}")
    public ArrayList<WHOData> getLatestLocalDataByLocation(@PathVariable("searchCountryName") String country) {
        return LocalDataUtil.getLatestWHODataByCountry(country);
    }

    @CrossOrigin
    @RequestMapping("getOrderedLocalDataList/{attr}/{reverseOrder}")
    public ArrayList<WHOData> getOrderedLocalDataList(@RequestBody ArrayList<WHOData> dataArrayList, @PathVariable String attr, @PathVariable int reverseOrder) {
        return LocalDataUtil.getOrderedWHODataList(dataArrayList, attr, reverseOrder);
    }

    @CrossOrigin
    @RequestMapping("getMapLocalData")
    public ArrayList<Object> getMapLocalData() {
        return LocalDataUtil.getMapWHOData();
    }

    @CrossOrigin
    @RequestMapping("getLocalDataForOneMonth/{date}")
    public ArrayList<ArrayList<Object>> getLocalDataForOneMonth(@PathVariable String date) {
        return LocalDataUtil.getWHODataForOneMonth(LocalDate.parse(date));
    }

    @CrossOrigin
    @RequestMapping("getLocalDataTotalDeathsForOneMonthByLocations/{date}")
    public ArrayList<ArrayList<Object>> getLocalDataTotalDeathsForOneMonthByLocations(@PathVariable String date, @RequestBody ArrayList<String> countries) {
        return LocalDataUtil.getWHODataTotalDeathsForOneMonthByCountries(LocalDate.parse(date), countries);
    }

    @CrossOrigin
    @RequestMapping("getLatestNewCasesNewDeathsLocalDataForLocations")
    public ArrayList<ArrayList<Object>> getLatestNewCasesNewDeathsLocalDataForLocations(@RequestBody ArrayList<String> countries) {
        return LocalDataUtil.getLatestNewCasesNewDeathsWHODataForCounties(countries);
    }

    @CrossOrigin
    @RequestMapping("getLocalDataPieTotalCasesData")
    public HashMap<String, Object> getLocalDataPieTotalCasesData(@RequestBody ArrayList<String> countries) {
        return LocalDataUtil.getWHODataPieTotalCasesData(countries);
    }

    @CrossOrigin
    @RequestMapping("getLocalDataPieTotalDeathsData")
    public HashMap<String, Object> getLocalDataPieTotalDeathsData(@RequestBody ArrayList<String> countries) {
        return LocalDataUtil.getWHODataPieTotalDeathsData(countries);
    }



    @CrossOrigin
    @RequestMapping("latestOnlineData")
    public ArrayList<WHOData> getLatestOnlineData() {
        return OnlineDataUtil.getLatestWHOData();
    }

    @CrossOrigin
    @RequestMapping("latestOnlineDate")
    public LocalDate getLatestOnlineDate() {
        return OnlineDataUtil.getWHODataLatestDate();
    }

    @CrossOrigin
    @RequestMapping("latestOnlineDataByLocation/{searchCountryName}")
    public ArrayList<WHOData> getLatestOnlineDataByLocation(@PathVariable("searchCountryName") String country) {
        return OnlineDataUtil.getLatestWHODataByCountry(country);
    }

    @CrossOrigin
    @RequestMapping("getOrderedOnlineDataList/{attr}/{reverseOrder}")
    public ArrayList<WHOData> getOrderedOnlineDataList(@RequestBody ArrayList<WHOData> dataArrayList, @PathVariable String attr, @PathVariable int reverseOrder) {
        return OnlineDataUtil.getOrderedWHODataList(dataArrayList, attr, reverseOrder);
    }

    @CrossOrigin
    @RequestMapping("getMapOnlineData")
    public ArrayList<Object> getMapOnlineData() {
        return OnlineDataUtil.getMapWHOData();
    }

    @CrossOrigin
    @RequestMapping("getOnlineDataForOneMonth/{date}")
    public ArrayList<ArrayList<Object>> getOnlineDataForOneMonth(@PathVariable String date) {
        return OnlineDataUtil.getWHODataForOneMonth(LocalDate.parse(date));
    }

    @CrossOrigin
    @RequestMapping("getOnlineDataTotalDeathsForOneMonthByLocations/{date}")
    public ArrayList<ArrayList<Object>> getOnlineDataTotalDeathsForOneMonthByLocations(@PathVariable String date, @RequestBody ArrayList<String> countries) {
        return OnlineDataUtil.getWHODataTotalDeathsForOneMonthByCountries(LocalDate.parse(date), countries);
    }

    @CrossOrigin
    @RequestMapping("getLatestNewCasesNewDeathsOnlineDataForLocations")
    public ArrayList<ArrayList<Object>> getLatestNewCasesNewDeathsOnlineDataForLocations(@RequestBody ArrayList<String> countries) {
        return OnlineDataUtil.getLatestNewCasesNewDeathsWHODataForCounties(countries);
    }

    @CrossOrigin
    @RequestMapping("getOnlineDataPieTotalCasesData")
    public HashMap<String, Object> getOnlineDataPieTotalCasesData(@RequestBody ArrayList<String> countries) {
        return OnlineDataUtil.getWHODataPieTotalCasesData(countries);
    }

    @CrossOrigin
    @RequestMapping("getOnlineDataPieTotalDeathsData")
    public HashMap<String, Object> getOnlineDataPieTotalDeathsData(@RequestBody ArrayList<String> countries) {
        return OnlineDataUtil.getWHODataPieTotalDeathsData(countries);
    }
}
