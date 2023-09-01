package com.jimicloud.dgsjavaplayground;

import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.jimicloud.dgsjavaplayground.repositories")
class ApplicationConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String uri;


    @NotNull
    @Override
    protected String getDatabaseName() {
        return "dgs-java-playground";
    }

    @NotNull
    @Override
    public MongoClient mongoClient() {
        MongoClient client = MongoClients.create(uri);
        ListDatabasesIterable<Document> databases = client.listDatabases();
        databases.forEach(System.out::println);
        return client;
    }
}