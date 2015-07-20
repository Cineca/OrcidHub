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

angular.module('huborcidApp')
    .controller('Main2Controller', function ($scope,$stateParams, $rootScope, Principal, $http) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            $http.get('oauth/apps/'+$stateParams.id).
            success(function(data, status, headers, config) {
            	$scope.appsdata = data;
            	$rootScope.helplink = data.urlHelp;
            }).
            error(function(data, status, headers, config) {
              // called asynchronously if an error occurs
              // or server returns response with an error status.
            });
            $scope.isSelected = 'yep';
            $scope.onText = 'Sì';
            $scope.offText = 'No';
            $scope.isActive = true;
            $scope.size = 'normal';
            $scope.animate = true;
            $scope.radioOff = true;
            $scope.handleWidth = "auto";
            $scope.labelWidth = "auto";
            $scope.inverse = true;


            $scope.toggle = function() {
              $scope.isSelected = $scope.isSelected === 'yep' ? 'nope' : 'yep';
            };

            $scope.setUndefined = function() {
              $scope.isSelected = undefined;
            };

            $scope.toggleActivation = function() {
              $scope.isActive = !$scope.isActive;
            }
        });
    });
