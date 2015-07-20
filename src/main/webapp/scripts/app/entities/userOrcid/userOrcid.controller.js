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
    .controller('UserOrcidController', function ($scope, UserOrcid, ParseLinks) {
        $scope.userOrcids = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            UserOrcid.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.userOrcids = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
            UserOrcid.get({id: id}, function(result) {
                $scope.userOrcid = result;
                $('#saveUserOrcidModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.userOrcid.id != null) {
                UserOrcid.update($scope.userOrcid,
                    function () {
                        $scope.refresh();
                    });
            } else {
                UserOrcid.save($scope.userOrcid,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
            UserOrcid.get({id: id}, function(result) {
                $scope.userOrcid = result;
                $('#deleteUserOrcidConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            UserOrcid.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteUserOrcidConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#saveUserOrcidModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.userOrcid = {username: null, passwordPlain: null, passwordHash: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
