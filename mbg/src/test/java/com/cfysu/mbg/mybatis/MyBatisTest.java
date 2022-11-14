package com.cfysu.mbg.mybatis;

import java.io.InputStream;
import java.util.Date;

import com.alibaba.alime.shop.dataobject.plugin.model.ApaasAppRobotPlugins;
import com.alibaba.alime.shop.mapper.plugin.mapper.ApaasAppRobotPluginsMapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2022/10/25
 */
public class MyBatisTest {

    private SqlSession session;

    private ApaasAppRobotPluginsMapper mapper;

    @Before
    public void before() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 1.SqlSessionFatoryBuilder，根据全局配置文件创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 2.SqlSessionFatory，在SQLSessionFactory中拿到一个SqlSession
        session = sqlSessionFactory.openSession(true);
        // 3.SqlSession，通过接口类拿到相应Mapper
        // 注：1.mapper.xml的namespace=接口相对路径
        //     2.mapper.xml的statementId=接口方法名
        mapper = session.getMapper(ApaasAppRobotPluginsMapper.class);
    }

    @Test
    public void testInsert() {
        try {

            ApaasAppRobotPlugins apaasAppRobotPlugins = new ApaasAppRobotPlugins();
            apaasAppRobotPlugins.setId(null);
            apaasAppRobotPlugins.setPluginCode("NER");
            apaasAppRobotPlugins.setGmtCreate(new Date());
            apaasAppRobotPlugins.setGmtModify(new Date());
            apaasAppRobotPlugins.setCreateUserId(165295);
            apaasAppRobotPlugins.setModifyUserId(165295);
            apaasAppRobotPlugins.setPluginGroup("DEFAULT");
            apaasAppRobotPlugins.setPluginName("ner");
            apaasAppRobotPlugins.setPluginDesc("命名实体识别");
            apaasAppRobotPlugins.setStatus(0);

            // 4.Mapper，通过Mapper调用mapper.xml中的sql
            int insert = mapper.insert(apaasAppRobotPlugins);
        } finally {
            // 5.关闭当前会话（连接）SqlSesion
            session.close();
        }
    }
}
