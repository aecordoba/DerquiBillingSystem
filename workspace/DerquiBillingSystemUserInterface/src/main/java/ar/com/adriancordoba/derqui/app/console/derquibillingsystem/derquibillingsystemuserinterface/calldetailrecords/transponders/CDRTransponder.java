/*
 * 		CDRTransponder.java
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
 * 		CDRTransponder.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Dec 21, 2018
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.transponders;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.Ticket;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.TicketsCollection;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field.CallType;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field.CalledSubscriberCondition;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field.CallingSubscriberCategory;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field.CarrierService;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field.StructureCode;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public abstract class CDRTransponder {
	private File inputFile;
	protected int registersNumber;
	private TicketsCollection ticketsCollection;

	private static final Logger logger = LogManager.getLogger(CDRTransponder.class);

	/**
	 * @param inputFile
	 */
	public CDRTransponder(File inputFile, TicketsCollection ticketsCollection) {
		this.inputFile = inputFile;
		this.ticketsCollection = ticketsCollection;
	}

	abstract public boolean transpond() throws Exception;

	public Ticket addTicket(String line) throws Exception {
		Ticket ticket = new Ticket();

		ticket.setLine(line);
		ticket.setStructureCode(new StructureCode(line.charAt(0)));
		ticket.setCarrierService(new CarrierService(line.charAt(1)));
		ticket.setCallType(new CallType(line.charAt(2)));
		ticket.setCallingSubscriberNumber(line.substring(3, 13));
		ticket.setCallingSubscriberCategory(new CallingSubscriberCategory(line.charAt(13)));
		ticket.setCalledSubscriberNumber(line.substring(14, 34).trim());
		ticket.setCalledSubscriberCondition(new CalledSubscriberCondition(line.charAt(34)));
		try {
			ticket.setConnectionDate(LocalDate.parse(line.substring(39, 43) + line.substring(37, 39) + line.substring(35, 37), DateTimeFormatter.BASIC_ISO_DATE));
		} catch (DateTimeParseException exception) {
			logger.error("Erroneous Connection Date format: " + line.substring(35, 43) + ".");
			throw new Exception("Erroneous field.");
		}
		try {
			ticket.setConnectionTime(LocalTime.parse(line.substring(43, 45) + ":" + line.substring(45, 47) + ":" + line.substring(47, 49), DateTimeFormatter.ISO_TIME));
		} catch (DateTimeParseException exception) {
			logger.error("Erroneous Connection Time format: " + line.substring(43, 49) + ".");
			throw new Exception("Erroneous field.");
		}
		try {
			ticket.setDuration(Duration.parse("PT" + line.substring(49, 51) + "H" + line.substring(51, 53) + "M" + line.substring(53, 55) + "S"));
		} catch (DateTimeParseException exception) {
			logger.error("Erroneous Duration format: " + line.substring(49, 55) + ".");
			throw new Exception("Erroneous field.");
		}
		try {
			ticket.setChargingPulses(Integer.parseInt(line.substring(55, 59).trim()));
		} catch (NumberFormatException exception) {
			logger.error("Erroneous Charging Pulses format: " + line.substring(55, 59) + ".");
			throw new Exception("Erroneous field.");
		}
		String incomingCircuit = line.substring(59, 67).trim();
		if (incomingCircuit.isEmpty())
			incomingCircuit = null;
		ticket.setIncomingCircuit(incomingCircuit);
		String outgoingCircuit = line.substring(67, 75).trim();
		if (outgoingCircuit.isEmpty())
			outgoingCircuit = null;
		ticket.setOutgoingCircuit(outgoingCircuit);
		String callReferenceValue = line.substring(75, 77).trim();
		if (callReferenceValue.isEmpty())
			callReferenceValue = null;
		ticket.setCallReferenceValue(callReferenceValue);
		String carrierNumber = line.substring(77).trim();
		if (carrierNumber.isEmpty())
			carrierNumber = null;
		ticket.setCarrierNumber(carrierNumber);

		ticketsCollection.addTicket(ticket);
		return ticket;
	}

	/**
	 * @return the inputFile
	 */
	public File getInputFile() {
		return inputFile;
	}

	/**
	 * @return the registersNumber
	 */
	public int getRegistersNumber() {
		return registersNumber;
	}
}
