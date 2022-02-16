# COVID-19 Data Visualization WEB project

##### Members:  Li Yichen 11912433, Li Boyan 11912914

## Part 1. Overview

Data visualization is the art of providing insights with the aid of some type of visual representation, such as charts, graphs, or more complex forms of visualizations like dashboards. 

This project aims to visualize COVID-19 data, with `Spring Boot` as the back-end development framework and `Vue`, `jQuery`, `Bootstrap`, `eCharts` and other libraries as the front-end.

This project supports the following:

- Tracking the latest COVID-19 data automatically. By setting the **scheduled task**, the crawler is automatically performed every 5 minutes.  
- Two data modes: **Local File Data** and **Online Data**.
- Data multi-access: **WHO Data Souce** and **OWID Data Souce**.
- Search function: Search the specific countries (**fuzzy matching**) in table-view, display the results and roll back the unsearched state.
- Sort function: Each field of data can be sorted in table-view, in ascending or descending order.
- Map visualization: An **interactive world map** is used to present data.
- A variety of visual data statistics: There are **twelve different charts**, including three bar charts, three line charts and three pie charts. Moreover, each each is **interactive**, **animated**, and can be set with **flexible parameters** to adjust the data to be displayed.  There are charts that show **trends** in time series data, which can be **fast-forward**, **paused** or **rewind** using the timeline bar.
- Data save: Table data can be saved in **CSV**, **SQL**, **TXT**, **JSON**, and **XLS** formats. All graphs saving is also supported for all charts.

## Part 2. Quick Start

1.  Requirements

    Install some software to start the Front-end, such as `Webstorm`, `python -m http.server`, etc.

    Install `Java` to start the Back-end.

2.  Run the Back-end

    ```bash
    cd Back-End
    java -jar COVID-19-0.0.1-SNAPSHOT.jar
    ```

    When you see info like below means it starts successfully:

    ```bash
      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::                (v2.6.1)
    
    2021-12-21 13:54:33.188  INFO 17728 --- [           main] cs209.covid19.Covid19Application         : Starting Covid19Application v0.0.1-SNAPSHOT using Java 1.8.0_312 on DESKTOP-I76CLVT with PID 17728 (C:\Users\Bugmaker\Desktop\COVID19-数据可视化\Back-End\COVID-19-0.0.1-SNAPSHOT.jar started by Bugmaker in C:\Users\Bugmaker\Desktop\COVID19-数据可视化\Back-End)
    2021-12-21 13:54:33.188  INFO 17728 --- [           main] cs209.covid19.Covid19Application         : No active profile set, falling back to default profiles: default
    2021-12-21 13:54:33.726  INFO 17728 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
    2021-12-21 13:54:33.734  INFO 17728 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
    2021-12-21 13:54:33.734  INFO 17728 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.55]
    2021-12-21 13:54:33.770  INFO 17728 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
    2021-12-21 13:54:33.771  INFO 17728 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 551 ms
    2021-12-21 13:54:34.335  INFO 17728 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
    2021-12-21 13:54:34.344  INFO 17728 --- [           main] cs209.covid19.Covid19Application         : Started Covid19Application in 1.442 seconds (JVM running for 50.321)
    ```

3.  Run the Front-end

    ```bash
    python -m http.server
    ```

    When you see info like below means it starts successfully:

    ```bash
    Serving HTTP on :: port 8000 (http://[::]:8000/) ...
    ```

4.  Open the browser

    ```bash
    http://localhost:8000/
    ```

## Part 3. The structure

#### Part 3.1 Back-end structure

```bash
└─src
    ├─main
    │  ├─java
    │  │  └─cs209
    │  │      └─covid19
    │  │          │  Covid19Application.java
    │  │          │
    │  │          ├─controller
    │  │          │      OWIDDataController.java
    │  │          │      WHODataController.java
    │  │          │
    │  │          ├─pojo
    │  │          │      OWIDData.java
    │  │          │      WHOData.java
    │  │          │
    │  │          └─utils
    │  │                  LocalDataUtil.java
    │  │                  OnlineDataUtil.java
    │  │
    │  └─resources
    │      │  application.properties
    │      │
    │      ├─static
    │      │      owid-covid-data.csv
    │      │      WHO-COVID-19-global-data.csv
    │      │
    │      └─templates
    └─test
        └─java
            └─cs209
                └─covid19
                        Covid19ApplicationTests.java

```

