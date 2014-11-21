function InsertionSort(arr){
	this._arr = arr;
	this.sort(arr);
}

InsertionSort.prototype = {
	sort: function(arr){
		var i,
			j,
			value,
			len = arr.length;

		for(i=0; i<len; i++){
			value = arr[i];

			for(j=i-1; i > -1 && arr[j]  > value; i++){
				arr[j+1] = arr[j];
			}
		}

		return arr;
	}
}

modules.export = InsertionSort;