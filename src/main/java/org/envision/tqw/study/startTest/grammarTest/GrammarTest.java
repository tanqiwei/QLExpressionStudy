package org.envision.tqw.study.startTest.grammarTest;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.DynamicParamsUtil;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import com.ql.util.express.match.QLPattern;
import org.envision.tqw.study.BeanDefine.BeanExample;
import org.envision.tqw.study.BeanDefine.ObjectBean;
import org.envision.tqw.study.util.FileUtil;
import org.envision.tqw.study.util.QLUtil;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: GrammarTest
 * @Description:
 *      展示QL的语法与Java语法的不同,仅采用QL语法;
 *      展示QL其他特殊之处的标准语法
 * @Author: qiwei.tan
 * @Date: 2019/8/26 16:53
 * @Version: 1.0
 */
public class GrammarTest {

    /**
     * 数组创建
     * @throws Exception
     */
    @Test
    public void TestArrayCreate() throws Exception {
        int totalSize = FileUtil.getFileTotalLine("script/Grammar/ArrayListTest.txt");
        for(int i=1;i<=totalSize;i++){
            String express = FileUtil.readFileByLine("script/Grammar/ArrayListTest.txt",i);
            QLUtil.RunExpressHasOutput(express);
        }
    }

    /**
     * Java对象的操作
     */
    @Test
    public void testJavaObjectOperate() throws Exception {
        String express = FileUtil.readFileByPath("script/Grammar/ObjectTest.txt");
        ExpressRunner runner = new ExpressRunner(false,true);
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        ObjectBean tempObject= new ObjectBean(100,60);
        context.put("object", tempObject);
        Object r =  QLUtil.RunExpressHasOutput(runner,express,context);
        Assert.assertTrue("数据执行错误", !r.toString().equals(260));
    }

    /**
     * Java对象操作2
     */
    @Test
    public void testJavaObject2Operate() throws Exception {
        String express = FileUtil.readFileByPath("script/Grammar/ObjectTest2.txt");
        QLUtil.RunExpressNoOutput(express);
    }

    /**
     * 数组遍历
     */
    @Test
    public void testArrayErgodicTest() throws Exception {
        String express = FileUtil.readFileByPath("script/Grammar/ArrayErgodicTest.txt");
        QLUtil.RunExpressNoOutput(express);
    }
    /**
     * map遍历
     */

    @Test
    public void testMapErgodicTest()throws Exception{
        String express = FileUtil.readFileByPath("script/Grammar/MapErgodicTest.txt");
        QLUtil.RunExpressNoOutput(express);
    }

    /**
     * 展示绑定java类或者对象的method
     */
    @Test
    public void testBindingClassOrObjectMethod() throws Exception {

        ExpressRunner runner = new ExpressRunner();
        /**
         * 绑定类方法成函数
         */
        runner.addFunctionOfClassMethod("取绝对值", Math.class.getName(), "abs",
                new String[] { "double" }, null);
        runner.addFunctionOfClassMethod("转换为大写", BeanExample.class.getName(),
                "upper", new String[] { "String" }, null);
        /**
         * 绑定对象方法
         */
        runner.addFunctionOfServiceMethod("打印", System.out, "println",new String[] { "String" }, null);
        runner.addFunctionOfServiceMethod("contains", new BeanExample(), "anyContains",
                new Class[] { String.class, String.class }, null);
        String path = "script/Grammar/BindingClassOrObjectMethoTest.txt";
        int totalLine = FileUtil.getFileTotalLine(path);
        for(int i=1;i<=totalLine;i++){
            String express = FileUtil.readFileByLine(path,i);
            QLUtil.RunExpressHasOutput(runner,express);
        }
    }

    /**
     * 测试宏定义
     */
    @Test
    public void testMacro()throws Exception {
        ExpressRunner runner = new ExpressRunner();
        String path = "script/Grammar/MacroTest.txt";

        runner.addMacro("计算平均成绩", FileUtil.readFileByLine(path,1));
        runner.addMacro("是否优秀", FileUtil.readFileByLine(path,2));
        IExpressContext<String, Object> context =new DefaultContext<String, Object>();
        context.put("语文", 88);
        context.put("数学", 99);
        context.put("英语", 95);
        QLUtil.RunExpressHasOutput(runner,FileUtil.readFileByLine(path,3),context);
    }

    /**
     * 测试所需变量定义（编译期）
     */
    @Test
    public void testVarNeedDef() throws Exception {
        String express = FileUtil.readFileByPath("script/Grammar/varNeedDefTest.txt");
        ExpressRunner runner = new ExpressRunner(true,true);
        String[] names = runner.getOutVarNames(express);
        for(String s:names){
            System.out.println("var : " + s);
        }
    }

