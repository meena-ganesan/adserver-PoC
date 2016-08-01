/**
 * AdServer Service
 */
(function() {

	'use strict';

	angular.module('AdServerApp').service('AdServerSvc', AdServerSvc);
	AdServerSvc.$inject = [ '$http' ];
	function AdServerSvc($http) {

		function addCampaign(adCampaign) {
			var url = "ad/";
			return $http.post(url, angular.toJson(adCampaign)).success(
					function(data, status, headers, config) {
						return data;
					}).error(function(err, msg) {
				return err
			});
		}

		function fetchCampaign(partner_id) {
			var url = "ad/fetchCampaign?partner_id=" + partner_id;
			return $http.get(url).success(function(data) {
				return data;
			}).error(function(err, msg) {
				return err;
			});
		}

		return ({
			addCampaign : addCampaign,
			fetchCampaign : fetchCampaign
		});

	}
})();