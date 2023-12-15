package com.lingh;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import com.lingh.commons.AbstractShardingCommonTest;
import com.lingh.commons.FileTestUtils;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class EtcdTest {

    private AbstractShardingCommonTest abstractShardingCommonTest;

    @SuppressWarnings({"resource"})
    @Test
    void assertShardingInLocalTransactions() throws SQLException, IOException {
        try (
                GenericContainer<?> etcdImage = new GenericContainer<>(DockerImageName.parse("quay.io/coreos/etcd:v3.5.11"))
                        .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(62391), new ExposedPort(2379)))))) {
            etcdImage.start();
            DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(FileTestUtils.readFromFileURLString("etcd.yaml"));
            abstractShardingCommonTest = new AbstractShardingCommonTest(dataSource);
            this.initEnvironment();
            abstractShardingCommonTest.processSuccess();
            abstractShardingCommonTest.cleanEnvironment();
        }
    }

    private void initEnvironment() throws SQLException {
        abstractShardingCommonTest.getOrderRepository().createTableIfNotExistsInMySQL();
        abstractShardingCommonTest.getOrderItemRepository().createTableIfNotExistsInMySQL();
        abstractShardingCommonTest.getAddressRepository().createTableIfNotExists();
        abstractShardingCommonTest.getOrderRepository().truncateTable();
        abstractShardingCommonTest.getOrderItemRepository().truncateTable();
        abstractShardingCommonTest.getAddressRepository().truncateTable();
    }
}
