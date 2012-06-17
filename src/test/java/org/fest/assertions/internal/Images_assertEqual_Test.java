/*
 * Created on Oct 21, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.data.Offset;
import org.fest.assertions.test.TestData;
import org.fest.test.TestFailures;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static java.awt.Color.BLUE;
import static org.fest.assertions.data.Offset.offset;
import static org.fest.assertions.data.Point.atPoint;
import static org.fest.assertions.error.ShouldBeEqualColors.shouldBeEqualColors;
import static org.fest.assertions.error.ShouldBeEqualImages.shouldBeEqualImages;
import static org.fest.assertions.error.ShouldHaveSize.shouldHaveSize;
import static org.fest.assertions.internal.Images.sizeOf;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Tests for <code>{@link Images#assertEqual(AssertionInfo, BufferedImage, BufferedImage)}</code>.
 *
 * @author Yvonne Wang
 */
public class Images_assertEqual_Test {

  private static BufferedImage actual;
  private static Offset<Integer> offset;

  @BeforeClass
  public static void setUpOnce() {
    actual = TestData.fivePixelBlueImage();
    offset = offset(0);
  }

  private Failures failures;
  private Images images;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    images = new Images();
    images.failures = failures;
  }

  @Test
  public void should_pass_if_images_are_equal() {
    images.assertEqual(TestData.someInfo(), actual, TestData.newImage(5, 5, BLUE));
  }

  @Test
  public void should_pass_if_images_are_same() {
    images.assertEqual(TestData.someInfo(), actual, actual);
  }

  @Test
  public void should_pass_if_both_images_are_null() {
    images.assertEqual(TestData.someInfo(), null, null);
  }

  @Test
  public void should_fail_if_actual_is_null_and_expected_is_not() {
    AssertionInfo info = TestData.someInfo();
    try {
      images.assertEqual(TestData.someInfo(), null, TestData.fivePixelBlueImage());
    }
    catch (AssertionError e) {
      verifyFailureThrownWhenImagesAreNotEqual(info);
      return;
    }
    TestFailures.failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_expected_is_null_and_actual_is_not() {
    AssertionInfo info = TestData.someInfo();
    try {
      images.assertEqual(TestData.someInfo(), actual, null);
    }
    catch (AssertionError e) {
      verifyFailureThrownWhenImagesAreNotEqual(info);
      return;
    }
    TestFailures.failBecauseExpectedAssertionErrorWasNotThrown();
  }

  private void verifyFailureThrownWhenImagesAreNotEqual(AssertionInfo info) {
    verify(failures).failure(info, shouldBeEqualImages(offset));
  }

  @Test
  public void should_fail_if_images_have_different_size() {
    AssertionInfo info = TestData.someInfo();
    BufferedImage expected = TestData.newImage(6, 6, BLUE);
    try {
      images.assertEqual(info, actual, expected);
    }
    catch (AssertionError e) {
      verify(failures).failure(info, shouldHaveSize(actual, sizeOf(actual), sizeOf(expected)));
      return;
    }
    TestFailures.failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_images_have_same_size_but_different_color() {
    AssertionInfo info = TestData.someInfo();
    BufferedImage expected = TestData.fivePixelYellowImage();
    try {
      images.assertEqual(info, actual, expected);
    }
    catch (AssertionError e) {
      verify(failures).failure(info, shouldBeEqualColors(TestData.yellow(), TestData.blue(), atPoint(0, 0), offset));
      return;
    }
    TestFailures.failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
