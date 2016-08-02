package com.cee.wsr.document.element;

import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Text;



public class ParagraphGenerator {
	private static ObjectFactory objectFactory = new ObjectFactory();
	
	public static P createP(String textValue) {
		P paragraph = objectFactory.createP();
		R run = objectFactory.createR();
		Text text = objectFactory.createText();
		text.setValue(textValue);
		paragraph.getContent().add(text);
		paragraph.getContent().add(run);
		return paragraph;
	}
}
