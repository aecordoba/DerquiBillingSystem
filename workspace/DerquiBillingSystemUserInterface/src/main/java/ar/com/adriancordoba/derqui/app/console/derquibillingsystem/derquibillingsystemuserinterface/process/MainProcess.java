/*
 * 		MainProcess.java
 *   Copyright (C) 2019  Adrián E. Córdoba [software.asia@gmail.com]
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
 * 		MainProcess.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		May 7, 2019
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.TicketsCollection;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class MainProcess {
	private String inputDirectory;
	private String outputFilename;
	private BufferedReader reader;
	private TicketsCollection ticketsCollection;
	private static ResourceBundle bundle = ResourceBundle.getBundle("i18n/UserInterfaceMessagesBundle"); // I18N
	private static final Logger logger = LogManager.getLogger(MainProcess.class);;

	/**
	 * @param inputDirectory
	 * @param outputFilename
	 * @throws Exception
	 */
	public MainProcess(String inputDirectory, String outputFilename) throws Exception {
		reader = new BufferedReader(new InputStreamReader(System.in));
		ticketsCollection = new TicketsCollection();
		this.inputDirectory = inputDirectory;
		this.outputFilename = outputFilename;
	}

	public boolean process() throws Exception {
		boolean result = true;
		try {
			AMAFilesProcessor amaFilesProcessor = new AMAFilesProcessor(getInputDirectory(), ticketsCollection);
			logger.info("Reading AMA files from " + inputDirectory + " directory.");
			System.out.println(bundle.getString("System.AMAFilesDirectory") + " " + inputDirectory);
			if (amaFilesProcessor.process()) {
				logger.info("No problem found while it's reading AMA files.");
			} else {
				result = false;
				logger.info("Some CDRs problems were found while it's reading AMA files.");
				System.out.println(bundle.getString("System.AMAFiles.Problem"));
			}

			ticketsCollection = amaFilesProcessor.getTicketsCollection();
			if (ticketsCollection.getSize() > 0) {
				logger.info("Tickets read from " + ticketsCollection.getFirstTicketClosedTime() + " to " + ticketsCollection.getLastTicketClosedTime() + ".");
				System.out.println(bundle.getString("System.TicketsReadFrom") + " " + ticketsCollection.getFirstTicketClosedTime() + " " + bundle.getString("System.TicketsReadTo") + " " + ticketsCollection.getLastTicketClosedTime() + ".");
				String outputFilename = null;
				int process = getSelectedMenuOption();
				while (process != 0) {
					outputFilename = getOutputFilename();
					switch (process) {
					case 0:
						return true;
					case 1:
						ticketsCollection.saveRawTickets(outputFilename);
						break;
					case 2:
						ticketsCollection.saveCallingNumberTickets(outputFilename, getCallingNumber());
						break;
					case 3:
						ticketsCollection.saveCalledNumberTickets(outputFilename, getCalledNumber());
						break;
					}
					this.outputFilename = null;
					process = getSelectedMenuOption();
				}
			} else {
				logger.info("No valid CDR to process.");
				System.out.println(bundle.getString("System.NoValidCDR"));
			}
		} catch (Exception e) {
			logger.fatal("Fatal errors.");
			throw new Exception();
		}

		return result;
	}

	private String getInputDirectory() throws Exception {
		if (inputDirectory == null) {
			System.out.println(bundle.getString("System.Inquiry.InputDirectory"));
			try {
				inputDirectory = reader.readLine();
			} catch (IOException e) {
				logger.warn("Cannot read input data.");
				throw new Exception();
			}
		}
		return inputDirectory;
	}

	private String getOutputFilename() throws Exception {
		if (outputFilename == null) {
			System.out.println(bundle.getString("System.Inquiry.OutputFilename"));
			try {
				outputFilename = reader.readLine();
			} catch (IOException e) {
				logger.warn("Cannot read input data.");
				throw new Exception();
			}
		}
		return outputFilename;
	}

	private String getCallingNumber() throws Exception {
		String callingNumber;
		System.out.println(bundle.getString("System.Inquiry.CallingNumber"));
		try {
			callingNumber = reader.readLine();
		} catch (IOException e) {
			logger.warn("Cannot read input data.");
			throw new Exception();
		}
		return callingNumber;
	}

	private String getCalledNumber() throws Exception {
		String calledNumber;
		System.out.println(bundle.getString("System.Inquiry.CalledNumber"));
		try {
			calledNumber = reader.readLine();
		} catch (IOException e) {
			logger.warn("Cannot read input data.");
			throw new Exception();
		}
		return calledNumber;
	}

	private int getSelectedMenuOption() {
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("\t\t\t" + bundle.getString("System.Menu.Options"));
		System.out.println("\t1- " + bundle.getString("System.Menu.Option1"));
		System.out.println("\t2- " + bundle.getString("System.Menu.Option2"));
		System.out.println("\t3- " + bundle.getString("System.Menu.Option3"));
		System.out.println("\t0- " + bundle.getString("System.Menu.Option0"));
		System.out.println();
		System.out.println(bundle.getString("System.Menu.Select"));
		int selection = scanner.nextInt();
		while (selection < 0 || selection > 3) {
			System.out.println(bundle.getString("System.Menu.InvalidSelection"));
			selection = scanner.nextInt();
		}
		return selection;
	}
}
