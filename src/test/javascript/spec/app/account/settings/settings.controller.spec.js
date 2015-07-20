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

    var $scope, q, Principal, Auth;

    // define the mock Auth service
    beforeEach(function() {
        Auth = {
            updateAccount: function() {}
        };

        Principal = {
            identity: function() {
                var deferred = q.defer();
                return deferred.promise;
            }
        };
    });


    describe('SettingsController', function () {

        beforeEach(inject(function ($rootScope, $controller, $q) {
            $scope = $rootScope.$new();
            q = $q;
            $controller('SettingsController',{$scope:$scope, Principal:Principal, Auth:Auth});
        }));

        it('should save account', function () {
            //GIVEN
            var account = {firstName: "John", lastName: "Doe"};
            $scope.settingsAccount = account;

            //SET SPY
            spyOn(Principal, 'identity').and.callThrough();

            spyOn(Auth, 'updateAccount').and.returnValue(new function(){
                var deferred = q.defer();
                $scope.error = null;
                $scope.success = 'OK';
                return deferred.promise;
            });


            //WHEN
            $scope.save();

            //THEN
            expect($scope.error).toBeNull();
            expect($scope.success).toBe('OK');
        });
    });
});
