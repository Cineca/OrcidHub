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
    .controller('ResultUploadOrcidEntityController', function ($scope, ResultUploadOrcidEntity, Application) {
        $scope.resultUploadOrcidEntitys = [];
        $scope.applications = Application.query();
        $scope.loadAll = function() {
            ResultUploadOrcidEntity.query(function(result) {
               $scope.resultUploadOrcidEntitys = result;
            });
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
            ResultUploadOrcidEntity.get({id: id}, function(result) {
                $scope.resultUploadOrcidEntity = result;
                $('#saveResultUploadOrcidEntityModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.resultUploadOrcidEntity.id != null) {
                ResultUploadOrcidEntity.update($scope.resultUploadOrcidEntity,
                    function () {
                        $scope.refresh();
                    });
            } else {
                ResultUploadOrcidEntity.save($scope.resultUploadOrcidEntity,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
            ResultUploadOrcidEntity.get({id: id}, function(result) {
                $scope.resultUploadOrcidEntity = result;
                $('#deleteResultUploadOrcidEntityConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            ResultUploadOrcidEntity.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteResultUploadOrcidEntityConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#saveResultUploadOrcidEntityModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.resultUploadOrcidEntity = {fileNameUpload: null, status: null, withErrors: null, fileResult: null, entityType: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
