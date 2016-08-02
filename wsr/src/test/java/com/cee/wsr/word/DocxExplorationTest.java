package com.cee.wsr.word;

import java.io.File;

import junit.framework.TestCase;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocxExplorationTest extends TestCase {
	private static final Logger LOG = LoggerFactory.getLogger(DocxExplorationTest.class);
	private static final String WrsPath = System.getProperty("user.dir") + "/JIRA.docx";

	public void testCrap() {
		try {
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
			MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
			// Example 1: add text in Title style
			mdp.addStyledParagraphOfText("Title", "Example 1");
			try {
				wordMLPackage.save(new File(WrsPath));
			} catch (Docx4JException d4je) {
				LOG.error("Unable to save document - " + WrsPath, d4je);
			}
		} catch (InvalidFormatException ife) {
			LOG.error("Unable to create document.", ife);
		}
	}
}
