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
    .config(function ($stateProvider) {
        $stateProvider
            .state('application', {
                parent: 'entity',
                url: '/application',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'huborcidApp.application.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/application/applications.html',
                        controller: 'ApplicationController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('application');
                        return $translate.refresh();
                    }]
                }
            })
            .state('applicationDetail', {
                parent: 'entity',
                url: '/application/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'huborcidApp.application.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/application/application-detail.html',
                        controller: 'ApplicationDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('application');
                        return $translate.refresh();
                    }]
                }
            });
    });
