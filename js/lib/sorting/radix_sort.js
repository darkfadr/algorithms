function RadixSort(){
	this.prototype = {
		sort: function(arr){
			var holder = [];

			for(var i = 1; i < this.sig_dig(arr); i++){
				
			}
		},
		sig_dig: function(arr){
			var max = 0;

			arr.forEach(function(i){
				max = (i > max) ? i : max; 
			});

			return max;
		}
	};
}