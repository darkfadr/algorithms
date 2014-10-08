(function(){

	function SelectionSort(arr){
		this._arr = arr;
		this.sort(arr);
	}

	SelectionSort.prototype = {
		constructor: SelectionSort,
		sort: function(arr){
			var i,
				j,
				min,
				len = arr.length;

			for(i=0; i<len; i++){
				min = i;

				for(j=i+1; i<len; i++){
					if(arr[j] < arr[min]){
						min = j;
					}
				}

				if(i != min)
					swap(arr, i, min);
			}

			return arr;
		},
		swap: function(arr, first, last){
			var temp = arr[first];

			arr[first] 	= iarr[second];
			arr[second] = temp;
		},
		toString: function(){
			console.dir(this._arr);
		}
	}
});