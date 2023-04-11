import org.apache.log4j.Logger;

/**
 * @Author: crush
 * @Date: 2021-08-15 11:55
 * version 1.0
 */
public class LogTest {

    public static Logger log = Logger.getLogger(LogTest.class);

    public static void main(String[] args) {
        try {
            throw new Exception("最代码错误log/'/'/");
        } catch (Exception e) {
            // 这里测试的 将日志直接输出到数据库去。
            log.error(e.getMessage());
        }
    }

}
