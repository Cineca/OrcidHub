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

// Grab the WebGL extensions currently supported and add to the Modernizr.webgl object
// spec: www.khronos.org/registry/webgl/specs/latest/#5.13.14

// based on code from ilmari heikkinen
// code.google.com/p/graphics-detect/source/browse/js/detect.js


(function(){

    if (!Modernizr.webgl) return;

    var canvas, ctx, exts;

    try {
        canvas  = document.createElement('canvas');
        ctx     = canvas.getContext('webgl') || canvas.getContext('experimental-webgl');
        exts    = ctx.getSupportedExtensions();
    }
    catch (e) {
        return;
    }

    if (ctx === undefined) {
        Modernizr.webgl = new Boolean(false);
    }
    else {
        Modernizr.webgl = new Boolean(true);
    }


    for (var i = -1, len = exts.length; ++i < len; ){
        Modernizr.webgl[exts[i]] = true;
    }

    // hack for addressing modernizr testsuite failures. sorry.
    if (window.TEST && TEST.audvid){
        TEST.audvid.push('webgl');
    }

    canvas = undefined;
})();