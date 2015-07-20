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
    .controller('ApplicationController', function ($scope, Application, UserOrcid, ParseLinks) {
        $scope.applications = [];
        $scope.userorcids = UserOrcid.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Application.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.applications = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
            Application.get({id: id}, function(result) {
                $scope.application = result;
                $('#saveApplicationModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.application.id != null) {
                Application.update($scope.application,
                    function () {
                        $scope.refresh();
                    });
            } else {
                Application.save($scope.application,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
            Application.get({id: id}, function(result) {
                $scope.application = result;
                $('#deleteApplicationConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Application.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteApplicationConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#saveApplicationModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.application = {applicationID: null, applicationSecret: null, name: null, urlCallback: null, orgUnit: null, email: null, urlNotify: null, notifyUsername: null, notifyPassword: null, allOrg: null, helpURL: null, helpMail: null, description: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
