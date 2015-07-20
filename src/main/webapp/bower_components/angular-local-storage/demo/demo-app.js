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
'use strict';
window.angular.module('demoModule', ['LocalStorageModule'])
.config(function(localStorageServiceProvider){
  localStorageServiceProvider.setPrefix('demoPrefix');
  // localStorageServiceProvider.setStorageCookieDomain('example.com');
  // localStorageServiceProvider.setStorageType('sessionStorage');
})
.controller('DemoCtrl',
  function($scope, localStorageService) {
    $scope.localStorageDemo = localStorageService.get('localStorageDemo');

    $scope.$watch('localStorageDemo', function(value){
      localStorageService.set('localStorageDemo',value);
      $scope.localStorageDemoValue = localStorageService.get('localStorageDemo');
    });

    $scope.storageType = 'Local storage';

    if (localStorageService.getStorageType().indexOf('session') >= 0) {
      $scope.storageType = 'Session storage';
    }

    if (!localStorageService.isSupported) {
      $scope.storageType = 'Cookie';
    }

    $scope.$watch(function(){
      return localStorageService.get('localStorageDemo');
    }, function(value){
      $scope.localStorageDemo = value;
    });

    $scope.clearAll = localStorageService.clearAll;
  }
);
