/* ----------------------------------------------------------------------------
 * File: CSVParser.java
 * Date: June 2nd, 2013
 * ----------------------------------------------------------------------------
 */
package stats.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import stats.core.Table;

/**
 * A very simple CSV parser released under a commercial-friendly license.
 * This just implements splitting a single line into fields.
 *
 * @author Glen Smith
 * @author Rainer Pruy
 */
public class CSVParser {

  /**
   * File to be parsed.
   */
  private File file;

  /**
   * Separator character, it separates different elements in the same row.
   */
  private char separator;

  /**
   * Escape character, it separates different rows.
   */
  private char escape;

  /**
   * Quote character, it is used to define the content of an element if
   * special character are used.
   */
  private char quotechar;

  private boolean strictQuotes;

  /**
   * Initial lines to be skipped before parsing.
   */
  private int linesToSkip;

  /**
   * Row headers parsing status variable. When true, first row will be parsed
   * as column headers.
   */
  private boolean parseRowHeaders;

  private String pending;

  private boolean inField = false;

  private final boolean ignoreLeadingWhiteSpace;

  /**
   * Default separator char.
   */
  public static final char DEFAULT_SEPARATOR = ',';

  /**
   * Default escape char.
   */
  public static final char DEFAULT_ESCAPE_CHARACTER = '\\';

  /**
   * Default quote char.
   */
  public static final char DEFAULT_QUOTE_CHARACTER = '"';

  /**
   * Default strict quote char.
   */
  public static final boolean DEFAULT_STRICT_QUOTES = false;

  public static final char NULL_CHARACTER = '\0';

  /**
   * Default constructor for CSVParser object. Constructs CSVParser using
   * a comma for the separator, a carriage return for escape and double quotes
   * for quoting.
   */
  public CSVParser() {
    this(DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER);
  }

