package org.envision.tqw.study.startTest.functionTest;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.envision.tqw.study.util.FileUtil;
import org.envision.tqw.study.util.QLUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName: FunctionTest
 * @Description:
 * 测试QLEpress的function相关
 * @Author: qiwei.tan
 * @Date: 2019/8/28 17:41
 * @Version: 1.0
 */
public class FunctionTest {

    /**
     * 测试function定义和使用
     */
    @Test
    public void testFunction1() throws Exception {
        String express = FileUtil.readFileByPath("script/Function/FunctionTest1.txt");
        QLUtil.RunExpressHasOutput(express);
    }
    /*
     * 函数定义测试2
     */
    @Test
    public void testFunction2() throws Exception {
        String funExp = FileUtil.readFileByPath("script/Function/FunctionTest2.txt");
        ExpressRunner runner = new ExpressRunner();
        /**
         * 装载表达式，但不执行，例如一些宏定义，或者自定义函数
         */
        runner.loadMutilExpress("",funExp);
        String exp = "FAILFUN(-1,'error')";
        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        QLUtil.RunExpressHasOutput(runner,exp,context);
        exp = "FAILFUN(-12,'error')";
        QLUtil.RunExpressHasOutput(runner,exp,context);
    }


}
