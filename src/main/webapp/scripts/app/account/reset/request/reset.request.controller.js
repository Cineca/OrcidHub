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
    .controller('RequestResetController', function ($rootScope, $scope, $state, $timeout, Auth) {

        $scope.success = null;
        $scope.error = null;
        $scope.errorEmailNotExists = null;
        $scope.resetAccount = {};
        $timeout(function (){angular.element('[ng-model="resetAccount.email"]').focus();});

        $scope.requestReset = function () {

            $scope.error = null;
            $scope.errorEmailNotExists = null;

            Auth.resetPasswordInit($scope.resetAccount.email).then(function () {
                $scope.success = 'OK';
            }).catch(function (response) {
                $scope.success = null;
                if (response.status === 400 && response.data === 'e-mail address not registered') {
                    $scope.errorEmailNotExists = 'ERROR';
                } else {
                    $scope.error = 'ERROR';
                }
            });
        }

    });
