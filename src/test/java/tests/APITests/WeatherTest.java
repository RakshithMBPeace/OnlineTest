package tests.APITests;

import Core.BaseAPI;


import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static Utils.LoadProperties.*;


public class WeatherTest extends BaseAPI {

    public String apiURL;
    public String cityName;
    public String appid;
    public String stateCode;
    public String countryCode;
    {
        try {
            apiURL = getPropertyValue("apiUrl");
            cityName= getPropertyValue("City");
            appid= getPropertyValue("appid");
            stateCode= getPropertyValue("StateCode");
            countryCode= getPropertyValue("CountryCode");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String cityNameStateCode=cityName+","+stateCode;
    public String cityNameStateCodeCountryCode=cityName+","+stateCode+","+countryCode;
    public String cityID;
    public String longitude;
    public String latitiude;




    @Test
    public void testWeatherUsingCityName(){
        Map<String, String> headerMap=new HashMap<String, String>();
        Map<String, String> queryParams= new HashMap<String, String>();
        queryParams.put("q", cityName);
        queryParams.put("appid", appid);
        Response resp = getAPI(headerMap, apiURL,queryParams);
        JSONObject respJson = new JSONObject(resp.asString());
        String actCityName= respJson.getString("name");
        Assert.assertEquals(cityName,actCityName);
    }

    @Test
    public void testWeatherUsingCityNameStateCode() {
        Map<String, String> headerMap = new HashMap<String, String>();
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("q", cityNameStateCode);
        queryParams.put("appid", appid);
        Response resp = getAPI(headerMap, apiURL, queryParams);
        JSONObject respJson = new JSONObject(resp.asString());
        String actCityName = respJson.getString("name");
        String actStateCode = respJson.get("cod").toString();
        Assert.assertEquals(cityName, actCityName);
        Assert.assertEquals(stateCode, actStateCode);
    }

    @Test
    public void testWeatherUsingCityNameStateCodeCoutryCode(){
        Map<String, String> headerMap=new HashMap<String, String>();
        Map<String, String> queryParams= new HashMap<String, String>();
        queryParams.put("q", cityNameStateCodeCountryCode);
        queryParams.put("appid", appid);
        Response resp = getAPI(headerMap, apiURL, queryParams);
        JSONObject respJson = new JSONObject(resp.asString());
        String actCityName = respJson.getString("name");
        String actStateCode = respJson.get("cod").toString();
        JSONObject countryJson =respJson.getJSONObject("sys");
        String actCountyCode = countryJson.getString("country");
        Assert.assertEquals(cityName, actCityName);
        Assert.assertEquals(stateCode, actStateCode);
        Assert.assertEquals(countryCode, actCountyCode);
        cityID = respJson.get("id").toString();
    }

    @Test
    public void testWeatherUsingCityToID(){
        Map<String, String> headerMap=new HashMap<String, String>();
        Map<String, String> queryParams= new HashMap<String, String>();
        queryParams.put("q", cityID);
        queryParams.put("appid", appid);
        Response resp = getAPI(headerMap, apiURL, queryParams);
        JSONObject respJson = new JSONObject(resp.asString());
        String actCityID = respJson.get("id").toString();
        Assert.assertEquals(cityID,actCityID);
        JSONObject coOrd= respJson.getJSONObject("coord");
        longitude = coOrd.get("lon").toString();
        latitiude = coOrd.get("lat").toString();
    }

    @Test
    public void testWeatherUsingCoOrdinates(){
        Map<String, String> headerMap=new HashMap<String, String>();
        Map<String, String> queryParams= new HashMap<String, String>();
        queryParams.put("lat", latitiude);
        queryParams.put("lon", longitude);
        queryParams.put("appid", appid);
        Response resp = getAPI(headerMap, apiURL, queryParams);
        JSONObject respJson = new JSONObject(resp.asString());
        JSONObject coOrd= respJson.getJSONObject("coord");
        String actLongitude = coOrd.get("lon").toString();
        String actLatitiude = coOrd.get("lat").toString();
        Assert.assertEquals(longitude,actLongitude);
        Assert.assertEquals(latitiude,actLatitiude);
    }

    @Test
    public void testWeatherValidateUsingTempValuesFromUI(){
        Map<String, String> headerMap=new HashMap<String, String>();
        Map<String, String> queryParams= new HashMap<String, String>();
        queryParams.put("lat", latitiude);
        queryParams.put("lon", longitude);
        queryParams.put("appid", appid);
        Response resp = getAPI(headerMap, apiURL, queryParams);
        JSONObject respJson = new JSONObject(resp.asString());
        System.out.println("peace"+resp.asString());
        JSONObject coOrd= respJson.getJSONObject("coord");
        String actLongitude = coOrd.get("lon").toString();
        String actLatitiude = coOrd.get("lat").toString();
        Assert.assertEquals(longitude,actLongitude);
        Assert.assertEquals(latitiude,actLatitiude);
    }
}
