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
// Animated PNG
// http://en.wikipedia.org/wiki/APNG
// By Addy Osmani
(function () {

    if (!Modernizr.canvas) return false;
    
    var image = new Image(),
        canvas = document.createElement('canvas'),
        ctx = canvas.getContext('2d');


    image.onload = function () {
        Modernizr.addTest('apng', function () {
            if (typeof canvas.getContext == 'undefined') {
                return false;
            } else {
                ctx.drawImage(image, 0, 0);
                return ctx.getImageData(0, 0, 1, 1).data[3] === 0;
            }
        });
    };

    image.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAACGFjVEwAAAABAAAAAcMq2TYAAAANSURBVAiZY2BgYPgPAAEEAQB9ssjfAAAAGmZjVEwAAAAAAAAAAQAAAAEAAAAAAAAAAAD6A+gBAbNU+2sAAAARZmRBVAAAAAEImWNgYGBgAAAABQAB6MzFdgAAAABJRU5ErkJggg==";

}());
