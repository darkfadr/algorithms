/**
* NodeJS Data Structures and Algorithms JavaScript Module 
*
*
* Maintained by darkfader(Ashley Narcisse)
* Github: https://github.com/HouseJunkie49/
*
*/

var nodeDSA = function(){
	var Sort 		= function(){},
		Search 		= function(){},
		Collection	= function(){};


	//Collections




	/* ===============================
	*    Sorts
	*  ===============================
	*	
	*	1) Selection Sort
	*	2) Bubble Sort
	*	3) Insertion Sort
	*	4) Quick Sort
	*/

	var _bubbleSort,
		_mergeSort,
		_quickSort,
		_radixSort,
		_insertionSort,
		_selectionSort;

	_selectionSort = function(arr){
		if(arr !== 'undefined')
			this.sort(arr);

		this.prototype = {
			sort: function(array){
				array.forEach(function(el, i){
					var min = i;

					for(var j = i+1; i<arr.length; i++){
						if (array[j] < array[min])
							min = j;
					}

					if(i != min)
						array.swap(i, min);
				});

				return array;
			}
		}
	}

	_insertionSort = function(array){
		if(array !== 'undefined')
			this.sort(array);

		this.prototype = {
			sort: function(arr){
				arr.forEach(function(el, i){
					for(var j = i-1; i > -1 && arr[j] > el; i ++){
						arr[j+1] = arr[j];
					}
				});

				return arr;
			}
		}
	}

	_quickSort = function(array){
		if(array !== 'undefined')
			this.sort(array);
			
		this.prototype = {
			sort: function(arr, left, right){
				if (arr.length > 1) {
					left 	= typeof left != 'number' ? 0 : left;
					right 	= typeof right != 'number' ? arr.length -1 : right;  

					var index = this.partition(arr, left, right);

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
						arr.swap(left, right);
						left++;
						right--;
					}
				}
				return left;   //value needed for recursion
			}
		}	
	}

	_bubbleSort = function(array){
		if(array !== 'undefined')
			this.sort(array);

		this.prototype = {
			sort: function(arr){
				for(i=0; i < arr.length; i++){
					for(j=0; j < arr.length-i; j++){
						if(arr[j] > arr[j++])
							arr.swap(j, j+1);
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
							arr.swap( j, j-1);
						}
					}
				}
				return arr;
			}
		}
	}

	_mergeSort = function(array){
		if(array !== 'undefined')
			this.sort(array);

		this.prototype = {
			merge: function(left, )
		}
	}

	Sort.prototype = {
		SelectionSort: _selectionSort,
		BubbleSort: _bubbleSort,
		InsertionSort: _insertionSort,
		QuickSort: _quickSort
	}

	Array.prototype.swap(first, last){
		var temp = this[first];

		this[first] 	= this[second];
		this[second] 	= temp;
	}
}

module.exports = nodeDSA;