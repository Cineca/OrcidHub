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
    .controller('PersonBioController', function ($scope, PersonBio) {
        $scope.personBios = [];
        $scope.loadAll = function() {
            PersonBio.query(function(result) {
               $scope.personBios = result;
            });
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            PersonBio.get({id: id}, function(result) {
                $scope.personBio = result;
                $('#deletePersonBioConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            PersonBio.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePersonBioConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.personBio = {biography: null, researcher_urls: null, external_identifiers: null, id: null};
        };
    });
