package com.rajarajanr.gedcom.parse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.rajarajanr.gedcom.exception.TokenizerException;
import com.rajarajanr.gedcom.exception.XMLCreationException;

/**
 * <p>
 * <b>Title: </b>ParserTest.java
 * </p>
 * <p>
 * <b>Description: </b> Test class to test the GEDCOMParser
 * </p>
 * <p>
 * <b>@author Originator:</b> rrajendran
 * <p>
 * <br>
 * 
 */
@RunWith(JUnit4.class)
public class ParserTest {
	@Test
	public void completeTestNoInputFilePath() {
		GEDCOMParser.main(new String[] {});
		File outFile = new File(GEDCOMParser.OUTPUT_XML);
		assertFalse("Expected false", outFile.exists());

	}

	@Test
	public void completeTestInvalidInput() {
		final String oFile = "completeTestInvalidInput.tst";
		final String iFile = "sampleInputTest.tst";
		final String fileContent = "0 @I0001@ INDI\n6 NAME Elizabeth Alexandra\n";
		final byte[] fileContentBytes = fileContent.getBytes();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(iFile));
			fos.write(fileContentBytes);
		} catch (IOException e) {
			System.out.println("IO Error While creating test file");
		} finally {
			try {
				if (null != fos) {
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				System.out.println("Error while closing resources");
			}
		}
		GEDCOMParser.main(new String[] { iFile, oFile });
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(oFile),
					Charset.forName("UTF-8"));
		} catch (IOException e) {
			fail("Unexpected exception while reading file\n" + e.getMessage());
		}
		assertEquals("Unexpected content", "<gedcom><INDI id=\"@I0001@\"",
				lines.get(0));

	}

	@Test
	public void completeTest() {
		final String iFile = "sampleInputTest.tst";
		final String oFile = "completeTest.tst";
		final String fileContent = "0 @I0001@ INDI\n1 NAME Elizabeth Alexandra Mary /Windsor/\n1 SEX F\n1 BIRT\n2 DATE 21 Apr 1926\n2 PLAC 17 Bruton Street, London, W1\n1 OCCU Queen\n1 FAMC @F0003@\n1 FAMS @F0001@\n1 NOTE @N0002@\n1 CHAN\n2 DATE 13 Dec 2003\n0 @I0002@ INDI\n1 NAME Philip /Mountbatten/\n1 SEX M\n1 BIRT\n2 DATE 1921\n1 TITL Duke of Edinburgh\n1 FAMC @F0002@\n1 FAMS @F0001@\n1 NOTE @N0001@\n1 CHAN\n2 DATE  6 Mar 2004\n";
		final byte[] fileContentBytes = fileContent.getBytes();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(iFile));
			fos.write(fileContentBytes);
		} catch (IOException e) {
			System.out.println("IO Error While creating test file");
		} finally {
			try {
				if (null != fos) {
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				System.out.println("Error while closing resources");
			}
		}
		GEDCOMParser.main(new String[] { iFile, oFile });
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(oFile),
					Charset.forName("UTF-8"));
		} catch (IOException e) {
			fail("Unexpected exception while reading file\n" + e.getMessage());
		}
		assertEquals(
				"Generated XML does not match with expected",
				"<gedcom><INDI id=\"@I0001@\"><NAME>Elizabeth Alexandra Mary /Windsor/</NAME><SEX>F</SEX><BIRT><DATE>21 Apr 1926</DATE><PLAC>17 Bruton Street, London, W1</PLAC></BIRT><OCCU>Queen</OCCU><FAMC>@F0003@</FAMC><FAMS>@F0001@</FAMS><NOTE>@N0002@</NOTE><CHAN><DATE>13 Dec 2003</DATE></CHAN></INDI><INDI id=\"@I0002@\"><NAME>Philip /Mountbatten/</NAME><SEX>M</SEX><BIRT><DATE>1921</DATE></BIRT><TITL>Duke of Edinburgh</TITL><FAMC>@F0002@</FAMC><FAMS>@F0001@</FAMS><NOTE>@N0001@</NOTE><CHAN><DATE>6 Mar 2004</DATE></CHAN></INDI></gedcom>",
				lines.get(0));

	}

	@Test
	public void readFile() {
		final String path = "readFileTest.tst";
		final String fileContent = "1 tag-or-id [data]\n2 tag-or-id [data]";
		final byte[] fileContentBytes = fileContent.getBytes();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(path));
			fos.write(fileContentBytes);
		} catch (IOException e) {
			System.out.println("IO Error While creating test file");
		} finally {
			try {
				if (null != fos) {
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				System.out.println("Error while closing resources"
						+ e.getMessage());
			}
		}
		List<String> lines = GEDCOMParser.readFile(path);
		assertNotNull("readFile failed", lines);
		assertEquals("mismatch in the number of read lines", 2, lines.size());
	}

	@Test
	public void tokenizeLine() {
		final String expectedTokens[] = { "1", "NAME",
				"Elizabeth Alexandra Mary /Windsor/" };
		final String line = "1 NAME Elizabeth Alexandra Mary /Windsor/";
		try {

			Assert.assertArrayEquals("error while tokenizing line contents",
					expectedTokens, GEDCOMParser.tokenizeLine(line));
		} catch (TokenizerException e) {
			fail("error while tokenizing line contents");
		}
	}

	@Test
	public void tokenizeLineNullParam() {
		try {
			GEDCOMParser.tokenizeLine(null);
			fail("error while tokenizing line contents");
		} catch (TokenizerException e) {
			assertEquals("Input String Null", e.getMessage());
		}
	}

	@Test
	public void tokenizeLineEmptyParam() {
		try {
			GEDCOMParser.tokenizeLine("");
			fail("error while tokenizing line contents");
		} catch (TokenizerException e) {
			assertEquals("Input not well formed", e.getMessage());
		}
	}

	@Test
	public void tokenizeLineWithID() {
		final String expectedTokens[] = { "0", "@I1@", "INDI" };
		final String line = "0 @I1@ INDI";
		try {
			Assert.assertArrayEquals(
					"error while tokenizing line with Unique Identifier",
					expectedTokens, GEDCOMParser.tokenizeLine(line));
		} catch (TokenizerException e) {
			fail("error while tokenizing line with Unique Identifier");
		}
	}

	@Test
	public void tokenizeLineWithIllegalID() {
		final String line = "0 @I1 INDI";
		try {

			GEDCOMParser.tokenizeLine(line);
			fail("Should not tokenize, input has Illegal Unique Identifier");
		} catch (TokenizerException e) {
			assertEquals("Input not well formed", e.getMessage());
		}
	}

	@Test
	public void tokenizeLineIllegalLevel() {
		final String line = "A NAME Elizabeth Alexandra Mary /Windsor/";
		try {
			GEDCOMParser.tokenizeLine(line);
			fail("Should not tokenize, input has illegal level");
		} catch (TokenizerException e) {
			assertEquals("Input not well formed", e.getMessage());
		}
	}

	@Test
	public void tokenizeLineIllegalTag() {
		final String line = "1 NAMES Elizabeth Alexandra Mary /Windsor/";
		try {
			GEDCOMParser.tokenizeLine(line);
			fail("Should not tokenize, input has illegal Tag");
		} catch (TokenizerException e) {
			assertEquals("Input not well formed", e.getMessage());
		}
	}

	@Test
	public void tokenizeLineIllegalTagLowercase() {
		final String line = "1 Name Elizabeth Alexandra Mary /Windsor/";
		try {
			GEDCOMParser.tokenizeLine(line);
			fail("Should not tokenize, input has illegal Tag");
		} catch (TokenizerException e) {
			assertEquals("Input not well formed", e.getMessage());
		}
	}

	@Test
	public void tokenizeLineNoData() {
		final String expectedTokens[] = { "1", "NAME", "" };
		final String line = "1 NAME";
		try {
			Assert.assertArrayEquals(
					"error while tokenizing line contents when there is no [data]",
					expectedTokens, GEDCOMParser.tokenizeLine(line));
		} catch (TokenizerException e) {
			fail("error while tokenizing line contents when there is no [data]");
		}
	}

	@Test
	public void tokenizeAllLinesNullParam() {
		try {
			GEDCOMParser.tokenizeAllLines(null);
			fail("error while tokenizing line contents");
		} catch (TokenizerException e) {
			assertEquals("Input list Null", e.getMessage());
		}
	}

	@Test
	public void tokenizeAllLinesEmptyParam() {
		try {
			assertEquals("Empty list expected", 0,
					(GEDCOMParser.tokenizeAllLines(new ArrayList<String>())
							.size()));
		} catch (TokenizerException e) {
			assertEquals("Unexpected Exception", e.getMessage());
		}
	}

	@Test
	public void tokenizeAllLines() {
		List<String> lines = new ArrayList<String>();
		lines.add("0 @I0001@ INDI");
		lines.add("1 NAME Elizabeth Alexandra Mary /Windsor/");
		String[] line1 = { "0", "@I0001@", "INDI" };
		String[] line2 = { "1", "NAME", "Elizabeth Alexandra Mary /Windsor/" };
		try {
			List<String[]> tokenizedLines = GEDCOMParser
					.tokenizeAllLines(lines);
			assertNotNull("Returned List null", tokenizedLines);
			Assert.assertArrayEquals(line1, tokenizedLines.get(0));
			Assert.assertArrayEquals(line2, tokenizedLines.get(1));
		} catch (TokenizerException e) {
			fail("Unexpected Exception");
		}
	}

	@Test
	public void isNewSubtreeNullId() {
		try {
			assertFalse("Expected False", GEDCOMParser.isNewSubTree(null));
		} catch (NullPointerException e) {
			fail("Unexpected Exception");
		}
	}

	@Test
	public void isNewSubtreeEmptyId() {
		assertFalse("Expected False", GEDCOMParser.isNewSubTree(""));
	}

	@Test
	public void isNewSubtreeInvalidId() {
		assertFalse("Expected false", GEDCOMParser.isNewSubTree("I12"));
	}

	@Test
	public void isNewSubtreeInvalidId2() {
		assertFalse("Expected False", GEDCOMParser.isNewSubTree("@I12"));
	}

	@Test
	public void isNewSubtreeInvalidId3() {
		assertFalse("Expected False", GEDCOMParser.isNewSubTree("I12@"));
	}

	@Test
	public void isNewSubtreeValidId() {
		assertTrue("Expected True", GEDCOMParser.isNewSubTree("@I12@"));
	}

	@Test
	public void createXMLSingleTree() {
		final String oFile = "createXMLSingleTree.tst";
		List<String[]> tokenizedList = new ArrayList<String[]>();
		tokenizedList.add(stringArray("0", "@I1@", "INDI"));
		tokenizedList.add(stringArray("1", "NAME", "Jamis Gordon /Buck/"));
		tokenizedList.add(stringArray("2", "SURN", "Buck"));
		tokenizedList.add(stringArray("2", "GIVN", "Jamis Gordon"));
		tokenizedList.add(stringArray("1", "SEX", "M"));
		try {
			GEDCOMParser.createXML(tokenizedList, oFile);
		} catch (XMLCreationException e1) {
			fail("Unexpected Excepton");
		}
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(oFile),
					Charset.forName("UTF-8"));
		} catch (IOException e) {
			fail("Unexpected exception while reading file");
		}
		assertEquals(
				"Generated XML does not match with expected",
				"<gedcom><INDI id=\"@I1@\"><NAME>Jamis Gordon /Buck/<SURN>Buck</SURN><GIVN>Jamis Gordon</GIVN></NAME><SEX>M</SEX></INDI></gedcom>",
				lines.get(0));
	}

	@Test
	public void createXMLMultipleTree() {
		final String oFile = "createXMLMultipleTree.tst";
		List<String[]> tokenizedList = new ArrayList<String[]>();
		tokenizedList.add(stringArray("0", "@I1@", "INDI"));
		tokenizedList.add(stringArray("1", "NAME", "Jamis Gordon /Buck/"));
		tokenizedList.add(stringArray("2", "SURN", "Buck"));
		tokenizedList.add(stringArray("2", "GIVN", "Jamis Gordon"));
		tokenizedList.add(stringArray("1", "SEX", "M"));
		tokenizedList.add(stringArray("0", "@I1@", "INDI"));
		tokenizedList.add(stringArray("1", "NAME", "Jamis Gordon /Buck/"));
		tokenizedList.add(stringArray("2", "SURN", "Buck"));
		tokenizedList.add(stringArray("2", "GIVN", "Jamis Gordon"));
		tokenizedList.add(stringArray("1", "SEX", "M"));
		try {
			GEDCOMParser.createXML(tokenizedList, oFile);
		} catch (XMLCreationException e) {
			fail("Unexpected Exception\n" + e.getMessage());
		}
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(oFile),
					Charset.forName("UTF-8"));
		} catch (IOException e) {
			fail("Unexpected exception while reading file\n" + e.getMessage());
		}
		assertEquals(
				"Generated XML does not match with expected",
				"<gedcom><INDI id=\"@I1@\"><NAME>Jamis Gordon /Buck/<SURN>Buck</SURN><GIVN>Jamis Gordon</GIVN></NAME><SEX>M</SEX></INDI><INDI id=\"@I1@\"><NAME>Jamis Gordon /Buck/<SURN>Buck</SURN><GIVN>Jamis Gordon</GIVN></NAME><SEX>M</SEX></INDI></gedcom>",
				lines.get(0));
	}

	@Test
	public void createXMLMultipleTreeInvalidLevel() {
		final String oFile = "createXMLMultipleTreeInvalidLevel.tst";
		List<String[]> tokenizedList = new ArrayList<String[]>();
		tokenizedList.add(stringArray("0", "@I1@", "INDI"));
		tokenizedList.add(stringArray("1", "NAME", "Jamis Gordon /Buck/"));
		tokenizedList.add(stringArray("2", "SURN", "Buck"));
		tokenizedList.add(stringArray("2", "GIVN", "Jamis Gordon"));
		tokenizedList.add(stringArray("1", "SEX", "M"));
		tokenizedList.add(stringArray("0", "@I1@", "INDI"));
		tokenizedList.add(stringArray("1", "NAME", "Jamis Gordon /Buck/"));
		tokenizedList.add(stringArray("2", "SURN", "Buck"));
		tokenizedList.add(stringArray("2", "GIVN", "Jamis Gordon"));
		tokenizedList.add(stringArray("0", "SEX", "M"));
		try {
			GEDCOMParser.createXML(tokenizedList, oFile);
		} catch (XMLCreationException e) {
			assertEquals(
					"Failed for the wrong reason,Expected XMLCreationException",
					"Invalid level in input file", e.getMessage());
		}
	}

	private String[] stringArray(String level, String tagOrId, String data) {
		String[] strArr = { level, tagOrId, data };
		return strArr;
	}
}
