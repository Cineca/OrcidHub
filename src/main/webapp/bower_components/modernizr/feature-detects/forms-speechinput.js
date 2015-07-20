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
// speech input for inputs
// by @alrra


// `webkitSpeech` in elem 
// doesn`t work correctly in all versions of Chromium based browsers.
//   It can return false even if they have support for speech i.imgur.com/2Y40n.png
//  Testing with 'onwebkitspeechchange' seems to fix this problem

// this detect only checks the webkit version because
// the speech attribute is likely to be deprecated in favor of a JavaScript API.
// http://lists.w3.org/Archives/Public/public-webapps/2011OctDec/att-1696/speechapi.html

// FIXME: add support for detecting the new spec'd behavior

Modernizr.addTest('speechinput', function(){
    var elem = document.createElement('input'); 
    return 'speech' in elem || 'onwebkitspeechchange' in elem; 
});