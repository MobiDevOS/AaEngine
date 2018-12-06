package com.zhujohnle.mobidevos.httpenginer;

import com.zhujohnle.mobidevos.framework.http.api.ResponseBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @auth &{zhujiule}
 * @date 2018/12/5
 * @copyright Api测试
 **/
public interface Api
{

   //GET 请求
   @GET("/getUserInfo/{userId}")
   Observable<ResponseBean> getUserInfo(@Path("userId") String userId);


   /*Post 请求*
     form-data 格式
    */
   @POST("/postUseInfo")
   Observable<ResponseBean> getUserInfo(@QueryMap Map<String,String> map);

   /*Post 请求*
   application/json 格式
  */
   @POST("/postUseInfo")
   Observable<ResponseBean> postUseInfo(@Body String body);

   /*Post 请求*
     application/json 格式
     @Headers("Content-Type:application/json;charset=UTF-8")
 */
   @POST("/postUseInfo")
   @Headers("Content-Type:application/json;charset=UTF-8")
   Observable<ResponseBean> postUseInfoWithHead(@Body String body);

   //   修改会员地址信息
   @PUT("/userController/editReceivingAddress/{id}")
   @Headers("Content-Type:application/json;charset=UTF-8")
   Observable<ResponseBean> editReceivingAddress(@Path("id") String id, @Body String body);


}
