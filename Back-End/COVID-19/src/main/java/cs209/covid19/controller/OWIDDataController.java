package cs209.covid19.controller;

import cs209.covid19.pojo.OWIDData;
import cs209.covid19.utils.LocalDataUtil;
import cs209.covid19.utils.OnlineDataUtil;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/owid")
public class OWIDDataController {

    @CrossOrigin
    @RequestMapping("latestLocalData")
    public ArrayList<OWIDData> getLatestLocalData() {
        return LocalDataUtil.getLatestOWIDData();
    }

    @CrossOrigin
    @RequestMapping("latestLocalDate")
    public LocalDate getLatestLocalDate() {
        return LocalDataUtil.getOWIDDataLatestDate();
    }

    @CrossOrigin
    @RequestMapping("latestLocalDataByLocation/{searchCountryName}")
    public ArrayList<OWIDData> getLatestLocalDataByLocation(@PathVariable("searchCountryName") String location) {
        return LocalDataUtil.getLatestOWIDDataByLocation(location);
    }

    @CrossOrigin
    @RequestMapping("getOrderedLocalDataList/{attr}/{reverseOrder}")
    public ArrayList<OWIDData> getOrderedLocalDataList(@RequestBody ArrayList<OWIDData> dataArrayList, @PathVariable String attr, @PathVariable int reverseOrder) {
        return LocalDataUtil.getOrderedOWIDDataList(dataArrayList, attr, reverseOrder);
    }

    @CrossOrigin
    @RequestMapping("getMapLocalData")
    public ArrayList<Object> getMapLocalData() {
        return LocalDataUtil.getMapOWIDData();
    }

    @CrossOrigin
    @RequestMapping("getLocalDataForOneMonth/{date}")
    public ArrayList<ArrayList<Object>> getLocalDataForOneMonth(@PathVariable String date) {
        return LocalDataUtil.getOWIDDataForOneMonth(LocalDate.parse(date));
    }

    @CrossOrigin
    @RequestMapping("getLocalDataTotalDeathsForOneMonthByLocations/{date}")
    public ArrayList<ArrayList<Object>> getLocalDataTotalDeathsForOneMonthByLocations(@PathVariable String date, @RequestBody ArrayList<String> locations) {
        return LocalDataUtil.getOWIDDataTotalDeathsForOneMonthByLocations(LocalDate.parse(date), locations);
    }

    @CrossOrigin
    @RequestMapping("getLatestNewCasesNewDeathsLocalDataForLocations")
    public ArrayList<ArrayList<Object>> getLatestNewCasesNewDeathsLocalDataForLocations(@RequestBody ArrayList<String> locations) {
        return LocalDataUtil.getLatestNewCasesNewDeathsOWIDDataForLocations(locations);
    }

    @CrossOrigin
    @RequestMapping("getLocalDataPieTotalCasesData")
    public HashMap<String, Object> getLocalDataPieTotalCasesData(@RequestBody ArrayList<String> locations) {
        return LocalDataUtil.getOWIDDataPieTotalCasesData(locations);
    }

    @CrossOrigin
    @RequestMapping("getLocalDataPieTotalDeathsData")
    public HashMap<String, Object> getLocalDataPieTotalDeathsData(@RequestBody ArrayList<String> locations) {
        return LocalDataUtil.getOWIDDataPieTotalDeathsData(locations);
    }



    @CrossOrigin
    @RequestMapping("latestOnlineData")
    public ArrayList<OWIDData> getLatestOnlineData() {
        return OnlineDataUtil.getLatestOWIDData();
    }

    @CrossOrigin
    @RequestMapping("latestOnlineDate")
    public LocalDate getLatestOnlineDate() {
        return OnlineDataUtil.getOWIDDataLatestDate();
    }

    @CrossOrigin
    @RequestMapping("latestOnlineDataByLocation/{searchCountryName}")
    public ArrayList<OWIDData> getLatestOnlineDataByLocation(@PathVariable("searchCountryName") String location) {
        return OnlineDataUtil.getLatestOWIDDataByLocation(location);
    }

    @CrossOrigin
    @RequestMapping("getOrderedOnlineDataList/{attr}/{reverseOrder}")
    public ArrayList<OWIDData> getOrderedOnlineDataList(@RequestBody ArrayList<OWIDData> dataArrayList, @PathVariable String attr, @PathVariable int reverseOrder) {
        return OnlineDataUtil.getOrderedOWIDDataList(dataArrayList, attr, reverseOrder);
    }

    @CrossOrigin
    @RequestMapping("getMapOnlineData")
    public ArrayList<Object> getMapOnlineData() {
        return OnlineDataUtil.getMapOWIDData();
    }

    @CrossOrigin
    @RequestMapping("getOnlineDataForOneMonth/{date}")
    public ArrayList<ArrayList<Object>> getOnlineDataForOneMonth(@PathVariable String date) {
        return OnlineDataUtil.getOWIDDataForOneMonth(LocalDate.parse(date));
    }

    @CrossOrigin
    @RequestMapping("getOnlineDataTotalDeathsForOneMonthByLocations/{date}")
    public ArrayList<ArrayList<Object>> getOnlineDataTotalDeathsForOneMonthByLocations(@PathVariable String date, @RequestBody ArrayList<String> locations) {
        return OnlineDataUtil.getOWIDDataTotalDeathsForOneMonthByLocations(LocalDate.parse(date), locations);
    }

    @CrossOrigin
    @RequestMapping("getLatestNewCasesNewDeathsOnlineDataForLocations")
    public ArrayList<ArrayList<Object>> getLatestNewCasesNewDeathsOnlineDataForLocations(@RequestBody ArrayList<String> locations) {
        return OnlineDataUtil.getLatestNewCasesNewDeathsOWIDDataForLocations(locations);
    }

    @CrossOrigin
    @RequestMapping("getOnlineDataPieTotalCasesData")
    public HashMap<String, Object> getOnlineDataPieTotalCasesData(@RequestBody ArrayList<String> locations) {
        return OnlineDataUtil.getOWIDDataPieTotalCasesData(locations);
    }

    @CrossOrigin
    @RequestMapping("getOnlineDataPieTotalDeathsData")
    public HashMap<String, Object> getOnlineDataPieTotalDeathsData(@RequestBody ArrayList<String> locations) {
        return OnlineDataUtil.getOWIDDataPieTotalDeathsData(locations);
    }

}
