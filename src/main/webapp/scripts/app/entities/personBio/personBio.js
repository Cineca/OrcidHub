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
            .state('personBio', {
                parent: 'entity',
                url: '/personBios',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'huborcidApp.personBio.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/personBio/personBios.html',
                        controller: 'PersonBioController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('personBio');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('personBio.detail', {
                parent: 'entity',
                url: '/personBio/{id}',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'huborcidApp.personBio.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/personBio/personBio-detail.html',
                        controller: 'PersonBioDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('personBio');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'PersonBio', function($stateParams, PersonBio) {
                        return PersonBio.get({id : $stateParams.id});
                    }]
                }
            })
            .state('personBio.new', {
                parent: 'personBio',
                url: '/new',
                data: {
                    roles: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/personBio/personBio-dialog.html',
                        controller: 'PersonBioDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {biography: null, researcher_urls: null, external_identifiers: null, id: null};
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('personBio', null, { reload: true });
                    }, function() {
                        $state.go('personBio');
                    })
                }]
            })
            .state('personBio.edit', {
                parent: 'personBio',
                url: '/{id}/edit',
                data: {
                    roles: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/personBio/personBio-dialog.html',
                        controller: 'PersonBioDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['PersonBio', function(PersonBio) {
                                return PersonBio.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('personBio', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
