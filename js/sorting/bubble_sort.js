(function(){

	function BubbleSort(arr){
		this.init(arr);
	}

	BubbleSort.prototype = {
		swap: function(arr, first, last){
			var temp = arr[first];

			arr[first] 	= iarr[second];
			arr[second] = temp;
		},
		init: function(arr){
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
		countdown: function(arr){
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
		toString: function(arr){
			console.dir(arr);
		}
	}
});