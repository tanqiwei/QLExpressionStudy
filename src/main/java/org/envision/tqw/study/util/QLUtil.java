package org.envision.tqw.study.util;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.apache.commons.logging.Log;

import java.util.List;

/**
 * @ClassName: QLUtil
 * @Description:
 *     执行语句的避免重复代码
 * @Author: qiwei.tan
 * @Date: 2019/8/29 9:56
 * @Version: 1.0
 */
public class QLUtil {

    private static IExpressContext<String, Object> Context = new DefaultContext<String, Object>();
    private static ExpressRunner Runner = new ExpressRunner();
    // ----------------------------------------- 自定义Runner和Context ---------------------------------------------------------
    /****
     * =================================================================================================================
     *
     *                                           会输出表达式
     *
     * =================================================================================================================
     */
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @param log               输出的log
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,IExpressContext<String, Object> context, List<String> errorList, boolean isCache, boolean isTrace, Log log) throws Exception{
        System.out.println("Expression:\n"+expression);
        Object r = runner.execute(expression, context, errorList, isCache, isTrace,log);
        System.out.println("Result:\n"+r);
        return r;
    }

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,IExpressContext<String, Object> context, List<String> errorList, boolean isCache, boolean isTrace) throws Exception{
        return RunExpressHasOutput(runner,expression,context,errorList,isCache,isTrace,null);
    }

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,IExpressContext<String, Object> context,boolean isCache, boolean isTrace) throws Exception{
        return RunExpressHasOutput(runner,expression,context,null,isCache,isTrace,null);
    }

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,IExpressContext<String, Object> context,boolean isTrace) throws Exception{
        return RunExpressHasOutput(runner,expression,context,null,false,isTrace,null);
    }


    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,IExpressContext<String, Object> context, List<String> errorList, boolean isCache) throws Exception{
        return RunExpressHasOutput(runner,expression,context,errorList,isCache,false,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,IExpressContext<String, Object> context, List<String> errorList) throws Exception{
        return RunExpressHasOutput(runner,expression,context,errorList,false,false,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,IExpressContext<String, Object> context) throws Exception{
        return RunExpressHasOutput(runner,expression,context,null,false,false,null);
    }
    /****
     * =================================================================================================================
     *
     *                                           不会输出表达式
     *
     * =================================================================================================================
     */

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @param log               输出的log
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, IExpressContext<String, Object> context, List<String> errorList,boolean isCache, boolean isTrace, Log log) throws Exception{
        System.out.println("Expression:\n"+expression);
        runner.execute(expression, context, errorList, isCache, isTrace,log);
    }

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, IExpressContext<String, Object> context, List<String> errorList,boolean isCache, boolean isTrace) throws Exception{
        RunExpressNoOutput(runner,expression, context, errorList, isCache, isTrace,null);
    }

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, IExpressContext<String, Object> context, List<String> errorList,boolean isCache) throws Exception{
        RunExpressNoOutput(runner,expression, context, errorList, isCache, false,null);
    }

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, IExpressContext<String, Object> context, List<String> errorList) throws Exception{
        RunExpressNoOutput(runner,expression, context, errorList, false, false,null);
    }

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, IExpressContext<String, Object> context) throws Exception{
        RunExpressNoOutput(runner,expression, context, null, false, false,null);
    }

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, IExpressContext<String, Object> context, boolean isCache, boolean isTrace) throws Exception{
        RunExpressNoOutput(runner,expression, context, null, isCache, isTrace,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param context           执行上下文
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, IExpressContext<String, Object> context,  boolean isTrace) throws Exception{
        RunExpressNoOutput(runner,expression, context, null, false, isTrace,null);
    }

    // ----------------------------------------- 仅自定义Runner ---------------------------------------------------------
    /****
     * =================================================================================================================
     *
     *                                           会输出表达式
     *
     * =================================================================================================================
     */

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @param log               输出的log
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,  List<String> errorList,boolean isCache, boolean isTrace, Log log) throws Exception{
        return RunExpressHasOutput(runner,expression, Context, errorList, isCache, isTrace,log);
    }

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,  List<String> errorList,boolean isCache, boolean isTrace) throws Exception{
        return RunExpressHasOutput(runner,expression, errorList, isCache, isTrace,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,  List<String> errorList,boolean isCache) throws Exception{
        return RunExpressHasOutput(runner,expression, errorList, isCache, false,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,  List<String> errorList) throws Exception{
        return RunExpressHasOutput(runner,expression, errorList, false, false,null);
    }

    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression,boolean isCache, boolean isTrace) throws Exception{
        return RunExpressHasOutput(runner,expression, null, isCache, isTrace,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression, boolean isTrace) throws Exception{
        return RunExpressHasOutput(runner,expression,  null, false, isTrace,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @throws Exception
     */
    public static Object RunExpressHasOutput(ExpressRunner runner,String expression) throws Exception{
        return RunExpressHasOutput(runner,expression,null,false,false,null);
    }
    /****
     * =================================================================================================================
     *
     *                                           不会输出表达式
     *
     * =================================================================================================================
     */
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @param log               输出的log
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, List<String> errorList,boolean isCache, boolean isTrace, Log log) throws Exception{
        RunExpressNoOutput(runner,expression, Context, errorList, isCache, isTrace,log);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, List<String> errorList,boolean isCache, boolean isTrace) throws Exception{
        RunExpressNoOutput(runner,expression, errorList, isCache, isTrace,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, List<String> errorList,boolean isCache) throws Exception{
        RunExpressNoOutput(runner,expression, errorList, isCache, false,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, List<String> errorList) throws Exception{
        RunExpressNoOutput(runner,expression, errorList, false, false,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression) throws Exception{
        RunExpressNoOutput(runner,expression, null, false, false,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression, boolean isCache, boolean isTrace) throws Exception{
        RunExpressNoOutput(runner,expression, null, isCache, isTrace,null);
    }
    /**
     * @param runner            执行器
     * @param expression        表达式
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(ExpressRunner runner,String expression,boolean isTrace) throws Exception{
        RunExpressNoOutput(runner,expression, null, false, isTrace,null);
    }
    // ----------------------------------------- 仅自定义Context ---------------------------------------------------------

    /****
     * =================================================================================================================
     *
     *                                           会输出表达式
     *
     * =================================================================================================================
     */
    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @param log               输出的log
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression,IExpressContext<String, Object> context, List<String> errorList, boolean isCache, boolean isTrace, Log log) throws Exception{
        return RunExpressHasOutput(Runner,expression,context,errorList,isCache,isTrace,log);
    }

    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression,IExpressContext<String, Object> context, List<String> errorList, boolean isCache, boolean isTrace) throws Exception{
        return RunExpressHasOutput(expression,context,errorList,isCache,isTrace,null);
    }

    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression,IExpressContext<String, Object> context, List<String> errorList, boolean isCache) throws Exception{
        return RunExpressHasOutput(expression,context,errorList,isCache,false,null);
    }

    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression,IExpressContext<String, Object> context, List<String> errorList) throws Exception{
        return RunExpressHasOutput(expression,context,errorList,false,false,null);
    }

    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression,IExpressContext<String, Object> context) throws Exception{
        return RunExpressHasOutput(expression,context,null,false,false,null);
    }

    /**
     *
     * @param expression        表达式
     * @param context           执行上下文
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression,IExpressContext<String, Object> context,  boolean isCache, boolean isTrace) throws Exception{
        return RunExpressHasOutput(expression,context,null,isCache,isTrace,null);
    }


    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression,IExpressContext<String, Object> context,  boolean isTrace) throws Exception{
        return RunExpressHasOutput(expression,context,null,false,isTrace,null);
    }


    /****
     * =================================================================================================================
     *
     *                                           不会输出表达式
     *
     * =================================================================================================================
     */
    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @param log               输出的log
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression, IExpressContext<String, Object> context, List<String> errorList,boolean isCache, boolean isTrace, Log log) throws Exception{
        RunExpressNoOutput(Runner,expression, context, errorList, isCache, isTrace,log);
    }
    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression, IExpressContext<String, Object> context, List<String> errorList,boolean isCache, boolean isTrace) throws Exception{
        RunExpressNoOutput(expression, context, errorList, isCache, isTrace,null);
    }
    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression, IExpressContext<String, Object> context, boolean isCache, boolean isTrace) throws Exception{
        RunExpressNoOutput(expression, context, null, isCache, isTrace,null);
    }
    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression, IExpressContext<String, Object> context, List<String> errorList,boolean isCache) throws Exception{
        RunExpressNoOutput(expression, context, errorList, isCache, false,null);
    }

    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression, IExpressContext<String, Object> context,boolean isTrace) throws Exception{
        RunExpressNoOutput(expression, context, null, false, isTrace,null);
    }
    /**
     * @param expression        表达式
     * @param context           执行上下文
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression, IExpressContext<String, Object> context) throws Exception{
        RunExpressNoOutput(expression, context, null, false, false,null);
    }



    // -------------------------------------------------- 默认Context --------------------------------------------------

    /**
     * 运行表达式，需要输出
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @param log               输出的log
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression, List<String> errorList, boolean isCache, boolean isTrace, Log log) throws Exception{
        return RunExpressHasOutput(Runner,expression, Context, errorList, isCache, isTrace,log);
    }

    /**
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression, List<String> errorList,boolean isCache, boolean isTrace) throws Exception{
        return RunExpressHasOutput(expression,errorList,isCache,isTrace,null);
    }

    /**
     *
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression, List<String> errorList,boolean isCache) throws Exception{
        return RunExpressHasOutput(expression,errorList,isCache,false,null);
    }
    /**
     *
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression, List<String> errorList) throws Exception{
        return RunExpressHasOutput(expression,errorList,false,false,null);
    }

    /**
     * @param expression        表达式
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression,boolean isCache, boolean isTrace) throws Exception{
       return RunExpressHasOutput(expression,null,isCache,isTrace,null);
    }
    /**
     * @param expression        表达式
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression, boolean isTrace) throws Exception{
        return RunExpressHasOutput(expression,null,false,isTrace,null);
    }

    /**
     * @param expression        表达式
     * @throws Exception
     */
    public static Object RunExpressHasOutput(String expression) throws Exception{
        return RunExpressHasOutput(expression,null,false,false,null);
    }
    /**
     * 不输出结果，仅运行
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @param log               输出的log
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression, List<String> errorList,boolean isCache, boolean isTrace, Log log) throws Exception{
        RunExpressNoOutput(Runner,expression, Context, errorList, isCache, isTrace,log);
    }
    /**
     * 不输出结果，仅运行
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression, List<String> errorList,boolean isCache, boolean isTrace) throws Exception{
        RunExpressNoOutput(expression,errorList,isCache,isTrace,null);
    }

    /**
     * 不输出结果，仅运行
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @param isCache           是否采用Cache缓存指令
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression, List<String> errorList,boolean isCache) throws Exception{
        RunExpressNoOutput(expression,errorList,isCache,false,null);
    }
    /**
     * 不输出结果，仅运行
     * @param expression        表达式
     * @param errorList         输出的错误信息List
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression, List<String> errorList) throws Exception{
        RunExpressNoOutput(expression,errorList,false,false,null);
    }
    /**
     * 不输出结果，仅运行
     * @param expression        表达式
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression) throws Exception{
        RunExpressNoOutput(expression,null,false,false,null);
    }

    /**
     * 不输出结果，仅运行
     * @param expression        表达式
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression, boolean isTrace) throws Exception{
        RunExpressNoOutput(expression,null,false,isTrace,null);
    }
    /**
     * 不输出结果，仅运行
     * @param expression        表达式
     * @param isCache           是否采用Cache缓存指令
     * @param isTrace           是否输出详细的执行指令信息
     * @throws Exception
     */
    public static void RunExpressNoOutput(String expression,boolean isCache, boolean isTrace) throws Exception{
        RunExpressNoOutput(expression,null,isCache,isTrace,null);
    }
}
