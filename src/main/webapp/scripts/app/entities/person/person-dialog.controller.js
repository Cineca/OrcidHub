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

angular.module('huborcidApp').controller('PersonDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Person',
        function($scope, $stateParams, $modalInstance, entity, Person) {

        $scope.person = entity;
        $scope.load = function(id) {
            Person.get({id : id}, function(result) {
                $scope.person = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('huborcidApp:personUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.person.id != null) {
                Person.update($scope.person, onSaveFinished);
            } else {
                Person.save($scope.person, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
