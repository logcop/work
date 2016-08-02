package com.cee.wsr.document;

public final class WordXMLTemplate {

	public static final String HEADER_FOOTER =
			"<w:sectPr xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">" +
			   "<w:hdr w:type=\"first\" >" +
			      "<w:p>" +
			         "<w:pPr>" +
			            "<w:pStyle w:val=\"Header\"/>" +
			         "</w:pPr>" +
			         "<w:r>" +
			            "<w:t>My Header</w:t>" +
			         "</w:r>" +
			      "</w:p>" +
			   "</w:hdr>" +
			   "<w:titlePg/>" +
			"</w:sectPr>" ;
	
	public static final String SIMPLE_HEADER = 
			"<w:hdr xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">" +
					"<w:p>" +
						"<w:pPr>" +
							"<w:spacing w:after=\"0\"/>" +
							"<w:rPr>" +
								"<w:b/>" +
								"<w:sz w:val=\"28\"/>" +
							"</w:rPr>" +
						"</w:pPr>" +
						"<w:r>" +
							"<w:rPr>" +
								"<w:b/>" +
								"<w:sz w:val=\"28\"/>" +
							"</w:rPr>" +
							"<w:t>LOGCOP Weekly Status Report</w:t>" +
						"</w:r>" +
					"</w:p>" +
				"</w:hdr>";
	
	public static final String HEADER = 
			"<w:hdr xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">" +
				"<w:p>" +
					"<w:pPr>" +
						"<w:spacing w:after=\"0\"/>" +
						"<w:rPr>" +
							"<w:b/>" +
							"<w:sz w:val=\"28\"/>" +
						"</w:rPr>" +
					"</w:pPr>" +
					"<w:r>" +
						"<w:rPr>" +
							"<w:b/>" +
							"<w:sz w:val=\"28\"/>" +
						"</w:rPr>" +
						"<w:t>LOGCOP Weekly Status Report</w:t>" +
					"</w:r>" +
				"</w:p>" +
				"<w:p>" +
					"<w:pPr>" +
						"<w:pStyle w:val=\"Header\"/>" +
						"<w:rPr>" +
							"<w:sz w:val=\"24\"/>" +
						"</w:rPr>" +
					"</w:pPr>" +
					"<w:r>" +
						"<w:rPr>" +
							"<w:noProof/>" +
							"<w:sz w:val=\"24\"/>" +
						"</w:rPr>" +
						/*"<w:pict>" +
							"<v:line from=\"0,17.35pt\" to=\"465.4pt,17.35pt\" style=\"position:absolute;z-index:251659264;visibility:visible;mso-wrap-style:square;mso-wrap-distance-left:9pt;mso-wrap-distance-top:0;mso-wrap-distance-right:9pt;mso-wrap-distance-bottom:0;mso-position-horizontal:absolute;mso-position-horizontal-relative:text;mso-position-vertical:absolute;mso-position-vertical-relative:text\" id=\"Straight Connector 1\" o:spid=\"_x0000_s1026\" strokecolor=\"black [3213]\" strokeweight=\"1pt\"/>" +
						"</w:pict>" +*/
					"</w:r>" +
					"<w:r>" +
						"<w:rPr>" +
							"<w:sz w:val=\"24\"/>" +
						"</w:rPr>" +
						"<w:t xml:space=\"preserve\">Week Ending: </w:t>" +
					"</w:r>" +
					"<w:sdt>" +
						"<w:sdtPr>" +
							"<w:rPr>" +
								"<w:sz w:val=\"24\"/>" +
							"</w:rPr>" +
							"<w:alias w:val=\"Publish Date\"/>" +
							"<w:tag w:val=\"\"/>" +
							"<w:id w:val=\"-177042472\"/>" +
							"<w:placeholder>" +
								"<w:docPart w:val=\"77EE36E9B29549F298FBC73FF23B9938\"/>" +
							"</w:placeholder>" +
							"<w:dataBinding w:prefixMappings=\"xmlns:ns0='http://schemas.microsoft.com/office/2006/coverPageProps' \" w:xpath=\"/ns0:CoverPageProperties[1]/ns0:PublishDate[1]\" w:storeItemID=\"{55AF091B-3C7A-41E3-B477-F2FDAA23CFDA}\"/>" +
							"<w:date w:fullDate=\"2016-04-03T00:00:00Z\">" +
								"<w:dateFormat w:val=\"dd-MMM-yyyy\"/>" +
								"<w:lid w:val=\"en-US\"/>" +
								"<w:storeMappedDataAs w:val=\"dateTime\"/>" +
								"<w:calendar w:val=\"gregorian\"/>" +
							"</w:date>" +
						"</w:sdtPr>" +
						"<w:sdtContent>" +
							"<w:r>" +
								"<w:rPr>" +
									"<w:sz w:val=\"24\"/>" +
								"</w:rPr>" +
								"<w:t>03-Apr-2016</w:t>" +
							"</w:r>" +
						"</w:sdtContent>" +
					"</w:sdt>" +
				"</w:p>" +
			"</w:hdr>";
}
