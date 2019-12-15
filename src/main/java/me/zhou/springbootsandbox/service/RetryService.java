package me.zhou.springbootsandbox.service;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryService {

  @Retryable(
      maxAttempts = 1,
      include = {TestExceptionOne.class}
  )
  public String testExceptionOne() throws TestExceptionOne {
    throw new TestExceptionOne("Exception One");
  }

  @Retryable(
      maxAttempts = 1,
      include = {TestExceptionOne.class}
  )
  public String testExceptionOneWithParam(String str) throws TestExceptionOne {
    throw new TestExceptionOne("Exception One with param");
  }

  @Retryable(
      maxAttempts = 1,
      include = {TestExceptionTwo.class}
  )
  public String testExceptionTwo() throws TestExceptionTwo {
    throw new TestExceptionTwo("Exception Two");
  }

  @Recover
  public String recoverForExceptionOne(TestExceptionOne e) {
    return e.getMessage();
  }

  @Recover
  public String recoverForExceptionOneWithParam(TestExceptionOne e, String str) {
    return String.format("%s (str: %s)", e.getMessage(), str);
  }

  @Recover
  public String recoverForExceptionTwo(TestExceptionTwo e) {
    return e.getMessage();
  }

}
