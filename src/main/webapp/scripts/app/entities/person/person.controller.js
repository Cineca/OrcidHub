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
    .controller('PersonController', function ($scope, Person, ParseLinks) {
        $scope.persons = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            Person.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.persons = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
            Person.get({id: id}, function(result) {
                $scope.person = result;
                $('#savePersonModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.person.id != null) {
                Person.update($scope.person,
                    function () {
                        $scope.refresh();
                    });
            } else {
                Person.save($scope.person,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
            Person.get({id: id}, function(result) {
                $scope.person = result;
                $('#deletePersonConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Person.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePersonConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#savePersonModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.person = {localID: null, firstName: null, lastName: null, email: null, orcid: null, orcidReleaseDate: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
