package test.myzs.com.ui2test;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.util.Log;

import static android.view.KeyEvent.KEYCODE_NUMPAD_4;
import static android.view.KeyEvent.KEYCODE_NUMPAD_5;
import static android.view.KeyEvent.KEYCODE_NUMPAD_6;
import static android.view.KeyEvent.KEYCODE_NUMPAD_8;

/**
 * Created by Administrator on 2017/11/7.
 */

public class liuCheng {
    public Instrumentation instrument= InstrumentationRegistry.getInstrumentation();
    public UiDevice ud=UiDevice.getInstance(instrument);
    AllClass ac=new AllClass();
//从图库上传图片，完成订单
    /*
1.判断是否在查询订单页面
2.根据配送流程一步步完成
3.拨打电话&标记到达收货地
4.从图库上传图片
5.输入取货码
6.完成订单
*/
    public void testCase004road(boolean str)throws Exception{
        Log.i("v","testcase004begin");
//进入我的订单
        UiObject2 uenter=ud.findObject(By.res("com.itsenpupulai.courierport:id/initorder"));

        //     uenter.clickAndWait(Until.newWindow(),10000);
        //   ud.click(231,1846);
        Thread.sleep(5000);
        //拨打发货人电话
        UiObject2 ufaphone=ud.findObject(By.res("com.itsenpupulai.courierport:id/phone_start").text("联系发货人"));
        Thread.sleep(5000);
        ac.telphone( ufaphone);


        //标记到达取货地
        UiObject2 ubiaoji=ud.findObject(By.res("com.itsenpupulai.courierport:id/tv_biaojiquhuo"));
        ubiaoji.click();
        Thread.sleep(5000);
        ud.click(509,1135);
        Thread.sleep(3000);
        //拍照
        UiObject2 upic=ud.findObject(By.res("com.itsenpupulai.courierport:id/takepic"));
        if(upic.isClickable()) {
            upic.click();
            Thread.sleep(3000);
            if(str) {
                //从图库选择
                ac.selectpiclist();
            }
            else {
                //相机拍照
                  ac.camerepic();
            }
        }
        else
        {
            Log.i("end pic","pic finished!");
        }
        //联系收货人
        UiObject2 ushouphone=ud.findObject(By.res("com.itsenpupulai.courierport:id/phone_end").text("联系收货人"));
       ac.telphone(ushouphone);

        //标记到达收货地
        Thread.sleep(10000);
        UiObject2 ushouhuodi=ud.findObject(By.res("com.itsenpupulai.courierport:id/tv_biaojishouhuo").text("标记到达"));
        //未标记到达收货地时进行标记
        if(ushouhuodi!=null) {
            ushouhuodi.click();
            UiObject2 knowbutton=ud.findObject(By.res("com.itsenpupulai.courierport:id/tv_yes"));
            ud.click(519,1110);
            //  knowbutton.clickAndWait(Until.newWindow(),10000);
            Thread.sleep(10000);
        }
        //输入取货码
        UiObject2 uquhuobutton=ud.findObject(By.res("com.itsenpupulai.courierport:id/tv_getquhuoma").text("输入取货码"));
        if(uquhuobutton !=null) {
            uquhuobutton.click();
            Thread.sleep(10000);
//输入取货码
            UiObject2 uedit1=ud.findObject(By.res("com.itsenpupulai.courierport:id/quhuoma_tv1"));
            uedit1.click();
            Thread.sleep(5000);
            ud.pressKeyCode(KEYCODE_NUMPAD_4) ;


            Thread.sleep(1000);

            UiObject2 uedit2=ud.findObject(By.res("com.itsenpupulai.courierport:id/quhuoma_tv2"));

            uedit2.click();
            Thread.sleep(5000);
            ud.pressKeyCode(KEYCODE_NUMPAD_6);
            UiObject2 uedit3=ud.findObject(By.res("com.itsenpupulai.courierport:id/quhuoma_tv3"));
            Thread.sleep(5000);
            uedit3.click();
            Thread.sleep(1000);
            ud.pressKeyCode(KEYCODE_NUMPAD_5);
            UiObject2 uedit4=ud.findObject(By.res("com.itsenpupulai.courierport:id/quhuoma_tv4"));
            Thread.sleep(5000);
            uedit4.click();
            Thread.sleep(1000);
            ud.pressKeyCode(KEYCODE_NUMPAD_8);
            Thread.sleep(10000);
            //关闭评价框

            UiObject2 uncomment=ud.findObject(By.res("com.itsenpupulai.courierport:id/comment_no").text("稍后评价"));
            Log.i("v","testcase005end");
            uncomment.clickAndWait(Until.newWindow(),10000);


        }
    }
}
