/*
 * 		StructureCode.java
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
 * 		StructureCode.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 7, 2019
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class StructureCode extends TicketField {
	private static final Logger logger = LogManager.getLogger(StructureCode.class);
	private static ResourceBundle bundle = ResourceBundle.getBundle("i18n/FieldsMessagesBundle"); // I18N

	private static final long serialVersionUID = 5998300127079005957L;

	public StructureCode(char code) throws Exception {
		super(code);
		if (code != '1' && code != '3' && code != '4') {
			logger.error("Structure Code not defined: " + code + ".");
			throw new Exception("Field not defined.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.adriancordoba.derqui.app.console.derquibillingsystem.
	 * calldetailrecords.field.TicketField#getMeaning()
	 */
	@Override
	public String getMeaning() {
		String meaning = null;

		switch (getCode()) {
		case '1':
			meaning = bundle.getString("Fields.StructureCode_1");
			break;
		case '3':
			meaning = bundle.getString("Fields.StructureCode_3");
			break;
		case '4':
			meaning = bundle.getString("Fields.StructureCode_4");
		}

		return meaning;
	}
}
