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
    .controller('EnvVariableController', function ($scope, EnvVariable) {
        $scope.envVariables = [];
        $scope.loadAll = function() {
            EnvVariable.query(function(result) {
               $scope.envVariables = result;
            });
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
            EnvVariable.get({id: id}, function(result) {
                $scope.envVariable = result;
                $('#saveEnvVariableModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.envVariable.id != null) {
                EnvVariable.update($scope.envVariable,
                    function () {
                        $scope.refresh();
                    });
            } else {
                EnvVariable.save($scope.envVariable,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
            EnvVariable.get({id: id}, function(result) {
                $scope.envVariable = result;
                $('#deleteEnvVariableConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            EnvVariable.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteEnvVariableConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#saveEnvVariableModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.envVariable = {name: null, variableValue: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
