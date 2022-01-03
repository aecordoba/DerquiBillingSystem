/*
 * 		TicketField.java
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
 * 		TicketField.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 7, 2019
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field;

import java.io.Serializable;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public abstract class TicketField implements Serializable{
	private char code;

	private static final long serialVersionUID = 1903137028900619956L;

	/**
	 * @param code
	 */
	public TicketField(char code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public char getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(char code) {
		this.code = code;
	}

	/**
	 * @return
	 */
	public abstract String getMeaning();
}
