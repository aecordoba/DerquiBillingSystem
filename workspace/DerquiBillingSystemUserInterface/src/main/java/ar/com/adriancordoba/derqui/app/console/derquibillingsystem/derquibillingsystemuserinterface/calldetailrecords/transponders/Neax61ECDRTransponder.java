/*
 * 		Neax61ECDRTransponder.java
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
 * 		Neax61ECDRTransponder.java
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

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Neax61ECDRTransponder extends CDRTransponder {
	private static final Logger logger = LogManager.getLogger(Neax61ECDRTransponder.class);

	/**
	 * @param bufferedReader
	 * @param bufferedWriter
	 */
	public Neax61ECDRTransponder(File inputFile, TicketsCollection ticketsCollection) {
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
		int lineNumber = 1;
		while (line != null) {
			if (line.length() != 80) {
				logger.error("File " + inputFile.getName() + " line " + lineNumber + " don't contain 80 characters.");
				result = false;
			} else {
				try {
					addTicket(line);
					registersNumber++;
				} catch (Exception exception) {
					logger.error("File " + inputFile.getName() + " line " + lineNumber + " with format error.");
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
}
