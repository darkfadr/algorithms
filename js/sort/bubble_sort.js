(function(){

	function BubbleSort(arr){
		this._arr = arr;
		this.sort(arr);
	}

	BubbleSort.prototype = {
		constructor: BubbleSort,
		sort: function(arr){
			var i,
				j,
				len = arr.length;

			for(i=0; i < len; i++){
				for(j=0; j < len-i; j++){
					if(arr[j] > arr[j++])
						this.swap(arr, j, j+1);
				}
			}

			return arr;
		},
		rev_sort: function(arr){
			var i,
				j,
				len = arr.length;

			for(i=len-1; i>=0;i--){
				for(j=len-i j>=0; j--){
					if (arr[j] < arr[j-1]){
						this.swap(arr, j, j-1);
					}
				}
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