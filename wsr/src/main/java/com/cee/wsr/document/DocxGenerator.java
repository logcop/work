package com.cee.wsr.document;

import java.io.File;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.PPrBase.Spacing;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cee.wsr.domain.Project;
import com.cee.wsr.domain.Sprint;
import com.cee.wsr.domain.StatusReport;
import com.cee.wsr.domain.JiraIssue;
import com.cee.wsr.utils.DateUtil;

@Component
public class DocxGenerator {
	private static final Logger LOG = LoggerFactory.getLogger(DocxGenerator.class);
	private static final String WrsPath = System.getProperty("user.dir") + "/JIRA.docx";

	/*public void generateDocument(StatusReport statusReport) {
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
	}*/
	
	private static ObjectFactory objectFactory = new ObjectFactory();
	
	public void generateDocument(StatusReport statusReport) {
		try {
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
			//MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
			
			// TODO: move below into an "add" function			
			// Delete the Styles part, since it clutters up our output
			//Relationship styleRel = mdp.getStyleDefinitionsPart().getSourceRelationships().get(0);
			//mdp.getRelationshipsPart().removeRelationship(styleRel);
			
			
			try {
				addHeader(wordMLPackage, statusReport.getTitle(), statusReport.getWeekEndingDate());
			} catch (Exception e) {
				e.printStackTrace();
			}		
			addSprint(wordMLPackage, statusReport.getSprint()); 
			addProjects(wordMLPackage, statusReport.getProjects());
			try {
				wordMLPackage.save(new File(WrsPath));
				// Display the result as Flat OPC XML
				/*FlatOpcXmlCreator worker = new FlatOpcXmlCreator(wordMLPackage);
				worker.marshal(System.out);*/
			} catch (Docx4JException d4je) {
				LOG.error("Unable to save document - " + WrsPath, d4je);
			}
		} catch (InvalidFormatException ife) {
			LOG.error("Unable to create document.", ife);
		}
	}
	

	public static final void addHeader(WordprocessingMLPackage wordMLPackage, String title, Date weekEndingDate) throws Exception{
		// the Header part
		Relationship relationship = createHeaderPart(wordMLPackage, title, weekEndingDate);		
		// an entry in SectPr
		createHeaderReference(wordMLPackage, relationship);		
	}
	
	
	private static Relationship createHeaderPart(WordprocessingMLPackage wordprocessingMLPackage, String title, Date weekEndingDate)
			throws Exception {

		HeaderPart headerPart = new HeaderPart();
		Relationship rel = wordprocessingMLPackage.getMainDocumentPart().addTargetPart(headerPart);

		Hdr hdr = getHdr(title, weekEndingDate);
		headerPart.setJaxbElement(hdr);

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
	}
	
	
	private static Hdr getHdr( String title, Date weekEndingDate)	throws Exception {
		Hdr hdr = objectFactory.createHdr();
		
		P p = createP(title, true, false, "14");
		hdr.getContent().add(p);
		
		String weekEndDate = DateUtil.toStringDate(weekEndingDate);
		hdr.getContent().add(createP("Week Ending: " + weekEndDate, false, false, "12"));
		
		return hdr;
	}
	

	public static final void addSprint(WordprocessingMLPackage wordMLPackage, Sprint sprint) {
		MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
		String sprintFontSize = "11";
		
		P beginEmptyP = createP("", false, false, sprintFontSize);
		mdp.addObject(beginEmptyP);
		P sprintNumberP = createLabelValueP("Current Sprint: ", String.valueOf(sprint.getNumber()), sprintFontSize);
		mdp.addObject(sprintNumberP);
		P sprintStartDateP = createLabelValueP("Start Date: ", DateUtil.toStringDate(sprint.getStartDate()), sprintFontSize);
		mdp.addObject(sprintStartDateP);
		P sprintEndDateP = createLabelValueP("End Date: ", DateUtil.toStringDate(sprint.getEndDate()), sprintFontSize);
		mdp.addObject(sprintEndDateP);		
	}
	

