package com.github.hotire.springtest.mockito;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.github.hotire.springtest.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class ArgumentCaptorTest {

  private User user;
  private ArgumentCaptor<Integer> argumentCaptor;


  @Before
  public void config() {
    user = mock(User.class);
    argumentCaptor = ArgumentCaptor.forClass(Integer.class);
  }


  @Test
  public void capture() {
    // given
    final Integer age = 20;

    // when
    user.setAge(age);

    // then
    verify(user, times(1)).setAge(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue()).isEqualTo(age);
  }
}
