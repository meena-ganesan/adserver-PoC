/**
 * AdServer Controller
 */
(function() {

	'use strict';

	angular.module('AdServerApp').controller('AdServerController',
			AdServerController);
	AdServerController.$inject = [ '$scope', '$http', 'AdServerSvc' ];
	function AdServerController($scope, $http, AdServerSvc) {
		$scope.resetFields = function() {
			$scope.error = null;
			$scope.status = null;
			$scope.adCampaign = null;
			$scope.partner_id = null;
			$scope.duration = null;
			$scope.ad_content = null;
		}
		$scope.OpSelect = function() {
			if (!$scope.adSvrOperation) {
				$scope.error = "Please select an operation to perform";
			} else if ($scope.adSvrOperation === 'add') {
				$scope.status = "";
				$scope.error = "";
				var adCampaign = {
					partnerId : $scope.partner_id,
					duration : $scope.duration,
					adContent : $scope.ad_content
				}
				AdServerSvc
						.addCampaign(adCampaign)
						.success(
								function(data) {
									if (data) {
										$scope.resetFields();
										var startDt = new Date(
												data.campaignStartDt);
										var endDt = new Date(data.campaignEndDt);
										$scope.adCampaign = {
											partnerId : data.partnerId,
											duration : data.duration,
											adContent : data.adContent,
											campaignStartDt : startDt,
											campaignEndDt : endDt
										};
										$scope.status = "Campaign Added successfully for Partner# "
												+ $scope.adCampaign.partnerId;
									} else {
										$scope.error = "Active Campaign Data found for Partner# "
												+ $scope.partner_id
												+ ". Could not update new campaign data.";
									}
								})
						.error(
								function(error) {
									$scope.error = "There was an error while adding Campaign for Partner# "
											+ $scope.partner_id;
								});
			} else if ($scope.adSvrOperation === 'fetch') {
				$scope.status = "";
				$scope.error = "";
				AdServerSvc
						.fetchCampaign($scope.partner_id)
						.success(
								function(data) {
									$scope.adCampaign = null;
									if (data) {
										var startDt = new Date(
												data.campaignStartDt);
										var endDt = new Date(data.campaignEndDt);
										$scope.adCampaign = {
											partnerId : data.partnerId,
											duration : data.duration,
											adContent : data.adContent,
											campaignStartDt : startDt,
											campaignEndDt : endDt
										};
										$scope.status = "Campaign Info fetched for Partner# "
												+ $scope.partner_id;
									} else {
										$scope.error = "No Active Campaign Data found for Partner# "
												+ $scope.partner_id;
									}
								})
						.error(
								function(error) {
									$scope.error = "Could not fetch Campaign Info for Partner# :"
											+ $scope.partner_id;
								});
			}
		};
	}
})();