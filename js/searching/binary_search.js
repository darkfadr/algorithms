(function(){
	function BinarySearch(){}

	BinarySearch.prototype = {
		search: function(arr, target, start, end){
			var middle = Math.floor((start+end)/2),
				value  = arr[middle];
			
			if(start > end)
				return -1; //invalid entry
			if(value > target)
				this.search(arr, target, start, middle-1);
			if(value < target)
				this.search(arr, target, middle+1, end);

			return middle;


		},
		getIndex: function(arr, target){
			return this.search(arr, target, 0, arr.length-1);	
		}
	};
});