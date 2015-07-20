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
    .controller('ReportatController', function ($scope,Reportat, RelPersonApplication, Person, Application, Token, ParseLinks) {
        $scope.relPersonApplications = [];
        //$scope.persons = Person.query();
        //$scope.applications = Application.query();
        //$scope.tokens = Token.query();
        $scope.page = 1;
        $scope.linkDownloadExcel = "api/reportat/downloadExcel";
        $scope.loadAll = function() {
        	Reportat.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.relPersonApplications = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
        	Reportat.get({id: id}, function(result) {
                $scope.relPersonApplication = result;
                $('#saveRelPersonApplicationModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.relPersonApplication.id != null) {
            	Reportat.update($scope.relPersonApplication,
                    function () {
                        $scope.refresh();
                    });
            } else {
            	Reportat.save($scope.relPersonApplication,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
        	Reportat.get({id: id}, function(result) {
                $scope.relPersonApplication = result;
                $('#deleteRelPersonApplicationConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
        	Reportat.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteRelPersonApplicationConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#saveRelPersonApplicationModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.relPersonApplication = {oauthAccessToken: null, dateReleased: null, dateDenied: null, valid: null, denied: null, errorDescription: null, notified: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
