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
// Test for (experimental) Content Security Policy 1.1 support.
//
// This feature is still quite experimental, but is available now in Chrome 22.
// If the `SecurityPolicy` property is available, you can be sure the browser
// supports CSP. If it's not available, the browser still might support an
// earlier version of the CSP spec.
//
// Editor's Draft: https://dvcs.w3.org/hg/content-security-policy/raw-file/tip/csp-specification.dev.html

Modernizr.addTest('contentsecuritypolicy', ('securityPolicy' in document || 'SecurityPolicy' in document));
