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
    .controller('ResultOrcidWorkController', function ($scope, ResultOrcidWork, Application) {
        $scope.resultOrcidWorks = [];
        $scope.applications = Application.query();
        $scope.loadAll = function() {
            ResultOrcidWork.query(function(result) {
               $scope.resultOrcidWorks = result;
            });
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
            ResultOrcidWork.get({id: id}, function(result) {
                $scope.resultOrcidWork = result;
                $('#saveResultOrcidWorkModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.resultOrcidWork.id != null) {
                ResultOrcidWork.update($scope.resultOrcidWork,
                    function () {
                        $scope.refresh();
                    });
            } else {
                ResultOrcidWork.save($scope.resultOrcidWork,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
            ResultOrcidWork.get({id: id}, function(result) {
                $scope.resultOrcidWork = result;
                $('#deleteResultOrcidWorkConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            ResultOrcidWork.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteResultOrcidWorkConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#saveResultOrcidWorkModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.resultOrcidWork = {fileNameUpload: null, status: null, withErrors: null, fileResult: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
