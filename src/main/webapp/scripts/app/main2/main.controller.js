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
    .controller('Main2Controller', function ($scope,$stateParams, $rootScope, Principal, $http, $modal,$log) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            
            $http.get('oauth/apps/'+$stateParams.id).
            success(function(data, status, headers, config) {
            	$scope.appsdata = data;
            	$rootScope.helplink = data.urlHelp;
            }).
            error(function(data, status, headers, config) {
              // called asynchronously if an error occurs
              // or server returns response with an error status.
            });

            
            
            $scope.isSelected = 'yep';
            $scope.onText = 'Sì';
            $scope.offText = 'No';
            $scope.isActive = true;
            $scope.size = 'normal';
            $scope.animate = true;
            $scope.radioOff = true;
            $scope.handleWidth = "auto";
            $scope.labelWidth = "auto";
            $scope.inverse = true;
            $scope.items = ['item1', 'item2', 'item3'];
            
            $scope.deleteApp = function (appId,index) {
                $http.get('oauth/delApp/'+$stateParams.id+'/'+appId).
                success(function(data, status, headers, config) {
                	$scope.appsdata.listApp.splice(index, 1);
                }).
                error(function(data, status, headers, config) {
                });
            };

        $scope.open = function (size) {

            var modalInstance = $modal.open({
              animation: $scope.animationsEnabled,
              templateUrl: 'myModalContent.html',
              controller: 'Main2ControllerIstance',
              size: size,
              resolve: {
            	  tokenId: function () {
                      return $stateParams.id;
                  }
              }
            });

            modalInstance.result.then(function (selectedItem) {
              $scope.addAppId = selectedItem;
              
              $http.get('oauth/addApp/'+$stateParams.id+'/'+$scope.addAppId).
              success(function(data, status, headers, config) {
            	  $scope.appsdata.listApp.push(data.app);
              }).
              error(function(data, status, headers, config) {
              });
              
                  
            }, function () {
              $log.info('Modal dismissed at: ' + new Date());
            });
          };
          
          
            
            
            $scope.toggle = function() {
              $scope.isSelected = $scope.isSelected === 'yep' ? 'nope' : 'yep';
            };

            $scope.setUndefined = function() {
              $scope.isSelected = undefined;
            };

            $scope.toggleActivation = function() {
              $scope.isActive = !$scope.isActive;
            }
        });
    });


angular.module('huborcidApp').controller('Main2ControllerIstance', function ($scope,$http, $modalInstance, tokenId) {
	 	
	    $http.get('oauth/newApps/'+tokenId).
	    success(function(data, status, headers, config) {
	    	$scope.items = data.listApp;
	    }).
	    error(function(data, status, headers, config) {
	    });
	    
	    $scope.selected2 = undefined;
	    $scope.selected = undefined;
	    

	  $scope.ok = function () {
		  if (typeof $scope.selected != 'undefined')
			  if (typeof $scope.selected.id != 'undefined')
				  $modalInstance.close($scope.selected.id);
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
	});
