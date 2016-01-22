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
            .state('person', {
                parent: 'entity',
                url: '/persons',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'huborcidApp.person.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/person/persons.html',
                        controller: 'PersonController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('person');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('person.detail', {
                parent: 'entity',
                url: '/person/{id}',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'huborcidApp.person.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/person/person-detail.html',
                        controller: 'PersonDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('person');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Person', function($stateParams, Person) {
                        return Person.get({id : $stateParams.id});
                    }]
                }
            })
            .state('person.new', {
                parent: 'person',
                url: '/new',
                data: {
                    roles: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/person/person-dialog.html',
                        controller: 'PersonDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {localID: null, firstName: null, lastName: null, email: null, orcid: null, orcidReleaseDate: null, needUpdate: null, id: null};
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('person', null, { reload: true });
                    }, function() {
                        $state.go('person');
                    })
                }]
            })
            .state('person.edit', {
                parent: 'person',
                url: '/{id}/edit',
                data: {
                    roles: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/person/person-dialog.html',
                        controller: 'PersonDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Person', function(Person) {
                                return Person.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('person', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
