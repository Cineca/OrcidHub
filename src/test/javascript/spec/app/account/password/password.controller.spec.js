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

describe('Controllers Tests ', function () {

    beforeEach(module('huborcidApp'));

    var $scope, $httpBackend, q, Auth;

    // define the mock Auth service
    beforeEach(function() {
        Auth = {
            changePassword: function() {}
        };
    });

    beforeEach(inject(function ($rootScope, $controller, $q, $injector) {
        $scope = $rootScope.$new();
        q = $q;
        $httpBackend = $injector.get('$httpBackend');
        $controller('PasswordController', {$scope: $scope, Auth: Auth});
    }));

    describe('PasswordController', function () {
        it('should show error if passwords do not match', function () {
            //GIVEN
            $scope.password = 'password1';
            $scope.confirmPassword = 'password2';
            //WHEN
            $scope.changePassword();
            //THEN
            expect($scope.doNotMatch).toBe('ERROR');
        });
        it('should call Service and set OK on Success', function () {
            //GIVEN
            var pass = 'myPassword';
            $scope.password = pass;
            $scope.confirmPassword = pass;

            spyOn(Auth, 'changePassword').and.returnValue(new function(){
                var deferred = q.defer();
                $scope.error = null;
                $scope.success = 'OK';
                return deferred.promise;
            });

            //WHEN
            $scope.changePassword();

            //THEN
            expect($scope.error).toBeNull();
            expect($scope.success).toBe('OK');
        });
    });
});
