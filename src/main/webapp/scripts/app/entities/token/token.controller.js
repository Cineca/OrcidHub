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
    .controller('TokenController', function ($scope, Token, Person, Application, ParseLinks) {
        $scope.tokens = [];
        $scope.persons = Person.query();
        $scope.applications = Application.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Token.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.tokens = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
            Token.get({id: id}, function(result) {
                $scope.token = result;
                $('#saveTokenModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.token.id != null) {
                Token.update($scope.token,
                    function () {
                        $scope.refresh();
                    });
            } else {
                Token.save($scope.token,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
            Token.get({id: id}, function(result) {
                $scope.token = result;
                $('#deleteTokenConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Token.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteTokenConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#saveTokenModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.token = {dateReleased: null, dateUsed: null, ott: null, urlCallback: null, orgUnit: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
