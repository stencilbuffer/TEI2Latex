/*******************************************************************************
 * Copyright 2013 Gabriel Siemoneit
 * 
 * This file is part of TEI2Latex.
 * 
 * TEI2Latex is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *   
 * TEI2Latex is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *   
 * You should have received a copy of the GNU General Public License
 * along with TEI2Latex.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.tei2latex.tei;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.liblatexdoc.LatexDocument;
import org.libteidoc.AbstractNoIgnoreHandler;

public class PbHandler extends AbstractNoIgnoreHandler {
	
	private static Logger logger = LogManager.getLogger(PbHandler.class.getName());

	public PbHandler(LatexDocument doc) {
		super(doc);
		// TODO Auto-generated constructor stub
	}
	
	public void end() {
		//logger.debug("attributes="+attributes);
		//logger.debug("getValue(id)="+attributes.getValue("id"));
		String n = "XXX";
		String rv = "x";
		String attString = attributes.getValue("n");
		
		if (attString != null) {
			if (!attString.isEmpty() && attString.contains("[")) {
				int beginIndex = attString.indexOf("[");
				int endIndex = attString.indexOf("]");
				
				n = attString.substring(0, beginIndex);
				rv = attString.substring(beginIndex+1, endIndex);
				
			}
		}

		//logger.debug("Page number: '" + n + "', ["+rv+"]");
		String outputString = "\\textup{\\,|\\,}";
		//outputString += "\\marginpar{{\\footnotesize\\setlength\\baselineskip{1ex}";//\\begin{flushleft}";
		outputString += "\\ledsidenote{";
		outputString += "fol. ";
		outputString += n;
		outputString += "${}^{"+rv+"}$";
		//outputString += "}}";//\\end{flushleft}\\par}";
		outputString += "}";
		
		logger.debug(outputString);
		//latexDocument.write(" | \\marginpar{{\\scriptsize\\setlength\\baselineskip{1ex}\\begin{flushleft}"+n+"${}^{"+rv+"}$\\end{flushleft}\\par}}");
		//latexDocument.write("\\ledsidenote{" + outputString + "}");
		
		document.write(outputString);
	}

}
