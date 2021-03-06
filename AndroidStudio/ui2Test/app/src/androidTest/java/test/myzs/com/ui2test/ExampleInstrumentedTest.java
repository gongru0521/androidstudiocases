package test.myzs.com.ui2test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiAutomatorInstrumentationTestRunner;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiWatcher;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@FixMethodOrder(value =MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest extends UiAutomatorInstrumentationTestRunner {


    public Instrumentation instrument= InstrumentationRegistry.getInstrumentation();
    public UiDevice ud= UiDevice.getInstance(instrument);
    AllClass ac=new AllClass();
    PicScreen pic=new PicScreen();
 //   instrument = InstrumentationRegistry.getInstrumentation();
   // ud = UiDevice.getInstance(instrument);

    @Before
    //reset
    public void resetapp() {
        //定义instrument
       // instrument = InstrumentationRegistry.getInstrumentation();
      //  ud = UiDevice.getInstance(instrument);


        //  @Override
        //注册监听
        ud.registerWatcher("tuidan", new UiWatcher() {
                    @Override
                    public boolean checkForCondition() {
                        //推单
                        UiObject2 utankuang = ud.findObject(By.res("com.itsenpupulai.courierport:id/push_accept_ll"));
                        //    UiObject call = new UiObject(new UiSelector().resourceId("com.android.incallui:id/unlock_answer_text").text("接听"));//由接听按钮判断为来电
                        //    UiObject call_reject = new UiObject(new UiSelector().resourceId("com.android.incallui:id/unlock_reject"));//挂断按钮
                        if (utankuang != null) {
                            System.out.println("电话监听器被触发啦！！！！");

                            utankuang.click();

                        }
                        return true;
                    }
                }
        );

    }
    @Test
    //启动app
    /*
    1.启动app
    2.判断是否进入系统首页
     */
    public void testcase001() throws Exception {
        //reset app

        for (int i = 0; i < 5; i++) {
            ud.pressHome();
            ud.pressBack();
            System.out.println("i" + i);
        }

//启动app
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.itsenpupulai.courierport");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//starts the app
        context.startActivity(intent);

        Thread.sleep(10000);
        Log.i("v","testcase001");

        pic.screenshot("testcase001");
        Thread.sleep(10000);

        UiObject2 utop=ud.findObject(By.res("com.itsenpupulai.courierport:id/top_center"));
        String text=utop.getText();
        assertEquals("全部订单",text);

    }

    // assertEquals(true,true);


//点击新的订单列表
    /*
    1.点击订单列表
    2.上下滑动列表，滑动到系统底部
     */
   @Test
    public void testcase002() throws Exception{
        Log.i("v","tescase002begin");
       //进入新的订单
       ac.newOrderList();
       Thread.sleep(1000);
       pic.screenshot("testcase002");
       //断言判断
     //  UiObject2 ujiedan=ud.findObject(By.res("com.itsenpupulai.courierport:id/takeorder"));
      //  String text2=ujiedan.getText();
    //   Assert.assertEquals("接单",text2);
       //滑动新的订单列表
       String scrolllist="android:id/list";
       ac.scrollList(scrolllist);
       //拍照
       pic.screenshot("testcase0020001");


       Log.i("v","testcase002button");


    }
    @Test
    //accetpt
    /*
    1.点击接单按钮
    2.判断是不是首单、是不是预约单，接单
     */
    //新的订单列表接单
    public void testcase003()throws Exception {
        Log.i("v","testcase003begin");

        UiObject2 ujedan = ud.findObject(By.res("com.itsenpupulai.courierport:id/takeorder"));
         ac.jiedan(ujedan);
            Log.i("v","testcase003end");
            //
        //加断言，显示查询则接单成功
        /*
      UiObject2 uchaxun=ud.findObject(By.text("查询"));
       String uchaxunstr=uchaxun.getText();
        Assert.assertEquals("查询",uchaxunstr);
*/
       //     pic.screenshot("testcase003");
            //

      //  }

    }
    //刷新我的订单
 //  @Test
    public void testcase005555() throws Exception{
        Log.i("v","testcase004begin");
       Thread.sleep(10000);
       UiObject2 umylist= ud.findObject(By.res("com.itsenpupulai.courierport:id/my_task_rl"));
    //   umylist.clickAndWait(Until.newWindow(),10000);
        ud.click(370,1838);
        Log.i("v","testcase004end");
       Thread.sleep(10000);
        pic.screenshot("testcase004");

    }

//完成订单
    //从图库上传图片，完成订单
    /*
1.判断是否在查询订单页面
2.根据配送流程一步步完成
3.拨打电话&标记到达收货地
4.从图库上传图片
5.输入取货码
6.完成订单
7.如果是“收取货款订单”，点击返回到系统首页
     */
  @Test
   public void testcase004()throws Exception {
       Log.i("v", "testcase004begin");
      UiObject2 ushouqu=ud.findObject(By.res("com.itsenpupulai.courierport:id/payment_tv"));
      if(ushouqu==null) {
          liuCheng liucheng = new liuCheng();
          //从图库选择相片
          liucheng.testCase004road(true);
      }
      else {
          Thread.sleep(10000);
          pic.screenshot("testcese003back");
          ud.pressBack();
      }
   }

    //从新的订单列表点击进入订单详情
    /*
    1.进入“新的订单页面”
   2.点击订单，进入订单详情页面
     */
    @Test
    public void testcase0051()throws Exception{

      //先进入我的新订单列表
     ac.newOrderList();
        Thread.sleep(10000);
        //进入订单详情页面
         ud.click(405,507);
     //   UiObject2 ujie=ud.findObject(By.res("com.itsenpupulai.courierport:id/submit").text("接单"));
     //   ac.jiedan(ujie);
        pic.screenshot("testcase005!!!");
       Thread.sleep(10000);
        //
    }
    //从订单详情页面点击接单
    /*
    1.点击接单按钮
    2.判断是不是首单、是不是预约单，接单
     */
    @Test
    public void testcase006()throws Exception{

        UiObject2 ujie=ud.findObject(By.res("com.itsenpupulai.courierport:id/submit").text("接单"));
        ac.jiedan(ujie);
        pic.screenshot("testcase0052!!!");
        Thread.sleep(1000);
        //
    }

    //相机拍照上传图片，完成订单，完成配送流程
    /*
    1.判断是否在查询订单页面
2.根据配送流程一步步完成
3.拨打电话&标记到达收货地
4.从相机拍照上传图片
5.输入取货码
6.完成订单
     */
    @Test
    public void testcase007()throws Exception {
        Thread.sleep(5000);
        Log.i("v", "testcase0053begin");
        UiObject2 ushouqu=ud.findObject(By.res("com.itsenpupulai.courierport:id/payment_tv"));
        if(ushouqu==null) {
            liuCheng liucheng = new liuCheng();
            //从相机拍照
            liucheng.testCase004road(false);
            Thread.sleep(5000);
            pic.screenshot("testcase0053");
        }
        else {
            Thread.sleep(10000);
            pic.screenshot("testcese005back");
            ud.pressBack();
        }
    }

    //侧滑栏设置->打开侧滑栏
   @Test
     public void testcase008()throws Exception{
        //打开侧栏
        ac.cehual();
       // uperson.clickAndWait(Until.newWindow(),10000);
        pic.screenshot("testcase008");
         Thread.sleep(5000);

    }
    //进入我的钱包
 @Test
    public void testcase00900()throws Exception {
        Thread.sleep(5000);
        UiObject2 myqian = ud.findObject(By.res("com.itsenpupulai.courierport:id/mywallet_ll"));

        myqian.click();
        Thread.sleep(5000);
        pic.screenshot("testcase009");
        Thread.sleep(5000);
    }
    //查看保证金
  @Test
    public void testcase00910()throws Exception {
        Thread.sleep(5000);
        //查看保证金
        UiObject2 ubao = ud.findObject(By.res("com.itsenpupulai.courierport:id/my_money_rl_ensure_money"));
      //  ubao.click();
        ud.click(445,775);
        Thread.sleep(5000);
        pic.screenshot("testcase0010");
        ud.pressBack();
        Thread.sleep(5000);
    }
    //进入账户明细页面，且查看各个模块信息s
    /*
    1.点击“账户明细”
2.滑动全部列表
3.点击收入tab
4.滑动收入列表
5.点击支出tab
6.滑动支出列表
7.点击页面返回按钮
     */
    @Test
    public void testcase00911()throws Exception {
        //进入账户明细
        UiObject2 udetail = ud.findObject(By.res("com.itsenpupulai.courierport:id/money_change_details"));
        udetail.clickAndWait(Until.newWindow(), 10000);
        Thread.sleep(5000);
        pic.screenshot("testcase0011");
        Thread.sleep(5000);

        //先查看收入列表
        UiObject2 ushouru = ud.findObject(By.res("com.itsenpupulai.courierport:id/transaction_tv_income_text").text("收入"));
        // ushouru.clickAndWait(Until.newWindow(),10000);
        ushouru.click();
        Thread.sleep(5000);
        //滑动列表
        String resmoneylist="android:id/list";
        ac.scrollList(resmoneylist);
        Thread.sleep(5000);
        pic.screenshot("testcase0011");
        //查看支出列表
        UiObject2 uzhichu = ud.findObject(By.res("com.itsenpupulai.courierport:id/transaction_tv_pay_text").text("支出"));
        uzhichu.click();
        Thread.sleep(10000);
        String zhichuist="android:id/list";
        ac.scrollList( zhichuist);
        Thread.sleep(5000);
        pic.screenshot("testcase00111");
        //查看全部列表
        UiObject2 uquanbu = ud.findObject(By.res("com.itsenpupulai.courierport:id/transaction_tv_all_text").text("全部"));
        uquanbu.click();
        Thread.sleep(10000);
        String allsmoneylist="android:id/list";
        ac.scrollList(allsmoneylist);
        Thread.sleep(5000);
        pic.screenshot("testcase001111");
        ud.pressBack();
        Thread.sleep(10000);
    }
        //返回到我的钱包
        //申请提现记录页面
    /*
    1.点击“提现记录”
2.滑动全部列表
3.点击“提现中”
4.滑动列表
5.点击“已完成”
6.滑动列表
7.点击页面返回按钮
     */
       @Test
        public void testcase00912()throws Exception {
        UiObject2 ujilu=ud.findObject(By.res("com.itsenpupulai.courierport:id/transaction_details"));
        ujilu.clickAndWait(Until.newWindow(),100000);
        Thread.sleep(5000);

        //进入提现记录
        //查看已完成列表
        UiObject2 ufini=ud.findObject(By.res("com.itsenpupulai.courierport:id/record_tv_income_text").text("已完成"));
        ufini.click();
            Thread.sleep(5000);
            //滑动已完成列表
            String yiwancheng="android:id/list";
            ac.scrollList(yiwancheng);
            Thread.sleep(5000);
            pic.screenshot("testcase0022111");
        Thread.sleep(10000);
        //查看提现中列表
        UiObject2 udoingti=ud.findObject(By.res("com.itsenpupulai.courierport:id/recor_apply_tv_text").text("提现中"));
        udoingti.click();
            //滑动提现中列表
            String tixianzhong="android:id/list";
            ac.scrollList(tixianzhong);
            Thread.sleep(5000);
            pic.screenshot("testcase0022111");
        Thread.sleep(10000);
        //回到我的钱包页面
        ud.pressBack();
        Thread.sleep(5000);




    }

    //进入申请提现页面
    /*
    1.点击“提现”按钮
2.点击修改账号按钮
     */
    @Test
    public void testcase00913() throws InterruptedException, IOException {
       UiObject2 tixianbutton=ud.findObject(By.res("com.itsenpupulai.courierport:id/get_cash"));
        tixianbutton.click();
        Thread.sleep(5000);
        pic.screenshot("testcase0013");
        //判断确认提现按钮是否存在
        UiObject2 usurebutton=ud.findObject(By.res("com.itsenpupulai.courierport:id/submit"));
        String surebuttonname=usurebutton.getText();
        Assert.assertEquals("确认提现",surebuttonname);
    }
    //进入提现页面->添加支付宝
    /*
    0.点击修改账号按钮
1.弹框上选择“添加支付宝”
2.点击确定
3.添加支付宝页面填写支付宝账号
4.获取短信验证码:1546
5.输入验证码
6.点击确定
     */
   @Test

    public void testcase00914() throws InterruptedException, IOException {
        //点击修改账号
        Thread.sleep(3000);
        UiObject2 updatezhang=ud.findObject(By.res("com.itsenpupulai.courierport:id/modify_account").text("修改账户"));
        updatezhang.click();
        Thread.sleep(3000);
        //选择支付宝
        ud.click(847,808);
        Thread.sleep(3000);
        //点击确定按钮
        ud.click(746,1113);
        Thread.sleep(3000);
        //添加付宝页面输入信息
        UiObject2 uedit=ud.findObject(By.res("com.itsenpupulai.courierport:id/account"));
        uedit.click();
        Thread.sleep(3000);
        uedit.setText("18638732236");
         Thread.sleep(10000);
        //获取验证码
        UiObject2 ucodebutton=ud.findObject(By.res("com.itsenpupulai.courierport:id/btn_getsmscode"));
        ucodebutton.click();
        Thread.sleep(10000);
        //输入验证码
        UiObject2 editcode=ud.findObject(By.res("com.itsenpupulai.courierport:id/smscode"));
        editcode.click();
        Thread.sleep(3000);
        editcode.setText("1546");
        Thread.sleep(3000);
        pic.screenshot("testcase0014");
        //点击确定按钮

        UiObject2 usurebutton2=ud.findObject(By.res("com.itsenpupulai.courierport:id/btn_sure"));
        usurebutton2.click();
        Thread.sleep(3000);





    }
    //0015
    //输入金额支付宝提现
    /*
    1.申请体现页面输入金额
2.点击“确认提现”按钮
3.判断是否停留在这个页面上，如果不是点击申请提现页面
     */

   @Test

    public void testcase00915() throws InterruptedException, IOException {
        //输入金额
        UiObject2 umoney=ud.findObject(By.res("com.itsenpupulai.courierport:id/money"));
        umoney.click();
        Thread.sleep(3000);
        umoney.setText("100");
        Thread.sleep(3000);
        ud.pressBack();
        Thread.sleep(3000);
        //点击确认提现按钮
        UiObject2 tixianbutton=ud.findObject(By.res("com.itsenpupulai.courierport:id/submit").text("确认提现"));
        pic.screenshot("testcase0015");
        tixianbutton.click();
        Thread.sleep(5000);
        UiObject2 tixianbuttonn=ud.findObject(By.res("com.itsenpupulai.courierport:id/get_cash"));

        if(tixianbuttonn!=null) {
            tixianbuttonn.click();
            Thread.sleep(5000);
            pic.screenshot("testcase0015111");
        }


    }

//testcase0016
    //进入提现页面->添加银行卡
    /*
    0.点击修改账号按钮
1.弹框上选择“添加银行卡”
2.点击确定
3.添加页面填写银行卡账号，6225880142383547
4.获取短信验证码
5.输入验证码
6.点击确定
     */
@Test

public void testcase00916() throws InterruptedException, IOException {
    //点击修改账号
    Thread.sleep(3000);
    UiObject2 updatezhang=ud.findObject(By.res("com.itsenpupulai.courierport:id/modify_account").text("修改账户"));
    updatezhang.click();
    Thread.sleep(3000);
    //选择银行卡
    ud.click(847,953);
    Thread.sleep(3000);
    //点击确定按钮
    ud.click(746,1113);
    Thread.sleep(3000);
    //添加银行卡页面输入信息
    //输入姓名
    UiObject2 uname=ud.findObject(By.res("com.itsenpupulai.courierport:id/name"));
    uname.click();
    Thread.sleep(3000);
    uname.setText("gongru");
    Thread.sleep(3000);
    //输入银行卡号
    UiObject2 ukahao=ud.findObject(By.res("com.itsenpupulai.courierport:id/account"));
    ukahao.click();
    Thread.sleep(3000);
    ukahao.setText("6225880142383547");
    Thread.sleep(3000);
    //选择银行卡
    UiObject2 ubankname=ud.findObject(By.res("com.itsenpupulai.courierport:id/bank"));
    ubankname.click();
    Thread.sleep(3000);
    ud.click(313,331);
    Thread.sleep(10000);
    //获取验证码
    UiObject2 ucodebuttonbank=ud.findObject(By.res("com.itsenpupulai.courierport:id/tv_getsmscode"));
    ucodebuttonbank.click();
    Thread.sleep(10000);
    //输入验证码
    UiObject2 editcodebank=ud.findObject(By.res("com.itsenpupulai.courierport:id/smscode"));
    editcodebank.click();
    Thread.sleep(3000);
    editcodebank.setText("1546");
    Thread.sleep(3000);
    pic.screenshot("testcase0014");
    //点击确定按钮

    UiObject2 usurebutton2bank=ud.findObject(By.res("com.itsenpupulai.courierport:id/btn_sure"));
    usurebutton2bank.click();
    Thread.sleep(3000);



}
//testcase0017
    /*
    输入金额银行卡提现
    1.申请体现页面输入金额
2.点击“确认提现”按钮
3.判断是否停留在这个页面上，如果是，点击返回
     */

  @Test

    public void testcase00917() throws InterruptedException, IOException {
        //输入金额
        UiObject2 umoney=ud.findObject(By.res("com.itsenpupulai.courierport:id/money"));
        umoney.click();
        Thread.sleep(3000);
        umoney.setText("100");
        Thread.sleep(3000);
        ud.pressBack();
        Thread.sleep(3000);
        //点击确认提现按钮
        UiObject2 tixianbutton=ud.findObject(By.res("com.itsenpupulai.courierport:id/submit").text("确认提现"));
        pic.screenshot("testcase0015");
        tixianbutton.click();
        Thread.sleep(5000);
        UiObject2 tixianbuttonn=ud.findObject(By.res("com.itsenpupulai.courierport:id/get_cash"));

        if(tixianbuttonn==null) {
            pic.screenshot("testcase0017");
            ud.pressBack();
            Thread.sleep(5000);
        }
        else {
            pic.screenshot("testcase0017111");
            //ud.pressBack();
            Thread.sleep(5000);
        }
    }
    //  testcase0018
    /*
    返回到我的钱包页面->系统首页
    0.判断是否停留在我的钱包这个页面上，如果是
1.点击“返回”，
2.从我的钱包页面返回到系统首页

     */

@Test

public void testcase00918() throws InterruptedException, IOException {
    Thread.sleep(5000);
    UiObject2 tixianbutton=ud.findObject(By.res("com.itsenpupulai.courierport:id/get_cash"));
    Assert.assertEquals("提现",tixianbutton.getText());
    if(tixianbutton!=null){
        pic.screenshot("testcase0018");
        ud.pressBack();
        Thread.sleep(5000);

    }

}
//testcase0019
   /* 查看积分列表
   1.点击侧滑栏->我的积分
2.滑动列表
    */

 @Test
   public void testcase00919() throws InterruptedException, IOException, UiObjectNotFoundException {
       //打开侧滑栏
       ac.cehual();
       Thread.sleep(5000);
       pic.screenshot("testcase00191111");
       //点击我的积分
       UiObject2 umyjifen=ud.findObject(By.res("com.itsenpupulai.courierport:id/ant_integral_ll"));
       if(umyjifen!=null){
           umyjifen.click();
           Thread.sleep(5000);
           pic.screenshot("testcase00192222");

           //滑动列表
           String jifenrelist="android:id/list";
           ac.scrollList(jifenrelist);
           Thread.sleep(5000);
           //查看积分规则->回到蚂蚁积分页面
           UiObject2 ujifengui=ud.findObject(By.res("com.itsenpupulai.courierport:id/top_regulation_tv").text("积分规则"));
           ujifengui.click();
           Thread.sleep(5000);
           pic.screenshot("testcase001933333");
           Thread.sleep(3000);
           ud.pressBack();
           //回到系统首页
           Thread.sleep(3000);
           ud.pressBack();


       }

   }
    //testcase0020
    //侧滑栏->入订单活动
    /*
1.侧滑栏->滑动活动列表
2.点击“查看详情”
3.点击我知道了，关闭弹框
     */
  @Test
    public  void testcase00920()throws InterruptedException, IOException, UiObjectNotFoundException {
        //打开侧滑栏
        ac.cehual();
        Thread.sleep(5000);
        pic.screenshot("testcase00201111");
        //点击进入订单活动
        UiObject2 udingdanhuodong=ud.findObject(By.res("com.itsenpupulai.courierport:id/tv_order_mission").text("订单活动"));
        udingdanhuodong.click();
        Thread.sleep(3000);
        //查看是否有订单任务
        UiObject2 norenwu=ud.findObject(By.text("暂无订单任务哟~"));
        if(norenwu!=null){
            Thread.sleep(3000);
            pic.screenshot("testcase00202222222");

        }
        else {
           pic.screenshot("testcase002000333333");
           ac.huodongdetail();
            Thread.sleep(3000);
            pic.screenshot("testcase002044444");
        }


    }
    //testcase00021
    //查看历史明细活动
    /*
    0.查看历史明细
1.点击“未到账”
2.滑动列表
3.点击“已到账”
4.滑动列表
5.查看“未达标”
6.滑动列表
7.查看“历史活动”
8.滑动列表
9.点击“查看详情”
10.点击我知道了，关闭弹框
     */

   @Test
    public  void testcase00921()throws InterruptedException, IOException, UiObjectNotFoundException {
        //点击历史明细按钮
        Thread.sleep(3000);
        UiObject2 hisotorylist=ud.findObject(By.res("com.itsenpupulai.courierport:id/top_call_kefu").text("历史明细"));
        hisotorylist.click();
        Thread.sleep(3000);
        //进入历史明细页面
        pic.screenshot("testcase002100000000");
        Thread.sleep(3000);
        //1.点击“已到账”
            UiObject2 gotmoney = ud.findObject(By.res("com.itsenpupulai.courierport:id/order_mission_tv_account_text").text("已到帐"));
        //查看列表->活动列表
            ac.huodonglist(gotmoney,"testcase0021yidaozhang");
            Thread.sleep(3000);
        //点击未到账
       // UiObject2 ungotmoney=ud.findObject(By.res("com.itsenpupulai.courierport:id/order_mission_tv_notaccount_text").text("未到帐"));
        UiObject2 ungotmoney=ud.findObject(By.res("com.itsenpupulai.courierport:id/order_mission_tv_notaccount_text"));
        Thread.sleep(3000);
        ac.huodonglist(ungotmoney,"testcase0021weidaozhang");
        Thread.sleep(3000);

        //点击未达标
        UiObject2 ungottop=ud.findObject(By.res("com.itsenpupulai.courierport:id/order_mission_tv_outtime_text").text("未达标"));
        Thread.sleep(3000);
        ac.huodonglist(ungottop,"testcase0021weidaobiao");

        Thread.sleep(3000);
        //点击历史活动
        UiObject2 unallhistory=ud.findObject(By.res("com.itsenpupulai.courierport:id/order_mission_tv_history").text("历史活动"));
        Thread.sleep(3000);
        ac.huodonglist(unallhistory,"testcase0021lishihuodong");
        Thread.sleep(3000);
        //查看订单详情->关闭
        //查看是否有订单任务
        UiObject2 norenwu=ud.findObject(By.text("暂无订单任务哟~"));
        if(norenwu!=null){
            Thread.sleep(3000);
            pic.screenshot("testcase002detail");

        }
        else {
            pic.screenshot("testcase00200detail");
            ac.huodongdetail();
            Thread.sleep(3000);
            pic.screenshot("testcase002detail");
        }
        Thread.sleep(3000);
        ud.pressBack();
        Thread.sleep(3000);
        ud.pressBack();

    }
//testcase0022
    //侧滑栏->进入完成订单
    /*
    1.点击“已完成”菜单
2.进入已完成列表
3.滑动列表
4.查看订单详情
     */

  @Test
    public  void testcase00922()throws InterruptedException, IOException, UiObjectNotFoundException {
        Thread.sleep(3000);
        //打开侧滑栏
        ac.cehual();
        Thread.sleep(3000);
        //打开完成订单列表
        UiObject2 ufinishilist=ud.findObject(By.res("com.itsenpupulai.courierport:id/endorder").text("已完成订单"));
        ufinishilist.click();
        Thread.sleep(3000);
        //截图
        pic.screenshot("testcase0022list");
        //
        Thread.sleep(3000);
        //滑动列表
        String ress="android:id/list";
        ac.scrollList(ress);
        Thread.sleep(3000);
        //查看订单详情
        ud.click(472,1006);
        Thread.sleep(3000);
        //判断是否进入订单详情页面
        UiObject2 strdetail=ud.findObject(By.res("com.itsenpupulai.courierport:id/top_center"));
        String str=strdetail.getText();
        Assert.assertEquals("订单详情",str);
        Thread.sleep(3000);







    }
    //testcase0023
    //对订单进行评价
    /*
    1.“立即评价”按钮存在时点击按钮
2.点击星星、标签、提交
3.“查看评价”按钮存在
4.点击按钮
5.点击3次返回按钮
     */

    @Test
    public  void testcase00923()throws InterruptedException, IOException, UiObjectNotFoundException {
        Thread.sleep(3000);
        pic.screenshot("testcase00230000000");
//判断是否是评价按钮或查看评价按钮
        UiObject2 ucommint=ud.findObject(By.res("com.itsenpupulai.courierport:id/order_detail_comment_sbtn").text("立即评价"));
        UiObject2 ucheckcommit=ud.findObject(By.res("com.itsenpupulai.courierport:id/order_detail_comment_sbtn").text("查看评价"));
        //如果立即按钮存在
        if(ucommint!=null){
            //进入评价页面
            ucommint.click();
            Thread.sleep(3000);
            pic.screenshot("testcase0023lijipingjia");
            Thread.sleep(3000);
            //提交信息
            //点击星星
            ud.click(741,584);
            Thread.sleep(3000);
            pic.screenshot("testcase0023xingxing");

            //选择标签
            ud.click(235,788);
            Thread.sleep(3000);
            ud.click(339,1025);
            Thread.sleep(3000);
            pic.screenshot("testcase0023biaoqian");
            Thread.sleep(3000);
            //输入信息
            UiObject2 uditor=ud.findObject(By.res("com.itsenpupulai.courierport:id/comment_more_content_et"));
            uditor.click();
            Thread.sleep(3000);
            uditor.setText("非常好!");
            Thread.sleep(3000);
            ud.pressBack();
            Thread.sleep(3000);
            pic.screenshot("testcase0023wenziString");
            Thread.sleep(3000);
            //提交信息
            UiObject2 utijiaobutton=ud.findObject(By.res("com.itsenpupulai.courierport:id/comment_commit_simbtn"));
            utijiaobutton.click();
            Thread.sleep(3000);
            //截图
          //  pic.screenshot("testcase0023biaoqian");
          //  Thread.sleep(3000);
            //返回到系统首页
            for(int i=0;i<2;i++) {
                pic.screenshot("testcase0023fanhuiback");
                ud.pressBack();
                Thread.sleep(3000);
            }



        }
        //如果查看按钮存在
        if(ucheckcommit!=null){
            //进入查看评价页面
            ucheckcommit.click();
            Thread.sleep(3000);
            pic.screenshot("testcase0023lijipingjia");
            Thread.sleep(3000);
            //返回到订单详情->已完成订单列表->系统首页
            for(int i=0;i<3;i++) {
                pic.screenshot("testcase0023fanhuiback");
                ud.pressBack();
                Thread.sleep(3000);
            }
        }


    }
    //testcase0024
    //进入订单热力图页面
    /*
    1.点击“订单热力图”
2.点击关闭按钮
     */
  @Test
    public  void testcase00924()throws InterruptedException, IOException, UiObjectNotFoundException {
        //打开侧滑栏
        ac.cehual();
        Thread.sleep(3000);
        //打开订单热力图
        UiObject2 ureli=ud.findObject(By.res("com.itsenpupulai.courierport:id/slide_heat_map").text("订单热力图"));
        ureli.click();
        Thread.sleep(3000);
        pic.screenshot("testcase002400reli");
        Thread.sleep(3000);
        //返回到系统首页
        ud.pressBack();



    }
    //testcase0025
    //进入推荐奖励页面
    /*
    1.点击“推荐奖励”
    2.点击关闭按钮
     */
  @Test
    public  void testcase00925()throws InterruptedException, IOException, UiObjectNotFoundException {
        //打开侧滑栏
        ac.cehual();
        Thread.sleep(3000);
        //打开推荐奖励
        UiObject2 utuijian=ud.findObject(By.res("com.itsenpupulai.courierport:id/invitefriend").text("推荐奖励"));
        utuijian.click();
        Thread.sleep(3000);
        pic.screenshot("testcase0025tuijian");
        Thread.sleep(3000);
        //返回到系统首页
        ud.pressBack();

    }
    //testcase0026
    //进入蚂蚁商城页面
    /*
   1.点击“蚂蚁商城”
   2.点击关闭按钮
     */
   @Test
    public  void testcase00926()throws InterruptedException, IOException, UiObjectNotFoundException {
        //打开侧滑栏
        ac.cehual();
        Thread.sleep(3000);
        //打开蚂蚁商城
        UiObject2 ushangcheng=ud.findObject(By.res("com.itsenpupulai.courierport:id/market_online").text("蚂蚁商城"));
        ushangcheng.click();
        Thread.sleep(3000);
        pic.screenshot("testcase0026mayishangcheng");
        Thread.sleep(3000);
        //返回到系统首页
        ud.pressBack();

    }

    //testcase0027
    //进入在线客服页面添加反馈并关闭反馈
    /*
   1.点击“在线客服”
2.点击“添加新反馈”
3.滑动列表
4.点击菜单
5.点击确定
6.输入内容
7.提交信息
     */
    @Test
    public  void testcase00927()throws InterruptedException, IOException, UiObjectNotFoundException {
        //打开侧滑栏
        ac.cehual();
        Thread.sleep(3000);
        //打开在线客服
        UiObject2 kefuonline=ud.findObject(By.res("com.itsenpupulai.courierport:id/report").text("在线客服"));
        kefuonline.click();
        Thread.sleep(3000);
        pic.screenshot("testcase0027onlinekefu");
        Thread.sleep(3000);
        //添加新反馈
        UiObject2 unewback=ud.findObject(By.res("com.itsenpupulai.courierport:id/check_report_tv_addreport").text("添加新反馈"));
        unewback.click();
        Thread.sleep(3000);
        pic.screenshot("testcase0027newfankui");
        //添加信息
        Thread.sleep(3000);
        //滑动选项
        ud.drag(517,1725,539,1455,3);
        Thread.sleep(3000);
        pic.screenshot("testcase0027huadongxuanxiang");
        //点击确定按钮，提交选项
        ud.click(1000,1323);
        Thread.sleep(3000);
        pic.screenshot("testcase0027tijiaoxinxi");
        Thread.sleep(3000);
        //编辑文字信息
        UiObject2 ufeedbackSTring=ud.findObject(By.res("com.itsenpupulai.courierport:id/feedback_et"));
        ufeedbackSTring.click();
        Thread.sleep(3000);
        ufeedbackSTring.setText("ok! 非常感谢。。。。。");
        Thread.sleep(3000);
        ud.pressBack();
        pic.screenshot("testcase0027bianjiwenzi");
        Thread.sleep(3000);
        //提交确定按钮
        UiObject2 utijiao=ud.findObject(By.res("com.itsenpupulai.courierport:id/submit").text("提交"));
        utijiao.click();
        Thread.sleep(3000);
        //返回到系统首页
       // ud.pressBack();
       // Thread.sleep(3000);



    }

    //testcase0028
    //反馈信息中添加文字、图片并且关闭
    /*
  1.点击信息
2.输入信息
3.添加图片
4.关闭会话
     */
  @Test
    public  void testcase00928() throws Exception {

        Thread.sleep(3000);
        ud.click(496,990);
        Thread.sleep(3000);
        pic.screenshot("testcase0028inruxinxiyemian");
        Thread.sleep(3000);
        //2.输入信息
       // 3.添加图片
        UiObject2 uedit=ud.findObject(By.res("com.itsenpupulai.courierport:id/report_index_et_msg"));
        uedit.click();
        Thread.sleep(3000);
        uedit.setText("你好，你好你好!");
        pic.screenshot("testcase0028jinruxinxiyemian");
        Thread.sleep(3000);
        ud.click(992,968);
        Thread.sleep(3000);
        //
        uedit.click();
        Thread.sleep(3000);
        //从图库上传图片
        ud.click(1004,962);
        Thread.sleep(3000);
        ac.selectpiclist();
        Thread.sleep(3000);
        pic.screenshot("testcase0028tukupic");
        //从相机拍照选择图片
        Thread.sleep(3000);
        uedit.click();
        Thread.sleep(3000);
        ud.click(1004,962);
        Thread.sleep(3000);
        ac.camerepic();
        Thread.sleep(3000);
        pic.screenshot("testcase0028camerapic");
        Thread.sleep(3000);
        //关闭会话->选择一般->点击确定
        ud.click(968,145);
        Thread.sleep(3000);
        ud.click(462,968);
        Thread.sleep(3000);
        pic.screenshot("testcase0028closing");
        ud.click(755,1229);
        Thread.sleep(3000);
        //返回到系统首页
        ud.pressBack();
        Thread.sleep(3000);



    }
    //testcase0029
    //查看蚂蚁课堂各个页面
    /*
   1.点击"侧滑栏"->“蚂蚁课堂”
2.点击配送教程
3.停留页面1min
4.返回到蚂蚁课堂
5.点击常见问题
6.返回到蚂蚁课堂，系统返回键
7.规章制度、蚂蚁装备、保险协议、注册条款同上
8.返回到系统首页
     */
   @Test
    public  void testcase00929()throws InterruptedException, IOException, UiObjectNotFoundException {
        //打开侧滑栏
        ac.cehual();
        Thread.sleep(3000);
        //打开蚂蚁课堂
        UiObject2 ulearning=ud.findObject(By.res("com.itsenpupulai.courierport:id/explain").text("蚂蚁课堂"));
        ulearning.click();
        Thread.sleep(3000);
        pic.screenshot("testcase0029learningpages");
        Thread.sleep(3000);
        //点击配送教程
        ud.click(282,813);
        Thread.sleep(10000);
       //进入Web页面->返回页面
        ac.ketang("testcase0029peisongjiaocheng");
        //点击常见问题并进入页面-》返回页面
         ud.click(286,964);
        ac.ketang("testcase0029requestionlists");
        //规章制度->->返回页面
        ud.click(360,1155);
        ac.ketang("testcase0029guizhangzhidu");
        //蚂蚁装备->返回页面
        ud.click(325,1296);
        ac.ketang("testcase0029mayizhuangbei");
        //保险协议-》fanhui
        ud.click(325,1455);
        ac.ketang("testcase0029baoxianexieyi");
        //注册协议-》fanhui
        ud.click(325,1604);
        ac.ketang("testcase0029zhucetiaokuan");
//返回到系统首页
        ud.pressBack();
    }


    //testcase0030
    //进入设置页面
    /*
   1.点击"侧滑栏"->“设置”
     */
    @Test
    public  void testcase00930()throws InterruptedException, IOException, UiObjectNotFoundException {
        //打开侧滑栏
        ac.cehual();
        Thread.sleep(3000);
        //进入设置页面
        UiObject2 usettings=ud.findObject(By.res("com.itsenpupulai.courierport:id/setting").text("设置"));
        usettings.click();
        Thread.sleep(3000);
        pic.screenshot("testcase0030settingpages");
        Thread.sleep(3000);
    }

    //testcase0031
    //修改账号
    /*
  1.点击账号
2.输入身份证号412823…
3.输入原始密码111111
4.输入新手机号18622222222
5.点击发送验证码
6.输入1546

     */
    @Test
    public  void testcase00931()throws InterruptedException, IOException, UiObjectNotFoundException {

        ac.newphoneupdate("18622222222");
        Thread.sleep(3000);
        ac.newphoneupdate("18638732236");

    }
    //testcase0032
    //修改密码
    /*
  1.点击修改密码
2.输入旧密码
3.输入新密码
4.提交信息

     */
   @Test
    public  void testcase00932()throws InterruptedException, IOException, UiObjectNotFoundException{
        //进入修改密码页面
       ac.newpwdfun("111111","123456");
      Thread.sleep(3000);
        ac.newpwdfun("123456","111111");
        //

    }
    //testcase0033
    //开启&关闭接单中、配送流程语音提示、接收推送订单
    /*
1.点击按钮
2.再次点击按钮

     */
   @Test
    public  void testcase00933()throws InterruptedException, IOException, UiObjectNotFoundException{
//开工或者收工按钮
        UiObject2 kaigong=ud.findObject(By.res("com.itsenpupulai.courierport:id/setting_tv_is_work").text("接单中"));
        if(kaigong!=null){
            Thread.sleep(3000);
            pic.screenshot("testcase0033yikaigong");
            Thread.sleep(3000);
            kaigong.click();
            Thread.sleep(3000);
            pic.screenshot("testcase0033yishougong2");
            Thread.sleep(3000);
            //
            kaigong.click();
            Thread.sleep(3000);
            pic.screenshot("testcase0033yishougong2");
            Thread.sleep(3000);

        }
        else{
            pic.screenshot("testcase0033yishougong");
            Thread.sleep(3000);
            ud.click(994,1053);
            Thread.sleep(3000);
            pic.screenshot("testcase0033yikaigong2");
            Thread.sleep(3000);
            //
            ud.click(994,1053);
            Thread.sleep(3000);
            pic.screenshot("testcase0033yikaigong2");
            Thread.sleep(3000);

        }
//配送流程语音关闭或开启
        ud.click(994,1170);
        Thread.sleep(3000);
        pic.screenshot("testcase0033yuyin");
        Thread.sleep(3000);
        //
        ud.click(994,1170);
        Thread.sleep(3000);
        pic.screenshot("testcase0033yuyin2");
        Thread.sleep(3000);
//接收推单开关
        ud.click(994,1292);
        Thread.sleep(3000);
        pic.screenshot("testcase0033tuidan");
        Thread.sleep(3000);
        //
        ud.click(994,1292);
        Thread.sleep(3000);
        pic.screenshot("testcase0033tuidan2");
        Thread.sleep(3000);
    }
    //离线地图下载
    /*
    1.查看“点击下载”按钮是否存在
2.若不存在择继续下载
     */

   @Test
    public  void testcase00934()throws InterruptedException, IOException, UiObjectNotFoundException{
        //判断离线地图是否已下载

        UiObject2 ditu=ud.findObject(By.res("com.itsenpupulai.courierport:id/push_order_offline_city_tv").text("点击下载"));
        if(ditu!=null){

            pic.screenshot("testcase0034tditunotexist");
            Thread.sleep(3000);
            ditu.click();
            Thread.sleep(3000);
            //确认下载
            ud.click(733,1092);
            Thread.sleep(30000);
            //
            pic.screenshot("testcase0034tditundownloadsuccess");

            Thread.sleep(3000);
    }
    }
    // 拨打客户电话
    /*
    1.点击拨打客户电话
2.弹框上点击“确定”按钮
     */

   @Test
    public  void testcase00935()throws InterruptedException, IOException, UiObjectNotFoundException{
        //点击拨打客服电话
        ud.click(478,1541);
        Thread.sleep(3000);
        pic.screenshot("testcase0035tphonecall");
        Thread.sleep(3000);
        ud.click(723,1096);
        Thread.sleep(3000);
        //
        //无sim卡，点击我知道了按钮
        ud.click(498, 1051);
        Thread.sleep(10000);

    }
    //点击关于蚂蚁专送
    /*
    1.滑动页面到底部
2.点击“关于蚂蚁专送”
3.点击返回，再次返回到系统首页
     */
    @Test
    public  void testcase00936()throws InterruptedException, IOException, UiObjectNotFoundException{

        //滑动到底部
        UiObject2 usset= ud.findObject(By.clazz("android.widget.ScrollView"));
        usset.fling(Direction.DOWN);
        Thread.sleep(3000);
        pic.screenshot("testcase0036huadong");
        Thread.sleep(3000);
//2.点击“关于蚂蚁专送”

        //UiObject2 uabour= ud.findObject(By.clazz("android.widget.ScrollView"));
       // uabour.click();
        ud.click(494,1549);
        Thread.sleep(3000);
        pic.screenshot("testcase0036aboutzhuansong");
        Thread.sleep(3000);
        //点击返回，再次返回到系统首页
        ud.pressBack();
        ud.pressBack();

















    }
}