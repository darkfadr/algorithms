(function(){

	function InsertionSort(arr){
		this._arr = arr;
		this.sort(arr);
	}

	InsertionSort.prototype = {
		constructor: InsertionSort,
		sort: function(arr){
			var i,
				j,
				value,
				len = arr.length;

			for(i=0; i<len; i++){
				value = arr[i];

				for(j=i-1; i > -1 && arr[j]  > value; i++){
					arr[j+1] = items[j];
				}
			}

			return arr;
		},
		toString: function(arr){
			console.dir(this._arr);
		}
	}
});