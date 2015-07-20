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
// Quota Storage Management API
// This API can be used to check how much quota an origin is using and request more

// Currently only implemented in Chrome.
// https://developers.google.com/chrome/whitepapers/storage
// By Addy Osmani

Modernizr.addTest('quotamanagement', function(){
  var storage = Modernizr.prefixed('StorageInfo', window);
  return !!(storage && 'TEMPORARY' in storage && 'PERSISTENT' in storage);
});
