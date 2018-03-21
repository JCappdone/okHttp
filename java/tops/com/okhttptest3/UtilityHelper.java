package tops.com.okhttptest3;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shriji on 18/1/18.
 */

public class UtilityHelper {

    public static final String BASE_URL = "http://192.168.43.190/okhttptest3/";
    private static final String FILE_NAME = "TEMP";
    private static final String USER_NAME = "username";

    public void writeUser(Context context,String username){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USER_NAME,username);
        editor.commit();

    }





}
