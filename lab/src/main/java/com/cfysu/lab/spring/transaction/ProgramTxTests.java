package com.cfysu.lab.spring.transaction;

import org.junit.Test;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Author canglong
 * @Date 2023/2/27
 */
public class ProgramTxTests {

    private PlatformTransactionManager platformTransactionManager;

    @Test
    public void programTX() {
        String param2 = null;
        //方式一，通过手动设置回滚状态，通知事务管理器执行回滚操作
        new TransactionTemplate(platformTransactionManager).execute((status -> {
            try {
                return doSth(param2);
            } catch (Exception e) {
                status.setRollbackOnly();
            }
            return null;
        }));

        //方式二，通过抛出异常的方式，通知事务管理器执行回滚操作
        new TransactionTemplate(platformTransactionManager).execute((status -> doSth(param2)));
    }

    private String doSth(String param1) {
        throw new RuntimeException("transaction rollback");
    }
}
