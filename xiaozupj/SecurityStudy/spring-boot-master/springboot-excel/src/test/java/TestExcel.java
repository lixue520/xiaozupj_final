import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.crush.excel.listener.*;
import com.crush.excel.pojo.*;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Author: crush
 * @Date: 2021-10-31 9:58
 * version 1.0
 */
@SpringBootTest
public class TestExcel {


    /**
     * demo1 最简单的读
     * 我们先操作单个的Sheet,进行读操作
     * * <p>1. 创建excel对应的实体对象 参照{@link QuestionModel}
     * * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DocumentListener}
     * * <p>3. 直接读即可
     */
    @Test
    public void demo1TestRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "E:\\project_code\\commons-utils\\springboot-excel\\src\\main\\resources\\excel\\demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoModel.class, new DemoListener()).sheet().doRead();


    }


    @Test
    public void demo2TestRead() {
        String fileName = "E:\\project_code\\commons-utils\\springboot-excel\\src\\main\\resources\\excel\\demo.xlsx";
        // 读取全部sheet
        // 这里需要注意 DemoDataListener的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
        // 读取部分sheet
        ExcelReader excelReader = EasyExcel.read(fileName).build();
//         这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
        ReadSheet readSheet1 =
                EasyExcel.readSheet(0).head(DemoModel.class).registerReadListener(new DemoListener()).build();
        // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
        excelReader.read(readSheet1);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

    /**
     * 最简单的写
     * <p>1. 创建excel对应的实体对象 参照{@link DemoModel}
     * <p>2. 直接写即可
     */
    @Test
    public void simpleWrite() {
        DemoModel model = new DemoModel();
        model.setName("宁在春");
        model.setCommunityName("知乎");
        model.setHomePageUrl("https://www.zhihu.com/creator/manage/creation/all");
        model.setEmail("nza_wyh@163.com");
        model.setSpecialty("SpringBoot");
        model.setStartDate("2021-10-31 12:00:00");
        DemoModel model2 = new DemoModel();
        model2.setName("宁在春2");
        model2.setCommunityName("知乎2");
        model2.setHomePageUrl("2https://www.zhihu.com/creator/manage/creation/all");
        model2.setEmail("nza_wyh@163.com2");
        model2.setSpecialty("SpringBoot2");
        model2.setStartDate("2021-10-31 12:00:00");
        List<DemoModel> models = new ArrayList<DemoModel>();
        models.add(model);
        models.add(model2);
        // 写法1
        String fileName = "E:\\project_code\\commons-utils\\springboot-excel\\src\\main\\resources\\excel\\demo2.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoModel.class).sheet(0).doWrite(models);

//        // 写法2
//        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoModel.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0).build();
        excelWriter.write(models, writeSheet);
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }


    /**
     * 根据模板写入
     * <p>1. 创建excel对应的实体对象 参照
     * <p>2. 使用 注解指定写入的列
     * <p>3. 使用withTemplate 写取模板
     * <p>4. 直接写即可
     */
    @Test
    public void templateWrite() {
        DemoModel model = new DemoModel();
        model.setName("宁在春");
        model.setCommunityName("知乎");
        model.setHomePageUrl("https://www.zhihu.com/creator/manage/creation/all");
        model.setEmail("nza_wyh@163.com");
        model.setSpecialty("SpringBoot");
        model.setStartDate("2021-10-31 12:00:00");
        DemoModel model2 = new DemoModel();
        model2.setName("宁在春2");
        model2.setCommunityName("知乎2");
        model2.setHomePageUrl("2https://www.zhihu.com/creator/manage/creation/all");
        model2.setEmail("nza_wyh@163.com2");
        model2.setSpecialty("SpringBoot2");
        model2.setStartDate("2021-10-31 12:00:00");
        List<DemoModel> models = new ArrayList<DemoModel>();
        models.add(model);
        models.add(model2);
        String templateFileName = "E:\\project_code\\commons-utils\\springboot-excel\\src\\main\\resources\\excel\\demo2.xlsx";
        String fileName = "E:\\project_code\\commons-utils\\springboot-excel\\src\\main\\resources\\excel\\templateWrite.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoModel.class).withTemplate(templateFileName).sheet().doWrite(models);
    }

    @Test
    public void test() {
        String s = "124.123";
        System.out.println(s.substring(s.lastIndexOf(".")));
    }

    /**
     * 读多个或者全部sheet,这里注意一个sheet不能读取多次，多次读取需要重新读取文件
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link QuestionModel}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DocumentListener}
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void repeatedRead() {
        String fileName = "E:\\project_code\\commons-utils\\springboot-excel\\src\\main\\resources\\excel\\Document_Success.xlsx";
        // 读取全部sheet
        // 这里需要注意 DemoDataListener的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
//        EasyExcel.read(fileName, QuestionModel.class, new DocumentListener()).doReadAll();

        // 读取部分sheet
        ExcelReader excelReader = EasyExcel.read(fileName).build();
//         这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
        ReadSheet readSheet1 =
                EasyExcel.readSheet(0).head(DocumentModel.class).registerReadListener(new DocumentListener()).build();
        ReadSheet readSheet2 =
                EasyExcel.readSheet(1).head(TemplateModel.class).registerReadListener(new TemplateListener()).build();

        ReadSheet readSheet3 =
                EasyExcel.readSheet(2).head(SectionModel.class).registerReadListener(new SectionListener()).build();

        ReadSheet readSheet4 =
                EasyExcel.readSheet(3).head(QuestionModel.class).registerReadListener(new QuestionListener()).build();

        ReadSheet readSheet5 =
                EasyExcel.readSheet(4).head(OptionModel.class).registerReadListener(new OptionListener()).build();

        ReadSheet readSheet6 =
                EasyExcel.readSheet(5).head(ConditionModel.class).registerReadListener(new ConditionListener()).build();

        ReadSheet readSheet7 =
                EasyExcel.readSheet(6).head(QuestionTooltipModel.class).registerReadListener(new QuestionTooltipListener()).build();

        ReadSheet readSheet8 =
                EasyExcel.readSheet(7).head(OptionTooltipModel.class).registerReadListener(new OptionTooltipListener()).build();

        // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
        excelReader.read(readSheet1, readSheet2, readSheet3, readSheet4, readSheet5, readSheet6, readSheet7, readSheet8);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }


}
