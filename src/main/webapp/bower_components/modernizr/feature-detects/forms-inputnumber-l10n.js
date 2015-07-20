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
// input[type="number"] localized input/output
// // Detects whether input type="number" is capable of receiving and
// // displaying localized numbers, e.g. with comma separator
// // https://bugs.webkit.org/show_bug.cgi?id=42484
// // Based on http://trac.webkit.org/browser/trunk/LayoutTests/fast/forms/script-tests/input-number-keyoperation.js?rev=80096#L9
// // By Peter Janes

Modernizr.addTest('localizedNumber', function() {
    var doc = document,
        el = document.createElement('div'),
        fake,
        root,
        input,
        diff;
    root = doc.body || (function() {
        var de = doc.documentElement;
        fake = true;
        return de.insertBefore(doc.createElement('body'), de.firstElementChild || de.firstChild);
    }());
    el.innerHTML = '<input type="number" value="1.0" step="0.1"/>';
    input = el.childNodes[0];
    root.appendChild(el);
    input.focus();
    try {
        doc.execCommand('InsertText', false, '1,1');
    } catch(e) { // prevent warnings in IE
    }
    diff = input.type === 'number' && input.valueAsNumber === 1.1 && input.checkValidity();
    root.removeChild(el);
    fake && root.parentNode.removeChild(root);
    return diff;
});
