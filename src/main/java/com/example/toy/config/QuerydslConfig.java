package com.example.toy.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@Slf4j
public class QuerydslConfig {

  @PersistenceContext
  private EntityManager entityManager;

  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    log.info("QUERYDSLCONFIG CHECK");
    return new JPAQueryFactory(entityManager);
  }
}