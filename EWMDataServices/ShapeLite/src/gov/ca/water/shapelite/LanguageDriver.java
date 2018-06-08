/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite;

/**
 * The language driver is a byte code that stores the language to use for
 * characters. This is stored in the 28th Byte offset in a DBF file and gives
 * some understanding of how to parse the characters. Since Unicode characters
 * were not used in DBF, that means that the characters in a dbf file can only
 * have 256 possible variants. 128 of those are consistently defined by the
 * ASCII character set. The value here controls what the top 128 characters are.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum LanguageDriver {

  /**
   * Back in the DOS days, separate Original Equipment Manufacturer code pages
   * were created so that text-mode PCs could display and print line-drawing
   * characters. They're still used today for direct FAT access, and for
   * accessing data files created by MS-DOS based applications. OEM code pages
   * typically have a 3-digit label, such as CP 437 for American English.
   */
  OEM(0),
  /**
   * Windows programs typically use a different character set than do DOS
   * programs, or programs that run in a Win32 console environment. The DOS
   * character set is known as the DOS/OEM character set, and includes various
   * line drawing characters and miscellaneous characters not in the
   * Windows/ANSI set.
   */
  DOS(0x01),
  /**
   * Similar to DOS, but with international characters.
   */
  International_DOS(0x02),
  /**
   * American Nation Standards Institute, windows character set. You can think
   * of Windows ANSI as a lower 128, and an upper 128. The lower 128 is
   * identical to ASCII, and the upper 128 is different for each ANSI character
   * set, and is where the various international characters are parked.
   */
  Windows_ANSI(0x03),
  /**
   * Since Windows GDI overrides the need for text-based line draw characters,
   * the old OEM line-draw characters could be freed up for something more
   * useful, like international characters and publishing symbols. An assortment
   * of 256-character Windows ANSI character sets cover all the 8-bit languages
   * targeted by Windows.
   */
  ANSI(0x57),
  /**
   * DOS - Greek.
   */
  Greek_MSDOS(0x6A),
  /**
   * Microsoft Disk Operating System - Eastern Europe.
   */
  EasternEuropean_MSDOS(0x64),
  /**
   * Microsoft Disk Operating System - Turkish.
   */
  Turkish_MSDOS(0x6B),
  /**
   * Microsoft Disk Operating System - Icelandic.
   */
  Icelandic_MSDOS(0x67),
  /**
   * Microsoft Disk Operating System - Nordic.
   */
  Nordic_MSDOS(0x66),
  /**
   * Microsoft Disk Operating System - Russian.
   */
  Russian_MSDOS(0x65),
  /**
   * Microsoft Windows - Chinese 1.
   */
  Chinese_Windows(0x78),
  /**
   * Microsoft Windows - Chinese 2.
   */
  Chinese_Windows2(0x7A),
  /**
   * Microsoft Windows - Japanese.
   */
  Japanese_Windows(0x7B),
  /**
   * Microsoft Windows - Hebrew.
   */
  Hebrew_Windows(0x7D),
  /**
   * Microsoft Windows - Arabic.
   */
  Arabic_Windows(0x7E),
  /**
   * Microsoft Windows - Easter Europe.
   */
  Eastern_European_Windows(0xC8),
  /**
   * Microsoft Windows - Russian.
   */
  Russian_Windows(0xC9),
  /**
   * Microsoft Windows - Turkish.
   */
  Turkish_Windows(0xCA),
  /**
   * Microsoft Windows - Greek.
   */
  Greek_Windows(0xCB);

  /**
   * the internal integer code value.
   */
  private final int code;

  /**
   * Gets the integer code representation of the language driver.
   *
   * @return the integer code.
   */
  public int code() {
    return code;
  }

  /**
   * Gets a new LanguageDriver enumeration with the integer value specified.
   *
   * @param value The integer value.
   */
  LanguageDriver(int value) {
    this.code = value;
  }

  /**
   * Returns the LanguageDriver based on the serialized value.
   *
   * @param value The byte value from the dbf file to parse.
   * @return The LanguageDriver enum corresponding to the specified byte value.
   */
  public static Optional<LanguageDriver> find(byte value) {
    for (LanguageDriver d : LanguageDriver.values()) {
      if (d.code() == value) {
        return Optional.of(d);
      }
    }
    return Optional.empty();
  }
}
