package http;

import config.PropertyUtil;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//This class provides methods common to all the api requests
public abstract class BaseApi {
    private final RequestSpecification requestSpecification;

    public BaseApi() {
        this.requestSpecification = RestAssured.given()
                .and().baseUri(PropertyUtil.getConfig().baseUrl());
    }

    protected BaseApi setBasePath(String basePath){
        this.requestSpecification.basePath(basePath);
        return this;
    }

    protected void setRequestBody(Object object){
        this.requestSpecification.body(object);
    }

    protected BaseApi setContentType(ContentType contentType){
        this.requestSpecification.contentType(contentType);
        return this;
    }

    protected void setPathParam(String paramName, Object value){
        this.requestSpecification.pathParams(paramName, value);
    }

    protected BaseApi setBasicAuth(String username, String password){
        this.requestSpecification.auth().preemptive().basic(username, password);
        return this;
    }

    public BaseApi loggAllRequestData(){
        this.requestSpecification.filter(new RequestLoggingFilter());
        return this;
    }

    public BaseApi logAllSpecifiedRequestDetails(LogDetail logDetail){
        this.requestSpecification.filter(new RequestLoggingFilter(logDetail));
        return this;
    }

    public BaseApi logAllResponseData(){
        this.requestSpecification.filter(new ResponseLoggingFilter());
        return this;
    }

    public BaseApi logAllSpecifiedResponseData(LogDetail logDetail){
        this.requestSpecification.filter(new ResponseLoggingFilter(logDetail));
        return this;
    }

    protected Response sendRequest(Method method){
        final RequestSpecification when = this.requestSpecification.when();
        return switch (method){
            case GET -> when.get();
            case PUT -> when.put();
            case POST -> when.post();
            case DELETE -> when.delete();
            case PATCH -> when.patch();
            default -> throw new IllegalArgumentException("Input method type not supported.");
        };
    }
}
