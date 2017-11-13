package test.myzs.com.ui2test;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Administrator on 2017/11/8.
 */

public class AllClass {
    public Instrumentation instrument = InstrumentationRegistry.getInstrumentation();
    public UiDevice ud = UiDevice.getInstance(instrument);
    PicScreen pic=new PicScreen();


//进入新的订单列表
    public void newOrderList() throws Exception {

        UiObject2 unewlist = ud.findObject(By.res("com.itsenpupulai.courierport:id/refresh"));

        if (unewlist != null) {
            unewlist.clickAndWait(Until.newWindow(), 10000);
            Thread.sleep(10000);
            Log.i("v", "testcase002button");

        } else {
            Log.i("v", "testcase002end");
            ud.click(827, 1745);

            Thread.sleep(10000);
        }


    }
//向下滑动方法
    public void scrollList(String res) throws UiObjectNotFoundException, InterruptedException {
        UiScrollable list = new UiScrollable(new UiSelector().resourceId(res));


        //判断是否可滑动
        boolean uhuadong = list.isScrollable();
        UiObject2 ufresh = ud.findObject(By.res("com.itsenpupulai.courierport:id/pull_to_refresh_text"));

        UiObject ulist = list.getChild(new UiSelector().index(2));
        int y = ud.getDisplayHeight();
        int x = ud.getDisplayWidth();
        if(list!=null) {
            //向下滑动
            for (int i = 0; i < 5; i++) {

     /*
       while (ufresh==null&&uhuadong==false) {
         int count1 = list.getChildCount();
               ud.drag(x / 2, y / 2, x / 2, 10, 10);

      }
*/
            //    list.flingToEnd(3);
          //      Thread.sleep(3000);
                ulist.dragTo(1625, 353, 10);
                Thread.sleep(3000);
                ud.drag(x / 2, y / 2, x/2,100, 10);
                Thread.sleep(3000);


              //  ufresh.fling();
           //     ufresh.fling(Direction.DOWN);

            }
        }

    }
    //拨打电话
    public void telphone( UiObject2 uib)throws Exception{

        Thread.sleep(10000);
        uib.click();
        // ud.click(555,1208);

        //判断是否是取货免打扰
        UiObject2 umiandarao=ud.findObject(By.text("com.itsenpupulai.courierport:id/tv_yes"));

        Thread.sleep(10000);
        //如果不是免打扰
        if(umiandarao==null) {
            //确认拨打电话
            UiObject2 ubuttonsure = ud.findObject(By.res("com.itsenpupulai.courierport:id/tv_yes").text("立即拨打"));
            // ubuttonsure.click();
            ud.click(555, 1208);
            Thread.sleep(10000);
            //无sim卡，点击我知道了按钮
            // UiObject2 uknowbutton=ud.findObject(By.res("android:id/button1").text("知道了"));
            //  uknowbutton.click();
            ud.click(498, 1051);
            Thread.sleep(10000);
        }
        //如果是面打扰
        else{

            umiandarao.click();
            Thread.sleep(10000);

        }

    }
    //从图库选择照片
    public void selectpiclist()throws Exception{
        UiObject2 upiclist = ud.findObject(By.res("com.itsenpupulai.courierport:id/dialog_get_photo_tv_local"));
       // upiclist.click();
        ud.click(504,1057);
        Thread.sleep(10000);
        ud.click(145, 486);
        Thread.sleep(5000);
        ud.click(145,486);
        Thread.sleep(5000);
        ud.click(458,960);
        Thread.sleep(5000);
        //点击确认按钮
        //华为p8
        ud.click(1004,68);
        Thread.sleep(5000);


    }
    //用相机拍照
    public  void camerepic()throws Exception{
        UiObject2 ucamer=ud.findObject(By.res("com.itsenpupulai.courierport:id/dialog_get_photo_tv_camera"));
        ud.click(478,906);
       // ucamer.click();
        //进入相机内拍照
        //  UiObject2 camerbutton=ud.findObject(By.res("com.oppo.camera:id/shutter_button"));
        //  camerbutton.clickAndWait(Until.newWindow(),10000);
        Thread.sleep(5000);
        ud.click(543,1710);
        Thread.sleep(3000);
        ud.click(988,1751);
        Thread.sleep(5000);
        //点击确认按钮
        //华为p8
        ud.click(986,74);
        Thread.sleep(5000);

    }
    //接单，从刷新订单列表和订单详情页面接单
    public void jiedan(UiObject2 uijie) throws InterruptedException {
         if(uijie!=null){
             uijie.click();
             Thread.sleep(5000);
             UiObject2 uyuyue=ud.findObject(By.res("com.itsenpupulai.courierport:id/title").text("预约单"));
             if(uyuyue==null) {
                 ud.click(725, 1066);
                 Thread.sleep(5000);
             }
             else{
                 ud.click(727, 1178);
                 Thread.sleep(5000);
             }

         }

    }
    //打开侧滑栏
    public void cehual() throws InterruptedException {
        Thread.sleep(10000);
        UiObject2 uperson=ud.findObject(By.res("com.itsenpupulai.courierport:id/top_persenal_center"));
        uperson.click();
        Thread.sleep(10000);

    }
//查看订单活动详情页面且关闭
    public void huodongdetail() throws InterruptedException {

        UiObject2 xiangqing = ud.findObject(By.res("com.itsenpupulai.courierport:id/order_mission_item_tv_index").text("查看详情 >"));
        if (xiangqing != null) {
            Thread.sleep(3000);
            xiangqing.click();
            Thread.sleep(3000);
            //关闭订单详情页面
            ud.click(504, 1327);
            Thread.sleep(3000);


        }
    }
        //查看历史明细中的各个模块->滑动列表
    public void huodonglist( UiObject2 list,String casenames) throws InterruptedException, IOException, UiObjectNotFoundException {

        //暂无明细是否显示
        UiObject2 unolist=ud.findObject(By.text("暂无明细哟~"));

      //点击未到账、已到账....按钮
        list.click();
        Thread.sleep(3000);
        pic.screenshot(casenames);
        //暂无明细不存在
        if(unolist!=null) {
            //滑动列表
            String historylist = "android:id/list";
            scrollList(historylist);
            Thread.sleep(3000);
        }
        Thread.sleep(3000);

    }
    //查看侧滑栏蚂蚁课堂中各个web页面->返回页面
    public void ketang(String str) throws InterruptedException, IOException {
        Thread.sleep(3000);
        pic.screenshot(str);
        Thread.sleep(10000);
        //返回
        ud.pressBack();
        Thread.sleep(3000);
    }
    //  //输入身份证、原始密码、手机号、获取短信验证码、短信验证码
    public void setings(UiObject2 uio,String str,String str2) throws InterruptedException, IOException {
//点击文本框并输入内容
        uio.click();
        Thread.sleep(3000);
        uio.setText(str);
        Thread.sleep(3000);
        //拍照
        pic.screenshot(str2);
        Thread.sleep(3000);
        ud.pressBack();
        Thread.sleep(3000);


    }
    //输入新手机号,更换手机号
      /*
  1.点击账号
2.输入身份证号412823…
3.输入原始密码111111
4.输入新手机号18622222222
5.点击发送验证码
6.输入1546

     */
    public void newphoneupdate(String newphonenum) throws InterruptedException, IOException {

//点击账号
        UiObject2 uphone = ud.findObject(By.res("com.itsenpupulai.courierport:id/tv_phone_number"));
        uphone.click();
        Thread.sleep(3000);
        pic.screenshot("testcase0031updatephone");
        Thread.sleep(3000);
        //输入身份证、原始密码、手机号、获取短信验证码、短信验证码
        UiObject2 cardnum = ud.findObject(By.res("com.itsenpupulai.courierport:id/revise_username_et_idnum_et"));
        setings(cardnum, "412823198901152507", "testcase0031shenfenhao");
        //
        UiObject2 password = ud.findObject(By.res("com.itsenpupulai.courierport:id/revise_username_pswd_et"));
       setings(password, "111111", "testcase0031mima");
        //
        UiObject2 newphone = ud.findObject(By.res("com.itsenpupulai.courierport:id/revise_username_newphone_num_et"));
        setings(newphone, newphonenum, "testcase0031newphone");
        //发送验证码

        UiObject2 yanzheng = ud.findObject(By.res("com.itsenpupulai.courierport:id/revise_username_code_btn").text("发送验证码"));
        yanzheng.click();
        Thread.sleep(3000);
        //
        UiObject2 yanzhengma = ud.findObject(By.res("com.itsenpupulai.courierport:id/revise_username_code_et"));
        setings(yanzhengma, "1546", "testcase0031yanzhengma");
        //提交确定信息

        UiObject2 suerbutton = ud.findObject(By.res("com.itsenpupulai.courierport:id/btn_finish"));
        suerbutton.click();
        Thread.sleep(3000);

    }

    //修改密码
    /*
  1.点击修改密码
2.输入旧密码
3.输入新密码
4.提交信息

     */

public void newpwdfun(String oldpwd,String newpwd) throws InterruptedException, IOException {
    //进入修改密码页面
    UiObject2 updatemima=ud.findObject(By.res("com.itsenpupulai.couierport:id/revise_pwd_rl"));
   // updatemima.click();
    ud.click(368,417);
    Thread.sleep(3000);
    pic.screenshot("testcase0032updatepdpages");
    Thread.sleep(3000);
    //输入旧密码和新密码
    UiObject2 oldpwd2 = ud.findObject(By.res("com.itsenpupulai.courierport:id/origin_pwd"));
    setings(oldpwd2, oldpwd, "testcase0032oldpwd");
    Thread.sleep(3000);
    UiObject2 newpwd2 = ud.findObject(By.res("com.itsenpupulai.courierport:id/new_pwd"));
    setings(newpwd2, newpwd, "testcase0032newspwd");
    Thread.sleep(3000);
    //提交信息

    UiObject2 suerbutton = ud.findObject(By.res("com.itsenpupulai.courierport:id/btn_finish"));
    suerbutton.click();
    Thread.sleep(3000);


}































}
