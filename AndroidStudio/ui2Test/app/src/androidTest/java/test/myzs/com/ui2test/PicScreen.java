
package test.myzs.com.ui2test;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiAutomatorInstrumentationTestRunner;
import android.support.test.uiautomator.UiDevice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/15.
 */


public class PicScreen extends UiAutomatorInstrumentationTestRunner {
    public Instrumentation instrument= InstrumentationRegistry.getInstrumentation();
    public UiDevice ud= UiDevice.getInstance(instrument);
    //
    /*
    @Before
    public void grantPermission() throws IOException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ud.executeShellCommand("pm grant " + getTargetContext().getPackageName() + " android.permission.WRITE_EXTERNAL_STORAGE");
        }

    }
    */
    public void screenshot(String str) throws IOException {
   //     Date a = new Date();
     //   SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd");
     //   String c = b.format(a);
     //   System.out.println(c);
        File files = new File("/sdcard/screens/"+str+".png");
     //   ud.takeScreenshot(files);
        ud.executeShellCommand("screencap -p "+files);
    }


   // instrument = InstrumentationRegistry.getInstrumentation();
   // ud = UiDevice.getInstance(instrument);
/*
   @Before
   public void grantPermission() throws IOException {
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           ud.executeShellCommand("pm grant " + getTargetContext().getPackageName() + " android.permission.WRITE_EXTERNAL_STORAGE");
       }

   }
*/
    //获取当前时间
    public String  getDate(){
        Date a = new Date();
        SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String c = b.format(a);
        return  c;
    }
    //根据时间拍照
    public void takeScreen(String getName) throws IOException {
        File screens =new File("/mnt/sdcard/screens");
//判断是否存在且指定创建的是否是文件夹
        /*
        if (!screens.exists() && !screens.isDirectory())
        {
            System.out.println("Create new folder:/mnt/sdcard/screens");
            screens.mkdir();
        } else
{
System.out.println("Already have folder:/mnt/sdcard/screens/");
} */
/*
        File files = new File("/mnt/sdcard/screens"+getDate()+"_"+getName+".png");
        String comms="adb shell screencap -p"+getDate()+getName+".png";
        String commspull="adb pull" +getDate()+getName+".png" +"E://downpic";

        //log.i("**********Create Screen:"+getDate()+getName+"**********");
        ud.executeShellCommand(comms);
        ud.executeShellCommand(commspull);
      //  ud.takeScreenshot(files);
      */
    }

}
