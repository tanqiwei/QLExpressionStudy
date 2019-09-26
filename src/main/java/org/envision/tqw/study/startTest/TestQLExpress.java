package org.envision.tqw.study.startTest;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.envision.tqw.study.util.FileUtil;
import org.envision.tqw.study.util.QLUtil;
import org.junit.Assert;
import org.junit.Test;


/**
 * @ClassName: Test
 * @Description:
 *      针对QLExpress第一次初次使用测试
 * @Author: qiwei.tan
 * @Date: 2019/8/26 14:50
 * @Version: 1.0
 */
public class TestQLExpress {


    /**
     * 表达式计算
     * @throws Exception
     */
    @Test
    public void testQLExpression()throws Exception{
        ExpressRunner runner = new ExpressRunner();
        /**
         * 表达式计算的数据注入接口
         */
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a",1);
        context.put("b",2);
        context.put("c",3);
        String express = "a+b*c";//===> 定义规则
        QLUtil.RunExpressHasOutput(express,context);// 解析规则+执行规则
    }

    /**
     * 普通java语句
     * @throws Exception
     */

    @Test
    public void testExpressForOperater() throws Exception {
        String express = FileUtil.readFileByPath("script/Operater/OperaterTest.txt");
        Object r = QLUtil.RunExpressHasOutput(express);
        Assert.assertEquals(45, r);
    }

    /**
     * 三元运算符测试
     * @throws Exception
     */
    @Test
    public void testTernaryOperator() throws Exception {
        String express = FileUtil.readFileByPath("script/Operater/TernaryOperator.txt");
        QLUtil.RunExpressHasOutput(express);
    }




}
