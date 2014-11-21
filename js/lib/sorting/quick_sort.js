function QuickSort(arr){
	this._arr = arr;
	this.sort(arr);
}

QuickSort.prototype = {
	sort: function(arr, left, right){
		var index;

		if(arr.length > 1){
			//in case left and right wasn't provided
			//can be replaces using .apply()
			left 	= typeof left != 'number' ? 0 : left;
			right 	= typeof right != 'number' ? arr.length -1 : right;

			index = this.partition(arr, left, right);

			if(left < index - 1){
				this.sort(arr, left, index - 1);
			}

			if(index < right){
				this.sort(arr, index, right);
			}
		}

		return arr;
	},
	partition: function(arr, left, right){
		var i = left,
			j = right,
			pivot = arr[Math.floor(left + right)/2];

		while(left <= right{
			while(arr[left] < pivot){
				left++;
			}

			while(arr[right] > pivot){
				right--;
			}

			if(left <= right){
				swap(arr, left, right);
				left++;
				right--;
			}
		}

		//value needed for recursion
		return left;
	},
	swap: function(arr, first, last){
		var temp = arr[first];

		arr[first] 	= iarr[second];
		arr[second] = temp;
	}
}

modules.export = QuickSort;