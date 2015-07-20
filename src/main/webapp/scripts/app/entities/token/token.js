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
            .state('token', {
                parent: 'entity',
                url: '/token',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'huborcidApp.token.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/token/tokens.html',
                        controller: 'TokenController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('token');
                        return $translate.refresh();
                    }]
                }
            })
            .state('tokenDetail', {
                parent: 'entity',
                url: '/token/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'huborcidApp.token.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/token/token-detail.html',
                        controller: 'TokenDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('token');
                        return $translate.refresh();
                    }]
                }
            });
    });
