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

// Avoid `console` errors in browsers that lack a console
if (!(window.console && console.log)) {
    (function() {
        var noop = function() {};
        var methods = ['assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error', 'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log', 'markTimeline', 'profile', 'profileEnd', 'markTimeline', 'table', 'time', 'timeEnd', 'timeStamp', 'trace', 'warn'];
        var length = methods.length;
        var console = window.console = {};
        while (length--) {
            console[methods[length]] = noop;
        }
    }());
}

// test helper object
window.TEST = {
  // note some unique members of the Modernizr object
  inputs    : ['input','inputtypes', 'textarea'],
  audvid    : ['video','audio'],
  API       : ['addTest', 'mq', 'hasEvent', 'testProp', 'testAllProps', 'testStyles', '_prefixes', '_domPrefixes', '_cssomPrefixes', 'prefixed'],
  extraclass: ['js'],
  privates  : ['_enableHTML5','_version','_fontfaceready'],
  deprecated : [
                { oldish : 'crosswindowmessaging', newish : 'postmessage'},
                { oldish : 'historymanagement', newish : 'history'},
              ],

  // utility methods
  inArray: function(elem, array) {
      if (array.indexOf) {
          return array.indexOf(elem);
      }
      for (var i = 0, length = array.length; i < length; i++) {
          if (array[i] === elem) {
              return i;
          }
      }
      return -1;
  },
  trim : function(str){
    return str.replace(/^\s*/, "").replace(/\s*$/, "");
  }
};

