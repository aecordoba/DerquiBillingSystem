/*
 * 		Neax61SigmaCDRTransponder.java
 *   Copyright (C) 2018  Adrián E. Córdoba [software.asia@gmail.com]
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * 		Neax61SigmaCDRTransponder.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Dec 21, 2018
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.transponders;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.TicketsCollection;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.helpers.EBCDIC;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Neax61SigmaCDRTransponder extends CDRTransponder {
	private int lineNumber;
	private static final Logger logger = LogManager.getLogger(Neax61SigmaCDRTransponder.class);

	/**
	 * @param bufferedReader
	 * @param bufferedWriter
	 */
	public Neax61SigmaCDRTransponder(File inputFile, TicketsCollection ticketsCollection) {
		super(inputFile, ticketsCollection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.adriancordoba.derqui.app.console.derquibillingsystem.
	 * derquibillingsystemuserinterface.transponders.CDRTransponder#transpond()
	 */
	@Override
	public boolean transpond() throws Exception {
		boolean result = true;
		File inputFile = getInputFile();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
		String line = bufferedReader.readLine();
		lineNumber = 1;
		while (line != null) {
			if (line.contains("fn=") && line.contains("blk_no=")) {
				String fn = line.substring(line.indexOf("fn=") + 3, line.indexOf("fn=") + 10).trim();
				String blk = line.substring(line.indexOf("blk_no=") + 7, line.indexOf("blk_no=") + 12).trim();
				String cdr = null;

				try {
					cdr = getCDR(bufferedReader);
					addTicket(cdr);
					registersNumber++;
				} catch (Exception exception) {
					logger.error("Problem in file " + inputFile.getName() + " fn=" + fn + " blk_no=" + blk + ".");
					result = false;
				}
			}

			line = bufferedReader.readLine();
			lineNumber++;
		}
		logger.debug(registersNumber + " CDRs read from " + inputFile.getName() + " file.");
		bufferedReader.close();
		return result;
	}

	private String getCDR(BufferedReader bufferedReader) throws Exception {
		String cdr = new String();
		String line = null;

		for (int i = 0; i < 4; i++) {
			line = bufferedReader.readLine();
			if (line != null) {
				lineNumber++;
				if (line.length() == 40) {
					for (int j = 0; j < line.length(); j += 2) {
						try {
							EBCDIC ebcdic = new EBCDIC(line.charAt(j), line.charAt(j + 1));
							cdr += ebcdic.getAsciiCharacter();
						} catch (Exception exception) {
							logger.error("Invalid EBCDIC code " + line.charAt(j) + line.charAt(j + 1) + " in CDR. (File " + getInputFile().getName() + " line " + lineNumber + ".)");
							throw new Exception("Invalid EBCDIC code.");
						}
					}
				} else {
					logger.error("Invalid CDR length. (File " + getInputFile().getName() + " line " + lineNumber + ".)");
					throw new Exception("Invalid CDR length.");
				}
			} else {
				logger.error("Invalid CDR length. (End Of File found.)");
				throw new Exception("End Of File found.");
			}
		}
		return cdr;
	}
}
