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
// by jussi-kalliokoski


// This test is asynchronous. Watch out.

// The test will potentially add garbage to console.

(function(){
  try {
    // we're avoiding using Modernizr._domPrefixes as the prefix capitalization on
    // these guys are notoriously peculiar.
    var BlobBuilder = window.MozBlobBuilder || window.WebKitBlobBuilder || window.MSBlobBuilder || window.OBlobBuilder || window.BlobBuilder;
    var URL         = window.MozURL || window.webkitURL || window.MSURL || window.OURL || window.URL;
    var data    = 'Modernizr',
        blob,
        bb,
        worker,
        url,
        timeout,
        scriptText = 'this.onmessage=function(e){postMessage(e.data)}';

    try {
      blob = new Blob([scriptText], {type:'text/javascript'});
    } catch(e) {
      // we'll fall back to the deprecated BlobBuilder
    }
    if (!blob) {
      bb = new BlobBuilder();
      bb.append(scriptText);
      blob = bb.getBlob();
    }

    url = URL.createObjectURL(blob);
    worker = new Worker(url);

    worker.onmessage = function(e) {
      Modernizr.addTest('blobworkers', data === e.data);
      cleanup();
    };

    // Just in case...
    worker.onerror = fail;
    timeout = setTimeout(fail, 200);

    worker.postMessage(data);
  } catch (e) {
    fail();
  }

  function fail() {
    Modernizr.addTest('blobworkers', false);
    cleanup();
  }

  function cleanup() {
    if (url) {
      URL.revokeObjectURL(url);
    }
    if (worker) {
      worker.terminate();
    }
    if (timeout) {
      clearTimeout(timeout);
    }
  }
}());
