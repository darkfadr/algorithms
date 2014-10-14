(function(){

	function MergeSort(arr){
		this._arr = arr;
		this.recursive_sort(arr);
	}

	MergeSort.prototype = {
		constructor: MergeSort,
		sort: function(arr){
			var work = [];

			if (arr.length < 2)
				return arr;

			for(var i =0; i < arr.length; i++){
				work.push(arr[i]);
			}

			work.push([]);

			for(var lim = arr.length; lim > 1; lim = Math.floor((lim+1)/2)){
				for(var j=0,k=0; k < lim; j++, k+=2){
					work[j] = merge(work[k], work[k+1]);
				}
				work[j] = [];  //in case of odd number
			}

			return work[0];
		},
		recursive_sort: function(arr){
			if(arr.length < 2)
				return arr;

			var mid 	= Math.floor(ar.length/2),
				left 	= arr.slice(0, mid),
				right	= arr.slice(mid);

			return recursive_sort(merge(left), merge(right));
		},
		merge: function(left, right){
			var result = [];

			while(left.length > 0 && right.length > 0){
				if(left[0] < right[0]){
					result.push(left.shift());
				} else{
					result.push(right.shift());
				}
			}

			result = result.concat(left).concat(right);

			left.splice(0, left.length);
			right.splice(0, right.length);

			return result;
		},
		toString: function(){
			console.dir(this._arr);
		}
	}
});