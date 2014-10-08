(function(){
	function LinkedList(){
		this._head = null;
		this._tail = null;
		this._length = 0;
	}

	function Node(data){
		this._data = data;
		this._next = null;
	}

	LinkedList.prototype = {
		constructor: LinkedList,
		add: function(data){
			var node = new Node(data);

			if (this._head === null) {
				this._head = node;
			} else if(this._tail === null){
				this._tail = node;
			} else{
				this._tail._next = node;
			}
			this._length++;
		},
		add: function(index, data){
			var i 	 = 0,
				prev = null,
				next = this._head,
				node = new Node(data);

			while(next._next !== null && i++ < index){
				prev = next;
				next = next._next;

				prev._next 	= node;
				node._next 	= next;
			}
			this._length++;
		}
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
			this._length--;
		}
	}
});