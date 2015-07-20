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
// CSS Regions
// http://www.w3.org/TR/css3-regions/
// By: Mihai Balan

// We start with a CSS parser test then we check page geometry to see if it's affected by regions
// Later we might be able to retire the second part, as WebKit builds with the false positives die out

Modernizr.addTest('regions', function() {

	/* Get the 'flowFrom' property name available in the browser. Either default or vendor prefixed.
	If the property name can't be found we'll get Boolean 'false' and fail quickly */
	var flowFromProperty = Modernizr.prefixed("flowFrom"),
		flowIntoProperty = Modernizr.prefixed("flowInto");

	if (!flowFromProperty || !flowIntoProperty){
		return false;
	}

	/* If CSS parsing is there, try to determine if regions actually work. */
	var container		= document.createElement('div'),
		content			= document.createElement('div'),
		region			= document.createElement('div'),

	/* we create a random, unlikely to be generated flow number to make sure we don't
	clash with anything more vanilla, like 'flow', or 'article', or 'f1' */
	flowName = 'modernizr_flow_for_regions_check';

	/* First create a div with two adjacent divs inside it. The first will be the
	content, the second will be the region. To be able to distinguish between the two,
	we'll give the region a particular padding */
	content.innerText		= 'M';
	container.style.cssText	= 'top: 150px; left: 150px; padding: 0px;';
	region.style.cssText	= 'width: 50px; height: 50px; padding: 42px;';

	region.style[flowFromProperty] = flowName;
	container.appendChild(content);
	container.appendChild(region);
	document.documentElement.appendChild(container);

	/* Now compute the bounding client rect, before and after attempting to flow the
	content div in the region div. If regions are enabled, the after bounding rect
	should reflect the padding of the region div.*/
	var flowedRect, delta,
		plainRect = content.getBoundingClientRect();


	content.style[flowIntoProperty] = flowName;
	flowedRect = content.getBoundingClientRect();

	delta = flowedRect.left - plainRect.left;
	document.documentElement.removeChild(container);
	content = region = container = undefined;

	return (delta == 42);
});
