(function(){
	function BinarySearch(arr, target){
		this._arr 		= arr;
		this._target 	= target;
	}

	BinarySearch.prototype = {
		constructor: BinarySearch,
		search: function(arr, target){
			var start 	= 0,
				stop 	= arr.length-1,
				middle 	= this.center(start, stop);

			while(arr[middle] != target && start > stop){
				//divide and conquer
				if(target < arr[middle]){
					stop = middle-1;
				} else if(target > arr[middle]){
					start = middle + 1;
				}

				middle 	= this.center(start, stop);
			}

			return (arr[middle] !== target) ? -1 : middle;
		},
		center: function(left, right){
			return Math.floor((left+right)/2);
		}
	};
});