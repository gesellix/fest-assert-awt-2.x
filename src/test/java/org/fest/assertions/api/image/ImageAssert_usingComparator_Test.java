/*
 * Created on Nov 29, 2010
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

import static org.fest.test.ExpectedException.none;
import static org.mockito.MockitoAnnotations.initMocks;

import java.awt.image.BufferedImage;
import java.util.Comparator;

import org.fest.assertions.api.ImageAssert;
import org.fest.assertions.api.ImageAssertBaseTest;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Tests for <code>{@link ImageAssert#usingComparator(java.util.Comparator)}</code>.
 * 
 * @author Joel Costigliola
 */
public class ImageAssert_usingComparator_Test extends ImageAssertBaseTest {

  @Rule
  public ExpectedException thrown = none();

  @Mock
  private Comparator<BufferedImage> comparator;

  @Before
  public void before() {
    initMocks(this);
  }

  @Override
  @Test
  public void should_have_internal_effects() {
    thrown.expect(UnsupportedOperationException.class);
    // in that, we don't care of the comparator, the point to check is that we can't use a comparator
    assertions.usingComparator(comparator);
  }

  @Override
  @Test
  public void should_return_this() {
    // Disabled since this method throws an exception
  }

  @Override
  protected ImageAssert invoke_api_method() {
    // Not used in this test
    return null;
  }

  @Override
  protected void verify_internal_effects() {
    // Not used in this test
  }
}
