package test.myzs.com.ui2test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiAutomatorInstrumentationTestRunner;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.view.KeyEvent.KEYCODE_NUMPAD_4;
import static android.view.KeyEvent.KEYCODE_NUMPAD_5;
import static android.view.KeyEvent.KEYCODE_NUMPAD_6;
import static android.view.KeyEvent.KEYCODE_NUMPAD_8;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest extends UiAutomatorInstrumentationTestRunner {
    public Instrumentation instrument;
    public UiDevice ud;
    PicScreen pic=new PicScreen();
/*
    @Before
    public void grantPermission() throws IOException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ud.executeShellCommand("pm grant com.itsenpupulai.courierport android.permission.WRITE_EXTERNAL_STORAGE");
        }

    }
*/
    @Before
    //reset
    public void resetapp() {
        //定义instrument
        instrument = InstrumentationRegistry.getInstrumentation();
        ud = UiDevice.getInstance(instrument);
        // Context of the app under test.
        //reset app

        for (int i = 0; i < 5; i++) {
            ud.pressHome();
            ud.pressBack();
            System.out.println("i" + i);

        }

    }

    @Test
    public void testCase001() throws Exception {
        //reset app

        for (int i = 0; i < 5; i++) {
            ud.pressHome();
            ud.pressBack();
            System.out.println("i" + i);
        }


        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.itsenpupulai.courierport");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//starts the app
        context.startActivity(intent);


        Log.i("v","testcase001");
    //    pic.takeScreen("testCase001");
        pic.screenshot("testcase001");
        Thread.sleep(10000);

    }
//启动app


    // assertEquals(true,true);



   // @Test
    public void testCase002() throws Exception{
        Log.i("v","testcase002begin");
        UiObject2 unewlist = ud.findObject(By.res("com.itsenpupulai.courierport:id/refresh"));
        if (unewlist != null) {
            unewlist.clickAndWait(Until.newWindow(),10000);
            Thread.sleep(10000);
            Log.i("v","testcase002button");

        }
        else
        {
            Log.i("v","testcase002end");
            ud.click(670,1841);
            Thread.sleep(10000);
        }


    }
   // @Test
    //accetpt
    public void testCase003()throws Exception {
        Log.i("v","testcase003begin");

        UiObject2 ujedan = ud.findObject(By.res("com.itsenpupulai.courierport:id/takeorder"));



        // UiObject ujiebutton2=new UiObject(new UiSelector().resourceId("ccom.itsenpupulai.courierport:id/tv_yes").index(2));
        if (ujedan != null) {
            ujedan.click();

            Thread.sleep(10000);
//点击接单按钮(通过坐标元素位置)
            ud.click(717,1130);
            Log.i("v","testcase003end");
        }

    }
    //刷新我的订单
 //   @Test
    public void testCase004() throws Exception{
        Log.i("v","testcase004begin");
       Thread.sleep(10000);
       UiObject2 umylist= ud.findObject(By.res("com.itsenpupulai.courierport:id/my_task_rl"));
    //   umylist.clickAndWait(Until.newWindow(),10000);
        ud.click(370,1838);
        Log.i("v","testcase004end");
       Thread.sleep(10000);

    }
    //拨打电话
    public void telphone( UiObject2 uib)throws Exception{

         uib.click();
        // ud.click(555,1208);

        Thread.sleep(10000);
        //确认拨打电话
        UiObject2 ubuttonsure=ud.findObject(By.res("com.itsenpupulai.courierport:id/tv_yes").text("立即拨打"));
        // ubuttonsure.click();
        ud.click(555,1208);
        Thread.sleep(10000);
        //无sim卡，点击我知道了按钮
        UiObject2 uknowbutton=ud.findObject(By.res("android:id/button1").text("知道了"));
        uknowbutton.click();
        Thread.sleep(10000);


    }
    //从图库选择照片
    public void selectpiclist()throws Exception{
        UiObject2 upiclist = ud.findObject(By.res("com.itsenpupulai.courierport:id/dialog_get_photo_tv_local"));
        upiclist.click();
        Thread.sleep(10000);
        ud.click(145, 486);
        Thread.sleep(5000);
        ud.click(145,486);
        Thread.sleep(5000);
    }
    //用相机拍照
    public  void camerepic()throws Exception{
     UiObject2 ucamer=ud.findObject(By.res("com.itsenpupulai.courierport:id/dialog_get_photo_tv_camera"));
        ucamer.clickAndWait(Until.newWindow(),10000);
        //进入相机内拍照
      //  UiObject2 camerbutton=ud.findObject(By.res("com.oppo.camera:id/shutter_button"));
      //  camerbutton.clickAndWait(Until.newWindow(),10000);
        Thread.sleep(5000);
        ud.click(543,1710);
        Thread.sleep(10000);
        ud.click(988,1751);
        Thread.sleep(10000);

    }

//完成订单
 //  @Test
    public void testCase005()throws Exception{
       Log.i("v","testcase005begin");
//进入我的订单
        UiObject2 uenter=ud.findObject(By.res("com.itsenpupulai.courierport:id/initorder"));

   //     uenter.clickAndWait(Until.newWindow(),10000);
     //   ud.click(231,1846);
        Thread.sleep(10000);
       //拨打发货人电话
       UiObject2 ufaphone=ud.findObject(By.res("com.itsenpupulai.courierport:id/phone_start").text("联系发货人"));
       telphone( ufaphone);


       //标记到达取货地
       UiObject2 ubiaoji=ud.findObject(By.res("com.itsenpupulai.courierport:id/tv_biaojiquhuo"));
       ubiaoji.click();
       Thread.sleep(10000);
       ud.click(509,1135);
       Thread.sleep(10000);
       //拍照
       UiObject2 upic=ud.findObject(By.res("com.itsenpupulai.courierport:id/takepic"));
       if(upic.isClickable()) {
           upic.click();
           Thread.sleep(10000);
           //从图库选择
          // selectpiclist();
           //相机拍照
           camerepic();

       }
       else
       {
           Log.i("end pic","pic finished!");
       }
       //联系收货人
       UiObject2 ushouphone=ud.findObject(By.res("com.itsenpupulai.courierport:id/phone_end").text("联系收货人"));
       telphone(ushouphone);

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
    //侧滑栏设置->打开侧滑栏
  //  @Test
    public void testCase006()throws Exception{
        Thread.sleep(10000);
        UiObject2 uperson=ud.findObject(By.res("com.itsenpupulai.courierport:id/top_persenal_center"));
        uperson.click();
        Thread.sleep(10000);
       // uperson.clickAndWait(Until.newWindow(),10000);
     //   pic.takeScreen("testCase006");

    }
    //进入我的钱包
  //  @Test
    public void testCase007()throws Exception {
        Thread.sleep(5000);
        UiObject2 myqian = ud.findObject(By.res("com.itsenpupulai.courierport:id/mywallet_ll"));
        myqian.click();
        Thread.sleep(10000);
    }
    //查看保证金
   // @Test
    public void testCase008()throws Exception {

        //查看保证金
        UiObject2 ubao = ud.findObject(By.res("com.itsenpupulai.courierport:id/my_money_rl_ensure_money"));
        ubao.clickAndWait(Until.newWindow(), 10000);
        Thread.sleep(5000);
        ud.pressBack();
        Thread.sleep(5000);
    }
    //查看账号明细
  //  @Test
    public void testCase009()throws Exception {
        //进入账户明细
        UiObject2 udetail = ud.findObject(By.res("com.itsenpupulai.courierport:id/money_change_details"));
        udetail.clickAndWait(Until.newWindow(), 10000);
        Thread.sleep(10000);
        //先查看收入列表
        UiObject2 ushouru = ud.findObject(By.res("com.itsenpupulai.courierport:id/transaction_tv_income_text").text("收入"));
        // ushouru.clickAndWait(Until.newWindow(),10000);
        ushouru.click();
        Thread.sleep(5000);
        //查看支出列表
        UiObject2 uzhichu = ud.findObject(By.res("com.itsenpupulai.courierport:id/transaction_tv_pay_text").text("支出"));
        uzhichu.click();
        Thread.sleep(10000);
        //查看全部列表
        UiObject2 uquanbu = ud.findObject(By.res("com.itsenpupulai.courierport:id/transaction_tv_all_text").text("全部"));
        uquanbu.click();
        Thread.sleep(10000);
        ud.pressBack();
        Thread.sleep(10000);
    }
        //返回到我的钱包
        //申请提现记录页面
     //   @Test
        public void testCase0010()throws Exception {
        UiObject2 ujilu=ud.findObject(By.res("com.itsenpupulai.courierport:id/transaction_details"));
        ujilu.clickAndWait(Until.newWindow(),100000);
        Thread.sleep(5000);
        //进入提现记录
        //查看已完成列表
        UiObject2 ufini=ud.findObject(By.res("com.itsenpupulai.courierport:id/record_tv_income_text").text("已完成"));
        ufini.click();
        Thread.sleep(10000);
        //查看提现中列表
        UiObject2 udoingti=ud.findObject(By.res("com.itsenpupulai.courierport:id/recor_apply_tv_text").text("提现中"));
        udoingti.click();
        Thread.sleep(10000);
        //回到我的钱包页面
        ud.pressBack();
        Thread.sleep(10000);




    }

}