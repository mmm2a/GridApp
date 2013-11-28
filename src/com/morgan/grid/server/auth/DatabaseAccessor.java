package com.morgan.grid.server.auth;

import java.text.DateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.google.inject.servlet.RequestScoped;

@RequestScoped
class DatabaseAccessor {

  private final EntityManager entityManager;

  @Inject DatabaseAccessor(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional void printEntries() {
    System.err.println("Listing entries:");
    for (AuthEntity entity : entityManager.createNamedQuery(
        "listAllEntries", AuthEntity.class).getResultList()) {
      System.err.println(entity);
    }
  }

  @Transactional void addEntry() {
    AuthEntity entity = new AuthEntity(DateFormat.getInstance().format(new Date()).toString());
    entityManager.persist(entity);
  }
}
