package org.envision.tqw.study.startTest.SceneTest.Scene1;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.envision.tqw.study.operatorDefine.ApproveOperator;
import org.envision.tqw.study.util.FileUtil;
import org.envision.tqw.study.util.QLUtil;
import org.junit.Test;

/**
 * @ClassName: SceneTwo
 * @Description:
 *      场景二，流程管理
 * @Author: qiwei.tan
 * @Date: 2019/9/2 15:09
 * @Version: 1.0
 */
public class SceneTwo {
    private IExpressContext<String,Object> expressContext = new DefaultContext<String, Object>();

    public void println(String s) {
        System.out.println(s);
    }



    /**
     * 执行一段文本
     * @throws Exception
     */
    @Test
    public void testApprove1() throws Exception{
        String express = FileUtil.readFileByPath("script/Scene/SceneTwoTest.txt");
        ExpressRunner runner = new ExpressRunner();
        //定义操作符别名
        runner.addOperatorWithAlias("如果", "if",null);
        runner.addOperatorWithAlias("否则", "else",null);
        runner.addOperatorWithAlias("大于", ">",null);
        //
        runner.addFunctionOfServiceMethod("打印", new SceneTwo(), "println",new String[] { "String" }, null);
        //定义方法
        runner.addFunction("审批通过", new ApproveOperator(1));
        runner.addFunction("报销入账", new ApproveOperator(2));
        runner.addFunction("打回修改", new ApproveOperator(3));
        //设置上下文变量
        IExpressContext<String,Object> expressContext = new DefaultContext<String,Object>();
        expressContext.put("经理", "王经理");
        expressContext.put("总监", "李总监");
        expressContext.put("财务", "张财务");
        expressContext.put("申请人", "小强");
        expressContext.put("金额", new Integer(4000));
        //执行表达式
        QLUtil.RunExpressNoOutput(runner,express,expressContext);
    }

    /**
     * 通过文件加载表达式
     * @throws Exception
     */
    @Test
    public void testApprove2()throws Exception{
        ExpressRunner runner = new ExpressRunner();
        //定义操作符别名
        runner.addOperatorWithAlias("如果", "if",null);
        runner.addOperatorWithAlias("否则", "else",null);
        runner.addOperatorWithAlias("大于", ">",null);
        //
        runner.addFunctionOfServiceMethod("打印", new SceneTwo(), "println",new String[] { "String" }, null);
        //定义方法
        runner.addFunction("审批通过", new ApproveOperator(1));
        runner.addFunction("报销入账", new ApproveOperator(2));
        runner.addFunction("打回修改", new ApproveOperator(3));
        //加载文件
        runner.loadExpress("script/Scene/approve1");
        //从指定文件中获取表示式构造指令集

        //设置上下文变量
        IExpressContext<String,Object> expressContext = new DefaultContext<String, Object>();
        expressContext.put("经理", "王经理");
        expressContext.put("总监", "李总监");
        expressContext.put("财务", "张财务");
        expressContext.put("申请人", "小强");
        expressContext.put("金额", new Integer(5000));

        runner.executeByExpressName("script/Scene/approve1", expressContext, null, false,false,null);
    }

    /**
     * 通过文件加载方法及表达式
     * @throws Exception
     */
    @Test
    public void testApprove3()throws Exception{
        ExpressRunner runner = new ExpressRunner();
        //定义操作符别名
        runner.addOperatorWithAlias("如果", "if",null);
        runner.addOperatorWithAlias("否则", "else",null);
        runner.addOperatorWithAlias("大于", ">",null);
        //
        runner.addFunctionOfServiceMethod("打印", new SceneTwo(), "println",new String[] { "String" }, null);
        //加载文件
        runner.loadExpress("script/Scene/approve");
        //设置上下文变量
        IExpressContext<String,Object> expressContext = new DefaultContext<String, Object>();
        expressContext.put("经理", "王经理");
        expressContext.put("总监", "李总监");
        expressContext.put("财务", "张财务");
        expressContext.put("申请人", "小强");
        expressContext.put("金额", new Integer(6000));

        runner.executeByExpressName("script/Scene/approve", expressContext, null, false,false,null);
    }

    /**
     * 从不同的文件中加载方法及表达式
     * @throws Exception
     */
    @Test
    public void testApprove4()throws Exception{
        ExpressRunner runner = new ExpressRunner();
        //定义操作符别名
        runner.addOperatorWithAlias("如果", "if",null);
        runner.addOperatorWithAlias("否则", "else",null);
        runner.addOperatorWithAlias("大于", ">",null);
        //
        runner.addFunctionOfServiceMethod("打印", new SceneTwo(), "println",new String[] { "String" }, null);

        //加载文件
        runner.loadExpress("script/Scene/approve1");
        runner.loadExpress("script/Scene/approve2");
        //设置上下文变量
        IExpressContext<String,Object> expressContext = new DefaultContext<String, Object>();
        expressContext.put("经理", "王经理");
        expressContext.put("总监", "李总监");
        expressContext.put("财务", "张财务");
        expressContext.put("申请人", "小强");
        expressContext.put("金额", new Integer(7000));
        runner.executeByExpressName("script/Scene/approve1", expressContext, null, false,false,null);
    }

}
