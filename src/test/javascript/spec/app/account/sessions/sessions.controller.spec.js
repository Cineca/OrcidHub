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

    describe('SessionsController', function () {
        var $scope, SessionsService;

        beforeEach(inject(function ($rootScope, $controller, Sessions) {
            $scope = $rootScope.$new();

            SessionsService = Sessions;
            $controller('SessionsController',{$scope:$scope, Sessions:SessionsService});
        }));

        it('should invalidate session', function () {
            //GIVEN
            $scope.series = "123456789";

            //SET SPY
            spyOn(SessionsService, 'delete');

            //WHEN
            $scope.invalidate($scope.series);

            //THEN
            expect(SessionsService.delete).toHaveBeenCalled();
            expect(SessionsService.delete).toHaveBeenCalledWith({series: "123456789"}, jasmine.any(Function), jasmine.any(Function));

            //SIMULATE SUCCESS CALLBACK CALL FROM SERVICE
            SessionsService.delete.calls.mostRecent().args[1]();
            expect($scope.error).toBeNull();
            expect($scope.success).toBe('OK');
        });
    });
});
