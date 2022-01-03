/*
 * 		CDRTransponderFactory.java
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
 * 		CDRTransponderFactory.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Dec 21, 2018
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.transponders;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.TicketsCollection;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class CDRTransponderFactory {
	private static CDRTransponderFactory cdrTransponderFactory;
	private TicketsCollection ticketsCollection;
	private int filesNumber;

	private static final Logger logger = LogManager.getLogger(CDRTransponderFactory.class);;

	private CDRTransponderFactory(TicketsCollection ticketsCollection) {
		this.ticketsCollection = ticketsCollection;
	}
	
	public static CDRTransponderFactory getInstance(TicketsCollection ticketsCollection) throws Exception {
		if (cdrTransponderFactory == null)
			cdrTransponderFactory = new CDRTransponderFactory(ticketsCollection);
		return cdrTransponderFactory;
	}

	public CDRTransponder getCDRTransponder(File inputFile) throws Exception {
		CDRTransponder cdrTransponder = null;

		if (inputFile.getName().startsWith("ama")) {
			cdrTransponder = new Neax61SigmaCDRTransponder(inputFile, ticketsCollection);
			filesNumber++;
		} else if (inputFile.getName().startsWith("a")) {
			cdrTransponder = new Neax61ECDRTransponder(inputFile, ticketsCollection);
			filesNumber++;
		} else
			logger.error("Invalid input filename: " + inputFile.getAbsolutePath() + ".");

		return cdrTransponder;
	}

	/**
	 * @return the filesNumber
	 */
	public int getFilesNumber() {
		return filesNumber;
	}
}
