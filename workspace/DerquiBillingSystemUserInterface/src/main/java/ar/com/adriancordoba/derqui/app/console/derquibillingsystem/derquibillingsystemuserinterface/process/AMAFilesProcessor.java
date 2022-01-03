/*
 * 		AMAFilesProcessor.java
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
 * 		AMAFilesProcessor.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 30, 2018
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.process;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.TicketsCollection;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.transponders.CDRTransponder;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.transponders.CDRTransponderFactory;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.process.filehandler.FileHandler;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class AMAFilesProcessor {
	private String inputDirectory;
	private int totalRegisters;
	private TicketsCollection ticketsCollection;

	private static final Logger logger = LogManager.getLogger(AMAFilesProcessor.class);;

	/**
	 * @param inputDirectory
	 */
	public AMAFilesProcessor(String inputDirectory, TicketsCollection ticketsCollection) {
		this.inputDirectory = inputDirectory;
		this.ticketsCollection = ticketsCollection;
	}

	public boolean process() throws Exception {
		boolean result = true;
		long initialTime = System.currentTimeMillis();

		try {
			FileHandler fileHandler = new FileHandler(inputDirectory);
			CDRTransponderFactory cdrTransponderFactory = CDRTransponderFactory.getInstance(ticketsCollection);

			for (File file : fileHandler.getFilesList()) {
				CDRTransponder cdrTransponder = cdrTransponderFactory.getCDRTransponder(file);
				if (cdrTransponder != null) {
					if (!cdrTransponder.transpond())
						result = false;
					totalRegisters += cdrTransponder.getRegistersNumber();
				} else
					result = false;
			}
			
			logger.info(totalRegisters + " CDRs processed from " + cdrTransponderFactory.getFilesNumber() + " files. (" + (((double)System.currentTimeMillis() - initialTime) / 1000) + " seconds.)");
		} catch (Exception exception) {
			logger.fatal("Cannot continue the application.", exception);
			throw new Exception("Cannot continue the application.");
		}

		return result;
	}

	/**
	 * @param inputDirectory the inputDirectory to set
	 */
	public void setInputDirectory(String inputDirectory) {
		this.inputDirectory = inputDirectory;
	}

	/**
	 * @return the ticketsCollection
	 */
	public TicketsCollection getTicketsCollection() {
		return ticketsCollection;
	}
}