1.  **Covid19Application.java**

    The startup of Spring boot framework.

2.  **OWIDData.java**

    ```java
        private String isoCode;
    
        private String continent;
    
        private String location;
    
        private int totalCases;
    
        private int newCases;
    
        private int totalDeaths;
    
        private int newDeaths;
    ```

3.  **WHOData.java**

    ```java
    	private String countryCode;
    
        private String country;
    
        private String WHORegion;
    
        private int newCases;
    
        private int cumulativeCases;
    
        private int newDeaths;
    
        private int cumulativeDeaths;
    ```

4.  **OWIDDataController.java**

    The OWID data controller in MVC framework, contains interfaces to get different OWID data (JSON).

    ```java
        // get the latest local Data from source <Our World In Data>.
    	@CrossOrigin
        @RequestMapping("latestLocalData")
        public ArrayList<OWIDData> getLatestLocalData();
    ```

    ```java
        // get the latest update date of local data from source <Our World In Data>.
    	@CrossOrigin
        @RequestMapping("latestLocalDate")
        public LocalDate getLatestLocalDate();
    ```

    ```java
        // get the latest local OWID data by specific location.
    	@CrossOrigin
        @RequestMapping("latestLocalDataByLocation/{searchCountryName}")
        public ArrayList<OWIDData> getLatestLocalDataByLocation(@PathVariable("searchCountryName") String location);
    ```

    ```java
        // get the ordered <dataArrayList> data.
        @CrossOrigin
        @RequestMapping("getOrderedLocalDataList/{attr}/{reverseOrder}")
        public ArrayList<OWIDData> getOrderedLocalDataList(@RequestBody ArrayList<OWIDData> dataArrayList, @PathVariable String attr, @PathVariable int reverseOrder);
    ```

    ```java
        // get the map data from local OWID.
        @CrossOrigin
        @RequestMapping("getMapLocalData")
        public ArrayList<Object> getMapLocalData();
    ```

    ```java
        // get the local OWID data for specific month.
        @CrossOrigin
        @RequestMapping("getLocalDataForOneMonth/{date}")
        public ArrayList<ArrayList<Object>> getLocalDataForOneMonth(@PathVariable String date);
    ```

    ```java
        // get the total deaths for specific month and locations.
        @CrossOrigin
        @RequestMapping("getLocalDataTotalDeathsForOneMonthByLocations/{date}")
        public ArrayList<ArrayList<Object>> getLocalDataTotalDeathsForOneMonthByLocations(@PathVariable String date, @RequestBody ArrayList<String> locations);
    ```

    ```java
        // get the latest new cases and new deaths from local OWID data for specific locations.
        @CrossOrigin
        @RequestMapping("getLatestNewCasesNewDeathsLocalDataForLocations")
        public ArrayList<ArrayList<Object>> getLatestNewCasesNewDeathsLocalDataForLocations(@RequestBody ArrayList<String> locations);
    ```

    ```java
        // get the latest total cases data (in Pie chart needed format) in local OWID.
        @CrossOrigin
        @RequestMapping("getLocalDataPieTotalCasesData")
        public HashMap<String, Object> getLocalDataPieTotalCasesData(@RequestBody ArrayList<String> locations);
    ```

    ```java
        // get the latest total deaths data (in Pie chart needed format) in local OWID.
        @CrossOrigin
        @RequestMapping("getLocalDataPieTotalDeathsData")
        public HashMap<String, Object> getLocalDataPieTotalDeathsData(@RequestBody ArrayList<String> locations);
    ```

    The remaining methods are similar to above, replacing **local OWID data** with **online OWIDdata**.

5.  **WHODataController.java**

    The methods are similar to **OWIDDataController.java**, replacing **OWID Data** with **WHO data**.

