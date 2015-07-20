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

angular.module('bsSwitchApp')
  .controller('MainCtrl', function ($scope, $log) {
    $scope.isSelected = 'nope';
    $scope.onText = 'Y';
    $scope.offText = 'N';
    $scope.isActive = true;
    $scope.size = 'normal';
    $scope.animate = true;
    $scope.radioOff = true;
    $scope.handleWidth = "auto";
    $scope.labelWidth = "auto";
    $scope.inverse = true;

    $scope.$watch('isSelected', function() {
      $log.info('Selection changed.');
    });

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
