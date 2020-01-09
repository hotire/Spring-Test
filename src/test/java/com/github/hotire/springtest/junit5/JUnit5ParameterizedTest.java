package com.github.hotire.springtest.junit5;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.Month;
import java.util.EnumSet;
import java.util.stream.Stream;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class JUnit5ParameterizedTest {

  @ParameterizedTest
  @ValueSource(strings = {"", "  "})
  void isBlank(String input) {
    assertThat(input).isBlank();
  }

  @ParameterizedTest
  @EnumSource(Month.class)
  void isAlwaysBetweenOneAndTwelve(Month month) {
    final int monthNumber = month.getValue();
    assertThat(monthNumber).isBetween(1, 12);
  }

  @ParameterizedTest
  @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
  void someMonths_Are30DaysLong(Month month) {
    final boolean isALeapYear = false;
    assertThat(month.length(isALeapYear)).isEqualTo(30);
  }

  @ParameterizedTest
  @EnumSource(value = Month.class, names = ".+BER", mode = EnumSource.Mode.MATCH_ANY)
  void fourMonths_AreEndingWithBer(Month month) {
    final EnumSet<Month> months =
      EnumSet.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
    assertThat(months).contains(month);
  }

  @ParameterizedTest
  @MethodSource("provideStringsForIsBlank")
  void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {
    assertThat(Strings.isBlank(input)).isEqualTo(expected);
  }

  @ParameterizedTest
  @ArgumentsSource(CustomArgumentProvider.class)
  void isBlank_ShouldReturnTrueForNullOrBlankStringsArgProvider(String input, boolean expected) {
    assertThat(Strings.isBlank(input)).isEqualTo(expected);
  }

  private static Stream<Arguments> provideStringsForIsBlank() {
    return Stream.of(
      Arguments.of(null, true),
      Arguments.of("", true),
      Arguments.of("  ", true),
      Arguments.of("not blank", false)
    );
  }

  private static class CustomArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
      throws Exception {
      return Stream.of(
        Arguments.of((String) null),
        Arguments.of(""),
        Arguments.of("   ")
      );
    }
  }

}
