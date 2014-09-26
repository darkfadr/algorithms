(function(){
	function LinkedList(){
		this._head = null;
		this._tail = null;
		
		this.prototype = {
			add: function(data){
				var node = new Node(data);

				if (this._head === null) {
					this._head = node;
				} else if(this._tail === null){
					this._tail = node;
				} else{
					this._tail._next = node;
				}
			},
			remove: function(index){
				var i 	 = 0,
					prev = null,
					node = this._head;
				while(node._next !== null && i++ < index){
					prev = node;
					node = node._next;
				}

				if(prev == null){
					this._head = node._next;
				} else{
					prev._next = node._next;
				}
			}
		}
	}

	function Node(data){
		this._data = data;
		this._next = null;
	}
});