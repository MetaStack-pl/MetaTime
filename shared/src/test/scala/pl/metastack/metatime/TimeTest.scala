package pl.metastack.metatime

import minitest.SimpleTestSuite

object TimeTest extends SimpleTestSuite with TestIgnored {
  import Implicits._

  testIgnored("construct") {
    assertEquals(Time(23, 10), ???)
  }

  test("implicits") {
    assertEquals(Hour(23), 23.hour)
  }

  testIgnored("hour") {
    assertEquals(Hour(25).days, Days(1, 1))
    assertEquals(Hour(25).days.truncate, Day(1))
  }

  testIgnored("operators") {
    assertEquals(Time(23, 10) > Time(22, 0), true)
    assertEquals(Time(23, 10) > Hour(22), true)
  }

  testIgnored("now") {
    assertEquals(Time.now(), ???)
  }

  testIgnored("locale") {
    implicit val locale = Locale.English.America
    assertEquals(Time(23).format, "11 pm")
  }

  testIgnored("fromNow") {
    assertEquals(Minute(5).fromNow, ???)
  }
}