/**
 * Main App.js
 */
(function() {

	'use strict';

	angular
			.module('AdServerApp', [])
			.config(
					[
							'$httpProvider',
							function($httpProvider) {
								$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
								$httpProvider.defaults.headers.common['Pragma'] = 'no-cache';
							} ]);

})();