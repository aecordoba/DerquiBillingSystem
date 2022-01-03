/*
 * 		CallingSubscriberCategory.java
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
 * 		CallingSubscriberCategory.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 8, 2019
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.field;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class CallingSubscriberCategory extends TicketField {
	private static final Logger logger = LogManager.getLogger(CallingSubscriberCategory.class);
	private static ResourceBundle bundle = ResourceBundle.getBundle("i18n/FieldsMessagesBundle"); // I18N

	private static final long serialVersionUID = -3301273041380770140L;

	/**
	 * @param code
	 * @throws Exception 
	 */
	public CallingSubscriberCategory(char code) throws Exception {
		super(code);
		if (code != '1' && code != '2' && code != '3' && code != '4' && code != '5' && code != 'B' && code != 'C' && code != 'E' && code != 'F') {
			logger.error("Calling Subscriber Category not defined: " + code + ".");
			throw new Exception("Field not defined.");
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.adriancordoba.derqui.app.console.derquibillingsystem.calldetailrecords.field.TicketField#getMeaning()
	 */
	@Override
	public String getMeaning() {
		String meaning = null;
		
		switch(getCode()) {
		case '1':
			meaning = bundle.getString("Fields.CallingSubscriberCategory_1");
			break;
		case '2':
			meaning = bundle.getString("Fields.CallingSubscriberCategory_2");
			break;
		case '3':
			meaning = bundle.getString("Fields.CallingSubscriberCategory_3");
			break;
		case '4':
			meaning = bundle.getString("Fields.CallingSubscriberCategory_4");
			break;
		case '5':
			meaning = bundle.getString("Fields.CallingSubscriberCategory_5");
			break;
		case 'B':
			meaning = bundle.getString("Fields.CallingSubscriberCategory_B");
			break;
		case 'C':
			meaning = bundle.getString("Fields.CallingSubscriberCategory_C");
			break;
		case 'E':
			meaning = bundle.getString("Fields.CallingSubscriberCategory_E");
			break;
		case 'F':
			meaning = bundle.getString("Fields.CallingSubscriberCategory_F");
		}
		return meaning;
	}

}
