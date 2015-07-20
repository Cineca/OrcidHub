/*
 * This file is part of huborcid.
 *
 * huborcid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * huborcid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with huborcid.  If not, see <http://www.gnu.org/licenses/>.
 */
// MathML
// http://www.w3.org/Math/ 
// By Addy Osmani
// Based on work by Davide (@dpvc) and David (@davidcarlisle)
// in https://github.com/mathjax/MathJax/issues/182

Modernizr.addTest('mathml', function(){
	var hasMathML = false;
	if ( document.createElementNS ) {
	var ns = "http://www.w3.org/1998/Math/MathML",
	    div = document.createElement("div");
	    div.style.position = "absolute"; 
	var mfrac = div.appendChild(document.createElementNS(ns,"math"))
	               .appendChild(document.createElementNS(ns,"mfrac"));
	mfrac.appendChild(document.createElementNS(ns,"mi"))
	     .appendChild(document.createTextNode("xx"));
	mfrac.appendChild(document.createElementNS(ns,"mi"))
	     .appendChild(document.createTextNode("yy"));
	document.body.appendChild(div);
	hasMathML = div.offsetHeight > div.offsetWidth;
	}
	return hasMathML;
});