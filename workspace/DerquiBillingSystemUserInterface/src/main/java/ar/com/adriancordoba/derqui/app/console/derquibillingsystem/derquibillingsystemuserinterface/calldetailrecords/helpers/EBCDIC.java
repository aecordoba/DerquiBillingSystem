/*
 * 		EBCDIC.java
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
 * 		EBCDIC.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Dec 20, 2018
 */
package ar.com.adriancordoba.derqui.app.console.derquibillingsystem.derquibillingsystemuserinterface.calldetailrecords.helpers;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class EBCDIC {
	private char[] code = new char[2];
	private Character asciiCharacter;

	/**
	 * @param c1
	 * @param c2
	 * @throws Exception 
	 */
	public EBCDIC(char c0, char c1) throws Exception {
		this.code[0] = c0;
		this.code[1] = c1;
		convertToAscii(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new String(code);
	}

	public char getAsciiCharacter() {
		return asciiCharacter;
	}
	
	private void convertToAscii(char[] code) throws Exception {
		switch (code[0]) {
		case '4':
			switch (code[1]) {
			case '0':
				asciiCharacter = ' ';
				break;
			}
			break;
		case '8':
			switch (code[1]) {
			case '1':
				asciiCharacter = 'a';
				break;
			case '2':
				asciiCharacter = 'b';
				break;
			case '3':
				asciiCharacter = 'c';
				break;
			case '4':
				asciiCharacter = 'd';
				break;
			case '5':
				asciiCharacter = 'e';
				break;
			case '6':
				asciiCharacter = 'f';
				break;
			case '7':
				asciiCharacter = 'g';
				break;
			case '8':
				asciiCharacter = 'h';
				break;
			case '9':
				asciiCharacter = 'i';
				break;
			}
			break;
		case '9':
			switch (code[1]) {
			case '1':
				asciiCharacter = 'j';
				break;
			case '2':
				asciiCharacter = 'k';
				break;
			case '3':
				asciiCharacter = 'l';
				break;
			case '4':
				asciiCharacter = 'm';
				break;
			case '5':
				asciiCharacter = 'n';
				break;
			case '6':
				asciiCharacter = 'o';
				break;
			case '7':
				asciiCharacter = 'p';
				break;
			case '8':
				asciiCharacter = 'q';
				break;
			case '9':
				asciiCharacter = 'r';
				break;
			}
			break;
		case 'a':
			switch (code[1]) {
			case '2':
				asciiCharacter = 's';
				break;
			case '3':
				asciiCharacter = 't';
				break;
			case '4':
				asciiCharacter = 'u';
				break;
			case '5':
				asciiCharacter = 'v';
				break;
			case '6':
				asciiCharacter = 'w';
				break;
			case '7':
				asciiCharacter = 'x';
				break;
			case '8':
				asciiCharacter = 'y';
				break;
			case '9':
				asciiCharacter = 'z';
				break;
			}
			break;
		case 'c':
			switch (code[1]) {
			case '1':
				asciiCharacter = 'A';
				break;
			case '2':
				asciiCharacter = 'B';
				break;
			case '3':
				asciiCharacter = 'C';
				break;
			case '4':
				asciiCharacter = 'D';
				break;
			case '5':
				asciiCharacter = 'E';
				break;
			case '6':
				asciiCharacter = 'F';
				break;
			case '7':
				asciiCharacter = 'G';
				break;
			case '8':
				asciiCharacter = 'H';
				break;
			case '9':
				asciiCharacter = 'I';
				break;
			}
			break;
		case 'd':
			switch (code[1]) {
			case '1':
				asciiCharacter = 'J';
				break;
			case '2':
				asciiCharacter = 'K';
				break;
			case '3':
				asciiCharacter = 'L';
				break;
			case '4':
				asciiCharacter = 'M';
				break;
			case '5':
				asciiCharacter = 'N';
				break;
			case '6':
				asciiCharacter = 'O';
				break;
			case '7':
				asciiCharacter = 'P';
				break;
			case '8':
				asciiCharacter = 'Q';
				break;
			case '9':
				asciiCharacter = 'R';
				break;
			}
			break;
		case 'e':
			switch (code[1]) {
			case '2':
				asciiCharacter = 'S';
				break;
			case '3':
				asciiCharacter = 'T';
				break;
			case '4':
				asciiCharacter = 'U';
				break;
			case '5':
				asciiCharacter = 'V';
				break;
			case '6':
				asciiCharacter = 'W';
				break;
			case '7':
				asciiCharacter = 'X';
				break;
			case '8':
				asciiCharacter = 'Y';
				break;
			case '9':
				asciiCharacter = 'Z';
				break;
			}
			break;
		case 'f':
			switch (code[1]) {
			case '0':
				asciiCharacter = '0';
				break;
			case '1':
				asciiCharacter = '1';
				break;
			case '2':
				asciiCharacter = '2';
				break;
			case '3':
				asciiCharacter = '3';
				break;
			case '4':
				asciiCharacter = '4';
				break;
			case '5':
				asciiCharacter = '5';
				break;
			case '6':
				asciiCharacter = '6';
				break;
			case '7':
				asciiCharacter = '7';
				break;
			case '8':
				asciiCharacter = '8';
				break;
			case '9':
				asciiCharacter = '9';
			}
		}
		if(asciiCharacter == null)
			throw new Exception("No character found for code " + new String(code) + ".");
	}
}
