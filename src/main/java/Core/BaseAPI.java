package Core;



import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseAPI {
        ScenarioContext scenarioContext;
        private static final Logger logger = LoggerFactory.getLogger(BaseAPI.class);
        private static ResponseSpecification responseSpecification;
        private RequestSpecification requestSpec = (RequestSpecification) RestAssured.given().log().all();

    public BaseAPI() {
    }


        public static String getResponseKeyValue(String key, JsonPath jsonPathEvaluator) {
            String responseValue = null;
            Map responseMap = jsonPathEvaluator.getMap("");

            try {
                if (responseMap.containsKey(key)) {
                    if (responseMap.get(key) == null) {
                        responseValue = "null";
                    } else {
                        responseValue = jsonPathEvaluator.get(key).toString();
                    }
                } else {
                    logger.error("Unable to find parameter key :: " + key + " in response");
                }

                return responseValue;
            } catch (Exception var5) {
                logger.info(String.valueOf(var5));
            }
            return responseValue;
        }

        public Response postAPI(Map<String, String> headerMap, Object request, String url) {
            RestAssured rest = null;
            logger.info("POST call to URL " + url);
            Response response = (Response)RestAssured.given().contentType(ContentType.JSON).headers(headerMap).and().body(request).when().post(url, new Object[0]);
            String responseText = response.asString();
            logger.info(" **** POST call Response time " + response.timeIn(TimeUnit.MILLISECONDS) + " ms");
            return response;
        }

        public Response getAPI(Map<String, String> headerMap, String url, Map<String,?> queryParamMap) {
            logger.info("GET call to URL " + url);
            Response response = (Response)this.requestSpec.contentType(ContentType.JSON).headers(headerMap).queryParams(queryParamMap).when().get(url, new Object[0]);
            logger.info(" **** GET call Response time " + response.timeIn(TimeUnit.MILLISECONDS) + " ms");
            return response;
        }

        public Response putAPI(Map<String, String> headerMap, Object request, String url) {
            logger.info("PUT call to URL " + url);
            Response response = (Response)RestAssured.given().contentType(ContentType.JSON).headers(headerMap).and().body(request).when().put(url, new Object[0]);
            String responseText = response.asString();
            logger.info(" **** PUT call Response time " + response.timeIn(TimeUnit.MILLISECONDS) + " ms");
            return response;
        }

    static {
        responseSpecification = RestAssured.expect().contentType(ContentType.JSON);
    }

    public class logFilter implements Filter {
        public logFilter() {
        }

        public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
            BaseAPI.logger.info("********************REQUEST********************");
            Response response = ctx.next(requestSpec, responseSpec);
            BaseAPI.logger.info("Method :" + requestSpec.getMethod());
            BaseAPI.logger.info("URI :" + requestSpec.getURI());
            BaseAPI.logger.info("Headers :" + requestSpec.getHeaders().toString());
            BaseAPI.logger.info("Body :" + requestSpec.getBody());
            BaseAPI.logger.info("********************RESPONSE********************");
            BaseAPI.logger.info("Response :" + response.prettyPrint());
            BaseAPI.this.scenarioContext.previousResponse = response;
            BaseAPI.this.scenarioContext.previousRequest = requestSpec;
            return response;
        }
    }
   }




