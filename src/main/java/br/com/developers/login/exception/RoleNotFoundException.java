package br.com.developers.login.exception;

public class RoleNotFoundException extends RuntimeException {
  public RoleNotFoundException(String msg) {
    super(msg);
  }
}
