package com.morgan.grid.server.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.google.common.base.Objects;

@NamedQueries({
  @NamedQuery(
      name = "listAllEntries",
      query = "SELECT a FROM AuthEntity AS a")
})
@Entity
public class AuthEntity {

  @Id @GeneratedValue
  private long id;

  @Column(length = 128, nullable = false)
  private String timestamp;

  AuthEntity() {
  }

  public AuthEntity(String timestamp) {
    this.timestamp = timestamp;
  }

  @Override public String toString() {
    return Objects.toStringHelper(AuthEntity.class)
        .add("id", id)
        .add("timestamp", timestamp)
        .toString();
  }
}
