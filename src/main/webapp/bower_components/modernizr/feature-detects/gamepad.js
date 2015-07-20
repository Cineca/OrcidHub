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
// GamePad API
// https://dvcs.w3.org/hg/gamepad/raw-file/default/gamepad.html
// By Eric Bidelman

// FF has Gamepad API support only in special builds, but not in any release (even behind a flag)
// Their current implementation has no way to feature detect, only events to bind to.
//   http://www.html5rocks.com/en/tutorials/doodles/gamepad/#toc-featuredetect

// but a patch will bring them up to date with the spec when it lands (and they'll pass this test)
//   https://bugzilla.mozilla.org/show_bug.cgi?id=690935

Modernizr.addTest('gamepads', !!Modernizr.prefixed('getGamepads', navigator));