    /**
     * 测试函数的不定参数
     * @throws Exception
     */
    @Test
    public void testMethodReplace() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        String express = FileUtil.readFileByLine("script/Grammar/MethodReplaceTest.txt",1);
        IExpressContext<String,Object> expressContext = new DefaultContext<String,Object>();
        runner.addFunctionOfServiceMethod("getTemplate", this, "getTemplate", new Class[]{Object[].class}, null);
        //(1)默认的不定参数可以使用数组来代替
        QLUtil.RunExpressHasOutput(runner,express);
        //(2)像java一样,支持函数动态参数调用,需要打开以下全局开关,否则以下调用会失败
        DynamicParamsUtil.supportDynamicParams = true;
        express = FileUtil.readFileByLine("script/Grammar/MethodReplaceTest.txt",2);
        QLUtil.RunExpressHasOutput(runner,express);
    }
    //等价于getTemplate(Object[] params)
    public Object getTemplate(Object... params) {
        String result = "";
        for(Object obj:params){
            result = result+obj+",";
        }
        return result;
    }

    /**
     * 快速创建集合
     * @throws Exception
     */
    @Test
    public void testSetCreateFast() throws Exception {
        String path = "script/Grammar/SetCreateFastTest.txt";
        int totalLine = FileUtil.getFileTotalLine(path);
        for(int i=1;i<=totalLine;i++)
            QLUtil.RunExpressHasOutput(FileUtil.readFileByLine(path,i));
    }

    /**
     * 测试CheckySyntax，提供复杂的语法检查，(比如检查自定义的java类)，不保证运行期在本地环境可以编译成指令
     */
    @Test
    public void testCheckySyntax()throws Exception{
        ExpressRunner runner = new ExpressRunner(false,true);
        String path = "script/Grammar/CheckySyntax.txt";
        int totalLine = FileUtil.getFileTotalLine(path);
        for(int i=1;i<=totalLine;i++){
            ArrayList<String> mockClasses = new ArrayList<String>();
            Assert.assertTrue(runner.checkSyntax(FileUtil.readFileByLine(path,i),true,mockClasses));
            System.out.println("未识别的java类列表:"+mockClasses);
        }
    }

    /**
     * char的比较
     * @throws Exception
     */
    @Test
    public void testCompare()throws Exception{
        String path = "script/Grammar/CompareExpress.txt";
        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("test",'a'+0);
        int totalLine = FileUtil.getFileTotalLine(path);
        for(int i=1;i <= totalLine;i++) {
            Object result = QLUtil.RunExpressHasOutput(FileUtil.readFileByLine(path,i),context,true,false);
            assert (true == (Boolean) result);
        }
    }

    /**
     * 是否忽略
     */
    @Test
    public void testIgnoreConstChar() throws Exception{
        String path = "script/Grammar/IgnoreConstCharTest.txt";
        ExpressRunner runner = new ExpressRunner();
        runner.setIgnoreConstChar(true);// 忽略,字符型成为字符串型
        String express = FileUtil.readFileByLine(path,1);
        QLUtil.RunExpressHasOutput(runner,express,false,true);
        runner.setIgnoreConstChar(false);// 不忽略,字符型成为整形
        try{
            QLUtil.RunExpressHasOutput(runner,express,false,true);
        }catch (Exception e){
            System.out.println("不忽略时，express=【"+express+"】\n调用会发生异常");
        }
        express = FileUtil.readFileByLine(path,2);
        Object r = QLUtil.RunExpressHasOutput(runner,express,false,true);
        System.out.println(r.getClass());
        QLUtil.RunExpressHasOutput(runner,FileUtil.readFileByLine(path,3),false,true);
        QLUtil.RunExpressHasOutput(runner,FileUtil.readFileByLine(path,4),false,true);
    }

    /**
     * 测试类的导入
     * @throws Exception
     */
    @Test
    public void testImportClassPath()throws Exception{
        String path="script/Grammar/ImportClassPath.txt";
        int totalLine = FileUtil.getFileTotalLine(path);
        for(int i=1;i<= totalLine;i++)
            try {
                QLUtil.RunExpressHasOutput(FileUtil.readFileByLine(path,i));
            } catch (Exception e) {
                System.out.println("SimpleDateFormat 没有定义，此处应该报错");
            }
    }

    /**
     * 关于栈的测试
     */
    @Test
    public void testStack()throws Exception{
        String path = "script/Grammar/StackTest.txt";
        int totalSize = FileUtil.getFileTotalLine(path);
        for(int i=1;i<=totalSize;i++)
        {
            QLPattern.printStackDepth = true;
            QLPattern.optimizeStackDepth = false;
            ExpressRunner runner = new ExpressRunner();
            String express = FileUtil.readFileByLine(path,i);
            QLUtil.RunExpressHasOutput(runner,express,true,false);
            System.out.println("优化栈深度之后:");
            QLPattern.printStackDepth = true;
            QLPattern.optimizeStackDepth = true;
            ExpressRunner runner2 = new ExpressRunner();
            QLUtil.RunExpressHasOutput(runner2,express,true,false);
            QLPattern.printStackDepth = false;
            System.out.println("================ 分割 ===================");
        }
    }

    /**
     * 测试非短路逻辑,并且输出出错信息
     */
    @Test
    public void testShortCircuit() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        runner.setShortCircuit(true);
        IExpressContext<String,Object> expressContext = new DefaultContext<String,Object>();
        expressContext.put("违规天数", 100);
        expressContext.put("虚假交易扣分", 11);
        expressContext.put("VIP", false);
        List<String> errorInfo = new ArrayList<String>();
        runner.getOperatorFactory().getOperator("<").setErrorInfo("$1 < $2 = false");
        runner.getOperatorFactory().getOperator(">").setErrorInfo("$1 > $2 = false");
        String expression = FileUtil.readFileByPath("script/Grammar/ShortCircuitTest.txt");
        boolean result = calculateLogicTest(runner,expression, expressContext, errorInfo);
        if(result){
            System.out.println(expression+" is true!");
        }else{
            System.out.println(expression+" is false!");
            for(String error : errorInfo){
                System.out.println(error);
            }
        }
    }
    /**
     * 测试非短路逻辑,并且输出出错信息
     * @throws Exception
     */
    @Test
    public void testNoShortCircuit() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        runner.setShortCircuit(false);
        IExpressContext<String,Object> expressContext = new DefaultContext<String,Object>();
        expressContext.put("违规天数", 100);
        expressContext.put("虚假交易扣分", 11);
        expressContext.put("VIP", false);
        List<String> errorInfo = new ArrayList<String>();
        runner.getOperatorFactory().getOperator("<").setErrorInfo("$1 < $2 = false");
        runner.getOperatorFactory().getOperator(">").setErrorInfo("$1 > $2 = false");
        String expression = FileUtil.readFileByPath("script/Grammar/ShortCircuitTest.txt");
        boolean result = calculateLogicTest(runner,expression, expressContext, errorInfo);
        if(result){
            System.out.println(expression+" is true!");
        }else{
            System.out.println(expression+" is false!");
            for(String error : errorInfo){
                System.out.println(error);
            }
        }

    }



    public boolean calculateLogicTest( ExpressRunner runner,String expression,IExpressContext<String,Object> expressContext,List<String> errorInfo) throws Exception {
        Boolean result = (Boolean)QLUtil.RunExpressHasOutput(runner,expression, expressContext, errorInfo, true, false);
        if(result.booleanValue() == true){
            return true;
        }
        return false;
    }


    /**
     * 类定义测试
     */
    @Test
    public void TestClassDefine() throws Exception{
        ExpressRunner runner = new ExpressRunner(false, false);
        String express = FileUtil.readFileByPath("script/Grammar/ClassDefineTest.txt");
        runner.loadMutilExpress("ClassTest", express);
        Object r = QLUtil.RunExpressHasOutput(runner,express);
        Assert.assertTrue("VClass的作用域错误", r.toString().equalsIgnoreCase("300"));
    }

    /**
     * 类定义测试
     */
    @Test
    public void TestClassDefine2() throws Exception{
        String expressDefine = FileUtil.readFileByPath("script/Grammar/ClassDefineTest2ForDefine.txt");
        String express = FileUtil.readFileByPath("script/Grammar/ClassDefineTest2.txt");
        ExpressRunner runner = new ExpressRunner(false, true);
        runner.loadMutilExpress("",expressDefine);
        runner.loadMutilExpress("ClassTest", express);
        Object r = QLUtil.RunExpressHasOutput(runner,express);
        Assert.assertTrue("VClass的作用域错误", r.toString().equalsIgnoreCase("330--430--xuannan--xuanyu--199.99--11.11"));
    }
    @Test
    public void testBitTest() throws Exception{
        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        int num = -10;
        context.put("num",num);
        String path = "script/Grammar/BitTest.txt";
        int totalLine = FileUtil.getFileTotalLine(path);
        for(int i=1;i<=totalLine;i++)
            QLUtil.RunExpressHasOutput(FileUtil.readFileByLine(path,i),context);
        long numL = -10L;
        context.put("num",numL);
        for(int i=1;i<=totalLine;i++)
            QLUtil.RunExpressHasOutput(FileUtil.readFileByLine(path,i),context);

    }


}
