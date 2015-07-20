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

describe('Directive Tests ', function () {

    beforeEach(module('huborcidApp'));

    var elm, scope, $httpBackend;

    beforeEach(inject(function($compile, $rootScope, $injector) {
        $httpBackend = $injector.get('$httpBackend');

        var html = '<password-strength-bar password-to-check="password"></password-strength-bar>';
        scope = $rootScope.$new();
        elm = angular.element(html);
        $compile(elm)(scope);

        $httpBackend.expectGET(/api\/account\?cacheBuster=\d+/).respond({});
        $httpBackend.expectGET('scripts/components/navbar/navbar.html').respond({});
        $httpBackend.expectGET('scripts/components/navbar2/navbar2.html').respond({});
        
        $httpBackend.expectGET('i18n/it/global.json').respond({});
        $httpBackend.expectGET('i18n/it/language.json').respond({});
        $httpBackend.expectGET('i18n/it/main.json').respond({});
        
        $httpBackend.expectGET('scripts/app/main/main.html').respond({});
    }));

    afterEach(function() {
        $httpBackend.flush();
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    describe('Password strength', function () {
        it("Should display the password strength bar", function() {
            expect(elm.find('ul').length).toEqual(1);
            expect(elm.find('li').length).toEqual(5);
        });

        it("Should change the first 2 points of the strength bar", function() {
            scope.$apply(function() {
                scope.password = "morethan5chars"; // that should trigger the 2 first points
            });

            var firstpointStyle = elm.find('ul').children('li')[0].getAttribute('style');
            expect(firstpointStyle).toContain('background-color: rgb(255, 153, 0)');

            var secondpointStyle = elm.find('ul').children('li')[1].getAttribute('style');
            expect(secondpointStyle).toContain('background-color: rgb(255, 153, 0)');

            var thirdpointStyle = elm.find('ul').children('li')[2].getAttribute('style');
            expect(thirdpointStyle).toContain('background-color: rgb(221, 221, 221)');
        });

        it("Should change the first 4 points of the strength bar", function() {
            scope.$apply(function() {
                scope.password = "mo5ch$=!"; // that should trigger the 3 first points
            });

            var firstpointStyle = elm.find('ul').children('li')[0].getAttribute('style');
            dump(firstpointStyle);
            expect(firstpointStyle).toContain('background-color: rgb(153, 255, 0)');

            var secondpointStyle = elm.find('ul').children('li')[1].getAttribute('style');
            expect(secondpointStyle).toContain('background-color: rgb(153, 255, 0)');

            var thirdpointStyle = elm.find('ul').children('li')[2].getAttribute('style');
            expect(thirdpointStyle).toContain('background-color: rgb(153, 255, 0)');

            var fourthpointStyle = elm.find('ul').children('li')[3].getAttribute('style');
            expect(fourthpointStyle).toContain('background-color: rgb(153, 255, 0)');

            var fifthpointStyle = elm.find('ul').children('li')[4].getAttribute('style');
            expect(fifthpointStyle).toContain('background-color: rgb(221, 221, 221)');
        });
    });
});
