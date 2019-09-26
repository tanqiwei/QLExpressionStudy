package org.envision.tqw.study.startTest.operatorTest;

import com.ql.util.express.ExpressRunner;

import org.envision.tqw.study.operatorDefine.AddNOperator;
import org.envision.tqw.study.operatorDefine.BinaryOperator;
import org.envision.tqw.study.operatorDefine.JoinOperator;
import org.envision.tqw.study.util.FileUtil;
import org.envision.tqw.study.util.QLUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName: OperatorTest
 * @Description:
 *      针对操作符的测试用例
 * @Author: qiwei.tan
 * @Date: 2019/8/26 16:03
 * @Version: 1.0
 */
public class OperatorTest {



    /**
     * 定义一个简单的二元操作符
     * @throws Exception
     */
    @Test
    public void testAddBinaryOperate() throws Exception{
        //定义表达式，相当于 1+(22+22)+(2+2)
        String express = FileUtil.readFileByPath("script/Operater/AddBinaryOperateTest.txt");
        ExpressRunner runner = new ExpressRunner();
        //定义操作符addT，其实现为AddTwiceOperator
        runner.addOperator("addT", new BinaryOperator());
        //执行表达式，并将结果赋给r
        int r = (Integer) QLUtil.RunExpressHasOutput(runner,express);
        Assert.assertTrue("操作符执行错误",r==49);
    }

    /**
     * 定义多元操作符
     * @throws Exception
     */
    @Test
    public void testNElementOperate()throws Exception{
        String express = FileUtil.readFileByPath("script/Operater/OperatorNTest.txt");
        ExpressRunner runner = new ExpressRunner();
        //定义操作符addN，其实现为AddNOperator，语法格式与in一致
        runner.addOperator("addN","in",new AddNOperator());
        //执行表达式，并将结果赋给r
        int r = (Integer)QLUtil.RunExpressHasOutput(runner,express,null,null,false,false);
        System.out.println(r);
        Assert.assertTrue("操作符执行错误",r==10);
    }




    /**
     * 中文表述的操作符
     */
    @Test
    public void testOperateReplace() throws Exception {
        String express = FileUtil.readFileByPath("script/Operater/OperateReplaceTest.txt");
        ExpressRunner runner = new ExpressRunner();
        runner.addOperatorWithAlias("如果", "if",null);
        runner.addOperatorWithAlias("则", "then",null);
        runner.addOperatorWithAlias("否则", "else",null);
        /**
         * 疑惑点，没有进行context设置语文，数学和英语，默认三者相加大于270??
         * 而且如果加入System.out.println(语文);并不打印其值
         */
        QLUtil.RunExpressHasOutput(runner,express);
    }

    /**
     * 操作符使用
     */
    @Test
    public void testOperateUserDefine() throws Exception {
        /**
         * 第一次使用addOperator定义操作符的首次别名
         */
        String express = FileUtil.readFileByLine("script/Operater/OperatorTest2.txt",1);
        ExpressRunner runner = new ExpressRunner();
        runner.addOperator("join",new JoinOperator());
        QLUtil.RunExpressHasOutput(runner,express);
    }
    /**
     * 操作符使用
     */
    @Test
    public void testOperateUserDefine2() throws Exception {
        String express = FileUtil.readFileByLine("script/Operater/OperatorTest2.txt",2);
        ExpressRunner runner = new ExpressRunner();
        runner.replaceOperator("+",new JoinOperator());
        QLUtil.RunExpressHasOutput(runner,express);
    }
    /**
     * 操作符使用addFunction
     * @TODO: 原官方Readme示例bug=> 按理[1,2,3]输出却是[1,2]，目前已修,将JoinOperator改成可支持多元运算符
     */
    @Test
    public void testOperateUserDefine3() throws Exception {
        String express = FileUtil.readFileByLine("script/Operater/OperatorTest2.txt",3);
        ExpressRunner runner = new ExpressRunner();
        runner.addFunction("join",new JoinOperator());
        QLUtil.RunExpressHasOutput(runner,express);
    }
}
