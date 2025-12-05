package org.taobao.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("org.taobao.mapper")
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        
        // 配置MyBatis的属性
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // 开启自动驼峰命名转换：将数据库下划线命名（如address_id）转换为Java驼峰命名（如addressId）
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);
        
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        
        return sessionFactory.getObject();
    }
}