	public static final void addProjects(WordprocessingMLPackage wordMLPackage, List<Project> projects) {
		for (Project project : projects) {
			addProject(wordMLPackage, project);
		}
	}
	
	
	public static final void addProject(WordprocessingMLPackage wordMLPackage, Project project) {
		MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
		String projectFontSize = "11";
		P emptyP = createP("", false, false, projectFontSize);
		mdp.addObject(emptyP);
		
		P projectP = createP(project.getName(), true, false, projectFontSize);
		mdp.addObject(projectP);
		
		Set<String> epics = project.getEpicsSet();		
		for (String epic : epics) {
			addEpic(epic, project.getTasksByEpic(epic), mdp, projectFontSize);
		}
	}
	
	
	public static final void addEpic(String epic, List<JiraIssue> jiraIssues, MainDocumentPart mdp, String epicFontSize) {
		P emptyP = createP("", false, false, epicFontSize);
		mdp.addObject(emptyP);
		P epicP = createP(epic, false, true, epicFontSize);
		mdp.addObject(epicP);
		
		for (JiraIssue jiraIssue : jiraIssues) {
			addTask(jiraIssue, mdp, epicFontSize);
		}
	}
	
	
	public static final void addTask(JiraIssue jiraIssue, MainDocumentPart mdp, String taskFontSize) {
		P summaryP = createP("     " + jiraIssue.getSummary(), false, false, taskFontSize);
		mdp.addObject(summaryP);
		P statusP = createP("          Status: " + jiraIssue.getStatus(), false, false, taskFontSize);
		mdp.addObject(statusP);
		addDevelopers(jiraIssue.getDevelopers(), mdp, taskFontSize);
		
	}
	
	
	public static final void addDevelopers(Set<String> developers, MainDocumentPart mdp, String taskFontSize) {
		String developerLabel = "";
		String developerValue = "";
		
		if(CollectionUtils.isEmpty(developers) || developers.size() == 1) {			
			developerLabel = "          Developer: ";
			if(!CollectionUtils.isEmpty(developers)) {
				developerValue = developers.iterator().next();
			}
			
		} else {
			
			developerLabel = "          Developers: ";
			
			StringBuilder sb = new StringBuilder();
			for (String developer : developers) {
				sb.append(developer).append(", ");
			}
			sb.delete(sb.length() -2, sb.length() -1); // get rid of last ','
			developerValue = sb.toString();
		}
		
		P developerP = createP(developerLabel + developerValue, false, false, taskFontSize);
		mdp.addObject(developerP);		
	}
	
	
	private static P createP(String textValue, Boolean bold, Boolean italic, String fontSize) {
		P p = objectFactory.createP();
		addGlobalParagraphStyle(p);
		R r = createStyledRun(textValue, bold, italic, fontSize);
		p.getContent().add(r);
		return p;
	}
	
	
	private static R createStyledRun(String textValue, Boolean bold, Boolean italic, String fontSize) {
		R run = objectFactory.createR();
		Text text = objectFactory.createText();
		text.setValue(textValue);
		text.setSpace("preserve");
		run.getContent().add(text);
		addStyling(run, bold, italic, fontSize);
		return run;
	}
	
	
	/**
     *  This is where we add the actual styling information. In order to do this
     *  we first create a paragraph. Then we create a text with the content of
     *  the cell as the value. Thirdly, we create a so-called run, which is a
     *  container for one or more pieces of text having the same set of
     *  properties, and add the text to it. We then add the run to the content
     *  of the paragraph.
     *  So far what we've done still doesn't add any styling. To accomplish that,
     *  we'll create run properties and add the styling to it. These run
     *  properties are then added to the run. Finally the paragraph is added
     *  to the content of the table cell.
     */
    private static void addStyling(R run,
                    Boolean bold, Boolean italic, String fontSize) { 
        RPr runProperties = objectFactory.createRPr();
        
        if (bold != null && bold) {
            addBoldStyle(runProperties);
        }
 
        if (fontSize != null && !fontSize.isEmpty()) {
            setFontSize(runProperties, fontSize);
        }
        
        if (italic != null && italic) {
        	addItalicStyle(runProperties);
        }
 
        run.setRPr(runProperties);        
    }
    
    
	/**
     *  In this method we're going to add the font size information to the run
     *  properties. First we'll create a half-point measurement. Then we'll
     *  set the fontSize as the value of this measurement. Finally we'll set
     *  the non-complex and complex script font sizes, sz and szCs respectively.
     */
    private static void setFontSize(RPr runProperties, String fontSize) {
        HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(fontSize).multiply(new BigInteger("2")));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
    }
	
    
	/**
     *  In this method we'll add the bold property to the run properties.
     *  BooleanDefaultTrue is the Docx4j object for the b property.
     *  Technically we wouldn't have to set the value to true, as this is
     *  the default.
     */
    private static void addBoldStyle(RPr runProperties) {
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        b.setVal(true);
        runProperties.setB(b);
    }
    
    
    private static void addItalicStyle(RPr runProperties) {
    	BooleanDefaultTrue b = new BooleanDefaultTrue();
        b.setVal(true);
    	runProperties.setI(b);
    }
	
    
	private static P createLabelValueP(String label, String value, String fontSize) {
		P paragraph = objectFactory.createP();		
		addGlobalParagraphStyle(paragraph);
		
		R labelRun = createStyledRun(label, true, false, fontSize);
		paragraph.getContent().add(labelRun);
		
		R valueRun = createStyledRun(value, false, false, fontSize);
		paragraph.getContent().add(valueRun);
		return paragraph;
	}   
    
	
    private static void addGlobalParagraphStyle(P paragraph) {
	    PPr paragraphProperties = objectFactory.createPPr();
	    // add the global paragraph styles
	    setNoSpaceStyle(paragraphProperties);
	    // set the properties to the paragraph
	    paragraph.setPPr(paragraphProperties);
    }    
    
    
    private static void setNoSpaceStyle(PPr paragraphProperties) {
    	Spacing spacing = objectFactory.createPPrBaseSpacing();
    	
    	spacing.setBefore(new BigInteger("0"));
    	spacing.setAfter(new BigInteger("0"));
    	spacing.setBeforeAutospacing(false);
    	spacing.setAfterAutospacing(false);

    	paragraphProperties.setSpacing(spacing);
    }    

}
