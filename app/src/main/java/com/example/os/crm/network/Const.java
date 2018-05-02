package com.example.os.crm.network;

/**
 ******************************************************
 *                                                    *
 *                                                    *
 *                       _oo0oo_                      *
 *                      o8888888o                     *
 *                      88" . "88                     *
 *                      (| -_- |)                     *
 *                      0\  =  /0                     *
 *                    ___/`---'\___                   *
 *                  .' \\|     |# '.                  *
 *                 / \\|||  :  |||# \                 *
 *                / _||||| -:- |||||- \               *
 *               |   | \\\  -  #/ |   |               *
 *               | \_|  ''\---/''  |_/ |              *
 *               \  .-\__  '-'  ___/-. /              *
 *             ___'. .'  /--.--\  `. .'___            *
 *          ."" '<  `.___\_<|>_/___.' >' "".          *
 *         | | :  `- \`.;`\ _ /`;.`/ - ` : | |        *
 *         \  \ `_.   \_ __\ /__ _/   .-` /  /        *
 *     =====`-.____`.___ \_____/___.-`___.-'=====     *
 *                       `=---='                      *
 *                                                    *
 *                                                    *
 *     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    *
 *                                                    *
 *               佛祖保佑         永无BUG              *
 *                                                    *
 *                                                    *
 ******************************************************
 *
 * Created by ninos on 2016/6/2.
 *
 */
public class Const {
    /**
     * URL
     */
    public static final String BASE_URL = "http://1g7514058j.imwork.net:9998";
    public static final String BASE_IMG = "http://121.40.189.165/";
//    public static final String BASE_URL = "http://127.0.0.1:63355/WebService/";
    
    public static final String KEY = "44d1e04f-fa08-40a2-8258-6356c172225a"; //加密VID
    public static final String UID = "b7d381fb-62aa-404e-824a-10a31d3698ef"; //加密UID

    public static final String LOGIN = "/crm/login";

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_FOOTER = -1;
    public static final int VIEW_TYPE_NORMAL = 1;

    public static String ServiceTime="";
    public static int hour=0;
    public static int min=0;
    public static int second=0;

    public static final int OK = 200;

    /**
     * 结算时  保存每个店铺的配送方式
     * */
    public static String[] PEISONGFANGSHI= new String[100];
    /**
     *
     * 保存每个商品的配送方式，方便记录价格
     * */
    public static String[] MAIJIAYUNSONGFANGSHI = new String[100];
    
    public static String getUrl(String token){
        if(token==null || token.equals("")){
            return "";
        }
    	return String.format(BASEURL, token);
    }
    public static final String BASEURL = "http://pp.xiangdaole.com/%s";

}
