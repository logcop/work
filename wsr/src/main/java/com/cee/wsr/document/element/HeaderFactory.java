package com.cee.wsr.document.element;

import java.util.List;

import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Text;

public class HeaderFactory {
	private static ObjectFactory objectFactory = new ObjectFactory();
	
	public static final void addHeader(WordprocessingMLPackage wordMLPackage) {
		// OK, the guts of this sample:
		// The 2 things you need:
		// 1. the Header part
		Relationship relationship = null;
		try {
			relationship = createHeaderPart(wordMLPackage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2. an entry in SectPr
		try {
			createHeaderReference(wordMLPackage, relationship);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Relationship createHeaderPart(WordprocessingMLPackage wordprocessingMLPackage)
			throws Exception {

		HeaderPart headerPart = new HeaderPart();
		Relationship rel = wordprocessingMLPackage.getMainDocumentPart().addTargetPart(headerPart);

		// After addTargetPart, so image can be added properly
		headerPart.setJaxbElement(getHdr(wordprocessingMLPackage, headerPart));

		return rel;
	}

	private static void createHeaderReference(WordprocessingMLPackage wordprocessingMLPackage,
			Relationship relationship) throws InvalidFormatException {

		List<SectionWrapper> sections = wordprocessingMLPackage.getDocumentModel().getSections();

		SectPr sectPr = sections.get(sections.size() - 1).getSectPr();
		// There is always a section wrapper, but it might not contain a sectPr
		if (sectPr == null) {
			sectPr = objectFactory.createSectPr();
			wordprocessingMLPackage.getMainDocumentPart().addObject(sectPr);
			sections.get(sections.size() - 1).setSectPr(sectPr);
		}

		HeaderReference headerReference = objectFactory.createHeaderReference();
		headerReference.setId(relationship.getId());
		headerReference.setType(HdrFtrRef.DEFAULT);
		sectPr.getEGHdrFtrReferences().add(headerReference);// add header or
		// footer references

	}

	private static Hdr getHdr(WordprocessingMLPackage wordprocessingMLPackage, Part sourcePart)
			throws Exception {

		Hdr hdr = objectFactory.createHdr();
		
		hdr.getContent().add(createP("Some paragraph text..."));
		return hdr;

	}
	
	private static P createP(String textValue) {
		P paragraph = objectFactory.createP();
		R run = objectFactory.createR();
		Text text = objectFactory.createText();
		text.setValue(textValue);
		paragraph.getContent().add(text);
		paragraph.getContent().add(run);
		return paragraph;
	}

	 
}
