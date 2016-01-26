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
            .state('resultUploadOrcidEntity', {
                parent: 'entity',
                url: '/resultUploadOrcidEntity',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'huborcidApp.resultUploadOrcidEntity.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/resultUploadOrcidEntity/resultUploadOrcidEntitys.html',
                        controller: 'ResultUploadOrcidEntityController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('resultUploadOrcidEntity');
                        return $translate.refresh();
                    }]
                }
            })
            .state('resultUploadOrcidEntityDetail', {
                parent: 'entity',
                url: '/resultUploadOrcidEntity/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'huborcidApp.resultUploadOrcidEntity.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/resultUploadOrcidEntity/resultUploadOrcidEntity-detail.html',
                        controller: 'ResultUploadOrcidEntityDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('resultUploadOrcidEntity');
                        return $translate.refresh();
                    }]
                }
            });
    });
