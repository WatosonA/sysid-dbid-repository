package com.example.sysid;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import com.github.springtestdbunit.dataset.DataSetLoader;

/**
 * テスト用のBean設定等.
 */
@TestConfiguration
public class TestConfigration {

    @Bean
    DatabaseDataSourceConnection dbUnitDatabaseConnection(DataSource dataSource) throws Exception {
        DatabaseDataSourceConnectionFactoryBean bean = new DatabaseDataSourceConnectionFactoryBean();

        bean.setDataSource(dataSource);

        DatabaseConfigBean config = new DatabaseConfigBean();
        config.setCaseSensitiveTableNames(false);
        config.setQualifiedTableNames(false);
        config.setMetadataHandler(new MySqlMetadataHandler());
        config.setDatatypeFactory(new MySqlDataTypeFactory());
        config.setTableType(new String[] { "TABLE", });
        bean.setDatabaseConfig(config);

        return bean.getObject();
    }

    /**
     * CSVでDBUnitのDatabaseSetupを可能にするBean.
     * ※多くのテーブルを扱う場合はCSVに外出ししておいたほうがよいか
     * @return CSVデータセットローダー
     */
    @Bean
    DataSetLoader csvDataSetLoader() {
        return new AbstractDataSetLoader() {
            @Override
            protected IDataSet createDataSet(Resource resource) throws Exception {
                return new CsvURLDataSet(resource.getURL());
            }
        };
    }

}
