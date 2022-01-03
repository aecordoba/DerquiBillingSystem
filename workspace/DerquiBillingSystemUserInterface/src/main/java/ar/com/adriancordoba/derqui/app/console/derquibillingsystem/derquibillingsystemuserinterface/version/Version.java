/*
 * 		Version.java
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
 * 		Version.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 8, 2019
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.version;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Version {
	private static final String VERSION = "2.0.2";

	private static final Logger logger = LogManager.getLogger(Version.class);

	public static String getVersion() {
		return VERSION;
	}
}
