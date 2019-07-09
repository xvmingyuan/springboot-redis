package com.xmy.common.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "spring.redis.cluster")
@PropertySource("classpath:application.properties")
public class RedisConfigurationProperties {
    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

    List<String> nodes = new ArrayList<>();
}
