/*
 * 		Ticket.java
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
 * 		Ticket.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 7, 2019
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field.CallType;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field.CalledSubscriberCondition;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field.CallingSubscriberCategory;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field.CarrierService;
import ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field.StructureCode;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Ticket implements Serializable {
	private String line;

	private StructureCode structureCode;
	private CarrierService carrierService;
	private CallType callType;
	private String callingSubscriberNumber;
	private CallingSubscriberCategory callingSubscriberCategory;
	private String calledSubscriberNumber;
	private CalledSubscriberCondition calledSubscriberCondition;
	private LocalDate connectionDate;
	private LocalTime connectionTime;
	private Duration duration;
	private int chargingPulses;
	private String incomingCircuit;
	private String outgoingCircuit;
	private String callReferenceValue;
	private String carrierNumber;

	private static ResourceBundle bundle = ResourceBundle.getBundle("i18n/FieldsMessagesBundle"); // I18N
	private static final long serialVersionUID = 7544596628873479654L;

	/**
	 * 
	 */
	public Ticket() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ticket [structureCode=" + structureCode.getMeaning() + ", carrierService=" + carrierService.getMeaning() + ", callType=" + callType.getMeaning() + ", callingSubscriberNumber=" + callingSubscriberNumber + ", callingSubscriberCategory=" + callingSubscriberCategory.getMeaning() + ", calledSubscriberNumber=" + calledSubscriberNumber + ", calledSubscriberCondition=" + calledSubscriberCondition.getMeaning() + ", connectionDate=" + connectionDate + ", connectionTime=" + connectionTime + ", duration=" + duration + ", chargingPulses=" + chargingPulses + ", incomingCircuit=" + incomingCircuit + ", outgoingCircuit=" + outgoingCircuit + ", callReferenceValue=" + callReferenceValue + ", carrierNumber=" + carrierNumber + "]";
	}

	public String getFormatedTicket() {
		StringBuilder stringBuilder = new StringBuilder("*************************************\n");

		stringBuilder.append(bundle.getString("Fields.CallingSubscriberNumber"));
		stringBuilder.append(": ");
		stringBuilder.append(callingSubscriberNumber);
		stringBuilder.append('\n');
		stringBuilder.append(bundle.getString("Fields.CallingSubscriberCategory"));
		stringBuilder.append(": ");
		stringBuilder.append("(" + callingSubscriberCategory.getCode() + ") " + callingSubscriberCategory.getMeaning());
		stringBuilder.append('\n');
		stringBuilder.append(bundle.getString("Fields.StructureCode"));
		stringBuilder.append(": ");
		stringBuilder.append("(" + structureCode.getCode() + ") " + structureCode.getMeaning());
		stringBuilder.append('\n');
		stringBuilder.append(bundle.getString("Fields.CarrierService"));
		stringBuilder.append(": ");
		stringBuilder.append("(" + carrierService.getCode() + ") " + carrierService.getMeaning());
		stringBuilder.append('\n');
		stringBuilder.append(bundle.getString("Fields.CallType"));
		stringBuilder.append(": ");
		stringBuilder.append("(" + callType.getCode() + ") " + callType.getMeaning());
		stringBuilder.append('\n');
		stringBuilder.append(bundle.getString("Fields.CalledSubscriberNumber"));
		stringBuilder.append(": ");
		stringBuilder.append(calledSubscriberNumber);
		stringBuilder.append('\n');
		stringBuilder.append(bundle.getString("Fields.CalledSubscriberCondition"));
		stringBuilder.append(": ");
		stringBuilder.append("(" + calledSubscriberCondition.getCode() + ") " + calledSubscriberCondition.getMeaning());
		stringBuilder.append('\n');
		stringBuilder.append(bundle.getString("Fields.ConnectionDate"));
		stringBuilder.append(": ");
		stringBuilder.append(connectionDate);
		stringBuilder.append('\t');
		stringBuilder.append(bundle.getString("Fields.ConnectionTime"));
		stringBuilder.append(": ");
		stringBuilder.append(connectionTime);
		stringBuilder.append('\n');
		stringBuilder.append(bundle.getString("Fields.Duration"));
		stringBuilder.append(": ");
		stringBuilder.append(duration);
		stringBuilder.append('\n');
		stringBuilder.append(bundle.getString("Fields.ChargingPulses"));
		stringBuilder.append(": ");
		stringBuilder.append(chargingPulses);
		if (incomingCircuit != null) {
			stringBuilder.append('\n');
			stringBuilder.append(bundle.getString("Fields.IncomingCircuit"));
			stringBuilder.append(": ");
			stringBuilder.append(incomingCircuit);
		}
		if (outgoingCircuit != null) {
			if (incomingCircuit != null)
				stringBuilder.append('\t');
			else
				stringBuilder.append('\n');
			stringBuilder.append(bundle.getString("Fields.OutgoingCircuit"));
			stringBuilder.append(": ");
			stringBuilder.append(outgoingCircuit);
		}
		if (callReferenceValue != null) {
			stringBuilder.append('\n');
			stringBuilder.append(bundle.getString("Fields.CallReferenceValue"));
			stringBuilder.append(": ");
			stringBuilder.append(callReferenceValue);
		}
		if (carrierNumber != null) {
			stringBuilder.append('\n');
			stringBuilder.append(bundle.getString("Fields.CarrierNumber"));
			stringBuilder.append(": ");
			stringBuilder.append(carrierNumber);
		}

		return stringBuilder.toString();
	}

	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	public LocalDateTime getClosedTime() {
		LocalDateTime startTime = LocalDateTime.of(connectionDate, connectionTime);
		LocalDateTime closedTime = startTime.plusSeconds(duration.getSeconds());
		return closedTime;
	}
	
	/**
	 * @param line the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * @return the structureCode
	 */
	public StructureCode getStructureCode() {
		return structureCode;
	}

	/**
	 * @param structureCode the structureCode to set
	 */
	public void setStructureCode(StructureCode structureCode) {
		this.structureCode = structureCode;
	}

	/**
	 * @return the carrierService
	 */
	public CarrierService getCarrierService() {
		return carrierService;
	}

	/**
	 * @param carrierService the carrierService to set
	 */
	public void setCarrierService(CarrierService carrierService) {
		this.carrierService = carrierService;
	}

	/**
	 * @return the callType
	 */
	public CallType getCallType() {
		return callType;
	}

	/**
	 * @param callType the callType to set
	 */
	public void setCallType(CallType callType) {
		this.callType = callType;
	}

	/**
	 * @return the callingSubscriberNumber
	 */
	public String getCallingSubscriberNumber() {
		return callingSubscriberNumber;
	}

	/**
	 * @param callingSubscriberNumber the callingSubscriberNumber to set
	 */
	public void setCallingSubscriberNumber(String callingSubscriberNumber) {
		this.callingSubscriberNumber = callingSubscriberNumber;
	}

	/**
	 * @return the callingSubscriberCategory
	 */
	public CallingSubscriberCategory getCallingSubscriberCategory() {
		return callingSubscriberCategory;
	}

	/**
	 * @param callingSubscriberCategory the callingSubscriberCategory to set
	 */
	public void setCallingSubscriberCategory(CallingSubscriberCategory callingSubscriberCategory) {
		this.callingSubscriberCategory = callingSubscriberCategory;
	}

	/**
	 * @return the calledSubscriberNumber
	 */
	public String getCalledSubscriberNumber() {
		return calledSubscriberNumber;
	}

	/**
	 * @param calledSubscriberNumber the calledSubscriberNumber to set
	 */
	public void setCalledSubscriberNumber(String calledSubscriberNumber) {
		this.calledSubscriberNumber = calledSubscriberNumber;
	}

	/**
	 * @return the calledSubscriberCondition
	 */
	public CalledSubscriberCondition getCalledSubscriberCondition() {
		return calledSubscriberCondition;
	}

	/**
	 * @param calledSubscriberCondition the calledSubscriberCondition to set
	 */
	public void setCalledSubscriberCondition(CalledSubscriberCondition calledSubscriberCondition) {
		this.calledSubscriberCondition = calledSubscriberCondition;
	}

	/**
	 * @return the connectionDate
	 */
	public LocalDate getConnectionDate() {
		return connectionDate;
	}

	/**
	 * @param connectionDate the connectionDate to set
	 */
	public void setConnectionDate(LocalDate connectionDate) {
		this.connectionDate = connectionDate;
	}

	/**
	 * @return the connectionTime
	 */
	public LocalTime getConnectionTime() {
		return connectionTime;
	}

	/**
	 * @param connectionTime the connectionTime to set
	 */
	public void setConnectionTime(LocalTime connectionTime) {
		this.connectionTime = connectionTime;
	}

	/**
	 * @return the duration
	 */
	public Duration getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	/**
	 * @return the chargingPulses
	 */
	public int getChargingPulses() {
		return chargingPulses;
	}

	/**
	 * @param chargingPulses the chargingPulses to set
	 */
	public void setChargingPulses(int chargingPulses) {
		this.chargingPulses = chargingPulses;
	}

	/**
	 * @return the incomingCircuit
	 */
	public String getIncomingCircuit() {
		return incomingCircuit;
	}

	/**
	 * @param incomingCircuit the incomingCircuit to set
	 */
	public void setIncomingCircuit(String incomingCircuit) {
		this.incomingCircuit = incomingCircuit;
	}

	/**
	 * @return the outgoingCircuit
	 */
	public String getOutgoingCircuit() {
		return outgoingCircuit;
	}

	/**
	 * @param outgoingCircuit the outgoingCircuit to set
	 */
	public void setOutgoingCircuit(String outgoingCircuit) {
		this.outgoingCircuit = outgoingCircuit;
	}

	/**
	 * @return the callReferenceValue
	 */
	public String getCallReferenceValue() {
		return callReferenceValue;
	}

	/**
	 * @param callReferenceValue the callReferenceValue to set
	 */
	public void setCallReferenceValue(String callReferenceValue) {
		this.callReferenceValue = callReferenceValue;
	}

	/**
	 * @return the carrierNumber
	 */
	public String getCarrierNumber() {
		return carrierNumber;
	}

	/**
	 * @param carrierNumber the carrierNumber to set
	 */
	public void setCarrierNumber(String carrierNumber) {
		this.carrierNumber = carrierNumber;
	}
}
