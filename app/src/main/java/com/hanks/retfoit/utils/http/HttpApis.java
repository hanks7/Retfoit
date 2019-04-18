package com.hanks.retfoit.utils.http;


import com.hanks.retfoit.bean.BaseBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by caihan on 2017/1/11.
 *
 * @GET 表明这是get请求
 * @POST 表明这是post请求
 * @PUT 表明这是put请求
 * @DELETE 表明这是delete请求
 * @PATCH 表明这是一个patch请求，该请求是对put请求的补充，用于更新局部资源
 * @HEAD 表明这是一个head请求
 * @OPTIONS 表明这是一个option请求
 * @HTTP 通用注解, 可以替换以上所有的注解，其拥有三个属性：method，path，hasBody
 * @Headers 用于添加固定请求头，可以同时添加多个。通过该注解添加的请求头不会相互覆盖，而是共同存在
 * @Header 作为方法的参数传入，用于添加不固定值的Header，该注解会更新已有的请求头
 * @Body 多用于post请求发送非表单数据, 比如想要以post方式传递json格式数据
 * @Filed 多用于post请求中表单字段, Filed和FieldMap需要FormUrlEncoded结合使用
 * @FiledMap 和@Filed作用一致，用于不确定表单参数
 * @Part 用于表单字段, Part和PartMap与Multipart注解结合使用, 适合文件上传的情况
 * @PartMap 用于表单字段, 默认接受的类型是Map<String,REquestBody>，可用于实现多文件上传
 * <p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * </p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * @Path 用于url中的占位符,{占位符}和PATH只用在URL的path部分，url中的参数使用Query和QueryMap代替，保证接口定义的简洁
 * @Query 用于Get中指定参数
 * @QueryMap 和Query使用类似
 * @Url 指定请求路径
 */
public interface HttpApis {

    String Url1 = "http://172.16.0.81:6666";
    String Url2 = "http://api.k780.com:88";
    /**
     * http://easyway.com.cn:8088/CustomServicesApi/Scanner?type=1&code=test&msg=t&verify=1
     */
    String Url3 = "http://easyway.com.cn:8088";

    /**
     * @param fields
     * @return
     */
    @FormUrlEncoded
    @POST("/api/Scanner")
    Call<BaseBean> getScanner3(@Url String url,
                               @FieldMap Map<String, String> fields
    );

    @GET
    Call<ResponseBody> profilePicture(@Url String url);

    @Headers({"urlname:manage"})
    @POST("members/auth")
    Call<BaseBean> doLogin(@Body RequestBody requestBody);

    @Headers({"urlname:mdffx"})
    @FormUrlEncoded
    @POST("login")
    Call<BaseBean> doLoginMdffx(@Field("username") String username, @Field("password") String password);


    @Headers({"urlname:manage"})
    @GET("members/datas")
    Call<BaseBean> doData(@Query("type") int type, @Query("params") int params);

    @POST("{path}")
    Call<ResponseBody> post(
            @Path(value = "path", encoded = true) String path,
            @QueryMap Map<String, Object> map);

    @GET("{path}")
    Call<ResponseBody> get(
            @Path(value = "path", encoded = true) String path,
            @QueryMap Map<String, Object> map);


    @Headers({"urlname:url1"})
    @GET("/api/Scanner?type=1&code=623&msg=&verify=")
    Call<BaseBean> test1();

    @Headers({"urlname:url2"})
    @GET("/?APP=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json")
    Call<BaseBean> test2();

    @GET("/CustomServicesApi/Scanner?type=1&code=test&msg=t&verify=1")
    Call<BaseBean> test3();
}
