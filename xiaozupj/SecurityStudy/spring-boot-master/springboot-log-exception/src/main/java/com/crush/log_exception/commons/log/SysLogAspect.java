package com.crush.log_exception.commons.log;

import cn.hutool.core.lang.UUID;
import com.crush.log_exception.commons.annotation.MyLog;
import com.crush.log_exception.entity.LogOperation;
import com.crush.log_exception.entity.LogUser;
import com.crush.log_exception.mapper.LogOperationMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 系统日志：切面处理类
 * @author crush
 */
@Aspect
@Component
public class SysLogAspect {
	/**我这里是使用log4j2把一些信息打印在控制台上面，可以不写 */
    private static final Logger log = LogManager.getLogger(SysLogAspect.class);

	/**操作数据库 */
    @Autowired
    private LogOperationMapper logOperationMapper;

    /**
     *  定义切点 @Pointcut
     *  在注解的位置切入代码
     */
    @Pointcut("@annotation(com.crush.log_exception.commons.annotation.MyLog)")
    public void logPoinCut() {
    }

    /**
     * 切面 配置通知
     * @param joinPoint
     */
    @Before("logPoinCut()")         //AfterReturning
    public void saveOperation(JoinPoint joinPoint) {
        log.info("---------------接口日志记录---------------");

        //用于保存日志
        LogOperation logOperation = new LogOperation();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作--方法上的Log的值
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            //保存操作事件
            String operation = myLog.operation();

            logOperation.setOperation(operation);

            //保存日志类型
            int type = myLog.type();
            logOperation.setType(type);

            log.info("operation="+operation+",type="+type);
        }
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        String requestURL = request.getRequestURL().toString();
        logOperation.setUrl(requestURL);
        // 客户端ip
        String ip = request.getRemoteAddr();
        logOperation.setLoginIp(ip);
        // 操作人账号、姓名（需要提前将用户信息存到session）

        LogUser user = (LogUser) request.getSession().getAttribute("user");
        if(user != null) {
            String userId = user.getId();
            String userName = user.getUsername();
            logOperation.setUserId(userId);
            logOperation.setUsername(userName);
            System.out.println(user);
        }
        log.info("url="+requestURL,"ip="+ip);
       
        //调用service保存Operation实体类到数据库
        //我id使用的是UUID，不需要的可以注释掉
        String id = UUID.randomUUID().toString();
        logOperation.setId(id);
        logOperationMapper.insert(logOperation);
    }
}
