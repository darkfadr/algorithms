(function(){
	function BinarySearchTree(){
		this._root = null;
	}

	BinarySearchTree.prototype = {
		constructor: BinarySearchTree,
		add: function(val){
			var current,
				node = {
					data: val;
					left: null;
					right: null
				};

			if(this._root === null){
				this.root = node;
			} else{
				current = this._root;

				while(true){

					if(val < current.data){
						if(current.left === null){
							current.left = node;
							break;
						} else{
							current = current.left
						}
					} else if(val > current.data){
						if(current.right === null){
							current.right = node;
							break;
						} else{
							current = current.right;
						}
					} else{
						break;
					}
				}
			}
		},
		contains: function(data){
			var found 	= false,
				current = this._root;

			while(!found && current){
				if(data < current.data){
					current = current.left;
				} else if(data > current.data){
					current  = current.right;
				} else{
					found = true;
				}
			}

			return found;
		},
		size: function(){},
		toArray: function(){},
		toString: function(){},
		traverse: function(){}
	}
});