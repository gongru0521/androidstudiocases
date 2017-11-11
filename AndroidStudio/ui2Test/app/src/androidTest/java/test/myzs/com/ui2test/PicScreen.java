
package test.myzs.com.ui2test;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiAutomatorInstrumentationTestRunner;
import android.support.test.uiautomator.UiDevice;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/9/15.
 */


public class PicScreen extends UiAutomatorInstrumentationTestRunner {
    public Instrumentation instrument= InstrumentationRegistry.getInstrumentation();
    public UiDevice ud= UiDevice.getInstance(instrument);
    //

    public void screenshot(String str) throws IOException {
      //获取手机当前时间
        Calendar calendar =  Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        String datestr = calendar.get(Calendar.YEAR)+"_"+(calendar.get(Calendar.MONTH)+1)+"_"+calendar.get(Calendar.DAY_OF_MONTH)+""+        calendar.get(Calendar.HOUR_OF_DAY)+""+calendar.get(Calendar.MINUTE)+""+calendar.get(Calendar.SECOND);

       //根据手机时间和case名称保存图片
        File files = new File("/sdcard/screens/"+str+datestr+".png");
        ud.executeShellCommand("screencap -p "+files);
    }


        File screens =new File("/mnt/sdcard/screens");
//判断是否存在且指定创建的是否是文件夹
/*
        if (!screens.exists() && !screens.isDirectory())
        {
            System.out.println("Create new folder:/mnt/sdcard/screens");
            screens.mkdir();
        }
*/

}
