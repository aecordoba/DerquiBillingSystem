/*
 * 		UserInterface.java
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
 * 		UserInterface.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 30, 2018
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.adriancordoba.app.console.commonservices.logging.Logging;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.process.AMAFilesProcessor;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.process.MainProcess;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.version.Version;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class UserInterface {
	private static ResourceBundle bundle = ResourceBundle.getBundle("i18n/UserInterfaceMessagesBundle"); // I18N
	private static final Logger logger;

	static {
		Logging.configure();
		logger = LogManager.getLogger(UserInterface.class);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Derqui Billing System is starting...");
		logger.info("Derqui Billing System V" + Version.getVersion());
		System.out.println(bundle.getString("System.Starting"));
		System.out.println(bundle.getString("System.Version") + Version.getVersion());
		String inputDirectory = null;
		String outputFilename = null;

		if (args.length != 0) {
			inputDirectory = args[0];

			if (args.length == 2) {
				outputFilename = args[1];
			}
			if (args.length > 2) {
				logger.error("Error in command line arguments.");
				System.out.println(bundle.getString("System.InputFormatError"));
				System.out.println(bundle.getString("System.Usage"));
				logger.fatal("Derqui Billing System ends with fatal errors.");
				System.out.println(bundle.getString("System.EndWithFatalErrors"));
				System.exit(1);
			}
		}

		try {
			MainProcess mainProcess = new MainProcess(inputDirectory, outputFilename);
			if (mainProcess.process()) {
				logger.info("Derqui Billing System ends. (No problem found.)");
				System.out.println(bundle.getString("System.EndWithNoProblems"));
			} else {
				logger.error("Derqui Billing System ends with some problems.");
				System.out.println(bundle.getString("System.EndWithProblems"));
			}
		} catch (Exception e) {
			logger.fatal("Derqui Billing System ends with fatal errors.");
			System.out.println(bundle.getString("System.EndWithFatalErrors"));
		}

	}
}
