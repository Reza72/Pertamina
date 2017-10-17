package com.example.prosia.listviewvolley.service;

/**
 * Created by PROSIA on 14/10/2017.
 */

public class Server {

    public static final String LOGIN_URL = "http://fahevizha72.000webhostapp.com/login.php";
    public static final String LISTDATAMASAKAN_URL = "http://fahevizha72.000webhostapp.com/resep_list.php";
    public static final String DETAILRESEP = "http://fahevizha72.000webhostapp.com/resep_list_post.php";
    public static final String SIGN_UP = "http://fahevizha72.000webhostapp.com/insertadmin.php";
    public static final String FIND_USER_ID = "http://fahevizha72.000webhostapp.com/cariuser.php";
    public static final String UPDATE_ADMIN = "http://fahevizha72.000webhostapp.com/updateadmin.php";

    //parameter untuk login.php $_POST[key] (sesuaikan dengan nama yang di get pada file [select ..  where ] login.php)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_DETAIL_RESEP = "judul";

    //response dari login.php sebagai parameter
    public static final String LOGIN_SUCCESS = "success";

    //parameter to save data
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD_SIGN_UP = "password";
    public static final String KEY_EMAIL_SIGN_UP = "email";
    public static final String KEY_PHONE = "phone";


    //parameter to find id admin
    public static final String FIND_ID = "id";


}
