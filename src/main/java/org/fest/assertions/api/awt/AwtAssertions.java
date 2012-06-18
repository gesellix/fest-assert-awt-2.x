package org.fest.assertions.api.awt;

import org.fest.assertions.util.ImageReader;

import java.awt.image.BufferedImage;

import static org.fest.assertions.api.factories.AssertionFactoryServiceLoader.getAssertionFor;

public class AwtAssertions {

  /**
   * Creates a new instance of <code>{@link ImageAssert}</code>. To read an image from the file system use <code>{@link
   * ImageReader#readImageFrom(String)}</code>.
   *
   * @param actual the actual value.
   *
   * @return the created assertion object.
   */
  public static ImageAssert assertThat(BufferedImage actual) {
    return (ImageAssert) getAssertionFor(actual);
  }
}
