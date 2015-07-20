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

// binaryType is truthy if there is support.. returns "blob" in new-ish chrome.
// plus.google.com/115535723976198353696/posts/ERN6zYozENV
// github.com/Modernizr/Modernizr/issues/370

Modernizr.addTest('websocketsbinary', function() {
  var protocol = 'https:'==location.protocol?'wss':'ws',
  protoBin;

  if('WebSocket' in window) {
    if( protoBin = 'binaryType' in WebSocket.prototype ) {
      return protoBin;
    }
    try {
      return !!(new WebSocket(protocol+'://.').binaryType);
    } catch (e){}
  }

  return false;
});
