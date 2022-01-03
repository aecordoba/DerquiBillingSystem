/*
 * 		TicketsCollection.java
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
 * 		TicketsCollection.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		May 6, 2019
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.process.MainProcess;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.process.filehandler.FileHandler;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class TicketsCollection {
	private List<Ticket> ticketsList;
	private Ticket firstTicket;
	private Ticket lastTicket;
	private static ResourceBundle bundle = ResourceBundle.getBundle("i18n/UserInterfaceMessagesBundle"); // I18N
	private static final Logger logger = LogManager.getLogger(TicketsCollection.class);

	/**
	 * 
	 */
	public TicketsCollection() {
		ticketsList = new ArrayList<>();
	}

	public LocalDateTime getFirstTicketClosedTime() {
		return firstTicket.getClosedTime();
	}

	public LocalDateTime getLastTicketClosedTime() {
		return lastTicket.getClosedTime();
	}

	public void saveRawTickets(String filename) throws Exception {
		saveRawTickets(filename, ticketsList);
		logger.info(ticketsList.size() + " raw CDR tickets were saved to " + filename + ".");
		System.out.println(ticketsList.size() + " " + bundle.getString("System.RawTicketsSaved") + " " + filename + ".");
	}

	public void saveCallingNumberTickets(String filename, String callingNumber) throws Exception {
		List<Ticket> filteredTickets = new ArrayList<>();
		for (Ticket ticket : ticketsList) {
			if (callingNumber.startsWith("*") && callingNumber.endsWith("*")) {
				String containsCallingNumber = callingNumber.substring(1, callingNumber.length() - 1);
				if (ticket.getCallingSubscriberNumber().contains(containsCallingNumber))
					filteredTickets.add(ticket);
			} else if (callingNumber.startsWith("*")) {
				String endsCallingNumber = callingNumber.substring(1);
				if (ticket.getCallingSubscriberNumber().endsWith(endsCallingNumber))
					filteredTickets.add(ticket);
			} else if (callingNumber.endsWith("*")) {
				String startsCallingNumber = callingNumber.substring(0, callingNumber.length() - 1);
				if (ticket.getCallingSubscriberNumber().startsWith(startsCallingNumber))
					filteredTickets.add(ticket);
			} else {
				if (ticket.getCallingSubscriberNumber().equals(callingNumber))
					filteredTickets.add(ticket);
			}
		}
		logger.info(filteredTickets.size() + " CDR tickets found for calling number " + callingNumber + ".");
		if (filteredTickets.size() > 0) {
			saveFormatedTickets(filename, filteredTickets);
			logger.info(filteredTickets.size() + " CDR tickets were saved to " + filename + " for calling number " + callingNumber + ".");
			System.out.println(filteredTickets.size() + " " + bundle.getString("System.FormatedTicketsSaved") + " " + filename + " " + bundle.getString("System.CallingNumber.TicketsSaved") + "  " + callingNumber + ".");
		} else {
			System.out.println(bundle.getString("System.NoCallingNumberTickets") + "  " + callingNumber + ".");
		}
	}

	public void saveCalledNumberTickets(String filename, String calledNumber) throws Exception {
		List<Ticket> filteredTickets = new ArrayList<>();
		for (Ticket ticket : ticketsList) {
			if (calledNumber.startsWith("*") && calledNumber.endsWith("*")) {
				String containsCalledNumber = calledNumber.substring(1, calledNumber.length() - 1);
				if (ticket.getCalledSubscriberNumber().contains(containsCalledNumber))
					filteredTickets.add(ticket);
			} else if (calledNumber.startsWith("*")) {
				String endsCalledNumber = calledNumber.substring(1);
				if (ticket.getCalledSubscriberNumber().endsWith(endsCalledNumber))
					filteredTickets.add(ticket);
			} else if (calledNumber.endsWith("*")) {
				String startsCalledNumber = calledNumber.substring(0, calledNumber.length() - 1);
				if (ticket.getCalledSubscriberNumber().startsWith(startsCalledNumber))
					filteredTickets.add(ticket);
			} else {
				if (ticket.getCalledSubscriberNumber().equals(calledNumber))
					filteredTickets.add(ticket);
			}
		}
		logger.info(filteredTickets.size() + " CDR tickets found for called number " + calledNumber + ".");
		if (filteredTickets.size() > 0) {
			saveFormatedTickets(filename, filteredTickets);
			logger.info(filteredTickets.size() + " CDR tickets were saved to " + filename + " for called number " + calledNumber + ".");
			System.out.println(filteredTickets.size() + " " + bundle.getString("System.FormatedTicketsSaved") + " " + filename + " " + bundle.getString("System.CalledNumber.TicketsSaved") + "  " + calledNumber + ".");
		} else {
			System.out.println(bundle.getString("System.NoCalledNumberTickets") + "  " + calledNumber + ".");
		}
	}

	private void saveRawTickets(String filename, List<Ticket> ticketsList) throws Exception {
		File outputFile = FileHandler.getFile(filename);
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
		for (Ticket ticket : ticketsList) {
			bufferedWriter.write(ticket.getLine());
			bufferedWriter.newLine();
		}
		bufferedWriter.close();
	}

	private void saveFormatedTickets(String filename, List<Ticket> ticketsList) throws Exception {
		File outputFile = FileHandler.getFile(filename);
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
		for (Ticket ticket : ticketsList) {
			bufferedWriter.write(ticket.getFormatedTicket());
			bufferedWriter.newLine();
		}
		bufferedWriter.close();
	}

	public void addTicket(Ticket ticket) {
		if(firstTicket == null && lastTicket == null)
			firstTicket = lastTicket = ticket;
		else {
			if(ticket.getClosedTime().isBefore(firstTicket.getClosedTime()))
				firstTicket = ticket;
			if(ticket.getClosedTime().isAfter(lastTicket.getClosedTime()))
				lastTicket = ticket;
		}
		ticketsList.add(ticket);
	}

	public int getSize() {
		return ticketsList.size();
	}
}
