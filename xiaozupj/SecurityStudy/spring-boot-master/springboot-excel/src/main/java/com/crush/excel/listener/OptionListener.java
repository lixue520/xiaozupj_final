package com.crush.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.crush.excel.mapper.QuestionMapper;
import com.crush.excel.pojo.OptionModel;
import com.crush.excel.pojo.QuestionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 有个很重要的点 DocumentListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 * @author crush
 */
public class OptionListener extends AnalysisEventListener<OptionModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionListener.class);
    private static Integer count=0;
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 10;

    List<OptionModel> list = new ArrayList<OptionModel>();

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(OptionModel data, AnalysisContext context) {
        count++;
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！"+count);
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
//       操作数据库
        LOGGER.info("存储数据库成功！");
    }
}