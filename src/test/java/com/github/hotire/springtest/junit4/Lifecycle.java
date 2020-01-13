package com.github.hotire.springtest.junit4;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class Lifecycle {
  private int sum = 1;

  @Test
  public void addingTwoReturnsThree() {
    sum += 2;
    assertThat(sum).isEqualTo(3);
  }

  @Test
  public void addingThreeReturnsFour() {
    sum += 3;
    assertThat(sum).isEqualTo(4);
  }
}