  /**
   * Constructs CSVParser with supplied separator. By default escape character
   * is a carriage return and quoting by double quote character.
   *
   * @param separator the delimiter to use for separating elements.
   */
  public CSVParser(char separator) {
    this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER);
  }

  /**
   * Constructs CSVParser with supplied separator and quote char. By default
   * escape character is a carriage return.
   *
   * @param separator the delimiter to use for separating elements.
   * @param quotechar the character to use for quoted elements.
   */
  public CSVParser(char separator, char quotechar) {
    this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER);
  }

  /**
   * Constructs CSVReader with supplied separator, quote and escape char.
   *
   * @param separator the delimiter to use for separating elements.
   * @param quotechar the character to use for quoted elements.
   * @param escape the character to use for escaping a separator or quote.
   */
  public CSVParser(char separator, char quotechar, char escape) {
    this(separator, quotechar, escape, DEFAULT_STRICT_QUOTES);
  }

  /**
   * Constructs CSVReader with supplied separator and quote char. Allows
   * setting the "strict quotes" flag to ignore any character outside the
   * quotes.
   *
   * @param separator the delimiter to use for separating elements.
   * @param quotechar the character to use for quoted elements.
   * @param escape the character to use for escaping a separator or quote.
   * @param strictQuotes if true, characters outside the quotes are ignored.
   */
  public CSVParser(char separator, char quotechar,
          char escape, boolean strictQuotes) {
    this(separator, quotechar, escape, strictQuotes, true);
  }

  /**
   * Constructs CSVReader with supplied separator, quote and escape char.
   * Allows setting the "strict quotes" and "ignore leading whitespace" flags.
   *
   * @param separator the delimiter to use for separating elements.
   * @param quotechar the character to use for quoted elements.
   * @param escape the character to use for escaping a separator or quote.
   * @param strictQuotes if true, characters outside the quotes are ignored.
   * @param ignoreLeadingWhiteSpace if true, white space in front of a quote in
   * a field is ignored.
   */
  public CSVParser(char separator, char quotechar, char escape,
          boolean strictQuotes, boolean ignoreLeadingWhiteSpace) {
    // check if separator, quote and escape char are unique
    if (isSameCharacter(separator, quotechar)
            || isSameCharacter(separator, escape)
            || isSameCharacter(quotechar, escape))
      throw new UnsupportedOperationException(
              "The separator, quote and escape characters must be different.");
    // check if the separator is not defined
    if (separator == NULL_CHARACTER) throw new UnsupportedOperationException(
              "The separator character must be defined.");
    // assign all local variables
    this.separator = separator;
    this.quotechar = quotechar;
    this.escape = escape;
    this.strictQuotes = strictQuotes;
    this.ignoreLeadingWhiteSpace = ignoreLeadingWhiteSpace;
    this.linesToSkip = 0;
    this.parseRowHeaders = true;
  }

  /**
   * Checks if during last call line string was not fully tokenized.
   *
   * @return true if something was left over from last call, else false.
   */
  public boolean isPending() {
    return pending != null;
  }

  public boolean setFile(File file) {
    // check if given file exists and is not a directory
    if (!file.exists() || !file.isFile()) return false;
    // assign the parsing file
    this.file = file;
    return true;
  }

  public void setParseRowHeader(boolean state) {
    this.parseRowHeaders = state;
  }

  public boolean setLinesToSkip(int lines) {
    // check if argument is negative
    if (lines < 0) return false;
    // set number of lines to skip
    this.linesToSkip = lines;
    return true;
  }

  public File getFile() {
    return this.file;
  }

  public boolean getParseRowHeader() {
    return this.parseRowHeaders;
  }

  public int getLinesToSkip() {
    return linesToSkip;
  }

  public Table parseFile() throws IOException, ArrayIndexOutOfBoundsException {
    // initialize buffered reader for parsing the file
    BufferedReader br = new BufferedReader(new FileReader(this.file));
    // skip lines before parsing
    for (int i = 0; i < linesToSkip; i++)
      br.readLine();
    // parse first line to get columns number
    String line = br.readLine();
    String[] firstLine = parseLine(line, false);
    // create new table and add all columns
    Table table = new Table();
    table.addColumns(firstLine.length - Table.DEFAULT_COLS);
    // if row header parsing is enabled, rename all columns
    // else fill first row
    for (int i = 0; i < firstLine.length; i++)
    {
      if (this.parseRowHeaders) table.setColumnName(i, firstLine[i]);
      else table.set(i, 0, firstLine[i]);
    }
    // initialize variables for convenience
    int row = parseRowHeaders ? 0 : 1;
    String[] tokens;
    // parse remaining line in the file
    while ((line = br.readLine()) != null)
    {
      // parse current line and check if element number is equal to columns
      tokens = parseLine(line, false);
      if (tokens.length != table.columns()) throw new IOException(
                "Number of elements on line " + row
                + "not compatible with parsing.");
      // add new row at the end of the table and set elements
      table.addRow();
      for (int i = 0; i < tokens.length; i++)
        table.set(i, row, tokens[i]);
      // update row number
      row++;
    }
    // trim number of rows, close and return the table
    table.removeRows(row, table.rows() - row);
    br.close();
    return table;
  }

  public int parseNumberOfRows() throws IOException {
    // initialize buffered reader
    BufferedReader br = new BufferedReader(new FileReader(this.file));
    // skip lines before counting
    for (int i = 0; i < linesToSkip; i++)
      br.readLine();
    // count lines in the buffer
    int rowCounter = 0;
    while (br.readLine() != null)
      rowCounter++;
    br.close();
    // don't consider first row if row header parsing is enabled
    if (parseRowHeaders) rowCounter--;
    // return counted rows
    return rowCounter;
  }

  public int parseNumberOfColumns() throws IOException {
    // initialize buffered reader
    BufferedReader br = new BufferedReader(new FileReader(this.file));
    // skip lines before counting
    for (int i = 0; i < linesToSkip; i++)
      br.readLine();
    // parse first line in the buffer and return number of tokens
    String[] tokens = parseLine(br.readLine(), false);
    return tokens.length;
  }

  /**
   * Parses an incoming String and returns an array of elements.
   *
   * @param nextLine the string to parse
   * @param multi
   * @return the comma-tokenized list of elements, or null if nextLine is null
   * @throws IOException if bad things happen during the read
   */
  private String[] parseLine(String nextLine, boolean multi)
          throws IOException {

    // reset pending string if  multiline mode is not set
    if (!multi && pending != null) pending = null;
    // if input line is null return pending string
    if (nextLine == null)
    {
      if (pending != null)
      {
        String s = pending;
        pending = null;
        return new String[]
        {
          s
        };
      } else return null;
    }
    // initialize variable for parsing
    List<String> tokensOnThisLine = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    boolean inQuotes = false;
    // append pending string to current parsing
    if (pending != null)
    {
      sb.append(pending);
      pending = null;
      inQuotes = true;
    }
    // scroll the string character by character
    char c;
    for (int i = 0; i < nextLine.length(); i++)
    {
      // define current character for convenience
      c = nextLine.charAt(i);

      if (c == escape)
      {
        if (isNextCharacterEscapable(nextLine, inQuotes || inField, i))
        {
          sb.append(nextLine.charAt(i + 1));
          i++;
        }
      } else if (c == quotechar)
      {
        if (isNextCharacterEscapedQuote(nextLine, inQuotes || inField, i))
        {
          sb.append(nextLine.charAt(i + 1));
          i++;
        } else
        {
          //inQuotes = !inQuotes;

          // the tricky case of an embedded quote in the middle: a,bc"d"ef,g
          if (!strictQuotes)
          {
            if (i > 2 //not on the beginning of the line
                    && nextLine.charAt(i - 1) != this.separator //not at the beginning of an escape sequence
                    && nextLine.length() > (i + 1)
                    && nextLine.charAt(i + 1) != this.separator //not at the	end of an escape sequence
                    )
            {

              if (ignoreLeadingWhiteSpace && sb.length() > 0 && isAllWhiteSpace(sb))
              {
                sb.setLength(0);  //discard white space leading up to quote
              } else
              {
                sb.append(c);
                //continue;
              }

            }
          }

          inQuotes = !inQuotes;
        }
        inField = !inField;
      } else if (c == separator && !inQuotes)
      {
        tokensOnThisLine.add(sb.toString());
        sb.setLength(0); // start work on next token
        inField = false;
      } else
      {
        if (!strictQuotes || inQuotes)
        {
          sb.append(c);
          inField = true;
        }
      }
    }
    // line is done - check status
    if (inQuotes)
    {
      if (multi)
      {
        // continuing a quoted section, re-append newline
        sb.append("\n");
        pending = sb.toString();
        sb = null; // this partial content is not to be added to field list yet
      } else
      {
        throw new IOException("Un-terminated quoted field at end of CSV line");
      }
    }
    if (sb != null)
    {
      tokensOnThisLine.add(sb.toString());
    }
    return tokensOnThisLine.toArray(new String[tokensOnThisLine.size()]);

  }

  /**
   * precondition: the current character is a quote or an escape
   *
   * @param nextLine the current line
   * @param inQuotes true if the current context is quoted
   * @param i current index in line
   * @return true if the following character is a quote
   */
  private boolean isNextCharacterEscapedQuote(String nextLine, boolean inQuotes, int i) {
    return inQuotes // we are in quotes, therefore there can be escaped quotes in here.
            && nextLine.length() > (i + 1) // there is indeed another character to check.
            && nextLine.charAt(i + 1) == quotechar;
  }

  /**
   * precondition: the current character is an escape
   *
   * @param nextLine the current line
   * @param inQuotes true if the current context is quoted
   * @param i current index in line
   * @return true if the following character is a quote
   */
  protected boolean isNextCharacterEscapable(String nextLine, boolean inQuotes, int i) {
    return inQuotes // we are in quotes, therefore there can be escaped quotes in here.
            && nextLine.length() > (i + 1) // there is indeed another character to check.
            && (nextLine.charAt(i + 1) == quotechar || nextLine.charAt(i + 1) == this.escape);
  }

  /**
   * precondition: sb.length() > 0
   *
   * @param sb A sequence of characters to examine
   * @return true if every character in the sequence is whitespace
   */
  protected boolean isAllWhiteSpace(CharSequence sb) {
    boolean result = true;
    for (int i = 0; i < sb.length(); i++)
    {
      char c = sb.charAt(i);

      if (!Character.isWhitespace(c))
      {
        return false;
      }
    }
    return result;
  }

  /**
   * Compares two given characters for equality.
   *
   * @param c1 first character to be compared.
   * @param c2 second character to be compared.
   * @return true if two characters are equal.
   */
  private boolean isSameCharacter(char c1, char c2) {
    return c1 != NULL_CHARACTER && c1 == c2;
  }

}