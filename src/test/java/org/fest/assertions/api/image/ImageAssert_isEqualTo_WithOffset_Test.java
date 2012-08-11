/*
 * Created on Jan 24, 2011
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.api.image;

import static org.fest.assertions.data.Offset.offset;
import static org.fest.assertions.test.AwtTestData.fivePixelYellowImage;
import static org.mockito.Mockito.verify;

import java.awt.image.BufferedImage;

import org.fest.assertions.api.ImageAssert;
import org.fest.assertions.api.ImageAssertBaseTest;
import org.fest.assertions.data.Offset;
import org.junit.BeforeClass;

/**
 * Tests for <code>{@link ImageAssert#isEqualTo(BufferedImage, Offset)}</code>.
 * 
 * @author Yvonne Wang
 */
public class ImageAssert_isEqualTo_WithOffset_Test extends ImageAssertBaseTest {

  private static Offset<Integer> offset;

  @BeforeClass
  public static void beforeOnce() {
    offset = offset(6);
  }

  private final BufferedImage expected = fivePixelYellowImage();

  @Override
  protected ImageAssert invoke_api_method() {
    return assertions.isEqualTo(expected, offset);
  }

  @Override
  protected void verify_internal_effects() {
    verify(images).assertEqual(getInfo(assertions), getActual(assertions), expected, offset);
  }
}