6.  **LocalDataUtil.java**

    The data util class to get and process data from **local data file**.

    ```java
    // the resouce of local OWID data file.
    private static final ClassPathResource OWIDDataFileResource = new ClassPathResource("static/owid-covid-data.csv");
    ```

    ```java
    // the resouce of local WHO data file.
    private static final ClassPathResource WHODataFileResource = new ClassPathResource("static/WHO-COVID-19-global-data.csv");
    ```

    ```java
    // the stored OWID data using HashMap structure.
    private static final HashMap<LocalDate, ArrayList<OWIDData>> localOWIDData = new HashMap<>();
    ```

    ```java
    // the stored WHO data using HashMap structure.
    private static final HashMap<LocalDate, ArrayList<WHOData>> localWHOData = new HashMap<>();
    ```

    ```java
    // load the OWID data from local data file.
    public static void loadOWIDData();
    ```

    ```java
    // get the latest updated date of local OWID data.
    public static LocalDate getOWIDDataLatestDate();
    ```

    ```java
    // get the latest updated data of local OWID data.
    public static ArrayList<OWIDData> getLatestOWIDData();
    ```

    ```java
    // get the latest OWID data for specific location.
    public static ArrayList<OWIDData> getLatestOWIDDataByLocation(String location);
    ```

    ```java
    // get the ordered <arrayList> data.
    public static ArrayList<OWIDData> getOrderedOWIDDataList(ArrayList<OWIDData> arrayList, String orderAttr, int reverseOrder);
    ```

    ```java
    // get the OWID data in world map needed format.
    public static ArrayList<Object> getMapOWIDData();
    ```

    ```java
    // get the total deaths OWID data for specific locations and month.
    public static ArrayList<ArrayList<Object>> getOWIDDataTotalDeathsForOneMonthByLocations(LocalDate localDate, ArrayList<String> locations);
    ```

    ```java
    // get the OWID data for specific month.
    public static ArrayList<ArrayList<Object>> getOWIDDataForOneMonth(LocalDate localDate);
    ```

    ```java
    // get the total cases data in pie chart needed format for specific locations.
    public static HashMap<String, Object> getOWIDDataPieTotalCasesData(ArrayList<String> locations);
    ```

    ```java
    // get the total deaths data in pie chart needed format for specific locations.
    public static HashMap<String, Object> getOWIDDataPieTotalDeathsData(ArrayList<String> locations);
    ```

    ```java
    // get the new cases and new deaths data in pie chart needed format for specific locations.
    public static ArrayList<ArrayList<Object>> getLatestNewCasesNewDeathsOWIDDataForLocations(ArrayList<String> locations);
    ```

    The remaining methods are similar to above, replacing **local OWID data souce**  with **Local WHO data souce**.

7.  **OnlineDataUtil.java**

    The data util class to get and process data from **realtime online data**. The methods are similar to **LocalDataUtil.java**, replacing the **local data souce** with **online data souce**.

#### Part 3.2 Front-end structure

```bash
│  index.html
│  world.json
│
├─bootstrap
│  ├─css
│  │      bootstrap-theme.css
│  │      bootstrap-theme.css.map
│  │      bootstrap-theme.min.css
│  │      bootstrap-theme.min.css.map
│  │      bootstrap.css
│  │      bootstrap.css.map
│  │      bootstrap.min.css
│  │      bootstrap.min.css.map
│  │
│  ├─fonts
│  │      glyphicons-halflings-regular.eot
│  │      glyphicons-halflings-regular.svg
│  │      glyphicons-halflings-regular.ttf
│  │      glyphicons-halflings-regular.woff
│  │      glyphicons-halflings-regular.woff2
│  │
│  └─js
│          bootstrap.js
│          bootstrap.min.js
│          npm.js
│
└─js
        axios.min.js
        echarts.min.js
        jquery-3.6.0.min.js
        jquery.base64.js
        lodash.min.js
        tableExport.min.js
        vue.min.js
        world.js
```

**Javascript method:**

```javascript
// update the covid data in vue.
updateCOVIDData: function ();

// search the country and update the table data.
searchCountry: function ();

// change the data source.
changeDataSource: function (source);

// sort the table data based on attr.
sortTable: function (attr);

// change the page to show different content.
changePage: function (index);

// different page render function.
page1Render: function ();
page2Render: function ();
page3Render: function ();
page4Render: function ();

// diffrent graph in pages render function.
page2Graph1Render: function ();
page2Graph2Render: function ();
page2Graph3Render: function ();
page3Graph1Render: function ();
page3Graph2Render: function ();
page3Graph3Render: function ();
page4Graph1Render: function ();
page4Graph2Render: function ();
page4Graph3Render: function ();

// update the data in graph to show.
page2Graph2ModifyCountryList: function (flag);
page2Graph3ModifyCountryList: function (flag);
page3Graph1ModifyCountryList: function (flag);
page3Graph2ModifyCountryList: function (flag);
page4Graph1ModifyCountryList: function (flag);
page4Graph2ModifyCountryList: function (flag);

// export the table data in different type format.
exportTable: function (type);
```
