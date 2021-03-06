package com.miguan.otk.model.services;

import com.miguan.otk.model.bean.Balance;
import com.miguan.otk.model.bean.Chatroom;
import com.miguan.otk.model.bean.Feedback;
import com.miguan.otk.model.bean.Game;
import com.miguan.otk.model.bean.Home;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.model.bean.Message;
import com.miguan.otk.model.bean.Mission;
import com.miguan.otk.model.bean.News;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.bean.Schedule;
import com.miguan.otk.model.bean.Screenshot;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.model.bean.Splash;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.model.bean.Version;
import com.miguan.otk.model.bean.Withdraw;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public interface Services {

    String BASE_URL = "http://api.beta.otkpk.com/v1/";

    //////////////////用户相关/////////////////////
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 是否成功
     */
    @FormUrlEncoded
    @POST("user/user/login")
    Observable<User> login(
            @Field("username") CharSequence username,
            @Field("password") CharSequence password
    );

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @param mobile 手机
     * @param captcha 验证码
     * @return 结果
     */
    @FormUrlEncoded
    @POST("user/user/sign-up")
    Observable<User> register(
            @Field("username") CharSequence username,
            @Field("mobile") CharSequence mobile,
            @Field("captcha") CharSequence captcha,
            @Field("password") CharSequence password
    );

    /**
     * 发送注册验证码
     * @param mobile 手机号
     * @return 是否成功
     */
    @FormUrlEncoded
    @POST("user/user/captcha-register")
    Observable<Boolean> sendCaptchaRegister(
            @Field("mobile") CharSequence mobile
    );

    /**
     * 发送重置密码验证码
     * @param mobile 手机号
     * @return 是否成功
     */
    @FormUrlEncoded
    @POST("user/user/captcha-reset")
    Observable<Boolean> sendCaptchaReset(
            @Field("mobile") CharSequence mobile
    );

    /**
     * 其他验证码
     * @param mobile 手机号
     * @return 是否成功
     */
    @FormUrlEncoded
    @POST("user/user/captcha-update")
    Observable<Boolean> sendCaptchaUpdate(
            @Field("mobile") CharSequence mobile,
            @Field("token") CharSequence token
    );

    /**
     * 忘记密码
     *
     * @param newPwd 新密码
     * @param mobile 手机
     * @param captcha 验证码
     * @return
     */
    @FormUrlEncoded
    @POST("user/user/reset-password")
    Observable<Boolean> modifyPwd(
            @Field("mobile") CharSequence mobile,
            @Field("captcha") CharSequence captcha,
            @Field("password") CharSequence newPwd
    );

    /**
     * 个人中心
     *
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("user/center/index")
    Observable<User> userInfo(
            @Field("token") CharSequence token
    );

    /**
     * 个人资料
     *
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("user/message/index")
    Observable<User> userProfile(
            @Field("token") CharSequence token
    );

    /**
     * 个人资料修改
     * token
     * photo
     * qq
     * email
     * actuality
     * birthday
     * province
     * city
     * sign
     * @return
     */
    @FormUrlEncoded
    @POST("user/message/edit")
    Observable<Boolean> modifyProfile(
            @FieldMap Map<String, String> request
    );

    /**
     * 立即签到
     *
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("user/sign/index")
    Observable<Sign> sign(
            @Field("token") CharSequence token
    );

    /**
     * 签到信息
     *
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("user/sign/signdata")
    Observable<Sign> signDetail(
            @Field("token") CharSequence token,
            @Field("month") String month,
            @Field("year") Integer year
    );

    /**
     * 参赛记录
     *
     * @param token 登录令牌
     * @return
     */
    @GET("user/banner/index")
    Observable<List<Match>> myMatchList(
            @Query("token") CharSequence token,
            @Query("page") Integer page
    );

    /**
     * 对战记录
     *
     * @param token 登录令牌
     * @return
     */
    @GET("user/banner/record")
    Observable<List<Battle>> againstList(
            @Query("token") CharSequence token,
            @Query("page") Integer page
    );

    /**
     * 余额明细
     * @param type 余额类型 money-元宝 score-撒币
     * @param token 登录令牌
     * @param page 页数
     * @return
     */
    @GET("user/balance/{type}")
    Observable<List<Balance>> balanceList(
            @Path("type") String type,
            @Query("token") CharSequence token,
            @Query("page") Integer page
    );

    /**
     * 提现记录首页
     * @param token 登录令牌
     * @return
     */
    @GET("user/record/getcashrecord")
    Observable<Withdraw> withdrawRecord(
            @Query("token") CharSequence token
    );

    /**
     * 提现记录列表
     * @param token 登录令牌
     * @param page 页数
     * @return
     */
    @GET("user/record/getcashrecord2")
    Observable<List<Withdraw>> withdrawRecord(
            @Query("token") CharSequence token,
            @Query("page") Integer page
    );

    /**
     * 提现记录列表
     * @param token 登录令牌
     * @param money 提现金额
     * @return
     */
    @GET("user/record/deposit")
    Observable<Boolean> withdraw(
            @Query("token") CharSequence token,
            @Query("money") Integer money
    );

    /**
     * 绑定支付宝
     */
    @FormUrlEncoded
    @POST("user/record/alipaybind")
    Observable<Boolean> bindAlipay(
            @Field("token") String token,
            @Field("mobile") String mobile,
            @Field("alipay_account") String account,
            @Field("captcha") String captcha
    );

    /**
     * 游戏账号列表
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("user/gameaccount/gameinfo")
    Observable<List<Game>> gameAccounts(
            @Field("token") String token
    );

    /**
     * 添加游戏账号
     */
    @FormUrlEncoded
    @POST("user/gameaccount/accountadd")
    Observable<Boolean> addGameAccount(
            @Field("token") String token,
            @Field("game_name") String game_name,
            @Field("game_account") String game_account
    );

    /**
     * 编辑游戏账号
     */
    @FormUrlEncoded
    @POST("user/gameaccount/accountupdate")
    Observable<Boolean> updateGameAccount(
            @Field("token") String token,
            @Field("id") int id,
            @Field("game_account") String game_account
    );

    /**
     * 消息分类最新一条
     */
    @FormUrlEncoded
    @POST("user/systemmessage/message")
    Observable<List<Message>> getMessageDesc(
            @Field("token") String token
    );

    /**
     * 消息列表
     */
    @FormUrlEncoded
    @POST("user/systemmessage/system-message")
    Observable<List<Message>> getMessageList(
            @Field("token") String token,
            @Field("type") Integer type,
            @Field("page") Integer page
    );

    /**
     * 吐槽一下
     * @param token 登录令牌
     * @param type  类型（0:产品建议、1:发现BUG、2:举报作弊、3:其他）
     * @param contact 联系方式
     * @param content 吐槽内容
     * @param img 上传照片
     * @param source 信息来源（来源 0:安卓 1：IOS）
     * @return
     */
    @FormUrlEncoded
    @POST("user/feedback/feedback")
    Observable<Feedback> feedback(
            @Field("token") String token,
            @Field("type") Integer type,
            @Field("contact") String contact,
            @Field("content") String content,
            @Field("img") String img,
            @Field("source") int source
    );

    ////////////////////比赛相关//////////////////////

    /**
     * 比赛首页
     *
     * @param token 登录令牌 可选
     * @return
     */
    @GET("home/home/index")
    Observable<Home> homeMatch(
            @Query("token") String token
    );

    /**
     * 赛事详情
     *
     * @param matchID 赛事ID
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("competition/competition/index")
    Observable<Match> matchDetail(
            @Field("token") String token,
            @Field("competition_id") int matchID
    );

    /**
     * 赛事参与用户列表
     *
     * @param matchID 赛事ID
     * @param type 参赛人员类型(1：正常，2：替补)
     * @return
     */

    @GET("competition/competition/competitors")
    Observable<List<User>> competitors(
            @Query("competition_id") int matchID,
            @Query("type") int type
    );

    /**
     * 赛事规则
     *
     * @param matchID 赛事ID
     * @return
     */

    @GET("competition/competition/rule")
    Observable<Match> competitionRule(
            @Query("competition_id") int matchID
    );

    /**
     * 对战表
     *
     * @param matchID 赛事ID
     * @param round 比赛第几轮（从0开始）
     * @return
     */
    @FormUrlEncoded
    @POST("competition/competition/table")
    Observable<Schedule> competitionSchedule(
            @Field("competition_id") int matchID,
            @Field("round") int round
    );

    /**
     * 赛事报名
     *
     * @param matchID 赛事ID
     * @param password 密码(选填)
     * @param code 邀请码(选填)
     * @return
     */
    @FormUrlEncoded
    @POST("competition/competition/join")
    Observable<Battle> competitionEnter(
            @Field("token") String token,
            @Field("competition_id") int matchID,
            @Field("password") String password,
            @Field("invitation_code") String code
    );

    /**
     * 对战页面
     *
     * @param battleID 赛事ID
     * @return
     */
    @FormUrlEncoded
    @POST("competition/battle/index")
    Observable<Battle> battleDetail(
            @Field("token") String token,
            @Field("battle_id") int battleID
    );

    /**
     * 对战准备
     *
     * @param matchID 赛事ID
     * @return
     */
    @FormUrlEncoded
    @POST("competition/battle/ready")
    Observable<Battle> ready(
            @Field("token") String token,
            @Field("battle_id") int matchID
    );

    /**
     * 对战pick
     *
     * @param battle_id 对战ID
     * @return
     */
    @FormUrlEncoded
    @POST("competition/battle/pick")
    Observable<Battle> pick(
            @Field("token") String token,
            @Field("battle_id") int battle_id,
            @FieldMap() Map<String, Integer> carPics
    );

    /**
     * 对战ban
     *
     * @param battleID 赛事ID
     * @return
     */
    @FormUrlEncoded
    @POST("competition/battle/ban")
    Observable<Battle> ban(
            @Field("token") String token,
            @Field("battle_id") int battleID,
            @Field("ban") Integer ban
    );

    /**
     * 对战提交结果
     *
     * @param battleID 赛事ID
     * @return
     */
    @FormUrlEncoded
    @POST("competition/battle/record")
    Observable<Battle> submitResult(
            @Field("token") String token,
            @Field("battle_id") Integer battleID,
            @Field("role") Integer role
    );

    /**
     * 重置对战结果
     *
     * @param battleID 赛事ID
     * @return
     */
    @FormUrlEncoded
    @POST("competition/battle/unrecord")
    Observable<Battle> resubmitResult(
            @Field("token") String token,
            @Field("battle_id") Integer battleID
    );

    /**
     * 赛事签到
     *
     * @param matchID 赛事ID
     * @return
     */
    @FormUrlEncoded
    @POST("competition/competition/joinsign")
    Observable<Boolean> competitionSign(
            @Field("token") String token,
            @Field("competition_id") int matchID
    );

    /**
     * 获取对战信息
     *
     * @param competitionID 赛事ID
     * @return
     */
    @FormUrlEncoded
    @POST("competition/competition/battle")
    Observable<Battle> battleID(
            @Field("token") String token,
            @Field("competition_id") int competitionID
    );

    /**
     * 对战截图列表
     *
     * @param battleID 对战ID
     * @return
     */
    @FormUrlEncoded
    @POST("competition/battle/uploadinfo")
    Observable<Screenshot> battleUploadInfo(
            @Field("token") String token,
            @Field("battle_id") int battleID,
            @Field("role") String role
    );

    /**
     * 对战提交截图
     *
     * @param battleID 对战ID
     * @return
     */
    @FormUrlEncoded
    @POST("competition/battle/upload")
    Observable<Battle> battleUpload(
            @Field("token") String token,
            @Field("battle_id") int battleID,
            @Field("kind") String kind,
            @Field("photo") String photo
    );

    /**
     * 获取聊天室ID
     *
     * @param room_type 房间类型（c:比赛房间，b:对战房间）
     * @param id 对战ID或者比赛ID
     * @return
     */
    @FormUrlEncoded
    @POST("competition/chat/room")
    Observable<Chatroom> chatRoomID(
            @Field("token") String token,
            @Field("room_type") String room_type,
            @Field("id") int id
    );

    ////////////////////其他//////////////////////
    /**
     * 资讯列表
     *
     * @param type 文章类型 '1'=>'新闻','2'=>'行业','3'=>'攻略','4'=>'评测','5'=>'活动',
     * @param page 当前页数
     * @return
     */
    @GET("article/article/article-list")
    Observable<List<News>> newsList(
            @Query("type") int type,
            @Query("page") int page
    );

    /**
     * 广告图
     *
     * @param type 广告图位置 read-splash-闪屏； read-opening-首页弹窗
     * @param mobile 手机系统类型(1:android, 2:ios)
     * @return
     */
    @GET("system/system/{type}")
    Observable<Splash> splash(
            @Path("type") String type,
            @Query("mobile") Integer mobile
    );

    /**
     * 每日任务
     *
     * @param token 登录
     * @return
     */
    @FormUrlEncoded
    @POST("user/task/task")
    Observable<List<Mission>> missionList(
            @Field("token") String token
    );

    /**
     * 每日任务领取奖励
     *
     * @param token 登录
     * @return
     */
    @FormUrlEncoded
    @POST("user/task/taskscore")
    Observable<Mission> missionDole(
            @Field("token") String token,
            @Field("id") int missionID
    );

    /**
     * 检测更新
     *
     * @param version 当前版本号
     * @return
     */
    @GET("system/system/version-upgrade")
    Observable<Version> checkUpdate(
            @Query("version") String version
    );

}
