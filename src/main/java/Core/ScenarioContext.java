package Core;

import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import org.apache.http.HttpResponse;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

        public WebDriver webDriver;
        public FilterableRequestSpecification previousRequest;
        public Response previousResponse;
        public Object previousHttpRequest;
        public HttpResponse previousHttpResponse;
        public Map<String, Object> requestTransactionMap = new HashMap();
        public Map<String, Object> responseTransactionMap = new HashMap();
        private Map<String, String> dataMapForHooks = new HashMap();
        private Map<String, String> dataStore = new HashMap();

        public ScenarioContext() {
        }

        public Map<String, String> getDataMapForHooks() {
            return this.dataMapForHooks;
        }

        public void setDataMapForHooks(String key, String value) {
            this.dataMapForHooks.put(key, value);
        }

        public Map<String, Object> getRequestTransactionMap() {
            return this.requestTransactionMap;
        }

        public void addRequestInTransactionMap(String transactionName, Object requestObject) {
            this.requestTransactionMap.put(transactionName, requestObject);
        }

        public void addResponseInTransactionMap(String transactionName, Object responseObject) {
            this.responseTransactionMap.put(transactionName, responseObject);
        }

        public Map<String, Object> getResponseTransactionMap() {
            return this.responseTransactionMap;
        }

        public Object getResponseFromTransactionMap(String transactionName) {
            return this.responseTransactionMap.get(transactionName);
        }

        public Map<String, String> getDataStore() {
            return this.dataStore;
        }

        public void setDataStore(String key, String value) {
            this.dataStore.put(key, value);
        }
    }


