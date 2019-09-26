package org.envision.tqw.study.startTest.Performance;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.envision.tqw.study.util.FileUtil;
import org.envision.tqw.study.util.QLUtil;
import org.junit.Test;

/**
 * @ClassName: PerfomanceTest
 * @Description:
 *      测试性能，精度等等
 * @Author: qiwei.tan
 * @Date: 2019/9/2 9:52
 * @Version: 1.0
 */
public class PerfomanceTest {
    ExpressRunner runner = new ExpressRunner();
    @Test
    public void testLocalCacheMutualImpact()throws Exception {
        //缓存在本地的脚本都是全局的，可以相互调用
        String path = "script/Performance/CachePerformanceTest.txt";
        IExpressContext<String, Object> context =new DefaultContext<String, Object>();
        runner.addMacro("计算平均成绩", FileUtil.readFileByLine(path,1));
        runner.addMacro("是否优秀", FileUtil.readFileByLine(path,2));
        context.put("语文", 88);
        context.put("数学", 99);
        context.put("英语", 95);
        long times =10000;
        long start = new java.util.Date().getTime();
        while(times-->0)
            calulateTask(false, context);
        long end = new java.util.Date().getTime();
        echo("不做缓存耗时："+ (end-start) +" ms");
        times =10000;
        start = new java.util.Date().getTime();
        while(times-->0)
            calulateTask(true, context);
        end = new java.util.Date().getTime();
        echo("做缓存耗时："+ (end-start) +" ms");
    }

    private void echo(Object obj){
        System.out.println(obj);
    }

    private void calulateTask(boolean isCache, IExpressContext<String, Object> context) throws Exception{
        QLUtil.RunExpressNoOutput(runner,"计算平均成绩",context,isCache,false);
    }

}
