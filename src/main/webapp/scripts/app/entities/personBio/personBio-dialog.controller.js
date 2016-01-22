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

angular.module('huborcidApp').controller('PersonBioDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'PersonBio', 'Person',
        function($scope, $stateParams, $modalInstance, entity, PersonBio, Person) {

        $scope.personBio = entity;
        $scope.persons = Person.query();
        $scope.load = function(id) {
            PersonBio.get({id : id}, function(result) {
                $scope.personBio = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('huborcidApp:personBioUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.personBio.id != null) {
                PersonBio.update($scope.personBio, onSaveFinished);
            } else {
                PersonBio.save($scope.personBio, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
