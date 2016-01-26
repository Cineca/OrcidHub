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
	.controller('UploadOrcidEntityController', ['$scope', '$translate', 'Upload', 'UploadOrcidEntity', 'ParseLinks', function ($scope, $translate, Upload, UploadOrcidEntity, ParseLinks) {
		$scope.linkDownloadTemplateExcelWorks = "assets/excel/template_upload_orcid_works.xls";
		$scope.linkDownloadTemplateExcelFunding = "assets/excel/template_upload_orcid_funding.xls";
		$scope.upload = function(file, typeEntity) {
			$scope.fileUp = file;
	        file.upload = Upload.upload({
	          url: 'api/uploadOrcidEntity/fileUpload/'+typeEntity,
	          data: {file: file},
	        });
	        
	        $scope.alerts = [];
	        
	        file.upload.then(function (response) {
	        	console.log(response);
	        	if(response.data.code == '001'){
	        		$scope.addAlert('success', $translate.instant(response.data.message, { fileName: file.name }));
	        	}else{
	        		$scope.addAlert('danger', $translate.instant(response.data.message, { fileName: file.name }));
	        	}
	        	$scope.loadAll();
	          },function (resp) {
	        	  $scope.addAlert('danger', resp.status);
	        	  $scope.loadAll();
	          }, function (evt) {
	        	  var progressPercentage = parseInt(100.0 * evt.loaded / evt.total); 
	        	  file.progress = progressPercentage;
	        	  console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
	        	  $scope.loadAll();
	          });
        }
		$scope.page = 1;
		$scope.resultUploadOrcidEntities = [];
        $scope.loadAll = function() {
        	UploadOrcidEntity.query({page: $scope.page, per_page: 20}, function(result, headers) {
               $scope.links = ParseLinks.parse(headers('link'));
               $scope.resultUploadOrcidEntities = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();
        
        
        $scope.alerts = [];
	   
	    
	    $scope.closeAlert = function(index) {
	    	$scope.alerts.splice(index, 1);
	    };
	    
	    $scope.addAlert = function(type, msg) {
	    	$scope.alerts.push({ type: type, msg: msg });
        };
	}]);


		
